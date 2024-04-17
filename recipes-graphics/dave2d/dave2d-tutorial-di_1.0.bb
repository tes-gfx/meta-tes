DESCRIPTION = "D/AVE 2D Tutorial DI"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdave2d libdrm"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_D2D_SRC_PATH}/software:"

PV = "1.0"
SRC_URI = " \
	file://tutorial-di \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_D2D_SVN_PATH}/tutorial;module=software;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/software"
B = "${WORKDIR}/software/build/linux_genip"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 ${B}/tutorial* ${D}${datadir}/${PN}/
  install -d ${D}${srcdir}/${PN}/build/linux_genip
  install -d ${D}${srcdir}/${PN}/code
  install -m 0644 ${S}/code/di_*.c ${D}${srcdir}/${PN}/code
  install -m 0644 ${S}/build/linux_genip/Makefile ${D}${srcdir}/${PN}/build/linux_genip
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
