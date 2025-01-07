DESCRIPTION = "Virtual Display (VD) userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

PV:tesintern = "1.0+svnr${SRCPV}"

DEPENDS = "libgenip"

inherit pkgconfig

SRCREV_FORMAT = "driver"
SRCREV_driver = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_VD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver \
"

S = "${WORKDIR}/driver"
B = "${WORKDIR}/driver/build"

do_install () {
  install -d ${D}${includedir}/linux 
  install -m 0644 ${S}/inc/vd.h ${D}${includedir}
  install -m 0644 ${S}/inc/linux/vd_platform_settings.h ${D}${includedir}/linux
  install -d ${D}${libdir} 
  install ${B}/libvd.a ${D}${libdir}
}

# We only have a static lib here, so the main package will be empty.
# But there are still dependencies on it. Therefore, we keep it as an empty package.
ALLOW_EMPTY:${PN} = "1"

RDEPENDS:${PN}-staticdev = "libgenip-staticdev"
