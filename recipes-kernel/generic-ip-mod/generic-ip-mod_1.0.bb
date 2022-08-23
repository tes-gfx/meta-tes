DESCRIPTION = "Generic IP core driver for the EvalKit. Do NOT use in production!"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "c174e3e19d6af7205bfc15816df70df8d004de95"
SRC_URI = "\
        git://github.com/tes-gfx/generic-ip-mod.git;branch=5.4.124-lts;protocol=https \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "genip"
