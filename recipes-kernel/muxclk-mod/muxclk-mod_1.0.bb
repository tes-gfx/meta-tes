DESCRIPTION = "TES muxed PLL clock driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PR = "r0"
PV_tesintern = "+svnr${SRCPV}"

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = "\
        file://muxclk \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	svn://svn.hamburg.tes/svn/tes_cdc/devel/linux;module=muxclk;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
"

S = "${WORKDIR}"
B = "${WORKDIR}/muxclk"
B_tesintern = "${WORKDIR}/muxclk"

KERNEL_MODULE_AUTOLOAD += "muxclk"
