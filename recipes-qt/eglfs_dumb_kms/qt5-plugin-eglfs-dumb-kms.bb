DESCRIPTION = "This plugin uses EGL on top of KMS for display output."
SUMMARY = "TES Qt EGL DUMB KMS plugin"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a45afd377d5adcb4b6b7e8f435c438c8"


PV = "1.0"
PR = "r0"

DEPENDS = "qtbase libdrm"

FILESEXTRAPATHS:prepend := "${TES_SRC}:"
SRC_URI = "\
	file://qt.sh \
	file://qt/eglfs_dumb_kms \
	file://tools/kms_helper \
"

SRCREV_FORMAT = "plugin_helper"
SRCREV_plugin = "${AUTOREV}"
SRCREV_helper = "${AUTOREV}"

SRC_URI:tesintern = "\
	file://qt.sh \
	${TES_SVN_PATH}/qt;module=eglfs_dumb_kms;path_spec=./qt/eglfs_dumb_kms;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=plugin \
	${TES_SVN_PATH}/tools;module=kms_helper;path_spec=./tools/kms_helper;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD};name=helper \
"

S = "${WORKDIR}/qt/eglfs_dumb_kms"

inherit qmake5

#
# Add script to set dumb KMS plugin as default eglfs device integration
#
do_install_append() {
	install -d ${D}${sysconfdir}/profile.d/
	install -m 0755 ${WORKDIR}/qt.sh ${D}${sysconfdir}/profile.d/
}


FILES_${PN} += "\
	${OE_QMAKE_PATH_PLUGINS}/egldeviceintegrations/libqeglfsdumbkms.so \
	qt.sh \
"
FILES_${PN}-dbg += "${OE_QMAKE_PATH_PLUGINS}/egldeviceintegrations/.debug/"
FILES_${PN}-dev += "${OE_QMAKE_PATH_LIBS}/cmake/*"
