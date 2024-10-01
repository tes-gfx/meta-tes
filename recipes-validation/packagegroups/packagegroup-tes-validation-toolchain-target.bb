SUMMARY = "Development resources for user space libraries for validation IP cores"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS:${PN} = " \
    libvd-dev \
    libvd-staticdev \
    libabc-dev \
    libabc-staticdev \
    googletest-dev \
"
