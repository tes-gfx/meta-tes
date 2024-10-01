SUMMARY = "Drivers and user space libraries for validation IP cores"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = " \
    generic-ip-mod \
    gman-mod \
    googletest \
"
