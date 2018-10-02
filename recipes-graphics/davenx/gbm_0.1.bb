DESCRIPTION = "GBM implementation for DaveNX and CDC"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "MIT"
DEPENDS = "davenx"

LIC_FILES_CHKSUM = "\
        file://../gbm/main/gbm.c;endline=26;md5=b871c7f2f477df29ee4c0ec437b187f7 \
"

FILESEXTRAPATHS_prepend := "${TES_SRC}:"
SRC_URI = "\
	file://gbm \
"

SRCREV = "${AUTOREV}"
SRC_URI_tesintern = "\
	${TES_SVN_PATH}/driver/kernel/linux;module=gbm;protocol=https;user=${TES_SVN_USER};pswd=${TES_SVN_PASSWORD}; \
"

S = "${WORKDIR}/gbm"

inherit pkgconfig

do_install () {
        install -m 0755 -d ${D}${libdir}
        oe_soinstall ${S}/libgbm.so.*.* ${D}${libdir}

        install -m 0755 -d ${D}${libdir}/pkgconfig
        install -m 0644 ${S}/main/gbm.pc ${D}${libdir}/pkgconfig/

        install -m 0755 -d ${D}${includedir}
        install -m 0644 ${S}/main/gbm.h ${D}${includedir}
}
