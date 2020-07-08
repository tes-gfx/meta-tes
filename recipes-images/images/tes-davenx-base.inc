#
# Add all what we need for OpenGL apps based on DNX and CDC
#


#
# Adding TES CDC KMS and DNX driver
#
IMAGE_INSTALL += "cdc-mod dnx-mod"
IMAGE_INSTALL += "fsl-rc-local haveged"
IMAGE_INSTALL_append_arria10 = " tes-itb-arria10"
MACHINE_EXTRA_RDEPENDS += "kernel-module-cdc"


#
# Adding the kernel image and firmware files (Device tree)
#
IMAGE_INSTALL += "kernel kernel-devicetree"


#
# Set TES GLES2 and EGL implementation as preferred GLES2 and EGL providers
#
PREFERRED_PROVIDER_virtual/libgles2 = "davenx"
PREFERRED_PROVIDER_virtual/egl = "davenx"


#
# Add KMS library to image
#
CORE_IMAGE_EXTRA_INSTALL += " \
	libdrm \
	libdrm-kms \
"
