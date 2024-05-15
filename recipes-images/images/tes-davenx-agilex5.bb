DESCRIPTION = "An image for easy evaluation of the TES DaveNX IP Core and the TES OpenGL ES 2.0 implementation, including a full command line, Qt5 (Debug) and handy development tools."

require tes-davenx-base.inc

#
# Qt dependencies
# Packages that enable simple and easy development of Qt
# applications: automatic download to target, debugging on
# target, ...
#
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
# Add DNX kernel module
#
#IMAGE_INSTALL += "dnx-mod"

#
# Add the TES tutorials and tests
#
#	dnx-integration-test
IMAGE_INSTALL += " \
	hellogl \
	egles-test \
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
EXTRA_IMAGE_FEATURES:tesdebug += "tools-profile dbg-pkgs"
PACKAGE_DEBUG_SPLIT_STYLE:tesdebug = "debug-file-directory"


#
# Inhibit striping (to enable readable profiling tool outputs)
#
INHIBIT_PACKAGE_STRIP:tesdebug = "1"


#
# Add smart package manager
#
IMAGE_FEATURES += "package-management"

#
# Set EXT3 partition to 2GB == 2097152kB
#
IMAGE_ROOTFS_SIZE = "2097152"

export IMAGE_BASENAME="tes-davenx-evalkit-image"

WKS_FILE_agilex5 = "sdimage-tes-arrow-axe5-eagle.wks"

###############################################################################
# SDK section
#


#
# Add Linux Kernel sources to SDK
#
TOOLCHAIN_TARGET_TASK += "kernel-devsrc libdrm-dev"
TOOLCHAIN_HOST_TASK += "nativesdk-kernel-devsrc-env"
