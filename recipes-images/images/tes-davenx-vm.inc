#
# Add the VM file to the deploy dir
#

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://vm/tes_dnx_arria_10_som.ova"


addtask fetch
addtask unpack after do_fetch


python () {
    # only create the VM for our internal release process
    overrides = d.getVar("OVERRIDES", True)

    if 'tesrelease' in overrides:
        bb.note('Building VM for TES internal release process.')
        d.delVarFlag('do_fetch', 'noexec')
        d.delVarFlag('do_unpack', 'noexec')
    else:
        d.setVarFlags('do_unpack_vm', {'noexec': 1})
        d.setVarFlags('do_patch_vm', {'noexec': 1})
}


do_unpack_vm () {
	tar xf ${WORKDIR}/vm/tes_dnx_arria_10_som.ova -C ${WORKDIR}/vm
}
addtask unpack_vm after do_unpack before do_patch_vm


create_vm_ova () {
	cp ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.vmdk ${WORKDIR}/vm/tes_dnx_arria_10_som-disk001.vmdk
	cd ${WORKDIR}/vm/
	tar cf ${B}/${IMAGE_NAME}.ova *.vmdk *.ovf
	cp ${B}/${IMAGE_NAME}.ova ${IMGDEPLOYDIR} 
}


fakeroot python do_patch_vm () {
    type = 'ova'
    deploy_dir = d.getVar('IMGDEPLOYDIR', True)
    img_name = d.getVar('IMAGE_NAME', True)
    link_name = d.getVar('IMAGE_LINK_NAME', True)
    overrides = d.getVar("OVERRIDES", True)

    bb.build.exec_func('create_vm_ova', d)

    """ Create a symlink and delete old file """
    dst = os.path.join(deploy_dir, link_name + "." + type)
    src = img_name + "." + type
    if os.path.exists(os.path.join(deploy_dir, src)):
        bb.note("Creating symlink: %s -> %s" % (dst, src))
        if os.path.islink(dst):
            os.remove(dst)
        os.symlink(src, dst)
    else:
        bb.note("Skipping symlink, source does not exist: %s -> %s" % (dst, src))
}
addtask patch_vm after do_image_ext3_vmdk before do_image_complete 
