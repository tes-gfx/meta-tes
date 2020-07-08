require u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "da9bd86cdb7ef5e4957730d214e6f63d3c5170b1"

UBOOT_BRANCH = "socfpga_v2019.04_dnx"

UBOOT_CONFIG = "arria10-socdk dreamchip-arria10som"
UBOOT_CONFIG[arria10-socdk] = "socfpga_arria10_defconfig"
UBOOT_CONFIG[dreamchip-arria10som] = "dreamchip_arria10som_defconfig"

DEPENDS += "bc-native bison-native"
