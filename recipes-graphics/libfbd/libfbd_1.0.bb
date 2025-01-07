DESCRIPTION = "FBD userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdrm-gman generic-ip-mod-uapi"

FILESEXTRAPATHS:prepend := "${TES_FBD_SRC_PATH}:"

PV = "1.0"
SRC_URI =  " \
	file://driver \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern =  " \
	${TES_FBD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver"
B = "${WORKDIR}/driver/build/linux"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir}/linux 
  install -m 0644 ${S}/include/fbd.h ${D}${includedir}
  install -m 0644 ${S}/include/fbd_types.h ${D}${includedir}
  install -m 0644 ${S}/include/linux/fbd_platform_settings.h ${D}${includedir}/linux
  install -d ${D}${libdir} 
  install ${B}/libfbd.a ${D}${libdir}
}

# We only have a static lib here, so the main package will be empty.
# But there are still dependencies on it. Therefore, we keep it as an empty package.
ALLOW_EMPTY:${PN} = "1"
