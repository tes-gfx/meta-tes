DESCRIPTION = "TES EGLES 2.0 test"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "virtual/libgles2 virtual/egl libkms-egl-dev libpng"

RDEPENDS_${PN} = "libdrm libkms-egl libgles2-tes libegl-tes libpng"


FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = " \
	file://egles_test;subdir=demos/ \
	file://kms_helper;subdir=tools/ \
	file://build \
	file://bagl \
	file://gles \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/demos;module=egles_test;path_spec=./demos/egles_test;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=bagl;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=gles;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH};module=build;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH}/tools;module=kms_helper;path_spec=./tools/kms_helper;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}"
srcdir = "${prefix}/src"
B = "${WORKDIR}/demos/egles_test/build/linux"


do_install () {
  install -d ${D}${datadir}/${PN}
  install -d ${D}${datadir}/${PN}/shader_dumps
  cp -r ${S}/demos/egles_test/bin ${D}${datadir}/${PN}/bin
  install -m 0755 ${B}/egles_test ${D}${datadir}/${PN}/bin
  install -d ${D}${srcdir}/${PN}
  cp -r ${S}/demos/egles_test/bin ${D}${srcdir}/${PN}/bin
  cp -r ${S}/demos/egles_test/src ${D}${srcdir}/${PN}/src
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
