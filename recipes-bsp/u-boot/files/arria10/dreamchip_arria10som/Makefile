.PHONY:
all : fit_spl_fpga.itb

fit_spl_fpga.itb : fit_spl_fpga.its dreamchip_arria10som_tes.core.rbf dreamchip_arria10som_tes.periph.rbf
	mkimage -E -p 400 -f $< $@

.PHONY:
clean:
	-rm -f *.itb

.PHONY:
deploy: fit_spl_fpga.itb
	ssh root@$(BOARD_IP) 'mount /dev/mmcblk0p1 /mnt/'
	scp $< root@$(BOARD_IP):/mnt/fit_spl_fpga.itb
	ssh root@$(BOARD_IP) 'umount /mnt/'
