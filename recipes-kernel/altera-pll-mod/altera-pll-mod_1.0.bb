DESCRIPTION = "Altera PLL Reconfig driver"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PR = "r0"
PV_tesintern = "1.0+svnr${SRCPV}"

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = "\
        file://altera_pll \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	svn://svn.hamburg.tes/svn/tes_cdc/devel/linux;module=altera_pll;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
"

S = "${WORKDIR}"
B = "${WORKDIR}/altera_pll"
B_tesintern = "${WORKDIR}/altera_pll"

KERNEL_MODULE_AUTOLOAD += "altr_pll"
