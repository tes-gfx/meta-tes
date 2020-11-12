DESCRIPTION = "D/AVE 2D SmartWatch Demo"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdave2d"

PV = "1.0"
SRC_URI = " \
	file://demos/smartwatch \	
"

PV_tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_D2D_SVN_PATH}/tutorial/software/code;module=smartwatch;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/smartwatch"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 smartwatch ${D}${datadir}/${PN}/
  cp -R --no-dereference --preserve=mode,links -v gfx ${D}${datadir}/${PN}/gfx
  install -d ${D}${srcdir}/${PN}  
  install -m 0644 ${S}/*.c ${D}${srcdir}/${PN}/
  install -m 0644 ${S}/*.h ${D}${srcdir}/${PN}/
  install -m 0644 ${S}/README.md ${D}${srcdir}/${PN}/
  install -m 0644 ${S}/Makefile ${D}${srcdir}/${PN}/
  cp -R --no-dereference --preserve=mode,links -v gfx ${D}${srcdir}/${PN}/gfx
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
RDEPENDS_${PN}-devsrc = "${PN}"
