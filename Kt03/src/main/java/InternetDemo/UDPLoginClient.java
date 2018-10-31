package InternetDemo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Created by PC-0775 on 2018/10/28.
 */

public class UDPLoginClient extends JFrame implements ActionListener{
    private static final int SERVER_PORT = 1234;     // server details
    private static final String SERVER_HOST = "localhost";

    private static final int BUFSIZE = 1024;   // max size of a message

    private DatagramSocket sock;
    private InetAddress serverAddr;

    private JTextArea jtaMesgs;
    private JTextField jtfUsername, jtfPassword;
    private JButton jbLogin;

    // ------------------------------------

    public static void main(String args[]) {
        new UDPLoginClient();
    }

    public UDPLoginClient(){
        super("UDP Login Server");

        initializeGUI();

        try {
            sock = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            serverAddr = InetAddress.getByName(SERVER_HOST);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320,450);
        setResizable(false);    // fixed size display
        setVisible(true);

        waitForPackets();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        login();
    }

    private void login()
  /* Check if the UserInfo has supplied a name and score, then
     send "score name & score &" to server
     Note: we should check that score is an integer, but we don't. */
    {
        String username = jtfUsername.getText().trim();
        String password = jtfPassword.getText().trim();
        // System.out.println("'" + name + "'   '" + score + "'");

        //非空检查
        if ((username.equals("")) && (password.equals("")))
            JOptionPane.showMessageDialog( null,
                    "No username and password entered", "login Error",
                    JOptionPane.ERROR_MESSAGE);
        else if (username.equals(""))
            JOptionPane.showMessageDialog( null,
                    "No username entered", "login Error",
                    JOptionPane.ERROR_MESSAGE);
        else if (password.equals(""))
            JOptionPane.showMessageDialog( null,
                    "No password entered", "login Error",
                    JOptionPane.ERROR_MESSAGE);
        else {
            sendMessage(serverAddr, SERVER_PORT,
                    "UserInfo " + username + " & " + password + " &");
            jtaMesgs.append("Sent " + username + " login request \n");
        }
    }  // end of login()

    private void sendMessage(InetAddress serverAddr, int serverPort, String mesg)
    // send message to the socket at the specified address and port
    {
        byte mesgData[] = mesg.getBytes();   // convert message to byte[] form
        try {
            DatagramPacket sendPacket =
                    new DatagramPacket( mesgData, mesgData.length, serverAddr, serverPort);
            sock.send( sendPacket );
        }
        catch(IOException ioe)
        {  System.out.println(ioe);  }
    }  // end of sendMessage()

    private void initializeGUI(){
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        jtaMesgs = new JTextArea(7, 7);
        jtaMesgs.setEditable(false);
        JScrollPane jsp = new JScrollPane( jtaMesgs);
        c.add( jsp, "Center");

        JLabel jlName = new JLabel("username: ");
        jtfUsername = new JTextField(6);

        JLabel jlScore = new JLabel("password: ");
        jtfPassword = new JTextField(6);
        jtfPassword.addActionListener(this);    // pressing enter triggers sending of name/score

        jbLogin = new JButton("Login");
        jbLogin.addActionListener(this);

        JPanel p1 = new JPanel( new FlowLayout() );
        p1.add(jlName); p1.add(jtfUsername);
        p1.add(jlScore); p1.add(jtfPassword);

        JPanel p2 = new JPanel( new FlowLayout() );
        p2.add(jbLogin);

        JPanel p = new JPanel();
        p.setLayout( new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(p1); p.add(p2);

        c.add(p, "South");

    }

    private void waitForPackets(){
        DatagramPacket receivePacket;
        byte data[];

        try{
            while (true) {
                // set up an empty packet
                data = new byte[BUFSIZE];
                receivePacket = new DatagramPacket(data, data.length);

                System.out.println("Waiting for a packet...");
                sock.receive( receivePacket );

                processServer(receivePacket);
            }
        }catch (IOException ioe)
        {  System.out.println(ioe);  }
    }

    private void processServer(DatagramPacket receivePacket)
    // extract server details from the received packet
    {
        InetAddress serverAddr = receivePacket.getAddress();
        int serverPort = receivePacket.getPort();
        String serverMesg = new String( receivePacket.getData(), 0,
                receivePacket.getLength() );

        System.out.println("Server packet from " + serverAddr +
                ", " + serverPort );
        System.out.println("Server mesg: " + serverMesg);

        showResponse(serverMesg);
    }

    private void showResponse(String mesg)
    {
        jtaMesgs.append( mesg + "\n");
    }
}
