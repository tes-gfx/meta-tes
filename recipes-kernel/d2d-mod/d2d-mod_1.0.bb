DESCRIPTION = "Preliminary driver for D/AVE 2D. Do NOT use in production!"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "5c7d380773b1e22a608ce5e8998911eef14eba69"
SRC_URI = "\
        git://github.com/tes-gfx/d2d-mod-prelim.git;branch=5.4.124-lts;protocol=https \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "d2d"
