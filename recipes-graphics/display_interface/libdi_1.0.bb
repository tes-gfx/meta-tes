DESCRIPTION = "Display Interface display abstraction library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

PROVIDES = "libdi-dev"

DEPENDS = "libdrm libcdc"

FILESEXTRAPATHS:prepend := "${TES_LIBRARIES_SRC_PATH}:"

PV = "1.0"
SRC_URI =  " \
	file://display_interface \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV:tesintern = "${AUTOREV}"
SRC_URI:tesintern =  " \
        ${TES_LIBRARIES_SVN_PATH}/display_interface;module=${TES_SVN_BRANCH};protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/${TES_SVN_BRANCH}"
B = "${WORKDIR}/${TES_SVN_BRANCH}/build/linux_genip"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/inc/di*.h ${D}${includedir}
  install -m 0644 ${S}/src/linux_genip/di_platform.h ${D}${includedir}
  install -d ${D}${libdir} 
  install ${B}/libdi.a ${D}${libdir}
}

ALLOW_EMPTY:${PN} = "1"
