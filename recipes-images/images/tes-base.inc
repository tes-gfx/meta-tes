require recipes-extended/images/core-image-full-cmdline.bb

IMAGE_INSTALL += "haveged"
IMAGE_INSTALL_append_arria10 = " tes-itb-arria10"
IMAGE_INSTALL_append_stratix10 = " tes-u-boot-script-stratix10"

#
# Adding the kernel image and firmware files (Device tree)
#
IMAGE_INSTALL += "kernel kernel-devicetree"

#
# Add KMS library to image
#
CORE_IMAGE_EXTRA_INSTALL += " \
	libdrm \
	libdrm-kms \
"

#
# Use linux-altera-ltsi 4.14 kernel
#
PREFERRED_PROVIDER_virtual/kernel = "linux-altera-ltsi"
PREFERRED_VERSION_linux-altera-ltsi = "4.14%"

#
# only generate tar.gz rootfs images
#
IMAGE_FSTYPES = "tar.gz ext3"

