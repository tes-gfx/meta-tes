#
# Add all Qt stuff we want
#


#
# QT5 configuration
# We're also trying to get rid of the XCB plugin...
#
IMAGE_INSTALL += " \
	qtbase \
	qtbase-examples \
	qt5-plugin-eglfs-dumb-kms \
	qtdeclarative \
	qtdeclarative-dev \
	qtdeclarative-tools \
    	qt5ledscreen \
	qtquickcontrols \
	qtquickcontrols2 \
	qtgraphicaleffects \
	qtlocation \
"

#
# Add the TES Qt demo
#
#IMAGE_INSTALL += " \
#	exhibdemo \
#"

inherit populate_sdk_qt5
