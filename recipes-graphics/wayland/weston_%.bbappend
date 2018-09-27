FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += "gbm"

PACKAGECONFIG = "kms fbdev clients"

PACKAGECONFIG[kms] = "--enable-drm-compositor,--disable-drm-compositor,drm udev davenx mtdev"
