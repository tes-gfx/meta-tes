require u-boot-socfpga-common.inc

COMPATIBLE_MACHINE = "stratix10"

UBOOT_REPO = "git://github.com/tes-gfx/u-boot-socfpga.git"
UBOOT_REPO:tesintern = "git:////home/hh04074/projects/ip/linux_agilex/yocto_build/repos/u-boot-socfpga"
UBOOT_PROT:tesintern = "file"

UBOOT_VERSION = "v2022.04_stratix10"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

SRCREV = "efbe5744866b2c93488b5b7a1ed16915390fd52a"
