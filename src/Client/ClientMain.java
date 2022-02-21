package Client;

import GenericThings.GeneralFrame;

import java.awt.*;
import java.io.IOException;

public class ClientMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GeneralFrame generalFrame = new GeneralFrame();
        ClientChat clientChat = new ClientChat() ;

        generalFrame.add(clientChat);
        generalFrame.setSize(new Dimension(generalFrame.getWidth() + 1 , generalFrame.getHeight()));
        generalFrame.setLocation(generalFrame.getX() , generalFrame.getY());

    }

}
