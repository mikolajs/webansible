#!/bin/bash

for i in {2..40}
  do 
    IP=10.0.0.$i
    echo $IP
    SSH_USER="administrator@"$IP":/home/administrator/"
    echo $SSH_USER
    scp ./install.sh $SSH_USER
    scp ./pinger $SSH_USER
    ssh -t administrator@$IP "sudo sh /home/administrator/install.sh"
  done

