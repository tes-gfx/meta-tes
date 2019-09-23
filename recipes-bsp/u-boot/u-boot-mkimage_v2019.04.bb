SUMMARY = "U-Boot bootloader environment image creation tool"                               

HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"                             
SECTION = "bootloaders"                                                         

DEPENDS = "openssl bison"

LICENSE = "GPLv2+"                                                              
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "389a37b3f0aa6fe3904384c766e4cb8b4a9081ec"


PV_append = "+git${SRCPV}"

UBOOT_BRANCH = "socfpga_v2019.04_dnx"
UBOOT_REPO_tesintern = "git:///home/christian/yocto/u-boot-socfpga"
UBOOT_PROT_tesintern = "file"
UBOOT_REPO = "git://github.com/c-thaler/u-boot-socfpga.git"
UBOOT_PROT = "https"

SRC_URI = "\
        ${UBOOT_REPO};protocol=${UBOOT_PROT};branch=${UBOOT_BRANCH} \
        "
SRC_URI += "file://0001-Fix-native-build-by-using-env-variables.patch"

S = "${WORKDIR}/git" 

EXTRA_OEMAKE = 'CROSS_COMPILE="${TARGET_PREFIX}" CC="${CC} ${CFLAGS} ${LDFLAGS}" STRIP=true V=1'

do_compile () {                                                                 
        oe_runmake sandbox_defconfig                                            
        oe_runmake tools NO_SDL=1                                         
} 

# Note that we need the DTC to parse ITS files.
do_install () {
        install -d ${D}${bindir}
        install -m 0755 tools/mkenvimage ${D}${bindir}/uboot-mkenvimage
        ln -sf uboot-mkenvimage ${D}${bindir}/mkenvimage
        install -m 0755 tools/mkimage ${D}${bindir}/uboot-mkimage
        ln -sf uboot-mkimage ${D}${bindir}/mkimage
        install -m 0755 scripts/dtc/dtc ${D}${bindir}/uboot-dtc
        ln -sf uboot-dtc ${D}${bindir}/dtc
}

RPROVIDES_${PN} += "mkimage" 
BBCLASSEXTEND = "native nativesdk"
