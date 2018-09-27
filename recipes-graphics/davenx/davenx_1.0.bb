SUMMARY = "TES DaveNX based implementation of the OpenGL 2.0 ES API"
DESCRIPTION = "\
	The library is not yet feature complete and still in \
	development. \
"

PROVIDES = "virtual/libgles2 virtual/egl libdrm-dnx-dev libegl-tes-dev"
LICENSE = "MIT & BSD"
LICENSE_libdrm-dnx = "MIT"
LICENSE_libdrm-dnx-dev = "MIT"
LIC_FILES_CHKSUM = "\
	file://../gles/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../bagl/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../egl/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../nxasm/docs/LICENSE.txt;md5=dccd4a0fb4baa9ea80d0bcfd93c4ef7e \
	file://../driver/kernel/linux/drm-dnx/LICENSE;md5=b3cff5eb85b2d0681a59405204a0a831 \
	file://../driver/user/docs/LICENSE.txt;md5=875d108c43e661a3610686e2a42dc826 \
	file://../glslang/glslang/MachineIndependent/ShaderLang.cpp;endline=35;md5=67bf0a40552becaa55981bb08f8d9726 \
"

DEPENDS = "libdrm"

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = "\
	file://egl.pc \
	file://glesv2.pc \
	file://gles \
	file://bagl \
	file://egl \
	file://driver \
	file://glslang \
	file://nxasm \
	file://build \
	file://interface \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	file://egl.pc \
	file://glesv2.pc \
	${TES_SVN_PATH};module=gles;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=bagl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=egl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=glslang;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=nxasm;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=build;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	file://tools \
"

S = "${WORKDIR}/build"
S_tesintern = "${WORKDIR}/build"

# Prevent DNX module from being built
EXTRA_OEMAKE           += "NOMODULE=1 YOCTO_BUILD=1 BUILD=release"
EXTRA_OEMAKE_tesintern += "NOMODULE=1 YOCTO_BUILD=1 BUILD=debug"

DEPENDS_liblges2-tes = "libdrm-dnx-dev"

RDEPENDS_libgles2-tes = "libdrm-dnx zlib libpng"
RDEPENDS_libegl-tes = "libgles2-tes"
RDEPENDS_libdrm-dnx = "libdrm libdrm-kms"
RDEPENDS_libgles2-tes-dev = "libgles2-tes"
RDEPENDS_libegl-tes-dev = "libegl-tes"
RDEPENDS_libdrm-dnx-dev = "libdrm-dnx"

PACKAGES =+ "\
	libegl-tes \
	libgles2-tes \
	libegl-tes-dev \
	libgles2-tes-dev \
	libdrm-dnx \
	libdrm-dnx-dev \
"

do_compile_prepend () {
	install -m 0755 -d ${D}${includedir}
}

do_unpack_append_tesintern () {
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
	install -m 0644 ${S}/../interface/src/nx_*.h ${D}${includedir}
}


FILES_libegl-tes = "${libdir}/libEGL.so.*"
FILES_libgles2-tes = "${libdir}/libGLESv2.so.*"
FILES_libdrm-dnx = "${libdir}/libdrm_dnx.so.*"
FILES_libegl-tes-dev = "\
	${includedir}/EGL \
	${includedir}/KHR \
	${libdir}/pkgconfig/egl.pc \
	${libdir}/libEGL.so \
"
FILES_libgles2-tes-dev = "\
	${includedir}/GLES2 \
	${includedir}/GLES3 \
	${libdir}/pkgconfig/glesv2.pc \
	${libdir}/libGLESv2.so \
"
FILES_libdrm-dnx-dev = "\
	${includedir}/dnx_drm*h \
	${includedir}/nx_*.h \
	${libdir}/libdrm_dnx.so \
"
