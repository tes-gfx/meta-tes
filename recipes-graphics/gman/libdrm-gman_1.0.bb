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

PV = "1.0+git+svnr${SRCPV}"
PV:tesintern = "1.0+svnr${SRCPV}"

SRCREV_FORMAT = "gman_di"
SRCREV_gman = "${AUTOREV}"
SRCREV_di = "${AUTOREV}"

SRC_URI = "\
	git://github.com/tes-gfx/drm-gman.git;branch=master;protocol=https;name=gman \
        ${TES_EVALKIT_SVN_PATH}/drivers;module=di;protocol=https;name=di;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

SRC_URI:tesintern = "\
	${TES_EVALKIT_SVN_PATH}/drivers;module=gman;protocol=https;name=gman;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
        ${TES_EVALKIT_SVN_PATH}/drivers;module=di;protocol=https;name=di;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/git"
S:tesintern = "${WORKDIR}/gman"

export DI_PATH := "${WORKDIR}/di"

do_install () {
  install -d ${D}${includedir}
  install -m 0644 ${S}/gman_drmif.h ${D}${includedir}
  install -d ${D}${libdir}
  oe_soinstall ${S}/libdrm_gman.so.*.* ${D}${libdir}
}
