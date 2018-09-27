SRCREV = "7d7678a026df93d1a36fa16c6850bd0d9bff93f7"

UBOOT_BRANCH = "socfpga_${PV}_arria10_dnx"
UBOOT_REPO = "git:///home/christian/yocto/u-boot-socfpga"
UBOOT_PROT = "file"

SRC_URI = "\
        ${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
        "
