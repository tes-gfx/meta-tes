SUMMARY = "TES start up board info script"
DESCRIPTION = "Outputs board info on login."
SECTION = "utils"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = "bash"

SRC_URI =  " \
    file://boardinfo.sh \
"

S = "${WORKDIR}"

do_install () {
	install -d ${D}${sysconfdir}/profile.d
	install -m 0755 boardinfo.sh ${D}${sysconfdir}/profile.d/boardinfo.sh
}

FILES_${PN} = " \
    ${sysconfdir}/profile.d/boardinfo.sh \
"
