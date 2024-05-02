FILESEXTRAPATHS:prepend := "${THISDIR}:"

KERNEL_REPO = "git://github.com/tes-gfx/linux-socfpga.git"
KERNEL_REPO:tesintern = "git:///home/hh04074/projects/ip/linux_agilex/yocto_build/repos/linux-socfpga"
KERNEL_PROT:tesintern = "file"
#KERNEL_REPO = "git://github.com/ArrowElectronics/linux-socfpga.git"
KBRANCH = "socfpga-axe5-eagle-tes"

LINUX_VERSION = "6.1.38"
LINUX_VERSION_SUFFIX = "-lts"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRCREV = "adb270aeb8a3f276d7a901e4c4a51fb322d742d2"

KBUILD_DEFCONFIG = "socfpga_agilex5_axe5_eagle_defconfig"

# Add kernel config fragment for DNX
SRC_URI:append = " \
	file://${BPN}/6.1/config/tes_dnx_cdc.cfg \
"

ADDSOURCES = ""

SRCREV_FORMAT = "default_interface"
SRCREV_interface = "${AUTOREV}"
ADDSOURCES:tesdavenx:tesintern = "\
	${TES_SVN_PATH};module=interface;name=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
	${TES_SVN_PATH}/driver/kernel/linux;module=drm-dnx;name=interface;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD} \
"

SRC_URI:append = " ${ADDSOURCES}"

# Device tree handling
SRC_URI:append:agilex5 = " file://${BPN}/6.1/dts/socfpga_agilex5_axe5_eagle_dnx.dts"


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

    if "agilex5" in d.getVar("OVERRIDES"):
        bb.note("copying Agilex5 specific files")
        bb.build.exec_func("do_copy_agx5", d)
}
addtask copy after do_configure before do_compile

do_copy_dnx() {
	cp ${WORKDIR}/drm-dnx/dnx_drm.h ${STAGING_KERNEL_DIR}/include/uapi/drm/
	cp ${WORKDIR}/interface/src/*.h ${STAGING_KERNEL_DIR}/include/
}

do_copy_agx5() {
	cp ${WORKDIR}/${BPN}/6.1/dts/*.dts ${STAGING_KERNEL_DIR}/arch/${ARCH}/boot/dts/arrow/
}

#
# Copy required header files into kernel staging directory (required for building module)
#
do_install:append:tesdavenx() {
	install -m 0644 ${WORKDIR}/interface/src/*.h ${STAGING_KERNEL_DIR}/include/
	install -m 0644 ${WORKDIR}/drm-dnx/dnx_drm.h ${STAGING_KERNEL_DIR}/include/uapi/drm/
}
