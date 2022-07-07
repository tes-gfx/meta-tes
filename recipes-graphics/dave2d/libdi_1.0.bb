DESCRIPTION = "Display Interface display abstraction library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdrm"

FILESEXTRAPATHS:prepend := "${TES_D2D_SRC_PATH}/software:"

PV = "1.0"
SRC_URI =  " \
	file://display_interface \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV:tesintern = "${AUTOREV}"
SRC_URI:tesintern =  " \
	${TES_TOOLS_SVN_PATH};module=display_interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/display_interface/build/linux"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/../../inc/di*.h ${D}${includedir}
  install -d ${D}${libdir} 
  install ${S}/libdi.a ${D}${libdir}
}

ALLOW_EMPTY_${PN} = "1"
