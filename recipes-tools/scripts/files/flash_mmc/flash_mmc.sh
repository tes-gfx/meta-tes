#!/bin/sh

ncolors=$(tput colors)
if test -n "$ncolors" && test $ncolors -ge 8; then
  BOLD="$(tput bold)"
  UNDERLINE="$(tput smul)"
  STANDOUT="$(tput smso)"
  NC="$(tput sgr0)"
  BLACK="$(tput setaf 0)"
  RED="$(tput setaf 1)"
  GREEN="$(tput setaf 2)"
  YELLOW="$(tput setaf 3)"
  BLUE="$(tput setaf 4)"
  MAGENTa="$(tput setaf 5)"
  CYAN="$(tput setaf 6)"
  WHITE="$(tput setaf 7)"
fi


function msg {
  echo "${GREEN}# $1${NC}"
}

function error {
  echo -n "$RED"
  echo   "###################################################"
  echo   "#                                                 #"
  printf "# ERROR: %-40s #\n" "$1"
  echo   "#                                                 #"
  echo   "###################################################"

  echo -n "${NC}"
  exit 1
}

function check {
  if [ $? -eq 0 ]; then
    msg "$1"
  else
    error "$2"
  fi
}


grep -q flash_mmc /proc/cmdline
if [ $? -ne 0 ]; then
  echo "no flash_mmc found-> exiting"
else

  echo -n "${GREEN}"
  echo "###################################################"
  echo "#                                                 #"
  echo "#       flashing root filesystem to mmc           #"
  echo "#                                                 #"
  echo "###################################################"
  echo -n "${NC}"

  msg "Creating filesystem ..."
  mkfs.ext4 -F -O ^huge_file /dev/mmcblk0p1
  check "Filesystem done" "Unable to create filesystem"

  msg "Mounting filesystem ..."
  mount /dev/mmcblk0p1 /mnt/
  check "Filesystem mounted" "Unable to mount root"

  msg "Copying files$ ..."
  rsync -aHx --info=progress2 / /mnt --exclude=/var/log/ --exclude=/var/cache/apt --exclude /tmp
  check "Copying finished" "Failed to copy files"

  msg "Unmounting filesystem$ ..."
  umount /mnt
  check "Filesystem unmounted" "Unmounting failed"


  echo -n "${GREEN}"
  echo "###################################################"
  echo "#                                                 #"
  echo "#            System is ready to run               #"
  echo "#                                                 #"
  echo "###################################################"
  echo -n "${NC}"
fi
