#!/bin/sh

SERVER_KEY="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC549PVa3R2ynarnP3aZK18JU5swbr30B0y00IvXV4NosmfXGQ4fM6qZNQCbUbydQr1YENOC7W+TKmqPuouWYkCFvGsy00rlC3Hb9cBETpScp2nQoYzFRTZ7rbo+oy3OC7smWe+tRMHhvXmOTsVDaHtmKiN4Cl/R/E3rE26gOlhR46VmVPbyAVmx9fkbI+cB8sPIECOnJ2xyXdKOgqz/G94Nw/DvTx6DiKFnnjTyEjAytmuTNVpAjV26VBe8cV3yI5tlX+ZBR4Z/ez9ntAuvBTuQ351xvkD53avPwn48dDl2pH/g3/veAsO+QIoN0iBa39NbgccShWJ9ybRF2Bhbt/b mikolajsochacki@gmail.com"

#install packages
#echo "install ssh"
#apt-get install ssh -y
#echo "install libsoup"
#apt-get install libsoup2.4-1 -y

echo "create ssh"
mkdir $HOME/.ssh
echo "insert key to authorized Keys"
echo $SERVER_KEY > $HOME/.ssh/authorized_keys
echo "change mod"
chmod 755 $HOME/.ssh/authorized_keys

BINARY_PATH=/usr/bin/pinger-ansible
INITD_PATH=/etc/init.d/pinger-ansible
CONF_PATH=/etc/ansible/webapt.conf

mkdir /etc/ansible

echo "#config file for client pinger" > $CONF_PATH
echo "" >> $CONF_PATH
echo "#server addres X.X.X.X or domain.com"  >> $CONF_PATH
echo "server_ip=192.168.28.100"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#password: now not used"  >> $CONF_PATH
echo "password=haslomaslo"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#port to ping  default: 8989"  >> $CONF_PATH
echo "server_port=8989"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#time in minutes in witch program ping again after success min: 10 minutes"  >> $CONF_PATH
echo "time_ping=20"  >> $CONF_PATH
echo ""  >> $CONF_PATH
echo "#time in minutes in witch program ping again after fail (not found server) min = 1 minute"  >> $CONF_PATH
echo "time_step=5"  >> $CONF_PATH
chmod 755 $CONF_PATH

cp pinger $BINARY_PATH
chmod 755 $BINARY_PATH

#create start file
echo "#!/bin/sh" > $INITD_PATH
echo "#start pinger-ansible" >> $INITD_PATH
echo " " >> $INITD_PATH
echo "/usr/bin/pinger-ansible &" >> $INITD_PATH
echo " " >> $INITD_PATH
chmod 755 $INITD_PATH

ln -s $INITD_PATH /etc/rc2.d/S78pinger-ansible

$BINARY_PATH &

