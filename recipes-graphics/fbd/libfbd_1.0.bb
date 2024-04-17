DESCRIPTION = "FBD userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

PV:tesintern = "1.0+svnr${SRCPV}"

DEPENDS = "libdrm-gman generic-ip-mod-uapi"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_FBD_SRC_PATH}:"
SRC_URI =  " \
	file://driver \
	file://th_fbd \
	file://tools \
"

SRCREV_FORMAT = "driver_th_fbd_tools"
SRCREV_driver = "${AUTOREV}"
SRCREV_th_fbd = "${AUTOREV}"
SRCREV_tools  = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_FBD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver \
	${TES_FBD_SVN_PATH};module=th_fbd;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=th_fbd \
	${TES_TOOLS_SVN_PATH};module=greg;path_spec=./tools/greg;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=tools \
"

S = "${WORKDIR}/driver"
B = "${WORKDIR}/driver/build/linux"

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
