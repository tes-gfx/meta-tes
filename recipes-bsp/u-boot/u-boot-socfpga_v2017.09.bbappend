require u-boot.inc

SRCREV_stratix10 = "e712eb8ba531b2f45b5c092cecffbd858e238df2"

UBOOT_BRANCH = "socfpga_v2017.09_dnx"
UBOOT_REPO_tesintern = "git:///home/christian/yocto/u-boot-socfpga"
UBOOT_PROT_tesintern = "file"
UBOOT_REPO = "git://github.com/c-thaler/u-boot-socfpga.git"
UBOOT_PROT = "https"

UBOOT_CONFIG = "stratix10-socdk-dnx"
UBOOT_CONFIG[stratix10-socdk-dnx] = "socfpga_stratix10_dnx_defconfig"

SRC_URI_stratix10_tesintern = " \
        git:///home/christian/yocto/u-boot-socfpga;protocol=file;branch=socfpga_v2017.09_dnx \
	"

SRC_URI_stratix10 = " \
        ${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
	"


S = "${WORKDIR}/git"
