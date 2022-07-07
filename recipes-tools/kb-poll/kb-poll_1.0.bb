DESCRIPTION = "Keyboard polling tool for use in shell scripts"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://main.c;endline=20;md5=9f04cb94394d96c310de03ac83284493"


FILESEXTRAPATHS:prepend := "${TES_SRC}:"
SRC_URI = " \
	file://tools/kb_poll \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/tools;module=kb_poll;subdir=tools;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"


S = "${WORKDIR}/tools/kb_poll"
B = "${WORKDIR}/tools/kb_poll"


do_install () {
  install -d ${D}${bindir}
  install -m 0755 kb_poll ${D}${bindir}
}
