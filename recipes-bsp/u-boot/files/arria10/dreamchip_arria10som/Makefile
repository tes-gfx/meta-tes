.PHONY:
all : fit_spl_arria10som.itb

%.itb : %.its
	mkimage -E -p 400 -f $< $@

.PHONY:
clean:
	rm *.itb
