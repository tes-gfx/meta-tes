DESCRIPTION = "TKScript"
HOMEPAGE = "http://tkscript.de/"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "virtual/libgles2 virtual/egl zlib libpng gnutls"

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = " \
	file://tools/tks \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/tools;module=tks;subdir=tools;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/tks-bin-aarch64-poky-linux-stratix10-02Sep2020"
B = "${WORKDIR}/tks-bin-aarch64-poky-linux-stratix10-02Sep2020"

do_unpack_tarball () {
  cd ${WORKDIR}
  tar xf ./tools/tks/tks-bin-aarch64-poky-linux-stratix10-02Sep2020.tar.gz
}
do_unpack[postfuncs] += "do_unpack_tarball"

do_install () {
  install -d ${D}
  cp -r * ${D}
}

FILES_${PN} = "${prefix}/*"

INSANE_SKIP_${PN} = "already-stripped"
