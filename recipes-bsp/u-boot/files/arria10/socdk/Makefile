.PHONY:
all : fit_spl_fpga.itb

%.itb : %.its
	mkimage -E -p 400 -f $< $@

.PHONY:
clean:
	rm *.itb
