#!/bin/bash

# Loop the demos in automatic mode. Pressing a button will switch to manual mode.
# This script requires the gpio_poll tool.

declare -a DEMOARR=(
	"/usr/share/exhibdemo/ExhibDemo"
	"/usr/share/qt5/examples/quick/demos/clocks/clocks"
	"/usr/share/qt5/examples/quickcontrols/extras/dashboard/dashboard"
	"/usr/share/qt5/examples/quickcontrols/extras/flat/flat"
	"/usr/share/qt5/examples/quickcontrols/extras/gallery/gallery"
	"/usr/share/qt5/examples/quickcontrols/controls/calendar/calendar"
	"/usr/share/qt5/examples/quickcontrols2/gallery/gallery"
	"/usr/share/egles-test/bin/egles_test v l x1 24"
	"/usr/share/egles-test/bin/egles_test v l x1 98"
	"/usr/share/egles-test/bin/egles_test v l x1 103"
)

DEMOLEN=${#DEMOARR[@]}

automode=1
loc=$(pwd)
x=0
event_file=/dev/input/by-path/*-event-kbd

while :
do
	dn=$(echo $(dirname ${DEMOARR[$x]})|cut -d" " -f1)
        cd ${dn}
	eval "./$(basename "${DEMOARR[$x]}") &"
	pid=$!

	echo Started process $pid

	cd $loc

	if [ $automode -eq 1 ]; then
		kb_poll -f 59 -f 60 -i -t 15 $event_file
		button=$?

		if [ $button -ne 255 ]; then
			automode=0
		else
			x=$(( ($x + 1) % $DEMOLEN ))
		fi
	fi
	
	if [ $automode -eq 0 ]; then
		sleep 1.0
		kb_poll -f 59 -f 60 $event_file
		button=$?
	fi

	echo Killing process $pid
	kill -KILL $pid
	wait $pid

	if [ $button -eq 60 ]; then
		x=$(( ($x + 1) % $DEMOLEN ))
	elif [ $button -eq 59 ]; then
		x=$(( ($x - 1) % $DEMOLEN ))
	fi

	echo "$x ${DEMOARR[$x]}"
done
