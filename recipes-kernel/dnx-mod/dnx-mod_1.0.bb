DESCRIPTION = "DRM driver for TES' D/AVE NX GPU."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://module/LICENSE;md5=801f80980d171dd6425610833a22dbe6"
LIC_FILES_CHKSUM:tesintern = "file://driver/kernel/linux/module/LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV:tesintern = "1.0+git+svnr${SRCPV}"

SRCREV_FORMAT    = "module_interface"
SRCREV_module    = "acf27c37a48b1addd803c839717107bc33b2b718"
SRCREV_interface = "${AUTOREV}"

FILESEXTRAPATHS:prepend := "${TES_SRC}/driver/kernel/linux:"
SRC_URI = "\
        file://module \
"

# Checkout whole driver since we need the directory structure for the interface includes
SRC_URI:tesintern = "\
	git://github.com/tes-gfx/dnx-mod.git;branch=4.14.73-ltsi-altera;protocol=https;destsuffix=driver/kernel/linux/module/;name=module \
	${TES_SVN_PATH};module=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=interface \
"

S = "${WORKDIR}"
B = "${WORKDIR}/module"
B:tesintern = "${WORKDIR}/driver/kernel/linux/module"

KERNEL_MODULE_AUTOLOAD += "dnx"
