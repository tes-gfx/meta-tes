require recipes-extended/images/core-image-full-cmdline.bb

COMPATIBLE_MACHINE = "[arria10]"


include tes-davenx-base.inc


#
# Use linux-altera-ltsi 4.14 kernel
#
PREFERRED_PROVIDER_virtual/kernel = "linux-altera-ltsi"
PREFERRED_VERSION_linux-altera-ltsi = "4.14%"


#
# only generate tar.gz rootfs images
#
IMAGE_FSTYPES = "tar.gz ext3"
