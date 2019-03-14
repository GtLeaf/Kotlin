package Socket.TCP

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.ServerSocket

class TcpServer {

}

fun main(args:Array<String>){
    var clientSentence = ""
    var capitalizedSentence:String
    val welcomeSocket = ServerSocket(6789)
    var count = 0 //记录了接入的客户端数量
    println("listening...")

    while (true){
//        var connectionSocket = welcomeSocket.accept()//只能建立单个Socket连接
        var connectionSocket = welcomeSocket.accept()
        //开启一个线程，此处可用线程池
        Thread{
            var inFromClient:BufferedReader? = null
            var outToClient:DataOutputStream? = null
            while (clientSentence != "exit"){
                if (null != connectionSocket){
                    println("连接成功!")
                }
                inFromClient = BufferedReader(InputStreamReader(connectionSocket.getInputStream()))
                outToClient = DataOutputStream(connectionSocket.getOutputStream())
                clientSentence = inFromClient.readLine()
                println("接收到消息$clientSentence")
                capitalizedSentence = clientSentence.toUpperCase()
                outToClient.writeBytes(capitalizedSentence+"\n")
            }
            connectionSocket.close()
            inFromClient!!.close()
            outToClient!!.close()

        }.start()
    }
}