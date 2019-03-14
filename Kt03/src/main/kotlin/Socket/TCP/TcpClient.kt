package Socket.TCP

import java.io.*
import java.net.InetAddress
import java.net.Socket
import java.net.UnknownHostException
import java.util.*

class TcpClient(val ip:String, val port:Int) {

    private lateinit var clientSocket:Socket

    init{
        try {
            clientSocket = Socket(ip, port)
        }catch (e: UnknownHostException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }catch (e:IllegalArgumentException){
            e.printStackTrace()
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}

fun main(args:Array<String>){

    //获取本机地址

    val sc = Scanner(System.`in`)
    val clientSocket = Socket(InetAddress.getLocalHost(), 6789)
    val outToServer = DataOutputStream(clientSocket.getOutputStream())
    val inputStreamRead = InputStreamReader(clientSocket.getInputStream())
    val inFromServer = BufferedReader(inputStreamRead)
    var userIn = "aa"
    println("请输入:")
    while (userIn != "exit"){
        outToServer.writeBytes(userIn+"\n")
        println(inFromServer.readLine())
        userIn = sc.nextLine()
    }
    println("程序结束")
}