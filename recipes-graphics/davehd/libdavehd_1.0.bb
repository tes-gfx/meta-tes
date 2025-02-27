DESCRIPTION = "D/AVE HD library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdrm-gman libdi generic-ip-mod-uapi"

FILESEXTRAPATHS:prepend := "${TES_DHD_SRC_PATH}:"

PV = "1.0"
SRC_URI =  " \
	file://driver \
	file://tools \
"

PV:tesintern = "1.0+svnr${SRCPV}"

SRCREV_FORMAT = "driver"
SRCREV_driver = "${AUTOREV}"
SRCREV_tools  = "${AUTOREV}"

SRC_URI:tesintern =  " \
	${TES_DHD_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver \
	${TES_DHD_SVN_PATH};module=tools/generators;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=tools \
"

S = "${WORKDIR}/driver"
B = "${WORKDIR}/driver/user/build/linux_genip"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir} 
  install -m 0644 ${S}/user/inc/*.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_types.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_registertypes.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_registerfile.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_registerfile_custom.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_registermap.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_registermap_custom.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_settings.h ${D}${includedir}
  install -m 0644 ${S}/config/platform/davehd_platform.h ${D}${includedir}
  install -m 0644 ${S}/config/platform/davehd_settings_linux_genip.h ${D}${includedir}
  install -m 0644 ${S}/config/platform/davehd_device_data_size_default.h ${D}${includedir}
  install -m 0644 ${S}/kernel/inc/davehd_kernel_driver.h ${D}${includedir}
  install -m 0644 ${S}/kernel/inc/davehd_kernel_client.h ${D}${includedir}
  install -m 0644 ${S}/kernel/inc/davehd_kernel_bridge.h ${D}${includedir}

  # Stuff for drivertest build
  install -m 0644 ${S}/user/src/davehd_internal.h ${D}${includedir}
  install -m 0644 ${S}/user/src/davehd_debug.h ${D}${includedir}
  install -m 0644 ${S}/user/src/davehd_cmdlist.h ${D}${includedir}
  install -m 0644 ${S}/user/src/davehd_os.h ${D}${includedir}
  install -m 0644 ${S}/user/src/davehd_stream.h ${D}${includedir}
  install -m 0644 ${S}/config/davehd_stcopcodes.h ${D}${includedir}
  install -d ${D}${libdir} 

  install ${B}/libdavehd.a ${D}${libdir}
}

# We only have a static lib here, so the main package will be empty.
# But there are still dependencies on it. Therefore, we keep it as an empty package.
ALLOW_EMPTY:${PN} = "1"
