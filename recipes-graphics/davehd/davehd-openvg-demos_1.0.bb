DESCRIPTION = "D/AVE HD OpenVG demos"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS += "libdi libdavehd libcdc libdrm-gman davehd-openvg"

PV = "1.0"

PV:tesintern = "1.0+svnr${SRCPV}"

SRCREV_FORMAT = "demos_dhdtest"
SRCREV_demos = "${AUTOREV}"
SRCREV_dhdtest = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_DHD_SVN_PATH}/ovg_test;module=demos;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};path_spec=ovg_test/demos/;name=demos; \
	${TES_DHD_SVN_PATH}/driver;module=test;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};externals=nowarn;path_spec=driver/test/;name=dhdtest; \
"

S = "${WORKDIR}/ovg_test/demos/build/linux"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 ovg_demo ${D}${datadir}/${PN}/
  install -m 0755 ../../res/ovg_resource.zip ${D}${datadir}/${PN}/
}
