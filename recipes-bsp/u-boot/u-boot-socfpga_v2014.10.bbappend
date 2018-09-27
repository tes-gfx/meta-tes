require u-boot.inc

DEPENDS += "mkpimage-native"


SRCREV = "7d7678a026df93d1a36fa16c6850bd0d9bff93f7"

UBOOT_BRANCH = "socfpga_${PV}_arria10_dnx"
UBOOT_REPO = "git:///home/christian/yocto/u-boot-socfpga"
UBOOT_PROT = "file"

SRC_URI = "\
        ${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
        "

UBOOT_CONFIG = "arria10-socdk-dnx arria10-wiesmann-dnx"
UBOOT_CONFIG[arria10-socdk-dnx] = "socfpga_arria10_dnx_defconfig"
UBOOT_CONFIG[arria10-wiesmann-dnx] = "wiesmann_dnx_defconfig"

UBOOT_MKPIMAGE = "u-boot-dtb-${config%_defconfig}.bin.mkpimage"

do_deploy_append() {
	for config in ${UBOOT_MACHINE}; do
        	mkpimage -v 1 ${B}/${config}/${UBOOT_BINARY} -o ${B}/${config}/${UBOOT_MKPIMAGE}
	        install ${B}/${config}/${UBOOT_MKPIMAGE} ${DEPLOYDIR}
	done
}

