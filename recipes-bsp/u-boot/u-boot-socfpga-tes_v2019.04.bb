require u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "7cddf77e087c916004b40b0eb4c7069edfc969fd"

UBOOT_BRANCH = "socfpga_v2019.04_dnx"

UBOOT_CONFIG = "arria10-socdk dreamchip-arria10som"
UBOOT_CONFIG[arria10-socdk] = "socfpga_arria10_defconfig"
UBOOT_CONFIG[dreamchip-arria10som] = "dreamchip_arria10som_defconfig"

DEPENDS += "bc-native bison-native u-boot-mkimage-native"
