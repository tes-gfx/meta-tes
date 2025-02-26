.PHONY:
all : fit_spl_arria10som.itb

%.itb : %.its
	mkimage -E -p 400 -f $< $@

.PHONY:
clean:
	rm *.itb

.PHONY:
deploy: fit_spl_arria10som.itb
	ssh root@$(BOARD_IP) 'mount /dev/mmcblk0p1 /mnt/'
	scp $< root@$(BOARD_IP):/mnt/fit_spl_fpga.itb
	ssh root@$(BOARD_IP) 'umount /mnt/'
