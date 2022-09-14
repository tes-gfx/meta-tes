DESCRIPTION = "D/AVE HD OpenVG 1.1 and EGL library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${TES_DHD_SRC_PATH}:"

DEPENDS += "libdi"

PV = "1.0"
SRC_URI =  " \
	file://driver \
"

PV:tesintern = "1.0+svnr${SRCPV}"

SRCREV_FORMAT = "openvg_egl_driver"
SRCREV_openvg = "${AUTOREV}"
SRCREV_egl = "${AUTOREV}"
SRCREV_driver = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_DHD_SVN_PATH};module=egl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=egl; \
	${TES_DHD_SVN_PATH};module=openvg1.1;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=openvg; \
	${TES_DHD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver; \
"

S = "${WORKDIR}"

do_compile() {

    pushd egl/build/linux
    oe_runmake || die "make failed"

    popd
    pushd openvg1.1/build/linux
    oe_runmake || die "make failed"
}

inherit pkgconfig

do_install () {
  install -d ${D}${includedir}/EGL
  install -d ${D}${includedir}/VG
  install -m 0644 ${S}/egl/include/EGL/*.h ${D}${includedir}/EGL
  install -m 0644 ${S}/openvg1.1/include/VG/*.h ${D}${includedir}/VG
  install -d ${D}${libdir} 
  install ${S}/egl/build/linux/libEGL.a ${D}${libdir}
  install ${S}/openvg1.1/build/linux/libOpenVG.a ${D}${libdir}
}
