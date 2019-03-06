require u-boot.inc

DEPENDS += "mkpimage-native"


SRCREV = "4634a6977dad609c208760543ee417f1f2faa674"

UBOOT_BRANCH = "socfpga_${PV}_arria10_dnx"
UBOOT_REPO_tesintern = "git:///home/fpga/devel/u-boot-socfpga"
UBOOT_PROT_tesintern = "file"
UBOOT_REPO = "git://github.com/c-thaler/u-boot-socfpga.git"
UBOOT_PROT = "https"

SRC_URI = "\
        ${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
        "

UBOOT_CONFIG = "arria10-socdk-dnx dreamchip-arria10som-dnx"
UBOOT_CONFIG[arria10-socdk-dnx] = "socfpga_arria10_dnx_defconfig"
UBOOT_CONFIG[dreamchip-arria10som-dnx] = "dreamchip_arria10som_sdmmc_defconfig"

UBOOT_MKPIMAGE = "u-boot-dtb-${config%_defconfig}.bin.mkpimage"

S = "${WORKDIR}/git"

do_deploy_append() {
	for config in ${UBOOT_MACHINE}; do
        	mkpimage -v 1 ${B}/${config}/${UBOOT_BINARY} -o ${B}/${config}/${UBOOT_MKPIMAGE}
	        install ${B}/${config}/${UBOOT_MKPIMAGE} ${DEPLOYDIR}
	done
}

