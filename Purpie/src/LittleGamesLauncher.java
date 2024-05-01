package Games.PURPIE.src;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LittleGamesLauncher {
    private boolean isFrameExtended = false;
    private static final JTable[] RightWindow = new JTable[4];
    private static final JButton[] MenuButtons = new JButton[6];
    private static final JTable MusicSwitch = new JTable();
    private static final JTable SoundSwitch = new JTable();
    private static int ChoosenGame = 0;
    private LittleGamesLauncher() throws Exception {
//        File SoundTrack = new File("wav\\Soundtrack.wav");
          ImageIcon PurpieIcon = new ImageIcon("img\\Icon.png");

        // Музыкальный плеер, нужен для проигрывания саундтреков игр
//        AudioInputStream SoundTrackInputStream;
//        Clip OST;
//        OST = AudioSystem.getClip();
//        SoundTrackInputStream = AudioSystem.getAudioInputStream(SoundTrack);
//        OST.open(SoundTrackInputStream);

        // Шрифты, цвета и границы для элементов
        Font ButtonFont = new Font("", Font.BOLD, 25);
        Font DetailFont = new Font("", Font.BOLD, 45);
        Color DarkGray = new Color(79, 79, 79);
        Color Gray = new Color(128, 128, 128);
        Color LightGray = new Color(175, 175, 175);
        Color Purple = new Color(210, 200, 255); // 210 200 255 // D2C8FFFF
        Border GrayBorder = BorderFactory.createBevelBorder(0, Gray, Gray, Gray, Gray);

        // Рамка появляется в сложенном виде, при использовании
        // Extend(frame) меняет состояние на разложенное и наоборот
        JFrame PurpieFrame = new JFrame("PURPIE");
        PurpieFrame.setIconImage(PurpieIcon.getImage());
        PurpieFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        PurpieFrame.setSize(276, 430);
        PurpieFrame.setLocationRelativeTo(null);
        PurpieFrame.setResizable(false);

        // Задний фон, содержит логотип и название приложения
        File PurpieTitle = new File("img\\Purpie.png");
        BufferedImage PurpTitle = ImageIO.read(PurpieTitle);
        JLabel BackgroundTitle = new JLabel(new ImageIcon(PurpTitle));
        BackgroundTitle.setBounds(0, 0, 676, 430);
        BackgroundTitle.setLayout(null);
        PurpieFrame.add(BackgroundTitle);

        // Главная панель, на ней распологаются все компоненты приложения
        JTable MainPanel = new JTable();
        MainPanel.setBounds(0, 0, 676, 430);
        MainPanel.setLayout(null);
        MainPanel.setBackground(Purple);
        BackgroundTitle.add(MainPanel);

        // Окно справа от меню, появляется в разложенном состоянии рамки
        for (int i = 0; i < 4; i++) {
            RightWindow[i] = new JTable();
            RightWindow[i].setBounds(260, 30, 370, 245);
            RightWindow[i].setBackground(Color.white);
            RightWindow[i].setBorder(GrayBorder);
            RightWindow[i].setVisible(false);
            MainPanel.add(RightWindow[i]);
        }

        JTextPane InfoText = new JTextPane();
        InfoText.setText("""
                Пожалуйста, не нажимайте
                на кнопки слишком быстро!

                Управляйте играми мышкой
                или клавиатурой :)
                """);
        InfoText.setBounds(8, 5, 355, 320);
        RightWindow[2].setSize(370, 330);
        InfoText.setForeground(Gray);
        InfoText.setFont(ButtonFont);
        RightWindow[2].add(InfoText);

        JTextPane ExitText = new JTextPane();
        ExitText.setText("""
                Вы уверены, что хотите выйти?
                """);
        ExitText.setBounds(8, 5, 355, 235);
        ExitText.setForeground(Gray);
        ExitText.setFont(ButtonFont);
        RightWindow[3].add(ExitText);

        // Первые два (фон и сам ползунок) - переключатель игровой музыки вкл/выкл
        JTable MusicSwitchBox = new JTable(); // MUSIC
        MusicSwitchBox.setBounds(15, 15, 95, 45);
        MusicSwitchBox.setBackground(Purple);
        MusicSwitchBox.setBorder(GrayBorder);
        RightWindow[1].add(MusicSwitchBox);

        MusicSwitch.setBounds(5, 5, 45, 35);
        MusicSwitch.setBackground(LightGray);
        MusicSwitch.setBorder(GrayBorder);
        MusicSwitchBox.add(MusicSwitch);

        // Вторые два (фон и сам ползунок) - переключатель игровых звуков вкл/выкл
        JTable SoundSwitchBox = new JTable(); // SOUND
        SoundSwitchBox.setBounds(15, 75, 95, 45);
        SoundSwitchBox.setBackground(Purple);
        SoundSwitchBox.setBorder(GrayBorder);
        RightWindow[1].add(SoundSwitchBox);

        SoundSwitch.setBounds(5, 5, 45, 35);
        SoundSwitch.setBackground(LightGray);
        SoundSwitch.setBorder(GrayBorder);
        SoundSwitchBox.add(SoundSwitch);

        JLabel[] SwitchLabel = {new JLabel(), new JLabel()};
        String[] SwitchLabelNames = {"MUSIC", "SOUNDS"};
        for (int i = 0; i < 2; i++) {
            SwitchLabel[i].setText(SwitchLabelNames[i]);
            SwitchLabel[i].setFont(DetailFont);
            SwitchLabel[i].setBounds(120, 15 + 60 * i, 300, 45);
            SwitchLabel[i].setForeground(DarkGray);
            RightWindow[1].add(SwitchLabel[i]);
        }

        // Создает кнопки располагает 4 из них в меню и 2 под окном справа от меню
        // Присваивает им имена и положение
        String[] MenuButtonsNames = {"PLAY", "MENU", "INFO", "QUIT"};

        for (int i = 0; i < 6; i++) {
            MenuButtons[i] = new JButton();
            MenuButtons[i].setBackground(Color.white);
            MenuButtons[i].setFont(ButtonFont);
            MenuButtons[i].setFocusPainted(false);
            MenuButtons[i].setBorder(GrayBorder);
            MainPanel.add(MenuButtons[i]);
        }
        for (int i = 0; i < 4; i++) {
            MenuButtons[i].setBounds(30, i * 85 + 30, 200, 75);
            MenuButtons[i].setText(MenuButtonsNames[i]);
        }
        for (int i = 0; i < 2; i++) {
            MenuButtons[i + 4].setBounds(260 + i * 190, 285, 180, 75);
            MenuButtons[i + 4].setVisible(false);
        }

        PurpieFrame.setVisible(true);

        MenuButtons[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MenuButtons[0].isEnabled()) {
                    PlayNote("A");
                    if (Objects.equals(MenuButtons[3].getText(), "QUIT")) {
                        String[] tempMenuNames = {"SNAKE", "TAG", "2048", "BACK", "", "PLAY"};
                        for (int i = 0; i < 6; i++) {
                            MenuButtons[i].setText(tempMenuNames[i]);
                        }
                        MenuButtons[4].setEnabled(false);
                        MenuButtons[5].setEnabled(false);
                        MenuButtons[4].setVisible(true);
                        MenuButtons[5].setVisible(true);
                        Retract(PurpieFrame, RightWindow[0]);
                    } else if (Objects.equals(MenuButtons[0].getText(), "SNAKE")) {
                        ChoosenGame = 1;
                        MenuButtons[5].setEnabled(true);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (MenuButtons[0].isEnabled()) {
                    PlayNote("F");
                    MenuButtons[0].setBackground(LightGray);
                } else MenuButtons[0].setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MenuButtons[0].setBackground(Color.white);
            }
        });

        MenuButtons[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (MenuButtons[1].isEnabled()) {
                    PlayNote("A");
                    if (Objects.equals(MenuButtons[3].getText(), "QUIT")) {
                        String[] tempMenuNames = {"AUDIO", "CONTROLS", "VIDEO", "BACK", "RESET", "APPLY"};
                        for (int i = 0; i < 6; i++)
                            MenuButtons[i].setText(tempMenuNames[i]);
                        for (int i = 0; i < 3; i++)
                            MenuButtons[i].setEnabled(false);
                        MenuButtons[4].setVisible(true);
                        MenuButtons[5].setVisible(true);
                        Retract(PurpieFrame, RightWindow[1]);
                    } else if (Objects.equals(MenuButtons[1].getText(), "TAG")) {
                        ChoosenGame = 2;
                        MenuButtons[5].setEnabled(true);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (MenuButtons[1].isEnabled()) {
                    PlayNote("E");
                    MenuButtons[1].setBackground(LightGray);
                } else MenuButtons[1].setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MenuButtons[1].setBackground(Color.white);
            }
        });

        MenuButtons[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MenuButtons[2].isEnabled()) {
                    PlayNote("A");
                    if (MenuButtons[2].isEnabled() && Objects.equals(MenuButtons[3].getText(), "QUIT")) {
                        String[] tempMenuNames = {"...", "...", "...", "BACK", "", ""};
                        for (int i = 0; i < 6; i++) {
                            MenuButtons[i].setText(tempMenuNames[i]);
                            MenuButtons[i].setEnabled(false);
                        }
                        MenuButtons[3].setEnabled(true);
                        Retract(PurpieFrame, RightWindow[2]);
                    } else if (Objects.equals(MenuButtons[2].getText(), "2048")) {
                        ChoosenGame = 3;
                        MenuButtons[5].setEnabled(true);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (MenuButtons[2].isEnabled()) {
                    PlayNote("D");
                    MenuButtons[2].setBackground(LightGray);
                } else MenuButtons[2].setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MenuButtons[2].setBackground(Color.white);
            }
        });

        MenuButtons[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MenuButtons[3].isEnabled()) {
                    PlayNote("A");
                    if (Objects.equals(MenuButtons[3].getText(), "QUIT")) {
                        String[] tempMenuNames = {"...", "...", "...", "...", "QUIT", "STAY"};
                        for (int i = 0; i < 6; i++)
                            MenuButtons[i].setText(tempMenuNames[i]);
                        for (int i = 0; i < 4; i++)
                            MenuButtons[i].setEnabled(false);
                        MenuButtons[4].setVisible(true);
                        MenuButtons[5].setVisible(true);
                        Retract(PurpieFrame, RightWindow[3]);
                    } else if (MenuButtons[3].isEnabled()) {
                        String[] tempMenuNames = {"PLAY", "MENU", "INFO", "QUIT", "", ""};
                        for (int i = 0; i < 6; i++) {
                            MenuButtons[i].setText(tempMenuNames[i]);
                            MenuButtons[i].setEnabled(true);
                        }
                        Retract(PurpieFrame, RightWindow[3]);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (MenuButtons[3].isEnabled()) {
                    PlayNote("C");
                    MenuButtons[3].setBackground(LightGray);
                } else MenuButtons[3].setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MenuButtons[3].setBackground(Color.white);
            }
        });

