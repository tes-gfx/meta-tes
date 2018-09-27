# additionally generate a vmdk from the ext3 image

IMAGE_FSTYPES_append = " ext3"


create_ext3_vmdk_image () {
	type="vmdk"
	qemu-img convert -O $type -o subformat=streamOptimized ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.ext3 ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.$type
}


fakeroot python do_image_ext3_vmdk() {
    type = 'vmdk'
    deploy_dir = d.getVar('IMGDEPLOYDIR', True)
    img_name = d.getVar('IMAGE_NAME', True)
    imgsuffix = d.getVar('IMAGE_NAME_SUFFIX', True)
    link_name = d.getVar('IMAGE_LINK_NAME', True)

    bb.build.exec_func('create_ext3_vmdk_image', d)

    if not link_name:
        return

    """ Create a symlink and delete old file """
    dst = os.path.join(deploy_dir, link_name + "." + type)
    src = img_name + imgsuffix + "." + type
    if os.path.exists(os.path.join(deploy_dir, src)):
        bb.note("Creating symlink: %s -> %s" % (dst, src))
        if os.path.islink(dst):
            os.remove(dst)
        os.symlink(src, dst)
    else:
        bb.note("Skipping symlink, source does not exist: %s -> %s" % (dst, src))
}
do_image_ext3_vmdk[depends] += "qemu-native:do_populate_sysroot"
do_image_ext3_vmdk[depends] += "${PN}:do_image_ext3"
do_image_ext3_vmdk[vardepsexclude] += "DATETIME"


addtask image_ext3_vmdk after do_image_ext3 before do_image_complete
