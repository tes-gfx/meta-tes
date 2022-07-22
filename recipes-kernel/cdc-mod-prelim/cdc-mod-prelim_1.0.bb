DESCRIPTION = "Preliminary driver for CDC. Do NOT use in production!"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "0aee6b8f390dfbb067c0f4d14da0e98f2aee5184"
SRC_URI = "\
        git://github.com/tes-gfx/cdc-mod-prelim.git;branch=5.4.124-lts;protocol=https \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "cdc"
