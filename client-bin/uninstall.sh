#!/bin/sh

BINARY_PATH=/usr/bin/pinger-ansible
INITD_PATH=/etc/init.d/pinger-ansible
CONF_PATH=/etc/ansible/webapt.conf

rm $BINARY_PATH
rm $INITD_PATH
rm $CONF_PATH
rm /etc/rc5.d/S78pinger-ansible
