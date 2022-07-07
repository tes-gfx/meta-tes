SUMMARY = "mkpimage tool"
DESCRIPTION = "Tool to generate an Intel SoC FPGA compatible boot header."
AUTHOR = "Pengutronix"
HOMEPAGE = "https://git.pengutronix.de/cgit/barebox/tree/scripts/socfpga_mkimage.c?id=55d29525146dcd280987dfd565bfd34f08858fb3"

SECTION = "bootloader"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r0"


FILESEXTRAPATHS:prepend := "${TES_SRC}:"
SRC_URI = " \
	file://tools/mkpimage \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/tools;module=mkpimage;subdir=tools;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"


S = "${WORKDIR}/tools/mkpimage"
B = "${WORKDIR}/tools/mkpimage"


do_install () {
	install -d ${D}${bindir}
	install -m 0755 mkpimage ${D}${bindir}
}

BBCLASSEXTEND = "native nativesdk"
