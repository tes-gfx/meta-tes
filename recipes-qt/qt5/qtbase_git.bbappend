# Remove no-opengl option
PACKAGECONFIG_GL = ""

PACKAGECONFIG += "examples gles2 eglfs kms accessibility fontconfig tslib sql-sqlite"

QT_CONFIG_FLAGS += "-qpa eglfs"
