package Games.PURPIE.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.awt.Font.BOLD;
import static java.awt.Font.SANS_SERIF;

public class Snek extends JPanel implements ActionListener, KeyListener {
    public static String GEF = "";
    String path = GEF + "img\\";
    private final ImageIcon
            Title = new ImageIcon(path + "SnekTitle.png");
    private final ImageIcon PauseL = new ImageIcon(path + "Panels\\SnekPaused.png");
    private final ImageIcon DeadL = new ImageIcon(path + "Panels\\SnekDead.png");
    private final ImageIcon PauseD = new ImageIcon(path + "Panels\\SnekPausedDark.png");
    private final ImageIcon DeadD = new ImageIcon(path + "Panels\\SnekDeadDark.png");
    public static final JFrame frame = new JFrame("SNACK ATACK!");
    //--------------------------------------------------------------------
    Random random = new Random();
    int[] snekX = new int[385], snekY = new int[385];
    int foodX, foodY, foodC, lengt = 3;
    boolean isStart, isDead, isLight;
    int direct;
    Timer timer = new Timer(80, this); // Таймер, чем меньше delay, тем быстрее ползает змейка
    Color scorCol = new Color(181, 230, 29); // Цвет таблички с счетом. Изначально: 181, 230, 29
    Color scColor = new Color(34, 177, 76); // Цвет текста для счета. Изначально: 34, 177, 76
    Color fildColL = new Color(234, 234, 234); // Цвет поля со змейкой
    Color fildColD = new Color(41, 41, 41); // Цвет поля со змейкой

    //--------------------------------------------------------------------

    public Snek() {
        frame.setSize(626, 510);
        frame.setLocationRelativeTo(null);
        ImageIcon purpieIcon = new ImageIcon(path + "Icon.png");
        frame.setIconImage(purpieIcon.getImage());
        frame.setResizable(false);
        frame.setBackground(new Color(34, 177, 76));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(this);

        this.setFocusable(true);
        this.addKeyListener(this);
        setup();
        timer.start();
    }

    public void paint(Graphics graphics) {

        graphics.setColor(scColor);
        graphics.fillRect(0, 0, 626, 510);
        graphics.setColor(scorCol);
        graphics.fillRect(5, 6, 187, 52);
        if (isLight) graphics.setColor(fildColL);
        else graphics.setColor(fildColD);
        graphics.fillRect(5, 65, 600, 400);
        Title.paintIcon(this, graphics, 5, 5);

        BufferedImage bigImg;
        try {
            bigImg = ImageIO.read(new File(path + "Sprites.png"));
            final int width = 25, height = 25, rows = 4, cols = 4;
            BufferedImage[] sprites = new BufferedImage[rows * cols];
            for (int i = 0; i < rows; i++) for (int j = 0; j < cols; j++)
            sprites[(i * cols) + j] = bigImg.getSubimage(j * width, i * height, width, height);

            switch (direct) {
                case 1 -> new ImageIcon(sprites[0]).paintIcon(this, graphics, snekX[0], snekY[0]);
                case 2 -> new ImageIcon(sprites[1]).paintIcon(this, graphics, snekX[0], snekY[0]);
                case 3 -> new ImageIcon(sprites[2]).paintIcon(this, graphics, snekX[0], snekY[0]);
                case 4 -> new ImageIcon(sprites[3]).paintIcon(this, graphics, snekX[0], snekY[0]);
            }

            for (int i = 1; i < lengt - 1; i++)
                new ImageIcon(sprites[8]).paintIcon(this, graphics, snekX[i], snekY[i]);

            if (snekX[lengt - 2] > snekX[lengt - 1])
                new ImageIcon(sprites[7]).paintIcon(this, graphics, snekX[lengt - 1], snekY[lengt - 1]);
            else if (snekX[lengt - 2] < snekX[lengt - 1])
                new ImageIcon(sprites[6]).paintIcon(this, graphics, snekX[lengt - 1], snekY[lengt - 1]);
            else if (snekY[lengt - 2] > snekY[lengt - 1])
                new ImageIcon(sprites[5]).paintIcon(this, graphics, snekX[lengt - 1], snekY[lengt - 1]);
            else if (snekY[lengt - 2] < snekY[lengt - 1])
                new ImageIcon(sprites[4]).paintIcon(this, graphics, snekX[lengt - 1], snekY[lengt - 1]);

            new ImageIcon(sprites[foodC]).paintIcon(this, graphics, foodX, foodY);                // Отрисовка еды на поле

            if (!isStart) {
                if (isLight) PauseL.paintIcon(this, graphics, 5, 65);
                else PauseD.paintIcon(this, graphics, 5, 65);
            }

            if (isDead) {
                if (isLight) DeadL.paintIcon(this, graphics, 5, 65);
                else DeadD.paintIcon(this, graphics, 5, 65);
            }
        } catch (IOException ignore) {/**/}

        graphics.setColor(scColor);                                     // Установка цвета графики (зеленый)
        graphics.setFont(new Font(SANS_SERIF, BOLD, 35));           // Шрифт надписи со счетом (зеленый)
        graphics.drawString("Score: " + (lengt - 3), 23, 44);    // Счет игры, равный длине минус 3
    }

