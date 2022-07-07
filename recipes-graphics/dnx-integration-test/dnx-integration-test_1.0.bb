DESCRIPTION = "A simple integration test for the D/AVE NX"
HOMEPAGE = "http://www.tes-dst.com"
DEPENDS = "libdrm-dnx-dev"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://integration_test.c;endline=20;md5=94748e61e7b69bb10cdadfc37eeb976f"

PR = "r0"
PV = "1.0"

FILESEXTRAPATHS:prepend := "${TES_SRC}:"
SRC_URI =  "file://demos/integration_test"

SRCREV = "${AUTOREV}"
SRC_URI:tesintern =  "${TES_SVN_PATH}/demos;module=integration_test;subdir=demos;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}"

S = "${WORKDIR}/demos/integration_test"
srcdir = "${prefix}/src"

inherit pkgconfig

do_install () {
  install -d ${D}${bindir}
  install -m 0755 integration_test ${D}${bindir}
  install -d ${D}${srcdir}/${PN}  
  install -m 0644 ${S}/*.c ${D}${srcdir}/${PN}/
}

#
# Add a src package for the project, enabling the user to modify and build
# the project as a start for evaluation.
# Sources, resources and project file have to be added to the package.
#
PACKAGES += "${PN}-devsrc"
FILES_${PN}-devsrc += "\
	${srcdir}/${PN} \
"
RDEPENDS_${PN}-devsrc += "${PN}"
