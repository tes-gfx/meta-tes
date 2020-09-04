DESCRIPTION = "TES Flight Demo"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
RDEPENDS_${PN} = "tks"


FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = " \
	file://demos/flight_demo \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/demos;module=flight_demo;subdir=demos;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"


S = "${WORKDIR}/demos/flight_demo"
srcdir = "${prefix}/src"
B = "${WORKDIR}/demos/flight_demo"


do_install () {
  install -d ${D}${datadir}/${PN}
  cp -r * ${D}${datadir}/${PN}/
}

