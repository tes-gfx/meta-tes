DESCRIPTION = "Kernel module for the Intel FPGA PLL IP."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"

inherit module

PV = "1.0+gitr${SRCPV}"

SRCREV = "a79a6430f44ec22ffafa0a4619facf276090782b"
SRC_URI = "\
	git://github.com/tes-gfx/intel-fpga-pll.git;protocol=https;branch=4.14.130-ltsi \
"

S = "${WORKDIR}/git"

KERNEL_MODULE_AUTOLOAD += "intel-pll"
