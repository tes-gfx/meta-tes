DESCRIPTION = "D/AVE 2D Level 1 userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdi"

FILESEXTRAPATHS:prepend := "${TES_D2D_SRC_PATH}/software:"

PV = "1.0"
SRC_URI =  " \
	file://driver_l1 \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern =  " \
	${TES_D2D_SVN_PATH}/software;module=driver_l1;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver_l1/build/linux_genip"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/../../code/dave_base.h ${D}${includedir}
  install -d ${D}${libdir} 
  install ${S}/libdave2d_l1.a ${D}${libdir}
}

# We only have a static lib here, so the main package will be empty.
# But there are still dependencies on it. Therefore, we keep it as an empty package.
ALLOW_EMPTY:${PN} = "1"
