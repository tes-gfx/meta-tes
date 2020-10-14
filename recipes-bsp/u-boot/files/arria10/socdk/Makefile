.PHONY:
all : fit_spl_socdk.itb

%.itb : %.its
	mkimage -E -p 400 -f $< $@

.PHONY:
deploy: all
	ssh root@$(BOARD_IP) 'mount /dev/mmcblk0p1 /mnt/'
	scp fit_spl_socdk.itb root@$(BOARD_IP):/mnt/
	ssh root@$(BOARD_IP) 'umount /mnt/'

.PHONY:
clean:
	rm *.itb
