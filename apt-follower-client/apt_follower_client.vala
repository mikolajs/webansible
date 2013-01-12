/* -*- Mode: C; indent-tabs-mode: t; c-basic-offset: 4; tab-width: 4 -*- */
/*
 * main.c
 * Copyright (C) 2012 Mikołaj Sochacki <mikolajsochacki@gmail.com>
 * 
 * apt-fallower is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.";
 */

using GLib;

public class AptFallowerClient : Object 
{	
	string ipAddress = "127.0.0.1";
	string password = "";
	string vector = "";
    string hostname = "host";
    const string orders_subpath = "/api/orders/";
    const string packages_subpath = "/api/packages/";
    const string config_file_path = "/home/ms/Programy/wioowszkole/apt-follower-new/config.conf"; // "/etc/apt-fallower/apt-fallower.conf";
    string server_port = "8080";
    string orders_fullpath = "";
    string packages_fullpath = "";
    long last_upgrade = 0L;
    long last_send_with_success = 0L;
    		
	public AptFallowerClient ()
	{	
        read_config_file();
        read_host_name();
        packages_fullpath =  "http://%s:%s%s%s".printf(ipAddress, server_port, packages_subpath, hostname);
        orders_fullpath =  "http://%s:%s%s%s".printf(ipAddress, server_port, orders_subpath, hostname);
        var now = new DateTime.now_local ();
	
        stdout.printf("%ld time", now.get_utc_offset());
	}


	static void main () 
	{		
        var client = new AptFallowerClient();  
  
        client.test();
       /*
       while(true)
        {
           
            client.get_connection_to_server();
            client.send_package_info();
            client.read_incomming_data();
            client.disconnect_server();
            Thread.usleep(2000000L);
            
            int n = 4;
            bool read = false;
            client.get_connection_to_server();
            while (!read && n > 0)
            {
                read = client.read_incomming_data();
                if(read) 
                { 
                    client.run_install();
                    print("read finish \n");
                    break;
                }
                n--;
                print("read loop %d \n",n);
                Thread.usleep(5000);
            }
            client.disconnect_server();
            Thread.usleep(1800000);
            */
            
        
       
	}
    


    public void send_package_info()
	{
		var aptConnector = new AptConnector();
        var packageXML = aptConnector.get_packages_in_xml(password);;
        var parameterData = "dataxml=" + packageXML;
        var session = new Soup.SessionSync ();
        var message = new Soup.Message ("POST", packages_fullpath);
      	message.request_headers.append("Content-Type","application/x-www-form-urlencoded");
      	//message.request_headers.append("Content-Length",parameterData.data.length.to_string());
        //message.set_response("application/x-www-form-urlencoded", Soup.MemoryUse.COPY, parameterData.data);
        message.request_body.append(Soup.MemoryUse.COPY, parameterData.data);
        session.send_message (message);
       // if(is_response_OK(message.response_body.to_string() ))
       // {
        	//var now = new DateTime.now_local ();
        	//last_send_with_success = now.
       // }
        stdout.write (message.response_body.data);
	}
    
    
    public void get_orders_info()
    {
        var session = new Soup.SessionSync ();
        print(orders_fullpath + "\n");
        var message = new Soup.Message ("GET", orders_fullpath);
        session.send_message (message);
        stdout.write (message.response_body.data);
    }
    
    
    public void run_install()
    {
        //not implemented
        print("run install");
    }
    
    private void read_config_file()
    {
        try 
        {
            var file = File.new_for_path(config_file_path);
            if(file.query_exists())
            {
                var file_stream = file.read();
                var inStr = new DataInputStream(file_stream);
                string line = "";
                while((line = inStr.read_line(null) ) != null) 
                {
                    line = line.strip();
                    string[] array;
                    if(line.length > 0 && line[0] != '#')
                    {
                        array = line.split("=");
                        if(array.length > 1) 
                        {
                            if(array[0] == "server_ip")
                                ipAddress = array[1].strip();
                             if(array[0] == "password")
                                password = array[1].strip();
                             if(array[0] == "vector")
                                vector = array[1].strip();
                             if(array[0] == "server_port")
                                server_port = array[1].strip();
                        }
                    } 
                }
            } 
            else stderr.printf("config file not exists!\n");
        }
        catch (Error e) {stderr.printf ("%s\n", e.message);}
    }
    
    private void check_last_upgrade()
    {
    }
    
    private void read_host_name()
    {
        hostname = Environment.get_host_name();
    }
    
    private bool is_response_OK(string xmlData)
    {
    	string last = "";
    	xmlData.strip().scanf("<root>%s</root>", last);
        return (last == "OK"); 
    }

    private long get_time_now_in_miliseconds() 
{
	var now = new DateTime().local_now();
	
}
    
	public void test() 
    {

        get_orders_info();
        Thread.usleep(500000L);
        send_package_info();

    }
}
