SUMMARY = "TES U-Boot script (Stratix 10)"
DESCRIPTION = "This recipe generates an U-Boot image for the TES boot script \
	an moves it to /boot."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"


DEPENDS = "u-boot-tools-native"


SRC_URI:append = " file://${MACHINE}/socdk/bootmmc.scr"

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
	boot/boot*.scr \
	boot/boot*.img \
"
