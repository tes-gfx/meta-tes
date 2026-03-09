DESCRIPTION = "TES Minnie test"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "virtual/libgles2 virtual/egl libpng"

PV:tesintern = "1.0+svnr${SRCPV}"

INSANE_SKIP:${PN} += "already-stripped"

RDEPENDS:${PN} = "libdrm libgles2-tes libegl-tes libpng bash"

inherit pkgconfig

SRCREV_FORMAT  = "minnie"
SRCREV_minnie    = "${AUTOREV}"

SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_SVN_PATH};module=minnie;subdir=svn;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=minnie \
"

S = "${WORKDIR}/svn/minnie"
srcdir = "${prefix}/src"
B = "${WORKDIR}/svn/minnie/native/build/linux_poky"
installdir = "/home/root"

EXTRA_OEMAKE += "\
	TKS_ROOT=${S} \
	BUILD_64=y \
	BUILD_ARM=y \
	CROSS_COMPILE=${TARGET_PREFIX} \
	CROSS_TARGET=DNX_PROXY \
	CROSS_ROOT=${RECIPE_SYSROOT} \
	TKS_TARGET_PREFIX=/usr/bin \
	TKS_TARGET_SITE_PREFIX=/usr/lib/tks \
	TKS_PREFIX=/usr/bin \
	TKS_SITE_PREFIX=/usr/lib/tks \
"

do_compile() {
  oe_runmake -f makefile.linux bin
}

do_install() {
  install -d ${D}${installdir}/${PN}
  install -d ${D}${installdir}/${PN}/bin
  install -d ${D}${installdir}/${PN}/bin/resources
  cp -r ${S}/tools/mib/mib ${D}${installdir}/${PN}/bin/resources
  install -m 0755 ${B}/test_mib ${D}${installdir}/${PN}/bin/
  install -m 0755 ${B}/test_min ${D}${installdir}/${PN}/bin/
  install -m 0755 ${B}/test_shadervg ${D}${installdir}/${PN}/bin/
}

FILES:${PN} += "/home/root"

