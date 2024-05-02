# Copy DTS files to kernel sources.
do_install:append() {
    cd ${S}
    if [ "${ARCH}" = "arm" ]; then
        cp -a --parents arch/arm/boot/dts $kerneldir/build/
        chown -R root:root $kerneldir/build/arch/arm/boot
    fi;
    if [ "${ARCH}" = "arm64" ]; then
        cp -a --parents arch/arm64/boot/dts $kerneldir/build/
        chown -R root:root $kerneldir/build/arch/arm64/boot
    fi;
}
