SUMMARY = "TES script for Qt demo autostart"
DESCRIPTION = "This recipe provides script that automatically starts selected demos. Demos can be toggled by F1 and F2 keys."
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"


RDEPENDS_${PN} = "bash kb-poll"


SRC_URI =  "file://autorun"


S = "${WORKDIR}"


do_install () {
	install -d ${D}/home/root/autorun
	install -m 0755 autorun/* ${D}/home/root/autorun/
	install -d ${D}${sysconfdir}/rc5.d
	ln -rsf ${D}/home/root/autorun/autorun.sh ${D}${sysconfdir}/rc5.d/S99tes_autorun
}


FILES_${PN} = " \
home/root/autorun \
${sysconfdir}/rc5.d/S99tes_autorun \
"
