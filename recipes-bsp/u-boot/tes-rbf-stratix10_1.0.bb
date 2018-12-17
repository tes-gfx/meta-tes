SUMMARY = "TES RBF for U-Boot (Stratix 10)"
DESCRIPTION = "This recipe generates an U-Boot image for the peripheral RBF and \
	install the core RBF to the boot folder."
SECTION = "bootloaders"
LICENSE = "CLOSED"
PR = "r0"


DEPENDS_${PN} = "u-boot-mkimage-native"


SRC_URI_append = " file://stratix10/socdk/socfpga_stratix10_socdk_tes.rbf"

S = "${WORKDIR}"


do_install () {
	install -d ${D}/boot
	install -m 0755 ${S}/${MACHINE}/socdk/socfpga_stratix10_socdk_tes.rbf ${D}/boot
}

inherit deploy
do_deploy () {
	install -m 0755 ${S}/${MACHINE}/socdk/socfpga_stratix10_socdk_tes.rbf ${DEPLOYDIR}
}
addtask deploy after do_install before do_build


FILES_${PN} = " \
	boot/*.rbf \
"
