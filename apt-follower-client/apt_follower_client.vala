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
	string ipAddress = "153.19.170.34"; //"127.0.0.1";
	string password = "";
	string vector = "";
    SocketClient client;
    SocketConnection conn;
    		
	public AptFallowerClient ()
	{	
        read_config_file();
	}


	static void main () 
	{		
        var client = new AptFallowerClient();   
        while(true)
        {
            Thread.usleep(2000000L);
            client.get_connection_to_server();
            client.send_package_info();
            client.read_incomming_data();
            client.disconnect_server();
            Thread.usleep(2000000L);
            /*
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
       
	}


    public void get_connection_to_server() 
    {
        try {
        client = new SocketClient ();
        conn = client.connect (new InetSocketAddress (new InetAddress.from_string(ipAddress), 55780)); 
        print (@"Connected to $ipAddress\n");
    } catch (Error e) {print("Error: %s \n", e.message);}
   }
   
   public void disconnect_server() 
   {
       try { conn.close(); } catch (Error e) {print("Error: %s \n", e.message);}
       print("disconnected server");
   }
 
    public void send_package_info()
	{
    try {
       
		var aptConnector = new AptConnector();
        var packageXML = aptConnector.get_packages_in_xml();
    
        conn.output_stream.write (packageXML.data);
        print ("Send packages \n");
        
    } catch (Error e) {
        stderr.printf ("%s\n", e.message);
    }
	}
    
    public bool read_incomming_data()
    {
        print("begin read incomming data");
        try {
        var input = new DataInputStream (conn.input_stream);
        print("before read line");
        conn.socket.set_blocking (true);
        var message = "";
        while(message = input.read_line (null).strip ()) != null);
       print ("Received orders: %s\n", message);
       return true;

    } catch (Error e) {
        stderr.printf ("%s\n", e.message);
    }
     return false;
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
            var file = File.new_for_path("/etc/apt-fallower/apt-fallower.conf");
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
                            if(array[0] == "server")
                                ipAddress = array[1].strip();
                             if(array[0] == "password")
                                password = array[1].strip();
                             if(array[0] == "vector")
                                vector = array[1].strip();
                        }
                    } 
                }
            } 
            else stderr.printf("config file not exists!\n");
        }
        catch (Error e) {stderr.printf ("%s\n", e.message);}
    }
    
    
	public void test() 
    {
        var host = "www.epodrecznik.edu.pl";
        var message = @"GET / HTTP/1.1\r\nHost: $host\r\n\r\n";
        conn.output_stream.write (message.data);
        var input = new DataInputStream (conn.input_stream);
        conn.socket.set_blocking (true);
        var recived = input.read_line (null).strip ();
        print ("Received orders: %s\n", recived);
    }
}
