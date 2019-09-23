require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

LICENSE = "GPLv2+"

PV_append = "+git${SRCPV}"

UBOOT_BRANCH = "master"
UBOOT_REPO_tesintern = "git:///home/christian/yocto/u-boot-socfpga"
UBOOT_PROT_tesintern = "file"
UBOOT_REPO = "git://github.com/c-thaler/u-boot-socfpga.git"
UBOOT_PROT = "https"

SRC_URI = "\
	${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
	"

S = "${WORKDIR}/git"

RPROVIDES_${PN} += "u-boot" 
