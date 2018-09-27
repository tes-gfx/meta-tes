DESCRIPTION = "GPIO polling tool for use in shell scripts"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://main.c;endline=20;md5=9f04cb94394d96c310de03ac83284493"


FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = " \
	file://gpio_poll \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/tools;module=gpio_poll;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"


S = "${WORKDIR}/gpio_poll"
B = "${WORKDIR}/gpio_poll"


do_install () {
  install -d ${D}${bindir}
  install -m 0755 gpio_poll ${D}${bindir}
}
