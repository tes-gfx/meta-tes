DESCRIPTION = "An image for easy evaluation of the TES IP Cores, including a full command line and handy development tools."

include tes-base.inc

IMAGE_INSTALL:append:cyclone5 = " tes-rbf-cyclone5"

# Add TES kernel modules
IMAGE_INSTALL += "generic-ip-mod gman-mod libdrm-gman"
MACHINE_EXTRA_RDEPENDS += "kernel-module-genip kernel-module-gman"

#
# Add very handy development tools
#
IMAGE_INSTALL += " pkgconfig gdbserver openssh-sftp-server gdb"
IMAGE_INSTALL += " \
	valgrind \
	devmem2 \
	i2c-tools \
	e2fsprogs-resize2fs \
	sshfs-fuse \
	rsync \
"

#
# Add Dave2D stuff
#
IMAGE_INSTALL:append:dave2d = " \
        dave2d-demo \
        dave2d-drivertest \
        dave2d-smartwatch-demo \
        dave2d-tutorial-di \
"

#
# Add DaveHD stuff
#
#
#    fbd-demo \
#    cdc-tutorial \
#
IMAGE_INSTALL:append:davehd = " \
    davehd-openvg-demos \
    davehd-drivertest \
    davehd-tutorial \
    davehd-performance \
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
EXTRA_IMAGE_FEATURES:append:tesdebug = " tools-profile dbg-pkgs"
PACKAGE_DEBUG_SPLIT_STYLE:tesdebug = "debug-file-directory"

#
# Inhibit striping (to enable readable profiling tool outputs)
#
INHIBIT_PACKAGE_STRIP:tesdebug = "1"

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
TOOLCHAIN_TARGET_TASK += " \
	kernel-devsrc \
	libdrm-dev \
	libdrm-gman-dev \
	libfbd-dev \
	libfbd-staticdev \
	libcdc-dev \
	libcdc-staticdev \
	libdave2d-dev \
	libdave2d-l1-dev \
	libdave2d-l1-staticdev \
	libdavehd-dev \
	libdavehd-staticdev \
	libdi-dev \
	libdi-staticdev \
	davehd-openvg-dev \
	davehd-openvg-staticdev \
"

TOOLCHAIN_TARGET_TASK:append:dave2d = "\
	dave2d-smartwatch-demo-devsrc \
	dave2d-demo-devsrc \
	dave2d-tutorial-di-devsrc \
"

TOOLCHAIN_TARGET_TASK:append:davehd = "\
        davehd-cdc-tutorial-devsrc \
        davehd-drivertest-devsrc \
"

TOOLCHAIN_HOST_TASK += "nativesdk-kernel-devsrc-env"

WKS_FILE = "sdimage-tes-cyclone5-de10-nano.wks"
