DESCRIPTION = "An image for easy evaluation of the TES IP Cores, including a full command line and handy development tools."

include tes-base.inc

IMAGE_INSTALL_append_cyclone5 = " tes-itb-cyclone5"

IMAGE_INSTALL += " pkgconfig gdbserver openssh-sftp-server gdb"

#
# Add very handy development tools
#
IMAGE_INSTALL += " \
	valgrind \
	devmem2 \
	i2c-tools \
	e2fsprogs-resize2fs \
	sshfs-fuse \
	rsync \
"

#
# Add KMS library test to image (modetest, ...)
#
CORE_IMAGE_EXTRA_INSTALL += " \
	libdrm-tests \
"

#
# Add debugging and developer utilities
#
EXTRA_IMAGE_FEATURES += "debug-tweaks"
EXTRA_IMAGE_FEATURES_tesdebug += "tools-profile dbg-pkgs"
PACKAGE_DEBUG_SPLIT_STYLE_tesdebug = "debug-file-directory"

#
# Inhibit striping (to enable readable profiling tool outputs)
#
INHIBIT_PACKAGE_STRIP_tesdebug = "1"

#
# Add smart package manager
#
#IMAGE_FEATURES += "package-management"

#
# Set EXT3 partition to 2GB == 2097152kB
#
IMAGE_ROOTFS_SIZE = "2097152"

export IMAGE_BASENAME="tes-ip-evalkit-image"


###############################################################################
# SDK section
#


#
# Add Linux Kernel sources to SDK
#
TOOLCHAIN_TARGET_TASK += "kernel-devsrc libdrm-dev"
TOOLCHAIN_HOST_TASK += "nativesdk-kernel-devsrc-env"

