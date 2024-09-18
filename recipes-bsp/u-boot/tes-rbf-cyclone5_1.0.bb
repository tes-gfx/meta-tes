SUMMARY = "TES RBF for U-Boot (Cyclone 5)"
DESCRIPTION = "This recipe copies the RBF files for DHD, D2D and CDC."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"

DEPENDS = "u-boot-tools-native dtc-native"

BB_STRICT_CHECKSUM = "ignore"

FILESEXTRAPATHS:prepend := "${YOCTOROOT}/repos/meta-tes/recipes-bsp/u-boot/files:"

SRC_URI:append = " \
	file://${MACHINE}/de10_nano/bootmmc.scr \
"

SRC_URI:append:tesintern:dave2d = " \
	http://build-linux/jenkins_artifacts/ip_cores/evalkit/de10nano/dave2d/terasic_de10_nano_rev_b.rbf;downloadfilename=socfpga_cyclone5_de10_nano_d2d.rbf \
"

SRC_URI:append:tesintern:davehd = " \
	http://build-linux/jenkins_artifacts/ip_cores/evalkit/de10nano/davehd/terasic_de10_nano_rev_b.rbf;downloadfilename=socfpga_cyclone5_de10_nano_dhd.rbf \
"

SRC_URI:append:tesintern:tescdc = " \
	http://build-linux/jenkins_artifacts/projects/stm_cdc_puma//hw/terasic_de10nano/r4386/terasic_de10_nano_rev_b.rbf;downloadfilename=socfpga_cyclone5_de10_nano_cdc.rbf \
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
	install -m 0755 ${S}/*.rbf ${DEPLOYDIR}
}
addtask deploy after do_install before do_build

FILES:${PN} = " \
	boot/*.rbf \
	boot/boot*.scr \
	boot/boot*.img \
"
