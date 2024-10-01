DESCRIPTION = "Generic IP (genip) userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

PV:tesintern = "1.0+svnr${SRCPV}"

DEPENDS = "generic-ip-mod-uapi"

inherit pkgconfig

SRCREV_FORMAT = "libgenip"
SRCREV_libgenip = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_EVALKIT_SVN_PATH}/drivers;module=libgenip;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=libgenip \
"

S = "${WORKDIR}/libgenip"

do_install () {
  install -d ${D}${includedir}
  install -m 0644 ${S}/genip.h ${D}${includedir}
  install -d ${D}${libdir} 
  install ${B}/libgenip.a ${D}${libdir}
}

# We only have a static lib here, so the main package will be empty.
# But there are still dependencies on it. Therefore, we keep it as an empty package.
ALLOW_EMPTY:${PN} = "1"
