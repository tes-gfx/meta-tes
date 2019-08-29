inherit meta


SLIMSDK_RDEPENDS = "${SLIMSDK_INSTALL}"
SLIMSDK_DEPENDS = "virtual/fakeroot-native xz-native"

SLIMSDK_DIR = "${WORKDIR}/slimsdk"
SLIMSDK_OUTPUT = "${SLIMSDK_DIR}/image"
SLIMSDK_DEPLOY = "${DEPLOY_DIR}/sdk"

SLIMSDK_OUTPUTNAME ?= "${DISTRO}-${IMAGE_BASENAME}-${TUNE_PKGARCH}-slimsdk-${SDK_VERSION}"

#
# Select the complementary package types that will be added additionally to
# the installed packages.
#
SLIMSDK_COMPLEMENTARY_GLOBS = "*-dev *-staticdev"

SLIMSDK_TARGET_MANIFEST = "${SLIMSDK_DEPLOY}/${SLIMSDK_OUTPUTNAME}.target.manifest"


def list_slimsdk_packages (d):
    from oe.package_manager import RpmPkgsList
    sdk_output = d.getVar('SLIMSDK_OUTPUT', True)
    target_path = d.getVar('SDKTARGETSYSROOT', True).strip('/')
    rootfs_dir = os.path.join(sdk_output, target_path)

    return RpmPkgsList(d, rootfs_dir).list_pkgs()


def write_target_slimsdk_manifest (d):
    from oe.sdk import sdk_list_installed_packages
    from oe.utils import format_pkg_list
    sdkmanifestdir = os.path.dirname(d.getVar("SLIMSDK_TARGET_MANIFEST", True))
    pkgs = list_slimsdk_packages(d)
    if not os.path.exists(sdkmanifestdir):
        bb.utils.mkdirhier(sdkmanifestdir)
    with open(d.getVar('SLIMSDK_TARGET_MANIFEST', True), 'w') as output:
        output.write(format_pkg_list(pkgs, 'ver'))


fakeroot python do_populate_slimsdk() {
    from oe.package_manager import *
    from oe.manifest import *
    from oe.utils import execute_pre_post_process

    img_type = d.getVar('IMAGE_PKGTYPE', True)
    if img_type != "rpm":
        bb.fatal("Only RPM is supported currently.")

    # Remove all packages except for the SlimSDK ones
    ld = bb.data.createCopy(d)
    ld.setVar("PACKAGE_INSTALL", "")
    ld.setVar("TOOLCHAIN_HOST_TASK", "")
    ld.setVar("TOOLCHAIN_TARGET_TASK", d.getVar("SLIMSDK_INSTALL", True))

    create_manifest(ld, manifest_dir=d.getVar('SLIMSDK_DIR', True),
                    manifest_type=Manifest.MANIFEST_TYPE_SDK_TARGET)

    manifest_dir = d.getVar("SLIMSDK_DIR", True)
    target_manifest = RpmManifest(d, manifest_dir,
                                  Manifest.MANIFEST_TYPE_SDK_TARGET)

    target_providename = ['/bin/sh',
                          '/bin/bash',
                          '/usr/bin/env',
                          '/usr/bin/perl',
                          'pkgconfig'
                          ]

    sdk_output = d.getVar('SLIMSDK_OUTPUT', True)
    target_path = d.getVar('SDKTARGETSYSROOT', True).strip('/')
    sdk_target_sysroot = os.path.join(sdk_output, target_path)
    target_pm = RpmPM(d,
                      sdk_target_sysroot,
                      d.getVar('TARGET_VENDOR', True),
                      'target',
                      target_providename
                      )

    mkdirhier(d.getVar("SLIMSDK_OUTPUT", True))

    populate_sysroot(d, target_pm, target_manifest)

    execute_pre_post_process(d, "tar_slimsdk")
}


def populate_sysroot(d, pm, manifest):
    pkgs_to_install = manifest.parse_initial_manifest()
    
    pm.create_configs()
    pm.write_index()
    pm.dump_all_available_pkgs()
    pm.update()
    
    pkgs = []
    pkgs_attempt = []
    for pkg_type in pkgs_to_install:
        if pkg_type == "PACKAGE_INSTALL_ATTEMPTONLY":
            pkgs_attempt += pkgs_to_install[pkg_type]
        else:
            pkgs += pkgs_to_install[pkg_type]
    
    pm.install(pkgs)    
    pm.install(pkgs_attempt, True)

    pm.install_complementary(d.getVar("SLIMSDK_COMPLEMENTARY_GLOBS", True))

    write_target_slimsdk_manifest(d)

    pm.remove_packaging_data()


def mkdirhier(dirpath):
        try:
            bb.utils.mkdirhier(dirpath)
        except OSError as e:
            bb.debug(1, "printing the stack trace\n %s" %traceback.format_exc())
            bb.fatal("cannot make dir for SDK: %s" % dirpath)


SLIMSDK_TAROPTS = "--owner=root --group=root"

fakeroot tar_slimsdk() {
	# Package it up
	mkdir -p ${SLIMSDK_DEPLOY}
	cd ${SLIMSDK_OUTPUT}/${SDKPATH}
	tar ${SLIMSDK_TAROPTS} -cf - . | xz -T > ${SLIMSDK_DEPLOY}/${SLIMSDK_OUTPUTNAME}.tar.xz
}


do_populate_slimsdk[dirs] = "${PKGDATA_DIR} ${TOPDIR}"
do_populate_slimsdk[depends] += "${@' '.join([x + ':do_populate_sysroot' for x in d.getVar('SLIMSDK_DEPENDS', True).split()])}  ${@d.getVarFlag('do_rootfs', 'depends', False)}"
do_populate_slimsdk[rdepends] = "${@' '.join([x + ':do_populate_sysroot' for x in d.getVar('SLIMSDK_RDEPENDS', True).split()])}"
do_populate_slimsdk[recrdeptask] += "do_packagedata do_package_write_rpm do_package_write_ipk do_package_write_deb"
addtask populate_slimsdk
