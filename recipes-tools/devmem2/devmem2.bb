SUMMARY = "Memory access tool"
DESCRIPTION = "Development tool to access memory addresses via /dev/mem."
AUTHOR = "Jan-Derk Bakker (jdb@lartmaker.nl)"
HOMEPAGE = "http://www.lartmaker.nl/lartware/port/devmem2.c"

SECTION = "developement"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"
PR = "r0"

SRC_URI = " \
	file://devmem2;subdir=tools \
"

SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_SVN_PATH}/tools;module=devmem2;subdir=tools;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/tools/devmem2"
B = "${WORKDIR}/tools/devmem2"

do_install () {
	install -d ${D}${bindir}
	install -m 0755 devmem2 ${D}${bindir}
}
