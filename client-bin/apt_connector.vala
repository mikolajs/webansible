/* -*- Mode: vala; tab-width: 4; intend-tabs-mode: t -*- */
/* apt-followel project
 * Copyright (C) Mikołaj Sochacki 2012 <mikolajsochacki@gmail.com>
 * licenced on LGPL
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.";
 */
using GLib;

public class AptConnector  : Object
{

    const string status_file_path = "/var/lib/dpkg/status";
    long modyfied_time = 0L;
   
    public AptConnector () 
	{
        
    }

	public string get_packages_in_xml(string password)
	{
		StringBuilder xmlString = new StringBuilder();
        xmlString.append("<root><upgrade>");
        xmlString.append(modyfied_time.to_string());
        xmlString.append("</upgrade><password>");
        xmlString.append(password);
        xmlString.append("</password><packageList>");
		var pack = get_package_list();
		xmlString.append(pack);
		xmlString.append("</packageList></root>");
		return xmlString.str;
	}

	private string get_package_list()
	{
		var packagesXML = new StringBuilder(); 
		
		var file = File.new_for_path(status_file_path);
		if (!file.query_exists ()) 
		{
			stderr.printf ("File '%s' doesn't exist.\n", file.get_path ());
			return "";
		}

		try 
		{
            get_last_time_upgrade(file);
			var dis = new DataInputStream (file.read());
			string line;
			string[] tmpArray; 
			
			while ((line = dis.read_line (null)) != null) 
			{
				tmpArray = line.split(" ");
				if(tmpArray.length > 1 && tmpArray[0] == "Package:")
					packagesXML.append("<package>" + tmpArray[1] + "</package>");
			}
		} 
		catch (Error e) 
		{
			error ("%s", e.message);
		}
		return  packagesXML.str;
	}
    
    private void get_last_time_upgrade(File file)
    {
        try {
          var info = file.query_info("*", 0);
          var time_val = info.get_modification_time();
          modyfied_time = time_val.tv_sec;
          modyfied_time *= 1000L; // change to miliseconds
          stdout.printf("status modyfied at: %ld " , modyfied_time);
      }
      catch (Error e) 
      {
          error("Error: %s", e.message);
      }
    }

}
