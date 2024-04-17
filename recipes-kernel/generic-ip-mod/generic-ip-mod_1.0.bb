DESCRIPTION = "Generic IP core driver for the EvalKit. Do NOT use in production!"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "${PN}-uapi"

inherit module

PV = "1.0+gitr${SRCPV}"
PV:tesintern = "1.0+svnr${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI = "\
        git://github.com/tes-gfx/generic-ip-mod.git;branch=5.4.124-lts;protocol=https \
"
SRC_URI:tesintern = "\
        ${TES_EVALKIT_SVN_PATH}/drivers;module=genip;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/git"
S:tesintern = "${WORKDIR}/genip"

do_install:append() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/genip_module.h ${D}${includedir}
}

KERNEL_MODULE_AUTOLOAD += "genip"

FILES:${PN}-uapi := " \
    ${includedir}/genip_module.h \
"
