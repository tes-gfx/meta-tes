SUMMARY = "TES RBF for U-Boot (Cyclone 5)"
DESCRIPTION = "This recipe copies the RBF files for DHD and D2D evalkit."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"

DEPENDS = "u-boot-tools-native dtc-native"

FILESEXTRAPATHS:prepend := "${YOCTOROOT}/repos/meta-tes/recipes-bsp/u-boot/files:"

SRC_URI:append = " \
	file://${MACHINE}/de10_nano/socfpga_cyclone5_de10_nano_d2d.rbf \
	file://${MACHINE}/de10_nano/socfpga_cyclone5_de10_nano_dhd.rbf \
	file://${MACHINE}/de10_nano/bootmmc.scr \
"

S = "${WORKDIR}"

do_compile () {
	echo PWD = $(pwd)
	mkimage -T script -C none -n "bootmmc" -d ${S}/${MACHINE}/de10_nano/bootmmc.scr ${B}/bootmmc_de10.img
}
do_compile[depends] += " u-boot-mkimage-native:do_populate_sysroot"

do_install () {
	install -d ${D}/boot
	install -m 0755 ${B}/boot*.img ${D}/boot
}

inherit deploy
do_deploy() {
	install -m 0755 ${B}/*.img ${DEPLOYDIR}
	install -m 0755 ${S}/${MACHINE}/de10_nano/*.rbf ${DEPLOYDIR}
}
addtask deploy after do_install before do_build

FILES:${PN} = " \
	boot/*.rbf \
	boot/boot*.scr \
	boot/boot*.img \
"
