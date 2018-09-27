SUMMARY = "TES script for flashing mmc"
DESCRIPTION = "This recipe provides a script that flashes the root filesyste to the mmc."
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"


DEPENDS_${PN} = "fsl-rc-local"
RDEPENDS_${PN} = "bash"


SRC_URI =  "file://flash_mmc/flash_mmc.sh"


S = "${WORKDIR}"


do_install () {
	install -d ${D}${sysconfdir}/rc.local.d
	install -m 0755 flash_mmc/flash_mmc.sh ${D}${sysconfdir}/rc.local.d/flash_mmc.sh
}


FILES_${PN} = " \
${sysconfdir}/rc.local.d/flash_mmc.sh \
"
