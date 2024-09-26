require recipes-extended/images/core-image-full-cmdline.bb

IMAGE_INSTALL += "haveged-service"
IMAGE_INSTALL:append:arria10 = " tes-itb-arria10"
IMAGE_INSTALL:append:stratix10 = " tes-u-boot-script-stratix10"

#
# Adding the kernel image and firmware files (Device tree)
#
IMAGE_INSTALL += "kernel kernel-devicetree"

#
# Add KMS library to image
#
CORE_IMAGE_EXTRA_INSTALL += " \
	libdrm \
	libdrm-kms \
"

#
# Use linux-altera-ltsi 5.4 kernel
#
PREFERRED_PROVIDER_virtual/kernel = "linux-altera-lts"
PREFERRED_VERSION_linux-altera-ltsi = "5.4%"

#
# Add googletest to image and SDK (required by some of our validation test)
#
IMAGE_INSTALL += "haveged-service googletest"
TOOLCHAIN_TARGET_TASK += "googletest-dev" 
