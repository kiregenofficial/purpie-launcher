package Games.PURPIE.old;

import Games.PURPIE.src.Snek;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GameLauncher extends JFrame implements ActionListener {
    public boolean isFrameExtended = false, isSoundSwitchOn = true, isMusicSwitchOn = false;
    public GameLauncher() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File file = new File("G:\\Kira\\TheTagGame\\Diskach.wav");
        String icon = "G:\\Kira\\TheTagGame\\Icon.png";

        AudioInputStream stream;
        Clip clip;
        clip = AudioSystem.getClip();
        stream = AudioSystem.getAudioInputStream(file);
        clip.open(stream);

// VARIABLES ---------------------------------------------------------------
        Font f1 = new Font("", Font.BOLD, 25);
        Font f2 = new Font("", Font.BOLD, 45);
        Color c1 = Color.gray;
        Color c2 = new Color(79, 79, 79);
        Color c3 = new Color(175, 175, 175);
        Color purple = new Color(210, 200, 255); // 210 200 255
        Border b1 = BorderFactory.createBevelBorder(1, c1, c1, c1, c1);


// FRAME -------------------------------------------------------------------
        JFrame frame = new JFrame("PURPIE");
        frame.setIconImage(new ImageIcon(icon).getImage());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(276, 430);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

// CONTAINER ---------------------------------------------------------------

//
//
//
//        panel.setBackground(purple);
//
//// BACKGROUND --------------------------------------------------------------
//
//        panelk
//        panelk.setVisible(true);
//        frame.add(panelk);
//        panelk.add(panel);
//
//        panelk.add(logoo);

        String purpie = "G:\\Kira\\TheTagGame\\Purpie.png";
        BufferedImage Imag = ImageIO.read(new File(purpie));
        JLabel panelka = new JLabel(new ImageIcon(Imag));
        panelka.setBounds(0, 0, 676, 430);
        panelka.setVisible(true);
        panelka.setLayout(null);
        frame.add(panelka);

        JTable panel = new JTable();
        panel.setBounds(0, 0, 676, 430);
        panel.setLayout(null);
        panel.setBackground(purple);
        panel.setVisible(true);
        panelka.add(panel);

// TABLE -------------------------------------------------------------------
        JTable label = new JTable();
        label.setBounds(260, 30, 370, 245);
        label.setBackground(Color.white);
        label.setBorder(b1);
        panel.add(label);
        label.setVisible(false);

        JTable wantexit = new JTable();
        wantexit.setBounds(260, 30, 370, 245);
        wantexit.setBackground(Color.white);
        wantexit.setBorder(b1);
        panel.add(wantexit);
        wantexit.setVisible(false);

        JLabel wannaquit = new JLabel();
        wannaquit.setBounds(0, 0, 370, 245);
        wannaquit.setVerticalAlignment(SwingConstants.CENTER);
        wannaquit.setHorizontalAlignment(SwingConstants.CENTER);
        wannaquit.setFont(f1);
        wantexit.add(wannaquit);

        JTable wantplay = new JTable();
        wantplay.setBounds(260, 30, 370, 245);
        wantplay.setBackground(Color.white);
        wantplay.setBorder(b1);
        panel.add(wantplay);
        wantplay.setVisible(false);

        JTable information = new JTable();
        information.setBounds(260, 30, 370, 245);
        information.setBackground(Color.white);
        information.setBorder(b1);
        panel.add(information);
        information.setVisible(false);

        JTextPane informatia = new JTextPane();
        informatia.setBounds(5, 5, 355, 235);
        informatia.setForeground(c1);
        informatia.setFont(f1);
        information.add(informatia);

        JTable switch1 = new JTable(); // MUSIC
        switch1.setBounds(15, 15, 95, 45);
        switch1.setBackground(purple);
        switch1.setBorder(b1);
        label.add(switch1);

        JTable switch2 = new JTable();
        switch2.setBounds(5, 5, 45, 35);
        switch2.setBackground(new Color(175, 175, 175));
        switch2.setBorder(b1);
        switch1.add(switch2);

        JLabel label1 = new JLabel("MUSIC");
        label1.setFont(f2);
        label1.setBounds(120, 15, 300, 45);
        label1.setForeground(c2);
        label.add(label1);

        JTable switch3 = new JTable(); // SOUND
        switch3.setBounds(15, 75, 95, 45);
        switch3.setBackground(purple);
        switch3.setBorder(b1);
        label.add(switch3);

        JTable switch4 = new JTable();
        switch4.setBounds(45, 5, 45, 35);
        switch4.setBackground(Color.white);
        switch4.setBorder(b1);
        switch3.add(switch4);

        JLabel label2 = new JLabel("SOUNDS");
        label2.setFont(f2);
        label2.setBounds(120, 75, 300, 45);
        label2.setForeground(c2);
        label.add(label2);

// BUTTONS -----------------------------------------------------------------
        JButton[] buttons = new JButton[4];
        JButton[] options = new JButton[2];
        String[] names = {"PLAY", "MENU", "INFO", "QUIT"};

        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(30, i * 85 + 30, 200, 75);
            buttons[i].setBackground(Color.white);
            buttons[i].setFont(f1);
            buttons[i].setText(names[i]);
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(b1);
            panel.add(buttons[i]);
        }

        for (int i = 0; i < 2; i++) {
            options[i] = new JButton();
            options[i].setBounds(260 + i * 190, 285, 180, 75);
            options[i].setBackground(Color.white);
            options[i].setFont(f1);
            options[i].setFocusPainted(false);
            options[i].setBorder(b1);
            panel.add(options[i]);
        }
        // 260, 30, 370, 245
        // i * 85 + 30
        options[0].setText("YES");
        options[1].setText("NO");

        frame.setVisible(true);

