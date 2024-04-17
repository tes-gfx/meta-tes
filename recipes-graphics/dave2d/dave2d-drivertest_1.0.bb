DESCRIPTION = "D/AVE 2D Drivertest"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdave2d libdrm"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_D2D_SRC_PATH}/software:"

PV = "1.0"
SRC_URI = " \
	file://drivertest \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_D2D_SVN_PATH}/software;module=drivertest;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/drivertest"
B = "${WORKDIR}/drivertest/build/linux_genip"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 drivertest ${D}${datadir}/${PN}/
}
