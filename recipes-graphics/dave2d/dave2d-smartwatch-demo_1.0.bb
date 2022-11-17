DESCRIPTION = "D/AVE 2D SmartWatch Demo"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdave2d libdrm"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_D2D_SRC_PATH}/software:"

PV = "1.0"
SRC_URI = " \
	file://smartwatch \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_D2D_SVN_PATH}/software;module=smartwatch;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/smartwatch/build/linux"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 smartwatch ${D}${datadir}/${PN}/
  cp -R --no-dereference --preserve=mode,links -v ${S}/../../gfx ${D}${datadir}/${PN}/gfx
  install -d ${D}${srcdir}/${PN}/src/linux
  install -d ${D}${srcdir}/${PN}/build/linux
  install -m 0644 ${S}/../../src/*.c ${D}${srcdir}/${PN}/src
  install -m 0644 ${S}/../../src/*.h ${D}${srcdir}/${PN}/src
  install -m 0644 ${S}/../../src/linux/*.c ${D}${srcdir}/${PN}/src/linux
  install -m 0644 ${S}/../../README.md ${D}${srcdir}/${PN}
  install -m 0644 ${S}/../../build/linux/Makefile ${D}${srcdir}/${PN}/build/linux
  cp -R --no-dereference --preserve=mode,links -v ${S}/../../gfx ${D}${srcdir}/${PN}/gfx
}

#
# Add a src package for the project, enabling the user to modify and build
# the project as a start for evaluation.
# Sources, resources and project file have to be added to the package.
#
PACKAGES += "${PN}-devsrc"
FILES:${PN}-devsrc += "\
	${srcdir}/${PN} \
"
RDEPENDS_${PN}-devsrc = "${PN}"
