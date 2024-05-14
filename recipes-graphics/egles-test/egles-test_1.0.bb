DESCRIPTION = "TES EGLES 2.0 test"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "virtual/libgles2 virtual/egl libpng"

PV:tesintern = "1.0+svnr${SRCPV}"

RDEPENDS_${PN} = "libdrm libgles2-tes libegl-tes libpng"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_SRC}:"
SRC_URI = " \
	file://demos/egles_test \
	file://tools/kms_helper \
	file://build \
	file://bagl \
	file://gles \
	file://driver \
	file://display \
"

SRCREV_FORMAT  = "test_display_bagl_gles_build_driver_tools"
SRCREV_test    = "${AUTOREV}"
SRCREV_display = "${AUTOREV}"
SRCREV_bagl    = "${AUTOREV}"
SRCREV_gles    = "${AUTOREV}"
SRCREV_build   = "${AUTOREV}"
SRCREV_driver  = "${AUTOREV}"
SRCREV_tools   = "${AUTOREV}"

SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_SVN_PATH}/demos;module=egles_test;path_spec=./svn/demos/egles_test;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=test \
	${TES_SVN_PATH};module=display;subdir=svn;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=display \
	${TES_SVN_PATH};module=bagl;subdir=svn;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=bagl \
	${TES_SVN_PATH};module=gles;subdir=svn;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=gles \
	${TES_SVN_PATH};module=build;subdir=svn;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=build \
	${TES_SVN_PATH};module=driver;subdir=svn;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver \
	${TES_SVN_PATH}/tools;module=kms_helper;path_spec=./svn/tools/kms_helper;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=tools \
"

S = "${WORKDIR}/svn"
srcdir = "${prefix}/src"
B = "${WORKDIR}/svn/demos/egles_test/build/linux"

EXTRA_OEMAKE += "GLES_USE_NXVC=0"
EXTRA_OEMAKE:append:tesclosed = " CFG=${MACHINE} SHADERTOY=1"
EXTRA_OEMAKE:append:tesmms = " CFG=${MACHINE} SHADERTOY=0"

python do_shadertoy() {
    if "tesclosed" not in d.getVar("OVERRIDES"):
         bb.warn("Removing shadertoy source code. Use tesclosed override to include shadertoy source code.")
         bb.build.exec_func('remove_closed_source', d) 
}

remove_closed_source() {
  rm -f  ${S}/demos/egles_test/src/test_cases/shadertoy*
  rm -rf ${S}/demos/egles_test/bin/resources/shadertoy
}
addtask shadertoy after do_unpack before do_patch

python do_mms() {
    if "tesmms" not in d.getVar("OVERRIDES"):
         bb.warn("Removing MMS/GTRI source code. Use tesmms override to include MMS/GTRI source code.")
         bb.build.exec_func('remove_mms_source', d) 
}

remove_mms_source() {
  rm -rf ${S}/demos/egles_test/src/test_cases/tes_render_sample
  rm -rf ${S}/demos/egles_test/bin/resources/textures/tes_render_sample
}
addtask mms after do_unpack before do_patch

do_install() {
  install -d ${D}${datadir}/${PN}
  install -d ${D}${datadir}/${PN}/shader_dumps
  cp -r ${S}/demos/egles_test/bin ${D}${datadir}/${PN}/bin
  install -m 0755 ${B}/egles_test ${D}${datadir}/${PN}/bin
}

