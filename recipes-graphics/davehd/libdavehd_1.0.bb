DESCRIPTION = "D/AVE HD library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdrm-gman libdi generic-ip-mod-uapi"

FILESEXTRAPATHS:prepend := "${TES_DHD_SRC_PATH}:"

PV = "1.0"
SRC_URI =  " \
	file://driver \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern =  " \
	${TES_DHD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver/user/build/linux_genip"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/../../inc/*.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/davehd_types.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/davehd_registerfile.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/davehd_registerfile_custom.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/davehd_registermap.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/davehd_registermap_custom.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/davehd_settings.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/platform/davehd_platform.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/platform/davehd_settings_linux_genip.h ${D}${includedir}
  install -m 0644 ${S}/../../../config/platform/davehd_device_data_size_default.h ${D}${includedir}
  install -d ${D}${libdir} 
  install ${S}/libdavehd.a ${D}${libdir}
}
