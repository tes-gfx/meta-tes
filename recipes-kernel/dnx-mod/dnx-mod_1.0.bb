DESCRIPTION = "DRM driver for TES' D/AVE NX GPU."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM:tesintern = "file://kernel_module/${MODULE_VERSION}/LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

MODULE_VERSION = "6.1"

SRCREV_FORMAT = "driver_interface"
SRCREV_driver = "${AUTOREV}"
SRCREV_interface = "${AUTOREV}"
SRC_URI:tesintern = "\
	${TES_SVN_PATH};module=driver/kernel/linux/module;path_spec=kernel_module;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=driver \
	${TES_SVN_PATH};module=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=interface \
"

S = "${WORKDIR}"
B:tesintern = "${WORKDIR}/kernel_module/${MODULE_VERSION}"

KERNEL_MODULE_AUTOLOAD += "dnx"

# Workaround for issue with including kernel headers in Yocto builds and repo builds.
# The source should include dnx_drm.h with square brackets (<>) isntead of double quotes.
do_copy_header() {
	cp ${STAGING_KERNEL_DIR}/include/uapi/drm/dnx_drm.h ${B}
}
do_copy_header[depends] += "virtual/kernel:do_copy"

addtask copy_header after do_fetch before do_compile
