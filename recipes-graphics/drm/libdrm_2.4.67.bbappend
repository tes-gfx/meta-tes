FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI_append = " file://files/libdrm-2.4.67.patch"
CFLAGS += "-DCOMPILE_LIBDRM"

#
# We do not want the drivers in our image.
#
EXTRA_OECONF_remove = "--enable-omap-experimental-api"
EXTRA_OECONF += "\
	--disable-intel \
	--disable-radeon \
	--disable-amdgpu \
	--disable-nouveau \
	--disable-vmwgfx \
	--disable-freedreno \
	--disable-vc4 \
"
