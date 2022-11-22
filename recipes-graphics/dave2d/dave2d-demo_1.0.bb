DESCRIPTION = "D/AVE 2D Demo"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdave2d libdrm"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${TES_D2D_SRC_PATH}/software:"

PV = "1.0"
SRC_URI = " \
	file://demo \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_D2D_SVN_PATH}/software;module=demo;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

B = "${WORKDIR}/demo/build/linux_genip"
S = "${WORKDIR}/demo"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 ${B}/demo ${D}${datadir}/${PN}/
  install -d ${D}${srcdir}/${PN}/code/linux_genip
  install -d ${D}${srcdir}/${PN}/code/picture
  install -d ${D}${srcdir}/${PN}/code/utils
  install -d ${D}${srcdir}/${PN}/code/demos
  install -d ${D}${srcdir}/${PN}/code/demos/dashb_classes
  install -d ${D}${srcdir}/${PN}/code/demos/menu_classes
  install -d ${D}${srcdir}/${PN}/code/demos/2dWowCluster/misc
  install -d ${D}${srcdir}/${PN}/code/demos/2dWowCluster/image/src
  install -d ${D}${srcdir}/${PN}/build/linux_genip
  install -m 0644 ${S}/code/*.{c,h} ${D}${srcdir}/${PN}/code
  install -m 0644 ${S}/code/picture/*.{c,cpp,h} ${D}${srcdir}/${PN}/code/picture
  install -m 0644 ${S}/code/utils/*.{c,cpp,h} ${D}${srcdir}/${PN}/code/utils
  install -m 0644 ${S}/code/linux_genip/*.{c,h} ${D}${srcdir}/${PN}/code/linux_genip
  install -m 0644 ${S}/code/demos/*.{c,cpp,h} ${D}${srcdir}/${PN}/code/demos
  install -m 0644 ${S}/code/demos/dashb_classes/*.{cpp,h} ${D}${srcdir}/${PN}/code/demos/dashb_classes
  install -m 0644 ${S}/code/demos/menu_classes/*.{cpp,h} ${D}${srcdir}/${PN}/code/demos/menu_classes
  install -m 0644 ${S}/code/demos/2dWowCluster/*.{c,h} ${D}${srcdir}/${PN}/code/demos/2dWowCluster
  install -m 0644 ${S}/code/demos/2dWowCluster/misc/*.{c,h} ${D}${srcdir}/${PN}/code/demos/2dWowCluster/misc
  install -m 0644 ${S}/code/demos/2dWowCluster/misc/imageDataFlash ${D}${srcdir}/${PN}/code/demos/2dWowCluster/misc
  install -m 0644 ${S}/code/demos/2dWowCluster/image/src/*.c ${D}${srcdir}/${PN}/code/demos/2dWowCluster/image/src
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

