SUMMARY = "TES RBF for U-Boot (Cyclone 5 LCD)"
DESCRIPTION = "This recipe adds RBF and boot script."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"

DEPENDS = "u-boot-tools-native dtc-native"

FILESEXTRAPATHS_prepend := "${TES_D2D_SRC_PATH}/hardware/work/quartus/dave2/socfpga_cyclone5_de0_nano_tes:"

SRC_URI_append = " \
	file://socfpga_cyclone5_de0_nano_tes_lcd.rbf;subdir=${MACHINE}/de0_nano \
	file://${MACHINE}/de0_nano/bootmmc_lcd.scr \
"

S = "${WORKDIR}"

do_compile () {
	echo PWD = $(pwd)
	mkimage -T script -C none -n "bootmmc" -d ${S}/${MACHINE}/de0_nano/bootmmc_lcd.scr ${B}/bootmmc_de0.img
}
do_compile[depends] += " u-boot-mkimage-native:do_populate_sysroot"

do_install () {
	install -d ${D}/boot
	install -m 0755 ${B}/boot*.img ${D}/boot
}

inherit deploy
do_deploy() {
	install -m 0755 ${B}/*.img ${DEPLOYDIR}
	install -m 0755 ${S}/${MACHINE}/de0_nano/*.rbf ${DEPLOYDIR}/socfpga_cyclone5_de0_nano_tes.rbf
}
addtask deploy after do_install before do_build

FILES_${PN} = " \
	boot/*.rbf \
	boot/boot*.scr \
	boot/boot*.img \
"