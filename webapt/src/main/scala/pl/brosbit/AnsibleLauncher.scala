package pl.brosbit
import java.lang.{ Process, ProcessBuilder }

class AnsibleLauncher {

    def makeCommandWithSudo(command: String) {
        val cat = new ProcessBuilder("ansible", "all", "-m", "ping")
        //"ansible all -m ping -u administrator"
        val proc = cat.start
        val os = proc.getOutputStream
        val err = proc.getErrorStream
        val is = proc.getInputStream
        val outArray = "haslo".toCharArray.map(_.asInstanceOf[Byte]) ++ Array(13.toByte)
        println(outArray.map(_.asInstanceOf[Char]).mkString(""))
        os.write(outArray)
        os.flush()

        proc.waitFor
        val inArray = new Array[Byte](is.available)
        val errArray = new Array[Byte](err.available)
        println(inArray.length.toString)
        is.read(inArray)
        err.read(errArray)
        println(inArray.map(_.asInstanceOf[Char]).mkString(""))
        println(errArray.map(_.asInstanceOf[Char]).mkString(""))
        os.close()
        is.close()
    }

    def makeCommandWithoutSudo(command: String) {
        val procesBuild = new ProcessBuilder(command.split(" ").toList.asInstanceOf[java.util.List[String]])
        val proc = procesBuild.start
        val err = proc.getErrorStream
        val is = proc.getInputStream
    }
}