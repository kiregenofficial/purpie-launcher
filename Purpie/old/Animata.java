package Games.PURPIE.old;

import javax.swing.*;
import java.awt.*;

public class Animata {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("AHAHAHHAHAHAHAHAHAHA");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for (int i = 0; i < 500; i++){
            frame.setSize(200 + i*2, 200+i);
            frame.setVisible(true);
            Thread.sleep(5);
        }
    }
}
