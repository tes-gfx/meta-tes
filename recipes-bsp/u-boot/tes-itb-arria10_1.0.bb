SUMMARY = "TES ITB for U-Boot (Arria 10)"
DESCRIPTION = "This recipe generates an U-Boot ITB image for the peripheral RBF."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"


DEPENDS = "u-boot-tools-native dtc-native"

FILESEXTRAPATHS:prepend := "${TES_BIN}/u-boot:"

SRC_URI:append = " file://${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes.core.rbf"
SRC_URI:append = " file://${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes.periph.rbf"
SRC_URI:append = " file://${MACHINE}/dreamchip_arria10som/bootmmc_arria10som.scr"
SRC_URI:append = " file://${MACHINE}/dreamchip_arria10som/fit_spl_arria10som.its"


S = "${WORKDIR}"


do_compile () {
	echo PWD = $(pwd)
	mkimage -E -p 400 -f ${MACHINE}/dreamchip_arria10som/fit_spl_arria10som.its fit_spl_arria10som.itb
	mkimage -T script -C none -n "bootmmc" -d ${S}/${MACHINE}/dreamchip_arria10som/bootmmc_arria10som.scr ${B}/bootmmc_arria10som.img
}
do_compile[depends] += " u-boot-mkimage-native:do_populate_sysroot"

do_install () {
	install -d ${D}/boot
	install -m 0755 ${S}/${MACHINE}/dreamchip_arria10som/boot*.scr ${D}/boot
	install -m 0755 ${B}/boot*.img ${D}/boot
}


inherit deploy
do_deploy() {
	install -m 0755 ${B}/*.img ${DEPLOYDIR}
	install -m 0755 ${B}/*.itb ${DEPLOYDIR}
	install -m 0755 ${S}/${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes.core.rbf ${DEPLOYDIR}
}
addtask deploy after do_install before do_build


FILES:${PN} = " \
	boot/*.rbf \
	boot/boot*.scr \
	boot/boot*.img \
"
