package GenericThings;

import javax.swing.*;
import java.awt.*;

public class GeneralFrame extends JFrame{
    public GeneralFrame(){
        setSize(new Dimension((toolkit.getScreenSize().width / 4 ), (toolkit.getScreenSize().height / 2 ) ));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    Toolkit toolkit = Toolkit.getDefaultToolkit();
}
