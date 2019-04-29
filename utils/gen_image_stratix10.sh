#!/bin/bash

echo "TES DaveNX image generator"
echo "Copyright (C) 2017 - 2018 TES Electronic Solutions GmbH"
echo "This program is free software; you can redistribute it and/or modify"
echo "it under the terms of the GNU General Public License as published by"
echo "the Free Software Foundation; either version 2 of the License, or"
echo "(at your option) any later version."
echo ""

if [ "$#" -ne 1 ]; then
	echo "Wrong number of parameters ($#)"

	echo "Usage: $0 DEPLOY_DIR_IMAGE"

	echo ""
	echo "DEPLOY_DIR_IMAGE - Path to the Yocto image deploy directory"
	echo ""
	echo "The size of the output image depends on the size of the EXT3"
	echo "image built by bitbake/Yocto. The size of the image's FAT"
	echo "partition is fixed to 128M."
	echo ""
	echo "Example:"
	echo "	./gen_image_stratix10.sh yocto/build/tmp/deploy/images/stratix10"
	exit -1
fi

BOARD=socdk
DEPLOYDIR=$1

IMAGE=tes-davenx-cdc-eval-stratix10-socdk.img
FAT_SIZE_BLOCKS=$(expr 128 \* 1024 \* 1024 \/ 512)
SIZE_BLOCKS=$(expr 4096 \+ ${FAT_SIZE_BLOCKS})

UBOOTIMAGE=${DEPLOYDIR}/u-boot-dtb.img
DEVICETREE=${DEPLOYDIR}/Image-socfpga_stratix10_${BOARD}_tes.dtb
KERNELIMAGE=${DEPLOYDIR}/Image
SYSROOT=${DEPLOYDIR}/tes-davenx-evalkit-image-stratix10.ext3
FPGABITFILE=${DEPLOYDIR}/socfpga_stratix10_${BOARD}_tes.rbf
UBOOTSCRIPT=${DEPLOYDIR}/u-boot.scr

EXT_FREE_BLOCKS=$(expr 128 \* 1024 \* 1024 \/ 512)
EXT_SIZE_BLOCKS=$(expr $(stat -Lc%s ${SYSROOT}) \/ 512 \+ ${EXT_FREE_BLOCKS})
SIZE=$(expr ${SIZE_BLOCKS} \+ ${EXT_SIZE_BLOCKS})

# Generate image file
echo "Generating empty image file..."
dd if=/dev/zero of=${IMAGE} bs=${SIZE} count=512


# Generate partition table
echo "Generating partition table..."
( \
	echo "o"; \
	echo "n"; echo "p"; echo "3"; \
		echo "2048"; echo "4095"; \
	echo "n"; echo "p"; echo "1"; \
		echo ""; echo "+128M"; \
	echo "n"; echo "p"; echo "2"; \
		echo ""; echo ""; \
	echo "t"; echo "1"; echo "b"; \
	echo "t"; echo "3"; echo "a2"; \
	echo "w"; echo "q" \
) | fdisk ${IMAGE}


# Get partition start and size parameters
TEMPVAL=($(fdisk -l ${IMAGE} | grep "\.img1"))
PART_FAT_START=${TEMPVAL[1]}
PART_FAT_SIZE=${TEMPVAL[3]}
TEMPVAL=($(fdisk -l ${IMAGE} | grep "\.img2"))
PART_EXT_START=${TEMPVAL[1]}
PART_EXT_SIZE=${TEMPVAL[3]}


# Creating file for FAT partition
echo "Creating FAT partition..."
FATIMG=tmp_${RANDOM}
dd if=/dev/zero of=${FATIMG} bs=64M count=1
mkfs.vfat ${FATIMG}


# Populating FAT partition
echo "Populating FAT partition..."
mcopy -i ${FATIMG} ${KERNELIMAGE} ::Image
mcopy -i ${FATIMG} ${DEVICETREE} ::socfpga_stratix10_${BOARD}.dtb
mcopy -i ${FATIMG} ${FPGABITFILE} ::socfpga_stratix10_${BOARD}_tes.rbf
mcopy -i ${FATIMG} ${UBOOTIMAGE} ::u-boot-dtb.img
mcopy -i ${FATIMG} ${UBOOTSCRIPT} ::u-boot.scr


# Wrtiting partitions to image file
echo "Writing partitions to image file..."
dd if=${FATIMG} of=${IMAGE} seek=${PART_FAT_START} bs=512 conv=notrunc
dd if=${SYSROOT} of=${IMAGE} seek=${PART_EXT_START} bs=512 conv=notrunc

rm ${FATIMG}

