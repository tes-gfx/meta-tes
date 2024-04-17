FILESEXTRAPATHS:prepend := "${THISDIR}:"

KERNEL_PROT = "https"
KERNEL_REPO = "git://github.com/tes-gfx/linux-socfpga.git"

SRCREV = "85d7cb262602855c4f22ff5e96b14d6147d1ab80"

#KBUILD_DEFCONFIG_stratix10 = "s10_devkit_defconfig"
#KBRANCH:append = "-drm"

DTB_OUTPUT ?= "arch/${ARCH}/boot/dts"
DTB_OUTPUT:stratix10 ?= "arch/${ARCH}/boot/dts/altera"

#
# Add base device tree and overlay for our design (enable FPGA2SDRAM bridge)
#
SRC_URI:append:cyclone5 = " \
	file://${BPN}/5.4/dts/socfpga_cyclone5_de10_sockit_d2d.dts \
	file://${BPN}/5.4/dts/socfpga_cyclone5_de10_sockit_dhd.dts \
	file://${BPN}/5.4/dts/socfpga_cyclone5_de10_sockit_tes_lcd.dts \
"

SRC_URI:append:arria10 = " file://${BPN}/5.4/dts/socfpga_arria10_socdk_tes.dts"
SRC_URI:append:arria10 = " file://${BPN}/5.4/dts/dreamchip_arria10som.dtsi"
SRC_URI:append:arria10 = " file://${BPN}/5.4/dts/dreamchip_arria10som_tes.dts"

SRC_URI:append:stratix10 = " file://${BPN}/5.4/dts/socfpga_stratix10_socdk_tes.dts"

#
# Add kernel configuration (e.g. DRM/KMS support, I2C encoder slaves, CMA, ...)
#
SRC_URI:append = " file://${BPN}/5.4/config/tes_dnx_cdc.cfg"

#
# Add DNX register headers and DRM UAPI definitions for kernel side
#
FILESEXTRAPATHS:prepend := "${TES_SRC}:${TES_SRC}/driver/kernel/linux:"
ADDSOURCES = ""
ADDSOURCES:tesdavenx = " \
	file://interface \
	file://drm-dnx \
"

SRCREV_interface = "${AUTOREV}"
#SRCREV_FORMAT = "default_dnx-rinterface"
ADDSOURCES:tesdavenx:tesintern = "\
	${TES_SVN_PATH};module=interface;name=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
	${TES_SVN_PATH}/driver/kernel/linux;module=drm-dnx;name=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

SRC_URI:append = " ${ADDSOURCES}"

#
# Copy base device tree into kernel source
#
python do_copy() {
    if "tesdavenx" in d.getVar("OVERRIDES"):
        bb.note("copying DaveNX specific files")
        bb.build.exec_func("do_copy_dnx", d)

    if "cyclone5" in d.getVar("OVERRIDES"):
        bb.note("copying Cyclone5 specific files")
        bb.build.exec_func("do_copy_c5", d)

    if "arria10" in d.getVar("OVERRIDES"):
        bb.note("copying Arria10 specific files")
        bb.build.exec_func("do_copy_a10", d)

    if "stratix10" in d.getVar("OVERRIDES"):
        bb.note("copying Stratix10 specific files")
        bb.build.exec_func("do_copy_s10", d)
}
addtask copy after do_configure before do_compile

do_copy_dnx() {
	cp ${WORKDIR}/${BPN}/5.4/dts/*.dts ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/
	cp ${WORKDIR}/drm-dnx/dnx_drm.h ${STAGING_KERNEL_DIR}/include/uapi/drm/
	cp ${WORKDIR}/interface/src/*.h ${STAGING_KERNEL_DIR}/include/
}

do_copy_c5() {
	cp ${WORKDIR}/${BPN}/5.4/dts/*.dts ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/
}

do_copy_a10() {
	cp ${WORKDIR}/${BPN}/5.4/dts/*.dts ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/
	cp ${WORKDIR}/${BPN}/5.4/dts/*.dtsi ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/
}

do_copy_s10() {
	cp ${WORKDIR}/${BPN}/5.4/dts/*.dts ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/altera/
}

#
# Copy required header files into kernel staging directory (required for building module)
#
do_install:append:tesdavenx() {
	install -m 0644 ${WORKDIR}/interface/src/*.h ${STAGING_KERNEL_DIR}/include/
	install -m 0644 ${WORKDIR}/drm-dnx/dnx_drm.h ${STAGING_KERNEL_DIR}/include/uapi/drm/
}
