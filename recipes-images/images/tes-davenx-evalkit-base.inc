require recipes-extended/images/core-image-full-cmdline.bb

COMPATIBLE_MACHINE = "[arria10]"


include tes-davenx-base.inc
#include tes-qt-base.inc


#
# Use linux-altera-ltsi 5.4 kernel
#
PREFERRED_PROVIDER_virtual/kernel = "linux-altera-lts"
PREFERRED_VERSION_linux-altera-ltsi = "5.4%"

