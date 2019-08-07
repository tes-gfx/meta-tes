DESCRIPTION = "TES EGLES 2.0 test"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "virtual/libgles2 virtual/egl libkms-egl-dev libpng"

PV_tesintern = "1.0+svnr${SRCPV}"

RDEPENDS_${PN} = "libdrm libkms-egl libgles2-tes libegl-tes libpng"

inherit pkgconfig

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = " \
	file://demos/egles_test \
	file://tools/kms_helper \
	file://build \
	file://bagl \
	file://gles \
"

SRCREV_FORMAT = "test_bagl_gles_build_tools"
SRCREV_test   = "${AUTOREV}"
SRCREV_bagl   = "${AUTOREV}"
SRCREV_gles   = "${AUTOREV}"
SRCREV_build  = "${AUTOREV}"
SRCREV_tools  = "${AUTOREV}"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/demos;module=egles_test;path_spec=./demos/egles_test;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=test \
	${TES_SVN_PATH};module=bagl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=bagl \
	${TES_SVN_PATH};module=gles;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=gles \
	${TES_SVN_PATH};module=build;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=build \
	${TES_SVN_PATH}/tools;module=kms_helper;path_spec=./tools/kms_helper;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=tools \
"

S = "${WORKDIR}"
srcdir = "${prefix}/src"
B = "${WORKDIR}/demos/egles_test/build/linux"

EXTRA_OEMAKE += "SHADERTOY=n"


do_shadertoy() {
  rm -f  ${S}/demos/egles_test/src/test_cases/shadertoy*
  rm -rf ${S}/demos/egles_test/bin/resources/shadertoy
}
addtask shadertoy after do_unpack before do_patch


do_install() {
  install -d ${D}${datadir}/${PN}
  install -d ${D}${datadir}/${PN}/shader_dumps
  cp -r ${S}/demos/egles_test/bin ${D}${datadir}/${PN}/bin
  install -m 0755 ${B}/egles_test ${D}${datadir}/${PN}/bin
  install -d ${D}${srcdir}/${PN}
  cp -r ${S}/demos ${D}${srcdir}/${PN}/
  find ${D}${srcdir}/${PN} \( -iname "*.o" -o -iname "*.d" -o -iname "*.a" \) -delete
  cp -r ${S}/build ${D}${srcdir}/${PN}/
  cp -r ${S}/bagl ${D}${srcdir}/${PN}/
  cp -r ${S}/gles ${D}${srcdir}/${PN}/
  cp -r ${S}/tools ${D}${srcdir}/${PN}/
#  install -m 0644 ${S}/main.c ${D}${srcdir}/${PN}/
}


#
# Add a src package for the project, enabling the user to modify and build
# the project as a start for evaluation.
# Sources, resources and project file have to be added to the package.
#
PACKAGES += "${PN}-src"
FILES_${PN}-src += "\
	${srcdir}/${PN} \
"
RDEPENDS_${PN}-src = "${PN}"
