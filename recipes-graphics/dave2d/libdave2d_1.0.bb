DESCRIPTION = "D/AVE 2D userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdave2d-l1"

PV = "1.0+svnr${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI =  " \
	${TES_D2D_SVN_PATH}/software;module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver/build/linux"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/../../inc/*.h ${D}${includedir}
  install -d ${D}${libdir} 
  oe_soinstall ${S}/libdave2d.so.*.* ${D}${libdir}
}

DEPENDS_${PN}-dev += "libdave2d-l1-dev libdi-dev"
