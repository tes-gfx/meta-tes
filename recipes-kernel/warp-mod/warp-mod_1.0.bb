DESCRIPTION = "Preliminary driver for Warpingengine. Do NOT use in production!"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "ba84f73f2616322b4ee7028112b5ada0cd662a83"
SRC_URI = "\
        git://github.com/tes-gfx/warp-mod-prelim.git;branch=master;protocol=https \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "warp"
