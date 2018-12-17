SUMMARY = "TES RBF for U-Boot (Arria 10)"
DESCRIPTION = "This recipe generates an U-Boot image for the peripheral RBF and \
	install the core RBF to the boot folder."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"


DEPENDS_${PN} = "u-boot-mkimage-native"


SRC_URI_append = " file://${MACHINE}/wiesmann/socfpga_arria10_wiesmann_tes.periph.rbf"
SRC_URI_append = " file://${MACHINE}/wiesmann/socfpga_arria10_wiesmann_tes.core.rbf"
SRC_URI_append = " file://${MACHINE}/wiesmann/bootmmc.scr"
SRC_URI_append = " file://${MACHINE}/wiesmann/bootnfs.scr"
SRC_URI_append = " file://${MACHINE}/wiesmann/bootnfs_flash_mmc.scr"
SRC_URI_append = " file://${MACHINE}/socdk/socfpga_arria10_socdk_tes.rbf"
SRC_URI_append = " file://${MACHINE}/socdk/bootmmc.scr"


S = "${WORKDIR}"


do_compile () {
	mkimage -A arm -T firmware -C none -O u-boot -a 0 -e 0 -n "RBF" -d ${S}/${MACHINE}/wiesmann/socfpga_arria10_wiesmann_tes.periph.rbf ${B}/socfpga_arria10_wiesmann_tes.periph.rbf.img
	mkimage -T script -C none -n "bootmmc" -d ${S}/${MACHINE}/wiesmann/bootmmc.scr ${B}/bootmmc.img
	mkimage -T script -C none -n "bootnfs" -d ${S}/${MACHINE}/wiesmann/bootnfs.scr ${B}/bootnfs.img
	mkimage -T script -C none -n "bootnfs_flash_mmc" -d ${S}/${MACHINE}/wiesmann/bootnfs_flash_mmc.scr ${B}/bootnfs_flash_mmc.img
	mkimage -T script -C none -n "bootmmc" -d ${S}/${MACHINE}/socdk/bootmmc.scr ${B}/bootmmc_socdk.img
}
do_compile[depends] += " u-boot-mkimage-native:do_populate_sysroot"

do_install () {
	install -d ${D}/boot
	install -m 0755 ${S}/${MACHINE}/wiesmann/socfpga_arria10_wiesmann_tes.core.rbf ${D}/boot
	install -m 0755 ${S}/${MACHINE}/wiesmann/boot*.scr ${D}/boot
	install -m 0755 ${B}/boot*.img ${D}/boot
}


inherit deploy
do_deploy() {
	install -m 0755 ${B}/*.img ${DEPLOYDIR}
	install -m 0755 ${S}/${MACHINE}/wiesmann/socfpga_arria10_wiesmann_tes.core.rbf ${DEPLOYDIR}
	install -m 0755 ${S}/${MACHINE}/socdk/socfpga_arria10_socdk_tes.rbf ${DEPLOYDIR}
}
addtask deploy after do_install before do_build


FILES_${PN} = " \
	boot/*.rbf \
	boot/boot*.scr \
	boot/boot*.img \
"
