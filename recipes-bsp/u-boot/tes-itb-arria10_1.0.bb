SUMMARY = "TES ITB for U-Boot (Arria 10)"
DESCRIPTION = "This recipe generates an U-Boot ITB image for the peripheral RBF."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"

COMPATIBLE_MACHINE = "(arria10)"

DEPENDS = "u-boot-tools-native dtc-native"

BB_STRICT_CHECKSUM = "ignore"

FILESEXTRAPATHS:prepend := "${TES_BIN}/u-boot:"

SRC_URI:append = " file://${MACHINE}/dreamchip_arria10som/fit_spl_fpga.its"

SRC_URI:append:tesintern:tesdavenx = " \
	http://build-linux/jenkins_artifacts/ip_cores/evalkit/dreamchip_arria10som/davenx/dreamchip_arria10som_tes_2x16.core.rbf;subdir=arria10/dreamchip_arria10som \
	http://build-linux/jenkins_artifacts/ip_cores/evalkit/dreamchip_arria10som/davenx/dreamchip_arria10som_tes_2x16.periph.rbf;subdir=arria10/dreamchip_arria10som \
"

S = "${WORKDIR}"


do_compile () {
	echo PWD = $(pwd)
	cp ${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes_2x16.core.rbf   ${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes.core.rbf
	cp ${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes_2x16.periph.rbf ${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes.periph.rbf
	mkimage -E -p 400 -f ${MACHINE}/dreamchip_arria10som/fit_spl_fpga.its fit_spl_fpga.itb
}
do_compile[depends] += " u-boot-mkimage-native:do_populate_sysroot"

inherit deploy
do_deploy() {
	install -m 0755 ${B}/*.itb ${DEPLOYDIR}
	install -m 0755 ${S}/${MACHINE}/dreamchip_arria10som/dreamchip_arria10som_tes.core.rbf ${DEPLOYDIR}
}
addtask deploy after do_install before do_build


FILES:${PN} = " \
	boot/*.rbf \
"

