package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadToReciveMenssage extends Thread{

    public ThreadToReciveMenssage(JTextArea jTextArea ){

        this.jTextArea = jTextArea ;

    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);

            while (true){

                Socket socket = serverSocket.accept();

                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                String[] s = dataInputStream.readUTF().split("&");

                jTextArea.append(s[0] + ": " + s[1] + "\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JTextArea jTextArea ;

}
