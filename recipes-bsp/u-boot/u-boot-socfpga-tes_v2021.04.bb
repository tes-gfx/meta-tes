require u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRCREV = "74631d6cb26f540d99591d5ee923a909f7205c61"

UBOOT_BRANCH = "socfpga_v2021.04_tes"

DEPENDS += "bc-native bison-native"

FILESEXTRAPATHS:prepend := "${THISDIR}/files/cyclone5/de10_nano:"

SRC_URI:append = " \
    file://env.txt \
"

do_configure:append() {
    sed -i "s/\$EVALKIT/${EVALKIT}/" ${WORKDIR}/env.txt
    cp ${WORKDIR}/env.txt ${S}
}
