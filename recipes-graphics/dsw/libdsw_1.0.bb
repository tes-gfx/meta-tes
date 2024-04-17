DESCRIPTION = "DSW userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

PV:tesintern = "1.0+svnr${SRCPV}"

DEPENDS = "libdrm-gman generic-ip-mod-uapi"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_DSW_SRC_PATH}:"
SRC_URI =  " \
	file://driver \
"

SRCREV:tesintern = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_DSW_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
"

S = "${WORKDIR}/driver"
B = "${WORKDIR}/driver/build/linux"

do_install () {
  install -d ${D}${includedir}/linux 
  install -m 0644 ${S}/include/dsw.h ${D}${includedir}
  install -m 0644 ${S}/include/dsw_types.h ${D}${includedir}
  install -m 0644 ${S}/include/linux/dsw_platform_settings.h ${D}${includedir}/linux
  install -d ${D}${libdir} 
  install ${B}/libdsw.a ${D}${libdir}
}

# We only have a static lib here, so the main package will be empty.
# But there are still dependencies on it. Therefore, we keep it as an empty package.
ALLOW_EMPTY:${PN} = "1"
