require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

LICENSE = "GPLv2+"

PV:append = "+git${SRCPV}"

UBOOT_BRANCH = "master"
UBOOT_REPO = "git://github.com/tes-gfx/u-boot-socfpga.git"
UBOOT_PROT = "https"

SRC_URI = "\
	${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
	"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

RPROVIDES_${PN} += "u-boot" 

UBOOT_CONFIG[cyclone5-de0] = "socfpga_de0_nano_soc_tes_defconfig"
UBOOT_CONFIG[cyclone5-de10] = "socfpga_de10_nano_evalkit_defconfig"
