
var hostMockup = [{id:23, hosts:[{id:234,name:"PC2", ip:"10.0.0.1", firm:"Nie", ping:"23.12.2014 14:30"},
		                                 {id:224,name:"PC4", ip:"10.0.0.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		                                 {id:134,name:"PC3", ip:"10.0.0.3", firm:"Nie", ping:"23.12.2014 14:30"}]}, 
		                                 {id:22, hosts:[{id:232,name:"KOM1", ip:"10.0.2.1", firm:"Nie", ping:"23.12.2014 14:30"},
		       		                                 {id:230,name:"KOM2", ip:"10.0.2.2", firm:"Nie", ping:"23.12.2014 14:30"}, 
		       		                                 {id:204,name:"KOM3", ip:"10.0.2.4", firm:"Nie", ping:"23.12.2014 14:30"}]},
		                  {id:34,  hosts:[{id:284,name:"HAL1", ip:"199.0.0.1", firm:"Tak", ping:"23.12.2014 14:30"},
			                                 {id:237,name:"HAL2", ip:"199.0.0.3", firm:"Tak", ping:"23.12.2014 14:30"}]} ];
var availHostsMockup =  [{id:124,name:"PH2", ip:"10.0.2.1", firm:"Nie", ping:"23.12.2014 14:37"},
	                                 {id:223,name:"PH4", ip:"10.0.1.2", firm:"Nie", ping:"23.12.2014 14:36"}, 
	                                 {id:174,name:"PH3", ip:"10.0.1.3", firm:"Nie", ping:"23.12.2014 14:39"}];

var groupMockup = [{name:"Klasa",id:23}, {name:"Sala",id:22},{name:"Hala",id:34}];

var commandsMockup = [{id:23,name:"ping", command:"ping -c 2 wp.pl", description:"Czy jest dostęp do internetu"},
                      {id:25,name:"update", command:"apt-get update -y", description:"Update list pakietów."},
{id:29,name:"packages", command:"dpkg --list", description:"Pobieranie listy pakietów"}];

var commandStoryMockup = [{id:23, success:12, all:24, date: "12.12.2013", content: " zawartość "}, 
                          {id:25,success:2, all:4,  date: "14.12.2013",  content: " zawartość "}];