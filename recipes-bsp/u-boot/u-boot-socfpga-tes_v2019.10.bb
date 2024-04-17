require u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "f320d16238f11d23a859817672d047b0834a5fe0"

UBOOT_BRANCH = "socfpga_v2019.10_dnx"

DEPENDS += "bc-native bison-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/arria10/dreamchip_arria10som:"

SRC_URI:append = " \
    file://env.txt \
"

do_configure:append() {
    sed -i "s/\$EVALKIT/${EVALKIT}/" ${WORKDIR}/env.txt
    cp ${WORKDIR}/env.txt ${S}
}
