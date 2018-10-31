package InternetDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.StringTokenizer;

/**
 * Created by PC-0775 on 2018/10/28.
 */

public class UDPLoginServer {
    private static final int PORT = 1234;
    private static final int BUFSIZE = 1024;   // max size of a message

    private DatagramSocket serverSock;

    private UserData userData;

    public static void main(String[] args){
        new UDPLoginServer();
    }

    public UDPLoginServer(){
        userData = new UserData();
        try {
            serverSock = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }

        waitForPackets();
    }

    private void waitForPackets(){
        DatagramPacket receivePacket;
        byte data[];

        try {
            while (true){
                //set up an empty packet
                data = new byte[BUFSIZE];
                receivePacket = new DatagramPacket(data, data.length);

                System.out.print("Waiting for a packet...");
                serverSock.receive(receivePacket);

                System.out.println(receivePacket.getData());

                processClient(receivePacket);

            }
        }catch (IOException ioe){
            System.out.print(ioe);
        }
    }

    private void processClient(DatagramPacket receivePacket)
    // extract client details from the received packet
    {
        InetAddress clientAddr = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        String clientMesg = new String( receivePacket.getData(), 0,
                receivePacket.getLength() );

        System.out.println("Client packet from " + clientAddr +
                ", " + clientPort );
        System.out.println("Client mesg: " + clientMesg);

        doRequest(clientAddr, clientPort, clientMesg);
    }  // end of processClient()


    private void doRequest(InetAddress clientAddr, int clientPort, String clientMesg)
  /*  A message from a client can be one of:
             "score name & score &"
      or     "get"
  */
    {
        if (clientMesg.trim().toLowerCase().equals("get")) {
            System.out.println("Processing 'get'");
            sendMessage(clientAddr, clientPort, "test~~haha" );
        }
        else if ((clientMesg.length() >= 6) &&     // "userInfo "
                (clientMesg.substring(0,8).toLowerCase().equals("userinfo"))) {
            System.out.println("Processing 'score'");
            if (checkUsernamePassword(clientMesg.substring(8))) {
                sendMessage(clientAddr, clientPort, "login success!" );
            }else {
                sendMessage(clientAddr, clientPort, "login fail!" );
            }
        }
        else
            System.out.println("Ignoring input line");
    }  // end of doRequest()

    private void sendMessage(InetAddress clientAddr, int clientPort, String mesg)
    // send message to the socket at the specified address and port
    {
        byte mesgData[] = mesg.getBytes();   // convert message to byte[] form
        try {
            DatagramPacket sendPacket =
                    new DatagramPacket( mesgData, mesgData.length, clientAddr, clientPort);
            serverSock.send( sendPacket );
        }
        catch(IOException ioe)
        {  System.out.println(ioe);  }
    }  // end of sendMessage()

    private boolean checkUsernamePassword(String data){
        StringTokenizer st = new StringTokenizer(data, " &");
        String username = st.nextToken();
        String password = st.nextToken();
        return userData.checkUser(new UserInfo(username, password));
    }
}
