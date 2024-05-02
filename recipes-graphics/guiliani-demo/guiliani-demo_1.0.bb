DESCRIPTION = "D/AVE 2D Guiliani-Demo"
HOMEPAGE = "http://www.tes-dst.com"
LICENSE = "CLOSED"
DEPENDS = "libdave2d libdrm"

inherit pkgconfig

FILESEXTRAPATHS:prepend := "${YOCTOROOT}/repos/meta-tes/recipes-graphics/guiliani-demo/files:"

PV = "1.0"
SRC_URI = " \
	file://guiliani_demos-2.3.tar.xz \
"

SRC_URI:tesintern = " \
	http://build-linux/jenkins_artifacts/ip_cores/dave2d/guiliani/guiliani_demos-2.3.tar.xz \
"

S = "${WORKDIR}"

do_install () {
  tar -xf ${YOCTOROOT}/repos/meta-tes/recipes-graphics/guiliani-demo/files/guiliani_demos-2.3.tar.xz -C ${S}
  install -d ${D}${datadir}/${PN}/CoffeeDemo
  install -m 0755 ${S}/CoffeeDemo/CoffeeDemo ${D}${datadir}/${PN}/CoffeeDemo
  install -m 0644 ${S}/CoffeeDemo/Resources.dat ${D}${datadir}/${PN}/CoffeeDemo
  install -d ${D}${datadir}/${PN}/GuilianiDemo
  install -m 0755 ${S}/GuilianiDemo/GuilianiDemo2.3 ${D}${datadir}/${PN}/GuilianiDemo
  install -m 0644 ${S}/GuilianiDemo/Resources2.3.dat ${D}${datadir}/${PN}/GuilianiDemo
  cp -R --no-dereference --preserve=mode,links -v ${S}/DashboardDemo ${D}${datadir}/${PN}/DashboardDemo
  install -m 0755 ${S}/DashboardDemo/DashboardDemo ${D}${datadir}/${PN}/DashboardDemo
}
