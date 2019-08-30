KERNEL_SRC := $(SDKTARGETSYSROOT)/usr/src/kernel

.PHONY:
all : dreamchip_arria10som_tes.dtb

%.dti : %.dts
	cpp -I$(KERNEL_SRC)/arch/arm/boot/dts -I$(KERNEL_SRC)/include -I$(KERNEL_SRC)/arch/arm64/boot/dts/altera -E -P -x assembler-with-cpp -o $@ $^

%.dtb : %.dti
	dtc -@ -I dts -O dtb -o $@ $^

.PHONY:
clean:
	rm *.dtb
