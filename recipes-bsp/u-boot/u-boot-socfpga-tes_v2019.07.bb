require u-boot.inc
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

UBOOT_BRANCH = "v2019.07"

SRCREV = "7e090b466c5ba874d31c1bf22c3a130d516cdc32"

DEPENDS += "dtc-native bc-native bison-native u-boot-mkimage-native"
