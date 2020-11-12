TARGET=devmem2

$(TARGET) : devmem2.o
	$(CC) $(LDFLAGS) -o $@ $^

.PHONY:
all : $(TARGET)

.PHONY:
deploy : all
	scp $(TARGET) root@$(BOARD_IP):/home/root/

.PHONY:
clean : 
	rm -f *.o $(TARGET)
