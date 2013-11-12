 #!/bin/bash
for IP in {2..40}
    do 
        ssh -t administrator@10.0.0.$IP "sudo sh /home/administrator/install.sh"
    done

