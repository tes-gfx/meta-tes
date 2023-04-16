DESCRIPTION = "CDC Tutorial"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdavehd libdrm libdi libcdc libdrm-gman"

PV = "1.0"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_CDC_SVN_PATH}/software/demos;module=tutorial_linux;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/tutorial_linux"
B = "${WORKDIR}/tutorial_linux/build"
srcdir = "${prefix}/src"

inherit pkgconfig

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 cdc_tutorial ${D}${datadir}/${PN}/

  install -d ${D}${srcdir}/${PN}/src
  install -m 0644 ${S}/src/*.c ${D}${srcdir}/${PN}/src/

  install -d ${D}${srcdir}/${PN}/build
  install -m 0644 ${S}/build/Makefile ${D}${srcdir}/${PN}/build/
}

PACKAGES += "${PN}-devsrc"
FILES:${PN}-devsrc += "\
        ${srcdir}/${PN}/* \
"
RDEPENDS_${PN}-devsrc = "${PN}"
