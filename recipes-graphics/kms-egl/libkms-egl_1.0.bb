DESCRIPTION = "Library to create an object of the TES native window type needed to initialize EGL"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://kms_egl.h;endline=20;md5=9f04cb94394d96c310de03ac83284493"
DEPENDS = "libdrm libegl-tes-dev"
PROVIDES_append = "${PN}-dev"

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI =  "file://kms_egl"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern =  " \
	${TES_SVN_PATH_CDC}/linux;module=kms_egl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
"


S = "${WORKDIR}/kms_egl"
B = "${WORKDIR}/kms_egl"
B_tesintern = "${WORKDIR}/kms_egl"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/kms_egl.h ${D}${includedir}
  install -d ${D}${libdir} 
  oe_soinstall ${S}/libkms_egl.so.*.* ${D}${libdir}
}


FILES_${PN} = " \
	${libdir}/libkms_egl.so.* \
"

FILES_${PN}-dev += " \
	${includedir}/kms_egl.h \
"
