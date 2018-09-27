FILESEXTRAPATHS_prepend := "${THISDIR}:"

KERNEL_REPO = "https://github.com/c-thaler/linux-socfpga.git"

KERNEL_DEVICETREE_arria10 = " \
	socfpga_arria10_socdk_tes.dtb \
	socfpga_arria10_wiesmann_tes.dtb \
"

DTB_OUTPUT ?= "arch/${ARCH}/boot/dts"

#
# Add base device tree and overlay for our design (enable FPGA2SDRAM bridge)
#
SRC_URI_append_arria10 = " file://${PN}/4.9/dts/socfpga_arria10_socdk_tes.dts"
SRC_URI_append_arria10 = " file://${PN}/4.9/dts/socfpga_arria10_wiesmann.dtsi"
SRC_URI_append_arria10 = " file://${PN}/4.9/dts/socfpga_arria10_wiesmann_tes.dts"
SRC_URI_append_arria10 = " file://${PN}/4.9/dts/socfpga_arria10_wiesmann_tes_messe.dts"


#
# Add kernel configuration (e.g. DRM/KMS support, I2C encoder slaves, CMA, ...)
#
SRC_URI_append = " file://${PN}/4.9/config/tes_dnx_cdc.cfg"


#
# Add required kernel patches
#
#SRC_URI_append = " file://${PN}/4.9/patches/todo.patch"

#
# Add DNX register headers and DRM UAPI definitions for kernel side
#
#FILESEXTRAPATHS_prepend := "${TES_SRC}:${TES_SRC}/driver/kernel/linux:"
#ADDSOURCES = " \
#	file://interface \
#	file://drm-dnx/dnx_drm.h \
#"


SRCREV_interface = "${AUTOREV}"
#SRCREV_FORMAT = "default_dnx-rinterface"
ADDSOURCES_tesintern = "\
	${TES_SVN_PATH};module=interface;name=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH}/driver/kernel/linux;module=drm-dnx;name=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"
SRC_URI_append = "${ADDSOURCES}"


#
# Copy base device tree into kernel source
#
do_compile_prepend() {
	cp ${WORKDIR}/${PN}/4.1/${MACHINE}/dts/*.dts ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/
	cp ${WORKDIR}/drm-dnx/dnx_drm.h ${STAGING_KERNEL_DIR}/include/uapi/drm/
	cp ${WORKDIR}/interface/src/*.h ${STAGING_KERNEL_DIR}/include/
}

do_compile_prepend_arria10() {
	cp ${WORKDIR}/${PN}/4.1/${MACHINE}/dts/*.dtsi ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/
}

#
# Copy required header files into kernel staging directory (required for building module)
#
do_install_append() {
	install -m 0644 ${WORKDIR}/interface/src/*.h ${STAGING_KERNEL_DIR}/include/
	install -m 0644 ${WORKDIR}/drm-dnx/dnx_drm.h ${STAGING_KERNEL_DIR}/include/uapi/drm/
}
