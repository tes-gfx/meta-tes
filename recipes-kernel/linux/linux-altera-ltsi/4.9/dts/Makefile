.PHONY:
all : dreamchip_arria10som_tes.dtb

%.dti : %.dts
	cpp -I$(KERNEL_SRC)/arch/arm/boot/dts -I$(KERNEL_SRC)/include -E -P -x assembler-with-cpp -o $@ $^

%.dtb : %.dti
	dtc -@ -I dts -O dtb -o $@ $^

.PHONY:
clean:
	rm *.dtb
