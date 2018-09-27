DESCRIPTION = "KMS driver for the TES CDC IP core. Currently, the driver is in a ver rudimentary state and contains some special IOCTL hacks for the MesseDemo."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PR = "r0"
PV_tesintern = "1.0+svnr${SRCPV}"

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = "\
        file://kms \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH_CDC}/linux;module=kms;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
"

S = "${WORKDIR}"
B = "${WORKDIR}/kms"
B_tesintern = "${WORKDIR}/kms"

KERNEL_MODULE_AUTOLOAD += "cdc"
