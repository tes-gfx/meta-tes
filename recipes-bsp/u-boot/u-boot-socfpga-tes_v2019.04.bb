require u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

FILESEXTRAPATHS =. "${THISDIR}/files/v2019.04:"

SRCREV = "389a37b3f0aa6fe3904384c766e4cb8b4a9081ec"

UBOOT_BRANCH = "socfpga_v2019.04_dnx"

SRC_URI_append = "\
	file://${MACHINE}/socdk/socfpga_arria10_socdk_tes.periph.rbf \
    "

DEPENDS += "bc-native bison-native u-boot-mkimage-native"
