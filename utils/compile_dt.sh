#!/bin/bash

KERNEL_SRC=/home/fpga/yocto/yocto/build/tmp/work-shared/arria10/kernel-source
INPUT=$1
INTERMED=$(mktemp)
OUTPUT=${INPUT%.dts}.dtb

cpp -nostdinc -I ${KERNEL_SRC}/arch/arm/boot/dts -I ${KERNEL_SRC}/arch/arm/boot/dts/include -undef -x assembler-with-cpp $INPUT > $INTERMED
dtc -I dts -O dtb -o $OUTPUT $INTERMED

rm $INTERMED
