DESCRIPTION = "Preliminary driver for Warpingengine. Do NOT use in production!"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "d674f7a4889232c2ef3154f04023539b987b446b"
SRC_URI = "\
        git://github.com/tes-gfx/warp-mod-prelim.git;branch=5.4.124-lts;protocol=https \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "warp"
