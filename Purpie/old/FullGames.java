package Games.PURPIE.old;

import Games.PURPIE.src.Snek;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FullGames extends JFrame implements KeyListener, ActionListener {
    public static void main(String[] args) {
        new FullGames();
    }

    static boolean a = true;

    public FullGames() {
        JFrame frame = new JFrame("Games");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JButton play = new JButton("PLAY");
        JButton menu = new JButton("MENU");
        JButton exit = new JButton("EXIT");

        play.setBounds(10, 40, 150, 50);
        panel.add(play);
        play.addActionListener(new Action0());
        play.setFocusPainted(false);
        play.setBackground(Color.white);

        menu.setBounds(10, 95, 150, 50);
        panel.add(menu);
        menu.addActionListener(new Action1());
        menu.setFocusPainted(false);
        menu.setBackground(Color.white);

        exit.setBounds(10, 150, 150, 50);
        panel.add(exit);
        exit.addActionListener(new Action2());
        exit.setFocusPainted(false);
        exit.setBackground(Color.white);

        panel.add(exit);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static class Action0 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Snek();
            JComponent component = (JComponent) e.getSource();
            Window window = SwingUtilities.getWindowAncestor(component);
            window.dispose();
        }
    }

    public static class Action1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame2 = new JFrame("Settings");
            frame2.setSize(230, 320);
            frame2.setLocationRelativeTo(null);
            frame2.setVisible(true);

            JLabel music = new JLabel("Music");
            music.setBounds(100, 100, 50, 30);

            JPanel musicPan = new JPanel();
            musicPan.setLayout(null);
            musicPan.add(music);
            frame2.add(musicPan);

            JButton m = new JButton("Musec");
            m.addActionListener(new Musec());
            m.setBounds(10, 10, 100, 50);
            musicPan.add(m);

        }
    }

    public static class Action2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame("Wanna leave?");
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setBackground(new Color(255, 255, 255));

            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(null);

            JButton ex = new JButton("Exit");
            ex.setBounds(7, 100, 132, 50);
            panel.add(ex);
            ex.addActionListener(new Exit());
            ex.setFocusPainted(false);
            ex.setBackground(Color.white);

            JButton st = new JButton("Stay");
            st.setBounds(146, 100, 132, 50);
            panel.add(st);
            st.addActionListener(new Stay());
            st.setFocusPainted(false);
            st.setBackground(Color.white);

            frame.setVisible(true);
        }
    }

    public static class Exit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(130);
        }
    }

    public static class Stay implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }

    public static class Musec extends Thread implements ActionListener, MouseListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileInputStream file = null;
            Player playMP3 = null;
            try {
                file = new FileInputStream("G:\\Kira\\TheTagGame\\3.mp3");
                playMP3 = new Player(file);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            Player finalPlayMP = playMP3;
            class MyThread extends Thread {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        try {
                            finalPlayMP.play();
                        } catch (JavaLayerException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
            Thread thread = new MyThread();
            a = !a;
            if (a) {
                thread.start();
            } else thread.interrupt();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
