package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreadToSendUsersData extends Thread{
    public ThreadToSendUsersData( String nick ){
        this.nick = nick ;
    }
    @Override
    public void run() {

        try {

            InetAddress inetAddress = InetAddress.getLocalHost();

            Socket socket = new Socket(inetAddress.getHostAddress() , 9090);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.writeUTF(nick);

            dataOutputStream.close();

            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String nick ;

}
