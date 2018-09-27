DESCRIPTION = "DRM driver for TES' D/AVE NX GPU."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PR = "r0"
PV = "1.0"

FILESEXTRAPATHS_prepend := "${TES_SRC}/driver/kernel/linux:"
SRC_URI = "\
        file://module \
"

SRCREV = "${AUTOREV}"
# Checkout whole driver since we need the directory structure for the interface includes
SRC_URI_tesintern = "\
	${TES_SVN_PATH};module=driver;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
	${TES_SVN_PATH};module=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
"

S = "${WORKDIR}"
B = "${WORKDIR}/module"
B_tesintern = "${WORKDIR}/driver/kernel/linux/module"

KERNEL_MODULE_AUTOLOAD += "dnx"
