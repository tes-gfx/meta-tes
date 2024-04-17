#!/bin/sh

# Enforce DUMB KMS as EGLFS device integration
export QT_QPA_EGLFS_INTEGRATION=eglfs_dumb_kms

# Use glyphcache workaround to prevent broken font textures
export QML_USE_GLYPHCACHE_WORKAROUND=1
