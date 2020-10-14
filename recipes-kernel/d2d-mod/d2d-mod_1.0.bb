DESCRIPTION = "Preliminary driver for D/AVE 2D. Do NOT use in production!"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "6e19aa4028b6eb77a69f926e26172942e9eb770e"
SRC_URI = "\
        git://github.com/tes-gfx/d2d-mod-prelim.git;branch=master;protocol=https \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "d2d"
