package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadToRecibeUsersData extends Thread{

    public ThreadToRecibeUsersData( JComboBox comboBox ) {
        userConected = new ArrayList<>();
        this.comboBox = comboBox ;
    }

    @Override
    public void run() {

        try {

            ServerSocket serverSocket = new ServerSocket(9000);

            while (true){

                Socket socket = serverSocket.accept();

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                String[] c = dataInputStream.readUTF().split("&");

                userConected.add(new ClientInfo(c[1],c[0]));

                fillCombobox();

                dataInputStream.close();

                socket.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillCombobox(){
        for (ClientInfo ci:
             userConected) {
            comboBox.addItem(ci.getIp());
        }
    }

    private void showUserConected(){
        for (ClientInfo ci:
             userConected) {
            System.out.println(ci.getNickName() + " con ip " + ci.getIp());
        }
    }

    private ArrayList<ClientInfo> userConected ;

    private ClientInfo a ;

    private JComboBox comboBox ;

}
