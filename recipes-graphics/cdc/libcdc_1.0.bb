DESCRIPTION = "CDC userspace library"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"

DEPENDS = "libdrm-gman generic-ip-mod-uapi"

FILESEXTRAPATHS:prepend := "${TES_CDC_SRC_PATH}/software:"

PV = "1.0"
SRC_URI =  " \
	file://driver \
"

PV:tesintern = "1.0+svnr${SRCPV}"
SRCREV = "${AUTOREV}"
SRC_URI:tesintern =  " \
	${TES_CDC_SVN_PATH}/software;module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/driver/build/linux"

inherit pkgconfig

do_install () {
  install -d ${D}${includedir}/linux 
  install -m 0644 ${S}/../../include/cdc.h ${D}${includedir}
  install -m 0644 ${S}/../../include/cdc_config.h ${D}${includedir}
  install -m 0644 ${S}/../../include/linux/cdc_linux.h ${D}${includedir}/linux
  install -d ${D}${libdir} 
  install ${S}/libcdc.a ${D}${libdir}
}

# We only have a static lib here, so the main package will be empty.
# But there are still dependencies on it. Therefore, we keep it as an empty package.
ALLOW_EMPTY:${PN} = "1"
