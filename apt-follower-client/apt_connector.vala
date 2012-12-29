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

	private string statusFilePath = "/var/lib/dpkg/status";

    // Constructor
    public AptConnector () 
	{
        
    }

	public string get_packages_in_xml()
	{
		var xmlString = "<root><packageList>";
		var pack = get_package_list();
		xmlString = xmlString.concat(pack);
		xmlString = xmlString.concat("</packageList></root>");
		return xmlString;
	}

	private string get_package_list()
	{
		var packagesXML = new StringBuilder(); 
		
		var file = File.new_for_path(statusFilePath);
		if (!file.query_exists ()) 
		{
			stderr.printf ("File '%s' doesn't exist.\n", file.get_path ());
			return "";
		}

		try 
		{
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

}
