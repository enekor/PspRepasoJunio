import java.io.BufferedReader
import java.io.InputStreamReader

object Ping{
    fun ping(url:String):String{
        val comando = ProcessBuilder()
        comando.command("powershell","/c","wsl ping -c 4 $url | wsl grep mdev")

        val proceso = comando.start()

        val ping = BufferedReader(InputStreamReader(proceso.inputStream))
        return "$url -> ${ping.readLine()}"
    }
}