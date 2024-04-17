#!/bin/sh
#

red="\033[00;31m"
green="\033[00;32m"
cyan="\033[00;36m"
nc="\033[00m"

# try to get board id (last char of hostname is 'board number')
boardid="`hostname | awk '{print substr($0,length($0),1)}'`"

# check driver module
if [ -z "`lsmod | grep dnx`" ]; then
	echo -e "This is board-$boardid ${red}no D/AVE NX present${nc}."
else	  
	# try to get kernel module info
	hwinfo="`cat /var/log/dmesg | grep "D/AVE NX" | awk '{split($0,s,":"); print s[2]}'`"

	# colorize numbers in output
	export GREP_COLOR='1;36'
	hwinfo="`echo $hwinfo | grep -E --color=always "[0-9]*"`"

	# try go get board status
	status="`sudo cat /sys/kernel/debug/dri/0/busy 2>/dev/null`"
	if [ -z "$status" ]; then
	  status="`sudo cat /sys/kernel/debug/dri/1/busy 2>/dev/null`"
	fi  
	if [ "$status" == "core ready..." ]; then
	  status="${green}READY${nc}"
	else
	  status="${red}BUSY${nc}"
	fi

	echo -e "This is board-$boardid running $hwinfo. Core is $status."
fi
echo

