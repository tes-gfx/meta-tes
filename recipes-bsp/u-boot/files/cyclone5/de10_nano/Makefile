.PHONY:
all : fit_spl_de10_nano.itb

%.itb : %.its
	mkimage -E -p 400 -f $< $@

.PHONY:
clean:
	rm *.itb
