DESCRIPTION = "D/AVE HD Performance Benchmark"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS += "libdi libdavehd libcdc libdrm-gman ncurses"

PV = "1.0"

PV:tesintern = "1.0+svnr${SRCPV}"

SRCREV_FORMAT = "perf_dhdtest"
SRCREV_perf = "${AUTOREV}"
SRCREV_dhdtest = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_DHD_SVN_PATH}/demos;module=performance;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};path_spec=demos/performance;name=perf; \
	${TES_DHD_SVN_PATH}/driver;module=test;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};externals=nowarn;path_spec=driver/test/;name=dhdtest; \
"

S = "${WORKDIR}/demos/performance"
B = "${WORKDIR}/demos/performance/build/linux_genip"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 ${B}/performance ${D}${datadir}/${PN}/
  install -m 0755 ${S}/test/data/* ${D}${datadir}/${PN}/
}

PACKAGES += "${PN}-devsrc"
FILES:${PN}-devsrc += "\
        ${srcdir}/${PN}/* \
"
RDEPENDS_${PN}-devsrc = "${PN}"
