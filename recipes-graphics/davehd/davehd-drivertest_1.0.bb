DESCRIPTION = "D/AVE HD Drivertest"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdavehd libdrm libdi libcdc libdrm-gman"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_DHD_SRC_PATH}:"

PV = "1.0"
SRC_URI = " \
	file://drivertest \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_DHD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver/test/build/linux_genip"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 drivertest ${D}${datadir}/${PN}/
  install -m 0755 ../../data/resource.zip ${D}${datadir}/${PN}/
}