// PLAY --------------------------------------------------------------------
        buttons[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Objects.equals(buttons[3].getText(), "QUIT")) {
                    buttons[0].setText("SNAKE");
                    buttons[1].setText("TAG");
                    buttons[2].setText("...");
                    buttons[2].setEnabled(false);
                    buttons[3].setText("BACK");
                    options[0].setText("...");
                    options[0].setEnabled(false);
                    options[1].setText("...");
                    options[1].setEnabled(false);
                    Retract(frame, wantplay);
                } else if (Objects.equals(buttons[0].getText(), "SNAKE")) {
                    if (isFrameExtended) {
                        buttons[0].setText("PLAY");
                        buttons[0].setEnabled(true);
                        buttons[1].setText("MENU");
                        buttons[1].setEnabled(true);
                        buttons[2].setText("INFO");
                        buttons[2].setEnabled(true);
                        buttons[3].setText("QUIT");
                        buttons[3].setEnabled(true);
                        options[0].setEnabled(true);
                        options[1].setEnabled(true);
                        Retract(frame, wantplay);
                    }
                    new Snek();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Objects.equals(buttons[0].getText(), "...")) {
                    buttons[0].setBackground(c3);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons[0].setBackground(Color.white);
            }
        });

// MENU --------------------------------------------------------------------

        buttons[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Objects.equals(buttons[3].getText(), "QUIT")) {
                    buttons[0].setText("AUDIO");
                    buttons[0].setEnabled(false);
                    buttons[1].setText("CONTROLS");
                    buttons[1].setEnabled(false);
                    buttons[2].setText("VIDEO");
                    buttons[2].setEnabled(false);
                    buttons[3].setText("BACK");
                    Retract(frame, label);
                    options[0].setText("RESET");
                    options[1].setText("APPLY");
                } else if (Objects.equals(buttons[1].getText(), "TAG")) {
                    if (isFrameExtended) {
                        buttons[0].setText("PLAY");
                        buttons[0].setEnabled(true);
                        buttons[1].setText("MENU");
                        buttons[1].setEnabled(true);
                        buttons[2].setText("INFO");
                        buttons[2].setEnabled(true);
                        buttons[3].setText("QUIT");
                        buttons[3].setEnabled(true);
                        options[0].setEnabled(true);
                        options[1].setEnabled(true);
                        Retract(frame, label);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Objects.equals(buttons[1].getText(), "...")) {
                    buttons[1].setBackground(c3);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons[1].setBackground(Color.white);
            }
        });

// INFO --------------------------------------------------------------------

        buttons[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!Objects.equals(buttons[2].getText(), "...") && Objects.equals(buttons[3].getText(), "QUIT")) {
                    buttons[0].setText("...");
                    buttons[0].setEnabled(false);
                    buttons[1].setText("...");
                    buttons[1].setEnabled(false);
                    buttons[2].setText("...");
                    buttons[2].setEnabled(false);
                    buttons[3].setText("BACK");
                    options[0].setText("...");
                    options[0].setEnabled(false);
                    options[1].setText("...");
                    options[1].setEnabled(false);
                    Retract(frame, information);
                    informatia.setText("""
                            Пожалуйста, не нажимайте
                            на кнопки слишком быстро!

                            Управляйте играми мышкой
                            или клавиатурой :)
                            """);
                } else if (!Objects.equals(buttons[3].getText(), "QUIT")) {
                    informatia.setText("");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Objects.equals(buttons[2].getText(), "...")) {
                    buttons[2].setBackground(c3);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons[2].setBackground(Color.white);
            }
        });

