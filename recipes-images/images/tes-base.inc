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
"

#
# Add setuptools to enable manual U-Boot builds with the SDK
#
TOOLCHAIN_HOST_TASK += "nativesdk-python3-setuptools nativesdk-swig"
