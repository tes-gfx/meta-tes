DESCRIPTION = "KMS driver for the TES CDC IP core. Currently, the driver is in a ver rudimentary state and contains some special IOCTL hacks for the MesseDemo."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

MODULE_VERSION = "6.1"

SRCREV = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_CDC_SVN_PATH}/software/linux/kms;module=${MODULE_VERSION};protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};path_spec=svn \
"

S = "${WORKDIR}/svn"

KERNEL_MODULE_AUTOLOAD += "cdc"
