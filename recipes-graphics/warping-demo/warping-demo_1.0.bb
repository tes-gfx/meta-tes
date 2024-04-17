DESCRIPTION = "Warpingengine-Demo"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdave2d libdrm"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${YOCTOROOT}/repos/meta-tes/recipes-graphics/warping-demo/files:"

PV = "1.0"
SRC_URI = " \
	file://image_warper.tar.xz \
"

S = "${WORKDIR}"

do_install () {
  tar -xf ${YOCTOROOT}/repos/meta-tes/recipes-graphics/warping-demo/files/image_warper.tar.xz -C ${S}

  install -d ${D}${datadir}/${PN}/images
  install -d ${D}${datadir}/${PN}/warping_tables
  install -d ${D}${datadir}/${PN}/results

  install -m 0755 ${S}/image_warper ${D}${datadir}/${PN}/image_warper

  install -m 0644 ${S}/images/cam_1024x768.png ${D}${datadir}/${PN}/images/cam_1024x768.png

  install -m 0644 ${S}/warping_tables/table_1024x768_28_00.cmpscrd ${D}${datadir}/${PN}/warping_tables/table_1024x768_28_00.cmpscrd
  install -m 0644 ${S}/warping_tables/table_1024x768_28_01.cmpscrd ${D}${datadir}/${PN}/warping_tables/table_1024x768_28_01.cmpscrd
  install -m 0644 ${S}/warping_tables/table_1024x768_28_025.cmpscrd ${D}${datadir}/${PN}/warping_tables/table_1024x768_28_025.cmpscrd

  install -m 0644 ${S}/results/out_1024x768_28_00.png ${D}${datadir}/${PN}/results/out_1024x768_28_00.png
  install -m 0644 ${S}/results/out_1024x768_28_01.png ${D}${datadir}/${PN}/results/out_1024x768_28_01.png
  install -m 0644 ${S}/results/out_1024x768_28_025.png ${D}${datadir}/${PN}/results/out_1024x768_28_025.png
  install -m 0644 ${S}/results/logfile.txt ${D}${datadir}/${PN}/results/logfile.txt


#  # 800x600
#  install -m 0644 ${S}/images/rgb_800x600.bmp ${D}${datadir}/${PN}/images/rgb_800x600.bmp
#  install -m 0644 ${S}/warping_tables/table_800x600.cmpscrd ${D}${datadir}/${PN}/warping_tables/table_800x600.cmpscrd
#  # 1024x768
#  install -m 0644 ${S}/images/rgb_1024x768.bmp ${D}${datadir}/${PN}/images/rgb_1024x768.bmp
#  install -m 0644 ${S}/warping_tables/table_1024x768.cmpscrd ${D}${datadir}/${PN}/warping_tables/table_1024x768.cmpscrd
#  # 1600x1200
#  install -m 0644 ${S}/images/rgb_1600x1200.bmp ${D}${datadir}/${PN}/images/rgb_1600x1200.bmp
#  install -m 0644 ${S}/warping_tables/table_1600x1200.cmpscrd ${D}${datadir}/${PN}/warping_tables/table_1600x1200.cmpscrd
}
