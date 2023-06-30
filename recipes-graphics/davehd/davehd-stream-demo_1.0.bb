DESCRIPTION = "D/AVE HD Stream Demo"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdavehd libdrm libdi libcdc libdrm-gman"

PV = "1.0"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_DHD_SVN_PATH}/demos;module=dhd_stream;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/dhd_stream"
B = "${WORKDIR}/dhd_stream/build"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 dhd_stream ${D}${datadir}/${PN}/

  install -d ${D}${srcdir}/${PN}/src
  install -m 0644 ${S}/src/*.c ${D}${srcdir}/${PN}/src/

  install -d ${D}${srcdir}/${PN}/build
  install -m 0644 ${S}/build/Makefile ${D}${srcdir}/${PN}/build/
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
