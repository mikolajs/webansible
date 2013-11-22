Simple tool for management remote machine using Ansible for Debian systems


#ROAD MAP

## WebAnsible:
 [scala, spray.io, h2 base, ansible]
* get pings over http in http://domain/pings path form clients
	(colect ip form hosts with DHCP) - done
* create /etc/ansible/hosts file from pings - done
 	(in future give input for ansible - $ANSIBLE_PATH) 
* create group of host and add or remove hosts from group
* insert deb repository and copy to group of clients
* show all available packages and allow to mark package be required or not (or without any policy) for group
* make install, update, upgrade by ansible
* allow create text file (i.e. config), config path and copy to group by ansible
* allow to run single command on group by ansible
* allow to run playbook on group

## pinger:
 [vala]
* ping to server and send their hostname (carry ip) - done
* configurable by file /etc/default/pinger.conf - done

## ScalApt:
 [scala]
* get deb links
* return available packages
* return dependences for package - list of packages

## RUN

Follow these steps to get started (for developers):

1. Git-clone this repository.

        $ git clone https://github.com/mikolajs/webansible.git

2. Change directory into your clone:

        $ cd webansible

3. Launch SBT:

        $ sbt

4. Compile everything and run all tests:

        > test

5. Start the application:

        > re-start

6. Browse to http://localhost:8080/

7. Start the application:

        > re-stop

8. Create jar with assembly:

        > assembly
        
Run jar file:
 
sudo java -jar webansible-assembly-{version}.jar

Open browser http://localhost:8989
