
using GLib;

public class Main : Object {
  int64 liczba = 60000000L;
  string toConvert = "10";
  
  static void main()
  {
    var m = new Main();
    m.mkTest();
  }

  void mkTest()
  {
    int64 myTime = liczba*long.parse(this.toConvert);
    print(myTime.to_string() + "\n");
  }

}
