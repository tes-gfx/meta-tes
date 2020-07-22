DESCRIPTION = "KMS driver for the TES CDC IP core. Currently, the driver is in a ver rudimentary state and contains some special IOCTL hacks for the MesseDemo."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "65bf5168cc53fe7b53f7dca0fb18bb7276fbf456"
SRC_URI = "\
	git://github.com/tes-gfx/cdc-kms.git;protocol=https;branch=cdc500-4.14.130-ltsi \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "cdc"
