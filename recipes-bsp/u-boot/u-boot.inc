UBOOT_BRANCH = "master"
UBOOT_REPO_tesintern = "git:///home/christian/yocto/u-boot"
UBOOT_PROT_tesintern = "file"
UBOOT_REPO = "git://github.com/c-thaler/u-boot.git"
UBOOT_PROT = "https"

PV_append = "+git${SRCPV}"

SRC_URI = "\
        ${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
        "

S = "${WORKDIR}/git"

RPROVIDES_${PN} += "u-boot"
