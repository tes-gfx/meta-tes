require u-boot.inc


SRCREV = "ec7f522b63ffd0cd826cc82f887a800349673e60"
SRCREV_stratix10 = "ec7f522b63ffd0cd826cc82f887a800349673e60"

UBOOT_BRANCH = "socfpga_${PV}_dnx"
UBOOT_REPO_tesintern = "git:///home/christian/yocto/u-boot-socfpga"
UBOOT_PROT_tesintern = "file"
UBOOT_REPO = "git://github.com/c-thaler/u-boot-socfpga.git"
UBOOT_PROT = "https"

SRC_URI_stratix10 = "\
        git:///home/christian/yocto/u-boot-socfpga;protocol=file;branch=socfpga_v2017.09_dnx \
        "

SRC_URI = "\
        git:///home/christian/yocto/u-boot-socfpga;protocol=file;branch=socfpga_v2017.09_dnx \
        "

S = "${WORKDIR}/git"