//        Clip FinOST = OST;
//        MusicSwitchBox.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                SwitchStateChange(MusicSwitch);
//                if (MusicSwitch.getX() == 5 && !Objects.requireNonNull(FinOST).isRunning()) {
//                    FinOST.loop(Clip.LOOP_CONTINUOUSLY);
//                } else {
//                    FinOST.stop();
//                    FinOST.setFramePosition(0);
//                }
//            }
//        });

//        MusicSwitch.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                SwitchStateChange(MusicSwitch);
//                if (MusicSwitch.getX() == 5 && !Objects.requireNonNull(FinOST).isRunning()) {
//                    FinOST.loop(Clip.LOOP_CONTINUOUSLY);
//                } else {
//                    FinOST.stop();
//                }
//            }
//        });

        SoundSwitchBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SwitchStateChange(SoundSwitch);
            }
        });

        SoundSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SwitchStateChange(SoundSwitch);
            }
        });

        MenuButtons[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MenuButtons[4].isEnabled()) {
                    PlayNote("A");
                    if (Objects.equals(MenuButtons[4].getText(), "QUIT")) {
                        System.exit(0);
                    } else if (Objects.equals(MenuButtons[4].getText(), "RESET")) {
                        if (MusicSwitch.getX() == 45) {
                            SwitchStateChange(MusicSwitch);
//                            FinOST.stop();
                        }
                        if (SoundSwitch.getX() == 5) {
                            SwitchStateChange(SoundSwitch);
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (MenuButtons[4].isEnabled()) {
                    PlayNote("G");
                    MenuButtons[4].setBackground(LightGray);
                } else MenuButtons[4].setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MenuButtons[4].setBackground(Color.white);
            }
        });

        MenuButtons[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MenuButtons[5].isEnabled()) {
                    PlayNote("A");
                    if (ChoosenGame == 1) {
                        Games.PURPIE.src.Snek.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        new Games.PURPIE.src.Snek();
                    } else if (ChoosenGame == 2) {
                        Games.PURPIE.src.Tags.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        new Games.PURPIE.src.Tags(4, 550, 30);
                    } else if (ChoosenGame == 3) {
                        Games.PURPIE.src.TwentyFourtyEight.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        new Games.PURPIE.src.TwentyFourtyEight();
                    }
                    if (isFrameExtended && ChoosenGame != 0 ||
                            Objects.equals(MenuButtons[5].getText(), "STAY") ||
                            Objects.equals(MenuButtons[5].getText(), "APPLY")) {
                        String[] tempMenuNames = {"PLAY", "MENU", "INFO", "QUIT", "", ""};
                        for (int i = 0; i < 6; i++) {
                            MenuButtons[i].setText(tempMenuNames[i]);
                            MenuButtons[i].setEnabled(true);
                        }
                        MenuButtons[5].setEnabled(false);
                        Retract(PurpieFrame, RightWindow[0]);
                    }
                    ChoosenGame = 0;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (MenuButtons[5].isEnabled()) {
                    PlayNote("G");
                    MenuButtons[5].setBackground(LightGray);
                } else MenuButtons[5].setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                MenuButtons[5].setBackground(Color.white);
            }
        });
    }

    //   IT'S JUST A BURNING MEMORY...
    //   boolean ENDLESS_REGRET = true;
    //   for(int i=0; ENDLESS_REGRET; i--)
    //   {  System.out.println("~UωU");  }

    public void PlayNote(String note) {
        class PlayNote extends Thread {
            @Override
            public void run() {
                File Note = new File("wav\\" + note + ".wav");
                AudioInputStream ButtonNoteInputStream;
                Clip SND;
                try {
                    SND = AudioSystem.getClip();
                } catch (LineUnavailableException notEx) {
                    throw new RuntimeException(notEx);
                }
                try {
                    ButtonNoteInputStream = AudioSystem.getAudioInputStream(Note);
                } catch (UnsupportedAudioFileException | IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    SND.open(ButtonNoteInputStream);
                } catch (LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                }

                if (SoundSwitch.getX() == 45) {
                    SND.start();
                }

            }
        }
        Thread ChangeStateThread = new PlayNote();
        ChangeStateThread.start();
    }

    public void SwitchStateChange(Component SwitchPart) {
        class ChangeState extends Thread {
            @Override
            public void run() {
                if (SwitchPart.getX() == 5) {
                    for (int i = 0; i <= 40; i++) {
                        SwitchPart.setLocation(5 + i, SwitchPart.getY());
                        SwitchPart.setBackground(new Color(175 + i * 2, 175 + i * 2, 175 + i * 2));
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            e.getCause();
                        }
                    }
                } else {
                    for (int i = 40; i >= 0; i--) {
                        SwitchPart.setLocation(5 + i, SwitchPart.getY());
                        SwitchPart.setBackground(new Color(175 + i * 2, 175 + i * 2, 175 + i * 2));
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            e.getCause();
                        }
                    }
                }
            }
        }
        Thread ChangeStateThread = new ChangeState();
        ChangeStateThread.start();
    }

    public void Retract(JFrame frame, JTable table) {
        class WidthExtend extends Thread {
            @Override
            public void run() {
                if (!isFrameExtended) {
                    table.setVisible(true);
                    isFrameExtended = true;
                    for (int i = 0; i <= 80; i++) {
                        frame.setSize(276 + i * 5, 430);
                        if (!isFrameExtended) break;
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
                        if (isFrameExtended) break;
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    for (int i = 0; i < 4; i++)
                        RightWindow[i].setVisible(false);
                    MenuButtons[4].setVisible(false);
                    MenuButtons[5].setVisible(false);
                }
            }
        }
        Thread thread = new WidthExtend();
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        new LittleGamesLauncher();
    }
}