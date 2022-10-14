DESCRIPTION = "D/AVE HD Drivertest"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdavehd libdrm libdi libcdc libdrm-gman ncurses"

FILESEXTRAPATHS:prepend := "${TES_DHD_SRC_PATH}:"

PV = "1.0"
SRC_URI = " \
	file://drivertest \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_DHD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver/test"
B = "${WORKDIR}/driver/test/build/linux_genip"
srcdir = "${prefix}/src"

do_install () {
  install -d ${D}${datadir}/${PN}
  install -m 0755 drivertest ${D}${datadir}/${PN}/
  install -m 0755 ../../data/resource.zip ${D}${datadir}/${PN}/

  install -d ${D}${srcdir}/${PN}
  cp --preserve=mode,timestamps -R ${S}/src ${D}${srcdir}/${PN}
  rm -rf ${D}${srcdir}/${PN}/src/testcases/other_testcases
  install -d ${D}${srcdir}/${PN}/build/linux_genip
  install -m 0644 ${S}/build/linux_genip/Makefile ${D}${srcdir}/${PN}/build/linux_genip
  install -d ${D}${srcdir}/${PN}/data
  install -m 0644 ${S}/data/resource.zip ${D}${srcdir}/${PN}/data
  install -d ${D}${srcdir}/${PN}/platform/linux_genip
  install -d ${D}${srcdir}/${PN}/platform/common
  install -d ${D}${srcdir}/${PN}/platform/stdlib
  install -m 0644 ${S}/platform/linux_genip/*  ${D}${srcdir}/${PN}/platform/linux_genip
  install -m 0644 ${S}/platform/common/* ${D}${srcdir}/${PN}/platform/common
  install -m 0644 ${S}/platform/stdlib/* ${D}${srcdir}/${PN}/platform/stdlib
  install -m 0644 ${S}/platform/*.c ${D}${srcdir}/${PN}/platform
  install -m 0644 ${S}/platform/*.h ${D}${srcdir}/${PN}/platform
}

PACKAGES += "${PN}-devsrc"
FILES:${PN}-devsrc += "\
        ${srcdir}/${PN}/* \
"
RDEPENDS_${PN}-devsrc = "${PN}"
