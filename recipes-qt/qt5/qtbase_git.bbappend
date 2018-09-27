# Build a Qt debug release
PACKAGECONFIG_RELEASE = "release"
PACKAGECONFIG_RELEASE_tesintern = ""

# Remove system jpeg due to errors in chromium compilation
PACKAGECONFIG_SYSTEM = "eglfs libpng zlib gles2 tslib jpeg examples sql-sqlite"

# Add accessibility for QtQuickControls and linuxfb and fontconfig
PACKAGECONFIG += " linuxfb accessibility fontconfig"

QT_CONFIG_FLAGS += "-qpa eglfs"
