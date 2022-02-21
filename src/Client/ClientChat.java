package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientChat extends JPanel {

    public ClientChat() throws IOException, ClassNotFoundException {
        //INITIALIZATION FIELDS
        jTextArea = new JTextArea() ;
        menssage = new JTextField() ;
        nickName = new JTextField() ;
        sendMessage = new JButton("ENVIAR");
        sendMessage.addActionListener(new EventToSendMessage());
        conected = new JButton("CONECTAR");
        conected.addActionListener(new EventConected());
        usersConected = new JComboBox();
        usersConected.setPreferredSize(new Dimension(200,20));
        threadToRecibeUsersData = new ThreadToRecibeUsersData(usersConected);
        threadToRecibeUsersData.start();
        threadToReciveMenssage = new ThreadToReciveMenssage(jTextArea);
        threadToReciveMenssage.start();
        inetAddress = InetAddress.getLocalHost() ;
        //BOXS CONFIGURE
        Box topBox = Box.createHorizontalBox();
        Box botBox = Box.createHorizontalBox();
        topBox.add(conected);
        topBox.add(new JLabel("Nick ")) ;
        topBox.add(nickName) ;
        topBox.add(new JLabel("Conectados ")) ;
        topBox.add(usersConected);
        botBox.add(menssage);
        botBox.add(sendMessage);
        // SET PANEL DATA
        setLayout(new BorderLayout());
        // ADD COMPONENTS TO PANEL
        add(topBox , BorderLayout.NORTH) ;
        add(jTextArea , BorderLayout.CENTER) ;
        add(botBox , BorderLayout.SOUTH) ;

    }

    private class EventConected implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            ThreadToSendUsersData threadToSendUsersData = new ThreadToSendUsersData(nickName.getText());

            if (conected.getText().equals("CONECTAR")){
                conected.setText("DESCONECTAR");
                threadToSendUsersData.start();
            }else if (conected.getText().equals("DESCONECTAR")){
                conected.setText("CONECTAR");
                threadToSendUsersData.interrupt();
            }

        }
    }

    private class EventToSendMessage implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                Socket socket = new Socket(inetAddress.getHostAddress() , 8080) ;

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

                dataOutputStream.writeUTF(nickName.getText()+"&"+usersConected.getSelectedItem()+"&"+menssage.getText());

                jTextArea.append(nickName.getText() + ": " + menssage.getText() + "\n");

                menssage.setText("");

                dataOutputStream.close();

                socket.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
    }

    private JTextArea jTextArea ;
    private JTextField menssage ;
    private JTextField nickName ;
    private JButton sendMessage ;
    private JButton conected ;
    private JComboBox usersConected ;
    private ThreadToRecibeUsersData threadToRecibeUsersData ;
    private ThreadToReciveMenssage threadToReciveMenssage ;
    private InetAddress inetAddress ;


}
