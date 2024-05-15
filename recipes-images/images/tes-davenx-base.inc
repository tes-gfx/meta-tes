#
# Add all what we need for OpenGL apps based on DNX and CDC
#

require tes-base.inc

#
# Adding TES CDC KMS and DNX driver
#
#IMAGE_INSTALL += "cdc-mod dnx-mod"
IMAGE_INSTALL += "dnx-mod"
IMAGE_INSTALL += "googletest"
#MACHINE_EXTRA_RDEPENDS += "kernel-module-cdc"
MACHINE_EXTRA_RDEPENDS += "kernel-module-dnx"

#
# Set TES GLES2 and EGL implementation as preferred GLES2 and EGL providers
#
PREFERRED_PROVIDER_virtual/libgles2 = "davenx"
PREFERRED_PROVIDER_virtual/egl = "davenx"