    public void food() {
        foodC = random.nextInt(6) + 9;
        foodX = random.nextInt(24) * 25 + 5;
        foodY = random.nextInt(16) * 25 + 65;
        for (int i = 1; i < lengt; i++) {
            if (foodX == snekX[i] && foodY == snekY[i]) {
                foodX = random.nextInt(24) * 25 + 5;
                foodY = random.nextInt(16) * 25 + 65;
                i = 1;
            }
        }
    }

    public void setup() {  // Функция SETUP вызывается, когда игра начинается сначала
        isStart = false;
        isDead = false;
        lengt = 3;
        direct = 4;
        food();
        for (int i = 0; i < 3; i++) {
            snekX[i] = 105 - 25 * i;
            snekY[i] = 90;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int KeyCode = keyEvent.getKeyCode();
        if (KeyCode == KeyEvent.VK_SPACE || KeyCode == KeyEvent.VK_ESCAPE) {
            if (isDead) setup();
            else isStart = !isStart;
        } else if (KeyCode == KeyEvent.VK_T)
            isLight = !isLight;
        else if ((KeyCode == KeyEvent.VK_UP || KeyCode == KeyEvent.VK_W) && direct != 2 && isStart)
            direct = 1;
        else if ((KeyCode == KeyEvent.VK_DOWN || KeyCode == KeyEvent.VK_S) && direct != 1 && isStart)
            direct = 2;
        else if ((KeyCode == KeyEvent.VK_LEFT || KeyCode == KeyEvent.VK_A) && direct != 4 && isStart)
            direct = 3;
        else if ((KeyCode == KeyEvent.VK_RIGHT || KeyCode == KeyEvent.VK_D) && direct != 3 && isStart)
            direct = 4;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();                       // Запускается таймер, раз в "delay" змейка движется на одну клетку
        if (isStart && !isDead) {               // Если змейка не мертва и игра не на паузе...
            for (int i = lengt; i > 0; i--) {
                snekX[i] = snekX[i - 1];       // Каждый сегмент тела перемещается по оси X, если движение горизонтально
                snekY[i] = snekY[i - 1];       // Каждый сегмент тела перемещается по оси Y, если движение вертикально
            }

            switch (direct) {
                case 1 -> {
                    snekY[0] = snekY[0] - 25;                            // Движение всего тела на клетку вверх
                    if (snekY[0] < 65) {
                        isDead = true;
                        snekY[0] += 25;
                    }
                }
                case 2 -> {
                    snekY[0] = snekY[0] + 25;                             // Движение всего тела на клетку вниз
                    if (snekY[0] > 440) {
                        isDead = true;
                        snekY[0] -= 25;
                    }
                }
                case 3 -> {
                    snekX[0] = snekX[0] - 25;                           // Движение всего тела на клетку влево
                    if (snekX[0] < 5) {
                        isDead = true;
                        snekX[0] += 25;
                    }
                }
                case 4 -> {
                    snekX[0] = snekX[0] + 25;                             // Движение всего тела на клетку вправо
                    if (snekX[0] > 580) {
                        isDead = true;
                        snekX[0] -= 25;
                    }
                }
            }

            if (snekX[0] == foodX && snekY[0] == foodY) {  // Если еда съедена...
                lengt++;                                  // Длина змейки увеличивается на 1
                food();                                   // Еда появляется вновь
            }

            for (int i = 1; i < lengt; i++) {
                if (snekX[0] == snekX[i] && snekY[0] == snekY[i]) { // Если голова змейки упирается в сегмент тела...
                    isDead = true;                                  // Змейка умирает
                    break;
                }
            }


        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new Snek();
    }
}
