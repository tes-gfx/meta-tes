#
# Add all Qt stuff we want
#


#
# Qt5 configuration
# We're also trying to get rid of the XCB plugin...
#
IMAGE_INSTALL += " \
    qtbase \
    qtdeclarative \
    qtdeclarative-dev \
    qtdeclarative-tools \
    qtquickcontrols \
    qtquickcontrols2 \
    qtgraphicaleffects \
    qtlocation \
"
# The plugin is once again broken. Comment out for now.
#    qt5-plugin-eglfs-dumb-kms 

#
# Add Qt5 demos (skip them for now, they're triggering lots of errors)
#
#IMAGE_INSTALL += " \
#	qtbase-examples \
#	qtdeclarative-examples \
#	qtquickcontrols-examples \
#	qtquickcontrols2-examples \
#    	qt5ledscreen \
#       qt5nmapper \
#       qt5nmapcarousedemo \
#       qt5-opengles2-test \
#       cinematicexperience \
#       quitbattery \
#       quitindicators \
#       qtsmarthome \
#"
IMAGE_INSTALL += " \
    qt5ledscreen \
    qt5nmapcarousedemo \
"

#
# Add some fonts for Qt
#
IMAGE_INSTALL += "\
    ttf-roboto \
    ttf-inconsolata \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
    ttf-dejavu-mathtexgyre \
    ttf-dejavu-common \
    ttf-liberation-mono \
    ttf-liberation-sans \
    ttf-liberation-serif \
"

#
# Add Qt demos and their sources
#
IMAGE_INSTALL += " \
    exhibdemo \
"

TOOLCHAIN_TARGET_TASK += " \
    exhibdemo-devsrc \
"

inherit populate_sdk_qt5
