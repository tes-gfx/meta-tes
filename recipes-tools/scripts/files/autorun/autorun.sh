#!/bin/sh
echo Starting demos...
cd /home/root
cd autorun
export QML_USE_GLYPHCACHE_WORKAROUND=1
export QT_QPA_EGLFS_INTEGRATION="eglfs_dumb_kms"
./loop.sh
