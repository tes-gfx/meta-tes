DESCRIPTION = "D/AVE 2D Level 1 userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdi"

FILESEXTRAPATHS_prepend := "${TES_SRC}/software:"

PV = "1.0"
SRC_URI =  " \
	file://driver_l1 \
"

PV_tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI_tesintern =  " \
	${TES_D2D_SVN_PATH}/software;module=driver_l1;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver_l1/build/linux"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/../../code/dave_base.h ${D}${includedir}
  install -d ${D}${libdir} 
  install ${S}/libdave2d_l1.a ${D}${libdir}
}

ALLOW_EMPTY_${PN} = "1"
