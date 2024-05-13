DESCRIPTION = "DRM driver for TES' D/AVE NX GPU."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM:tesintern = "file://driver/kernel/linux/module/LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRCREV_FORMAT = "module_interface"
SRCREV_module = "${AUTOREV}"
SRCREV_interface = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_SVN_PATH}/driver/kernel/linux;module=module;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};path_spec=driver/kernel/linux/module;name=module \
	${TES_SVN_PATH};module=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=interface \
"

S = "${WORKDIR}"
B:tesintern = "${WORKDIR}/driver/kernel/linux/module"

KERNEL_MODULE_AUTOLOAD += "dnx"

# Workaround for issue with including kernel headers in Yocto builds and repo builds.
# The source should include dnx_drm.h with square brackets (<>) isntead of double quotes.
do_copy_header() {
	cp ${STAGING_KERNEL_DIR}/include/uapi/drm/dnx_drm.h ${B}
}

addtask copy_header after do_fetch before do_compile
