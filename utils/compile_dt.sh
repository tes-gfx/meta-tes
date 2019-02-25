#!/bin/bash

KERNEL_SRC=/home/fpga/yocto/yocto/build/tmp/work-shared/arria10/kernel-source
DTC=/opt/poky/2.4.3/sysroots/x86_64-pokysdk-linux/usr/bin/dtc
INPUT=$1
INTERMED=$(mktemp)
OUTPUT=${INPUT%.dts}.dtb

cpp -nostdinc -I ${KERNEL_SRC}/arch/arm/boot/dts -I ${KERNEL_SRC}/arch/arm/boot/dts/include -undef -x assembler-with-cpp $INPUT > $INTERMED
$DTC -I dts -O dtb -@ -o $OUTPUT $INTERMED

rm $INTERMED
