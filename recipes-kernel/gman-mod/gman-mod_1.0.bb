DESCRIPTION = "Gfx Manager for TES EvalKits. Do NOT use in production!"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "3e537d4770c3794a1bd06429cc401d000e29efbb"
SRC_URI = "\
        git://github.com/tes-gfx/gman-mod.git;branch=5.4.124-lts;protocol=https \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "gman"
