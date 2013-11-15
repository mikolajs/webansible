#!/bin/sh


SERVER_KEY=

#install packages
apt-get install ssh -y
apt-get install libsoup2.4-1 -y

echo $SERVER_KEY > $HOME/.ssh/authorized_keys


BINARY_PATH=/usr/bin/pinger-ansible
INITD_PATH=/etc/init.d/pinger-ansible
CONF_PATH=/etc/ansible/webapt.conf

mkdir /etc/ansible

echo "#config file for client pinger" > $CONF_PATH
echo "" >> $CONF_PATH
echo "#server addres X.X.X.X or domain.com"  >> $CONF_PATH
echo "server_ip=10.0.0.25"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#password: now not used"  >> $CONF_PATH
echo "password=haslomaslo"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#port to ping  default: 8989"  >> $CONF_PATH
echo "server_port=8989"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#time in minutes in witch program ping again after success min: 10 minutes"  >> $CONF_PATH
echo "time_ping=60"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#time in minutes in witch program ping again after fail (not found server) min = 1 minute"  >> $CONF_PATH
echo "time_step=10"  >> $CONF_PATH

cp pinger $BINARY_PATH

#create start file
echo "#!/bin/sh" > $INITD_PATH
echo "#start pinger-ansible" >> $INITD_PATH
echo " " >> $INITD_PATH
echo "/usr/bin/pinger-ansible" >> $INITD_PATH
echo " " >> $INITD_PATH

ln -s $INITD_PATH /etc/rc5.d/S78pinger-ansible

$BINARY_PATH&
