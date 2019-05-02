SUMMARY = "TES RBF for U-Boot (Stratix 10)"
DESCRIPTION = "This recipe generates an U-Boot image for the peripheral RBF and \
	install the core RBF to the boot folder."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"


DEPENDS_${PN} = "u-boot-mkimage-native"


SRC_URI_append = " file://${MACHINE}/socdk/bootmmc.scr"

S = "${WORKDIR}"

do_compile () {
	mkimage -T script -C none -n "bootmmc" -d ${S}/${MACHINE}/socdk/bootmmc.scr ${B}/bootmmc_socdk.img
}
do_compile[depends] += " u-boot-mkimage-native:do_populate_sysroot"

do_install () {
	install -d ${D}/boot
	install -m 0755 ${B}/boot*.img ${D}/boot
}

inherit deploy
do_deploy () {
	install -m 0755 ${B}/boot*.img ${DEPLOYDIR}
}
addtask deploy after do_install before do_build


FILES_${PN} = " \
	boot/*.rbf \
	boot/boot*.scr \
	boot/boot*.img \
"
