DESCRIPTION = "D/AVE HD Tutorial"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdavehd libdrm libdi libcdc libdrm-gman"

PV = "1.0"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_DHD_SVN_PATH}/demos;module=tutorial;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/tutorial"
B = "${WORKDIR}/tutorial/build"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 dhd_tutorial_01 ${D}${datadir}/${PN}/
  install -m 0755 dhd_tutorial_02 ${D}${datadir}/${PN}/
  install -m 0755 dhd_tutorial_03 ${D}${datadir}/${PN}/
  install -m 0755 dhd_tutorial_04 ${D}${datadir}/${PN}/
  install -m 0755 dhd_tutorial_05 ${D}${datadir}/${PN}/

  install -d ${D}${datadir}/${PN}/res
  install -m 0644 ${S}/res/welding.dds ${D}${datadir}/${PN}/res/
  install -m 0644 ${S}/res/welding.png ${D}${datadir}/${PN}/res/
  install -m 0644 ${S}/res/welding.tga ${D}${datadir}/${PN}/res/
  install -m 0644 ${S}/res/gamut_512x512.bmp ${D}${datadir}/${PN}/res/

  install -d ${D}${srcdir}/${PN}/src
  install -m 0644 ${S}/src/*.c ${D}${srcdir}/${PN}/src/

  install -d ${D}${srcdir}/${PN}/build
  install -m 0644 ${S}/build/Makefile ${D}${srcdir}/${PN}/build/

  install -d ${D}${srcdir}/${PN}/res
  install -m 0644 ${S}/res/welding.dds ${D}${srcdir}/${PN}/res/
  install -m 0644 ${S}/res/welding.png ${D}${srcdir}/${PN}/res/
  install -m 0644 ${S}/res/welding.tga ${D}${srcdir}/${PN}/res/
  install -m 0644 ${S}/res/gamut_512x512.bmp ${D}${srcdir}/${PN}/res/
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