// QUIT --------------------------------------------------------------------

        buttons[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Objects.equals(buttons[3].getText(), "QUIT")) {
                    buttons[3].setText("BACK");
                    Retract(frame, wantexit);
                    options[0].setText("QUIT");
                    options[1].setText("STAY");
                    buttons[0].setText("...");
                    buttons[0].setEnabled(false);
                    buttons[1].setText("...");
                    buttons[1].setEnabled(false);
                    buttons[2].setText("...");
                    buttons[2].setEnabled(false);
                    buttons[3].setText("...");
                    buttons[3].setEnabled(false);
                    wannaquit.setText("Want to quit?");
                } else if (!Objects.equals(buttons[3].getText(), "...")) {
                    buttons[0].setText("PLAY");
                    buttons[0].setEnabled(true);
                    buttons[1].setText("MENU");
                    buttons[1].setEnabled(true);
                    buttons[2].setText("INFO");
                    buttons[2].setEnabled(true);
                    buttons[3].setText("QUIT");
                    buttons[3].setEnabled(true);
                    options[0].setEnabled(true);
                    options[1].setEnabled(true);
                    Retract(frame, label);
                    wannaquit.setText("");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Objects.equals(buttons[3].getText(), "...")) {
                    buttons[3].setBackground(c3);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttons[3].setBackground(Color.white);
            }
        });

        Clip finalClip = clip;
        switch1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MusicSwitch(switch2);
                if (!isMusicSwitchOn && !Objects.requireNonNull(finalClip).isRunning()) {
                    finalClip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    finalClip.stop();
                }
            }
        });

        switch2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MusicSwitch(switch2);
                if (!isMusicSwitchOn && !Objects.requireNonNull(finalClip).isRunning()) {
                    finalClip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    finalClip.stop();
                }
            }
        });

        switch3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SoundSwitch(switch4);
            }
        });

        switch4.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SoundSwitch(switch4);
            }
        });

        options[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Objects.equals(options[0].getText(), "QUIT")) {
                    System.exit(0);
                } else if (Objects.equals(options[0].getText(), "RESET")) {
                    if (isMusicSwitchOn) {
                        MusicSwitch(switch2);
                        finalClip.stop();
                    }
                    if (!isSoundSwitchOn) {
                        SoundSwitch(switch4);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Objects.equals(options[0].getText(), "...")) {
                    options[0].setBackground(c3);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                options[0].setBackground(Color.white);
            }
        });

        options[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Objects.equals(options[1].getText(), "STAY") || Objects.equals(options[1].getText(), "APPLY")) {
                    buttons[0].setText("PLAY");
                    buttons[0].setEnabled(true);
                    buttons[1].setText("MENU");
                    buttons[1].setEnabled(true);
                    buttons[2].setText("INFO");
                    buttons[2].setEnabled(true);
                    buttons[3].setText("QUIT");
                    buttons[3].setEnabled(true);
                    Retract(frame, wantexit);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!Objects.equals(options[1].getText(), "...")) {
                    options[1].setBackground(c3);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                options[1].setBackground(Color.white);
            }
        });


    }

    public void paint(Graphics graphics) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void MusicSwitch(Component a) {
        class SwitchSwitch extends Thread {
            @Override
            public void run() {
                if (!isMusicSwitchOn) {
                    for (int i = 0; i <= 40; i++) {
                        a.setLocation(5 + i, a.getY());
                        a.setBackground(new Color(175 + i * 2, 175 + i * 2, 175 + i * 2));
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException exx) {
                            exx.getCause();
                        }
                    }
                    isMusicSwitchOn = true;
                } else {
                    for (int i = 40; i >= 0; i--) {
                        a.setLocation(5 + i, a.getY());
                        a.setBackground(new Color(175 + i * 2, 175 + i * 2, 175 + i * 2));
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException exx) {
                            exx.getCause();
                        }
                    }
                    isMusicSwitchOn = false;
                }
            }
        }
        Thread thrd = new SwitchSwitch();
        thrd.start();
    }

    public void SoundSwitch(Component a) {
        class SwitchSwitchSwitch extends Thread {
            @Override
            public void run() {
                if (!isSoundSwitchOn) {
                    for (int i = 0; i <= 40; i++) {
                        a.setLocation(5 + i, a.getY());
                        a.setBackground(new Color(175 + i * 2, 175 + i * 2, 175 + i * 2));
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException exx) {
                            exx.getCause();
                        }
                    }
                    isSoundSwitchOn = true;
                } else {
                    for (int i = 40; i >= 0; i--) {
                        a.setLocation(5 + i, a.getY());
                        a.setBackground(new Color(175 + i * 2, 175 + i * 2, 175 + i * 2));
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException exx) {
                            exx.getCause();
                        }
                    }
                    isSoundSwitchOn = false;
                }
            }
        }
        Thread thrd = new SwitchSwitchSwitch();
        thrd.start();
    }

    public void Retract(JFrame frame, JTable label) {
        class WidthExtend extends Thread {
            @Override
            public void run() {
                if (!isFrameExtended) {
                    label.setVisible(true);
                    isFrameExtended = true;
                    for (int i = 0; i <= 80; i++) {
                        frame.setSize(276 + i * 5, 430);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    isFrameExtended = false;
                    for (int i = 80; i >= 0; i--) {
                        frame.setSize(276 + i * 5, 430);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    label.setVisible(false);
                }
            }
        }
        Thread thread = new WidthExtend();
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        new GameLauncher();
        Thread.sleep(1000);
    }
}
