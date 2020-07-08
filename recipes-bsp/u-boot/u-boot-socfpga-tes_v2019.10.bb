require u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"

SRCREV = "13d074c84aa9ee81f458e8f68766fbcdfba7962f"

UBOOT_BRANCH = "socfpga_v2019.10_dnx"

UBOOT_CONFIG = "stratix10-socdk"
UBOOT_CONFIG[stratix10-socdk] = "socfpga_stratix10_defconfig"

DEPENDS += "bc-native bison-native"
