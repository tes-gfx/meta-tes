DESCRIPTION = "D/AVE HD + CDC Tutorial"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdavehd libdrm libcdc libdrm-gman"

PV = "1.0"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_EVALKIT_SVN_PATH}/demos/dhd_cdc;module=tutorial;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/tutorial"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 tutorial ${D}${datadir}/${PN}/
  install -d ${D}${srcdir}/${PN}
  install -m 0644 ${S}/*.c ${D}${srcdir}/${PN}/
  install -m 0644 ${S}/Makefile ${D}${srcdir}/${PN}/
}

#
# Add a src package for the project, enabling the user to modify and build
# the project as a start for evaluation.
# Sources, resources and project file have to be added to the package.
#
PACKAGES += "${PN}-devsrc"
FILES:${PN}-devsrc += "\
        ${srcdir}/${PN}/* \
"
RDEPENDS_${PN}-devsrc = "${PN}"
