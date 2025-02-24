DESCRIPTION = "TES OpenGL 2.0 ES example"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://main.c;endline=20;md5=f59da969d3bfe07da4fae1d11c2554bf"
DEPENDS = "virtual/libgles2 virtual/egl libkms-egl-dev"


FILESEXTRAPATHS:prepend := "${TES_SRC}:"
SRC_URI = " \
	file://demos/hellogl \	
"

SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_SVN_PATH}/demos;module=hellogl;subdir=demos;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"


S = "${WORKDIR}/demos/hellogl"
srcdir = "${prefix}/src"
B = "${WORKDIR}/demos/hellogl"


do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 hellogl ${D}${datadir}/${PN}/
  install -d ${D}${srcdir}/${PN}  
  install -m 0644 ${S}/main.c ${D}${srcdir}/${PN}/
  install -m 0644 ${S}/Makefile ${D}${srcdir}/${PN}/
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
