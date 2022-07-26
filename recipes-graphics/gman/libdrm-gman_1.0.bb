SUMMARY = "Gfx Manager userspace DRM library"
DESCRIPTION = "\
	A user space DRM library for the Gfx Manager for TES EvalKits. Do NOT use in production!\
"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "\
	file://LICENSE;md5=e12568b5f33369db2e82fb2b3cee4270 \
"

PROVIDES = "libdrm-gman-dev"

DEPENDS = "libdrm"
RDEPENDS_${PN} = "libdrm"

inherit pkgconfig

PV = "1.0+gitr${SRCPV}"

SRCREV = "46e60c3f887d83a094dab2d30154e181ba7f461f"
SRC_URI = "\
	git://github.com/tes-gfx/drm-gman.git;branch=master;protocol=https \
"

S = "${WORKDIR}/git"

do_install () {
  install -d ${D}${includedir}
  install -m 0644 ${S}/gman_drmif.h ${D}${includedir}
  install -d ${D}${libdir}
  oe_soinstall ${S}/libdrm_gman.so.*.* ${D}${libdir}
}
