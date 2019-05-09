DESCRIPTION = "Adds a script to export KERNEL_SRC variable when sourcing the environment in the SDK."
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
SECTION = "sdk"
#DEPENDS = "kernel-devsrc"
PACKAGES = "${PN}"

inherit nativesdk

LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
	file://environment.d-kernel-devsrc.sh \
"

do_install() {
    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d
    install -m 644 ${WORKDIR}/environment.d-kernel-devsrc.sh ${D}${SDKPATHNATIVE}/environment-setup.d/kernel-devsrc.sh
}

FILES_${PN} = "${SDKPATHNATIVE}"
