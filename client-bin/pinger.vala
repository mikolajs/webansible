/*
 * pinger.vala
 * Copyright (C) 2013 Mikołaj Sochacki <mikolajsochacki@gmail.com>
 *
 * webapt is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.";
 */

using GLib;

public class Pinger : Object
{
  const int64 MIN_TIME_WAIT = 600000000L; //10 minutes
  const int64 MIN_TIME_STEP = 60000000L; //1 minute
  string ipAddress = "127.0.0.1";
  string password = "";
  string vector = "";
  string hostname = "host";
  int64 time_wait = MIN_TIME_WAIT;
  int64 time_step = MIN_TIME_STEP;
  const string config_file_path = "/etc/ansible/webapt.conf";
  string server_port = "8080";
  string ping_fullpath = "";
  int64 last_send_with_success = MIN_TIME_WAIT;

  public Pinger ()
  {
    read_config_file();
    hostname = Environment.get_host_name();
    ping_fullpath =  "http://%s:%s/pings?h=%s".printf(ipAddress, server_port, hostname);
  }


  static void main ()
  {
    var client = new Pinger();
    client.run();
  }

  public void make_ping()
  {
    var session = new Soup.SessionSync ();
    print(ping_fullpath + "\n");
    var message = new Soup.Message ("GET", ping_fullpath);
    session.send_message (message);
    print("Status code: ");
    stdout.write (message.status_code.to_string().data);
    print("\n");
    if ( message.status_code == 200) last_send_with_success = 0L;
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
              if(array[0] == "server_ip") ipAddress = array[1].strip();
              if(array[0] == "password") password = array[1].strip();
              if(array[0] == "vector") vector = array[1].strip();
              if(array[0] == "server_port") server_port = array[1].strip();
              if(array[0] == "time_ping")
              {
                time_wait = (int64)(int.parse(array[1].strip()))*MIN_TIME_STEP ;
                print("config time_wait = " + time_wait.to_string() + "\n");
                if(time_wait < MIN_TIME_WAIT) time_wait = MIN_TIME_WAIT;
              }
              if(array[0] == "time_step")
              {
                time_step = (int64)(int.parse(array[1].strip()))*MIN_TIME_STEP ;
                 print("config time_step = " + time_step.to_string()  + "\n");
                if(time_step < MIN_TIME_STEP) time_step = MIN_TIME_STEP;
              }
            }
         }
       }
     }
     else stderr.printf("config file not exists!\n");
   }
   catch (Error e) {stderr.printf ("%s\n", e.message);}
 }


    public void run()
    {
      while(true) {
        print("Next loop " + last_send_with_success.to_string() + "\n");
        if(last_send_with_success > time_wait) make_ping();
        else last_send_with_success += time_step;
          Thread.usleep((ulong)time_step);
        }
    }
}

