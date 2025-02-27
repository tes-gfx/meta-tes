SUMMARY = "TES DaveNX based implementation of the OpenGL 2.0 ES API"
DESCRIPTION = "\
	The library is not yet feature complete and still in \
	development. \
"

PV:tesintern = "1.0+svnr${SRCPV}"

PROVIDES = "virtual/libgles2 virtual/egl libdrm-dnx-dev libegl-tes-dev libdisplay-dnx-dev"
LICENSE = "MIT & BSD-3-Clause"
LICENSE_libdrm-dnx = "MIT"
LICENSE_libdrm-dnx-dev = "MIT"
LICENSE_libdisplay-dnx-dev = "MIT"
LIC_FILES_CHKSUM = "\
	file://../gles/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../bagl/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../egl/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../nxasm/docs/LICENSE.txt;md5=dccd4a0fb4baa9ea80d0bcfd93c4ef7e \
	file://../driver/kernel/linux/drm-dnx/LICENSE;md5=b3cff5eb85b2d0681a59405204a0a831 \
	file://../driver/user/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../glslang/LICENSE.txt;md5=918e668376010a04448a312fb37ae69b \
	file://../display/docs/LICENSE.txt;md5=d3a882ddb01ed28435692f93f33dd252 \
"

DEPENDS = "libdrm libpng"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_SRC}:"
SRC_URI = "\
	file://egl.pc \
	file://glesv2.pc \
	file://display \
	file://gles \
	file://bagl \
	file://egl \
	file://driver \
	file://glslang \
	file://nxasm \
	file://build \
	file://interface \
	file://tools/kms_helper \
	file://tools/nxvclient \
"

SRCREV_FORMAT = "display_gles_bagl_egl_driver_glslang_nxasm_build_interface_kms_tools"
SRCREV_display   = "${AUTOREV}"
SRCREV_gles      = "${AUTOREV}"
SRCREV_bagl      = "${AUTOREV}"
SRCREV_egl       = "${AUTOREV}"
SRCREV_driver    = "${AUTOREV}"
SRCREV_glslang   = "${AUTOREV}"
SRCREV_nxasm     = "${AUTOREV}"
SRCREV_build     = "${AUTOREV}"
SRCREV_interface = "${AUTOREV}"
SRCREV_tools     = "${AUTOREV}"

SRC_URI:tesintern = "\
	file://egl.pc \
	file://glesv2.pc \
	${TES_SVN_PATH};module=display;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=display \
	${TES_SVN_PATH};module=gles;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=gles \
	${TES_SVN_PATH};module=bagl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=bagl \
	${TES_SVN_PATH};module=egl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=egl \
	${TES_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver \
	${TES_SVN_PATH};module=glslang;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=glslang \
	${TES_SVN_PATH};module=nxasm;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=nxasm \
	${TES_SVN_PATH};module=build;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=build \
	${TES_SVN_PATH};module=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=interface \
	${TES_SVN_PATH};module=tools;externals=nowarn;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=tools \
	file://tools \
"

S = "${WORKDIR}/build"
S:tesintern = "${WORKDIR}/build"

# Prevent DNX module from being built
EXTRA_OEMAKE += "NOMODULE=1 YOCTO_BUILD=1 BUILD=release"
EXTRA_OEMAKE:append = " DNX_DISPLAY=cdc GLES_USE_NXVC=1"
EXTRA_OEMAKE:append:arria10 = " CFG=arria10"
EXTRA_OEMAKE:append:agilex5 = " CFG=agilex5"

DEPENDS_libgles2-tes = "libdrm-dnx-dev"
DEPENDS_libdisplay-dnx = "libdrm-dnx-dev"
DEPENDS_libegl-tes = "libdisplay-dnx-dev"

RDEPENDS_libgles2-tes = "libdrm-dnx zlib libpng"
RDEPENDS_libegl-tes = "libgles2-tes"
RDEPENDS_libdrm-dnx = "libdrm"
RDEPENDS_libgles2-tes-dev = "libgles2-tes"
RDEPENDS_libegl-tes-dev = "libegl-tes"
RDEPENDS_libdrm-dnx-dev = "libdrm-dnx"
RDEPENDS_libdisplay-dnx-dev = "libdisplay-dnx libdrm libdrm-kms libdrm-dnx"

PACKAGES =+ "\
	libegl-tes \
	libgles2-tes \
	libdisplay-dnx \
	libegl-tes-dev \
	libgles2-tes-dev \
	libdrm-dnx \
	libdrm-dnx-dev \
	libdisplay-dnx-dev \
"

do_compile:prepend () {
	install -m 0755 -d ${D}${includedir}
}

do_unpack:append:tesintern () {
    S = d.getVar('S', True)
    os.system("cp " + S + "/../tools/svnver/davenx_svnver.* " + S + "/../driver/user/src/")
    os.system("cp " + S + "/../tools/svnver/gles_svnver.* " + S + "/../gles/src/")
    os.system("cp " + S + "/../tools/svnver/egl_svnver.* " + S + "/../egl/src/")
}

do_install () {
	install -m 0755 -d ${D}${libdir}
	oe_soinstall ${S}/../gles/build/linux/libGLESv2.so.*.* ${D}${libdir}
	oe_soinstall ${S}/../egl/build/linux/libEGL.so.*.* ${D}${libdir}
	oe_soinstall ${S}/../driver/kernel/linux/drm-dnx/libdrm_dnx.so.*.* ${D}${libdir}
	oe_soinstall ${S}/../display/build/linux/libdisplay.so.*.* ${D}${libdir}

	install -m 0755 -d ${D}${libdir}/pkgconfig
	install -m 0644 ${S}/../*.pc ${D}${libdir}/pkgconfig/

	install -m 0755 -d ${D}${includedir}/GLES2
	install -m 0755 -d ${D}${includedir}/GLES3
	install -m 0755 -d ${D}${includedir}/EGL
	install -m 0755 -d ${D}${includedir}/KHR
	install -m 0644 ${S}/../gles/inc/GLES2/* ${D}${includedir}/GLES2
	install -m 0644 ${S}/../gles/inc/GLES3/* ${D}${includedir}/GLES3
	install -m 0644 ${S}/../gles/inc/EGL/* ${D}${includedir}/EGL
	install -m 0644 ${S}/../gles/inc/KHR/* ${D}${includedir}/KHR
	install -m 0644 ${S}/../driver/kernel/linux/drm-dnx/dnx_drm*h ${D}${includedir}
	install -m 0644 ${S}/../display/inc/* ${D}${includedir}
}


FILES:libegl-tes = "${libdir}/libEGL.so.*"
FILES:libgles2-tes = "${libdir}/libGLESv2.so.*"
FILES:libdrm-dnx = "${libdir}/libdrm_dnx.so.*"
FILES:libdisplay-dnx = "${libdir}/libdisplay.so.*"
FILES:libegl-tes-dev = "\
	${includedir}/EGL \
	${includedir}/KHR \
	${libdir}/pkgconfig/egl.pc \
	${libdir}/libEGL.so \
"
FILES:libgles2-tes-dev = "\
	${includedir}/GLES2 \
	${includedir}/GLES3 \
	${libdir}/pkgconfig/glesv2.pc \
	${libdir}/libGLESv2.so \
"
FILES:libdrm-dnx-dev = "\
	${includedir}/dnx_drm*h \
	${includedir}/nx_*.h \
	${libdir}/libdrm_dnx.so \
"
FILES:libdisplay-dnx-dev = "\
	${includedir}/display.h \
	${libdir}/libdisplay.so \
"
