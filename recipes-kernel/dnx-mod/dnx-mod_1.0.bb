DESCRIPTION = "DRM driver for TES' D/AVE NX GPU."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV_tesintern = "1.0+svnr${SRCPV}"

SRCREV_FORMAT = "driver_interface"
SRCREV_driver    = "${AUTOREV}"
SRCREV_interface = "${AUTOREV}"

FILESEXTRAPATHS_prepend := "${TES_SRC}/driver/kernel/linux:"
SRC_URI = "\
        file://module \
"

# Checkout whole driver since we need the directory structure for the interface includes
SRC_URI_tesintern = "\
	${TES_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver \
	${TES_SVN_PATH};module=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=interface \
"

S = "${WORKDIR}"
B = "${WORKDIR}/module"
B_tesintern = "${WORKDIR}/driver/kernel/linux/module"

KERNEL_MODULE_AUTOLOAD += "dnx"
