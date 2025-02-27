DESCRIPTION = "D/AVE HD OpenVG demos"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS += "libdi libdavehd libcdc libdrm-gman davehd-openvg ncurses"

PV = "1.0"

PV:tesintern = "1.0+svnr${SRCPV}"

SRCREV_FORMAT = "demos_dhdtest"
SRCREV_demos = "${AUTOREV}"
SRCREV_dhdtest = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_DHD_SVN_PATH};module=ovg_test/demos;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=demos; \
	${TES_DHD_SVN_PATH};module=driver/test;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};externals=nowarn;name=dhdtest; \
"

S = "${WORKDIR}/ovg_test/demos"
B = "${WORKDIR}/ovg_test/demos/build/linux"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 ovg_demo ${D}${datadir}/${PN}/
  install -m 0755 ../../res/ovg_resource.zip ${D}${datadir}/${PN}/

  install -d ${D}${srcdir}/${PN}/bin
  install -d ${D}${srcdir}/${PN}/build/linux
  install -m 0644 ${B}/Makefile ${D}${srcdir}/${PN}/build/linux/
  install -d ${D}${srcdir}/${PN}/platform/linux
  install -m 0644 ${S}/platform/linux/*.c ${D}${srcdir}/${PN}/platform/linux/
  install -d ${D}${srcdir}/${PN}/res
  install -m 0644 ${S}/res/*.zip ${D}${srcdir}/${PN}/res/
  install -d ${D}${srcdir}/${PN}/src/testcases
  install -m 0644 ${S}/src/testcases/*.c ${D}${srcdir}/${PN}/src/testcases/
  install -m 0644 ${S}/src/testcases/*.h ${D}${srcdir}/${PN}/src/testcases/
  install -d ${D}${srcdir}/${PN}/src/egl_framework
  install -m 0644 ${S}/src/egl_framework/*.c ${D}${srcdir}/${PN}/src/egl_framework/
  install -d ${D}${srcdir}/${PN}/src/ree_tests/performance
  install -m 0644 ${S}/src/ree_tests/performance/*.c ${D}${srcdir}/${PN}/src/ree_tests/performance/
  install -d ${D}${srcdir}/${PN}/src/ree_tests/test1
  install -m 0644 ${S}/src/ree_tests/test1/*.c ${D}${srcdir}/${PN}/src/ree_tests/test1/
  install -m 0644 ${S}/src/ree_tests/test1/*.h ${D}${srcdir}/${PN}/src/ree_tests/test1/
  install -d ${D}${srcdir}/${PN}/src/ree_tests/test2
  install -m 0644 ${S}/src/ree_tests/test2/*.c ${D}${srcdir}/${PN}/src/ree_tests/test2/
  install -d ${D}${srcdir}/${PN}/src/ree_tests/test3
  install -m 0644 ${S}/src/ree_tests/test3/*.c ${D}${srcdir}/${PN}/src/ree_tests/test3/
  install -d ${D}${srcdir}/${PN}/external_src
  install -m 0644 ${WORKDIR}/driver/test/platform/stdlib/*.c ${D}${srcdir}/${PN}/external_src/
  install -m 0644 ${WORKDIR}/driver/test/src/framework/*.c ${D}${srcdir}/${PN}/external_src/
  install -m 0644 ${WORKDIR}/driver/test/src/framework/*.h ${D}${srcdir}/${PN}/external_src/
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
