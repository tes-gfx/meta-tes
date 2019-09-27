DESCRIPTION = "Library to create an object of the TES native window type needed to initialize EGL"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1155026ea94fa1196103afa557260a1e"

DEPENDS = "libdrm libegl-tes-dev"
PROVIDES_append = "${PN}-dev"

PV = "1.0+gitr${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI =  "git://github.com/c-thaler/libkms-egl.git;protocol=https"

S = "${WORKDIR}/git"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/kms_egl.h ${D}${includedir}
  install -d ${D}${libdir} 
  oe_soinstall ${S}/libkms_egl.so.*.* ${D}${libdir}
}

FILES_${PN} = " \
	${libdir}/libkms_egl.so* \
"

FILES_${PN}-dev += " \
	${includedir}/kms_egl.h \
"
