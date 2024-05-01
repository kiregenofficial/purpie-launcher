package Games.PURPIE.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Tags extends JPanel implements ActionListener {
    private final int size;
    private final int nbTiles;
    private final int tileSize;
    private int blankPose;
    private final int margin;
    private final int gridSize;
    private static final Color FOREGROUND_COLOR = Color.white; // Константа цвета
    private static final Color EMPTINESS_COLOR = new Color(230, 227, 255); // Константа цвета
    private static final Color BACKGROUND_COLOR = new Color(210, 200, 255); // Константа цвета
    private static final Color DETAILS_COLOR = new Color(79, 79, 79); // Константа цвета
    private static final Color BORDER_COLOR = new Color(128, 128, 128);
    private static final Font BEAUTIFUL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    private static final Random RANDOM = new Random(); // Константа случайных чисел (?)
    Timer Millisecundomer = new Timer(990, this);
    private static int Time = 0;
    private static long Move = 2;
    private final int[] tiles;
    private boolean gameOver;
    public static final JFrame frame = new JFrame("The Tag Game");
    public Tags(int size, int dimension, int margin) {
        ImageIcon PurpieIcon = new ImageIcon(Snek.GEF + ":\\Kira\\Games\\PURPIE\\img\\Icon.png");

        this.size = size;
        this.margin = margin - 20;

        nbTiles = size * size - 1;
        tiles = new int[size * size];
        gridSize = dimension - 2 * size;
        tileSize = gridSize / size;

        setPreferredSize(new Dimension(561, 560 + 15 + 542 / 7));
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);
        setFont(BEAUTIFUL_FONT);

        frame.add(this, BorderLayout.CENTER);
        frame.setIconImage(PurpieIcon.getImage());
        frame.setSize(561, 560 + 15 + 542 / 7);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Millisecundomer.start();
                if (gameOver) {
                    newGame();
                } else {
                    int mouseX = mouseEvent.getX() - margin;
                    int mouseY = mouseEvent.getY() - (15 + 542 / 7 + 10);

                    if (mouseX < 0 || mouseX > gridSize || mouseY < 0 || mouseY > gridSize) return;

                    int col1 = mouseX / tileSize;
                    int row1 = mouseY / tileSize;
                    int col2 = blankPose % size;
                    int row2 = blankPose / size;

                    int clickPose = row1 * size + col1;
                    int direction = 0;

                    if (col1 == col2 && Math.abs(row1 - row2) > 0) direction = (row1 - row2) > 0 ? size : -size;
                    else if (row1 == row2 && Math.abs(col1 - col2) > 0) direction = (col1 - col2) > 0 ? 1 : -1;

                    if (direction != 0) {
                        do {
                            int newBlankPose = blankPose + direction;
                            tiles[blankPose] = tiles[newBlankPose];
                            blankPose = newBlankPose;
                        } while (blankPose != clickPose);
                        tiles[blankPose] = 0;
                        Move++;
                    }
                    gameOver = isSolved();
                }
                repaint();
            }
        });

        newGame();
    }

    private void newGame() {
        do {
            reset();
            shuffle();
        } while (!isSolvable());
        gameOver = false;
    }

    private void reset() {
        Millisecundomer.restart();
        Time = 0;
        Move = 0;
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = (i + 1) % tiles.length;
        }
        blankPose = tiles.length - 1;
    }

    private void shuffle() {
        int n = nbTiles;
        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = tiles[r];
            tiles[r] = tiles[n];
            tiles[n] = tmp;
        }
    }

    private boolean isSolvable() {
        int countInversions = 0;
        for (int i = 0; i < nbTiles; i++) {
            for (int j = 0; j < i; j++) {
                if (tiles[j] > tiles[i]) countInversions++;
            }
        }
        return countInversions % 2 == 0;
    }

    private boolean isSolved() {
        if (tiles[tiles.length - 1] != 0) {
            return false;
        }
        for (int i = nbTiles - 1; i >= 0; i--) {
            if (tiles[i] != i + 1) return false;
        }
        return true;
    }

    private void drawGrid(Graphics2D g2d) {
        for (int i = 0; i < tiles.length; i++) {
            int r = i / size, c = i % size;
            int x = margin + c * tileSize;
            int y = 15 + 542 / 7 + 10 + r * tileSize;

            if (tiles[i] == 0) {
                g2d.setColor(EMPTINESS_COLOR);
                g2d.fillRoundRect(x + 5, y + 5, tileSize - 10, tileSize - 10, 25, 25);
            } else {
                g2d.setColor(BORDER_COLOR);
                g2d.fillRoundRect(x + 5, y + 5, tileSize - 10, tileSize - 10, 25, 25);
                g2d.setColor(FOREGROUND_COLOR);
                g2d.fillRoundRect(x + 7, y + 7, tileSize - 14, tileSize - 14, 21, 21);
                g2d.setColor(DETAILS_COLOR);
                drawCenteredString(g2d, String.valueOf(tiles[i]), x, y);
            }
            g2d.setColor(EMPTINESS_COLOR.brighter());
            g2d.fillRoundRect(15, 15, tileSize * 4 - 10, 542 / 7, 25, 25);
            g2d.setColor(BACKGROUND_COLOR.darker());
            g2d.drawString("Moves: " + Move, 32, 71);
        }
    }

    private void drawStartMessage(Graphics2D g2d) {
        if (gameOver) {
            g2d.setFont(getFont().deriveFont(Font.BOLD, 20));
            g2d.setColor(DETAILS_COLOR);
            String s = "Tap here to start";
            Millisecundomer.stop();
            g2d.drawString(s, (getWidth() - g2d.getFontMetrics().stringWidth(s)) / 2, getHeight() - margin);
        }
    }

    private void drawCenteredString(Graphics2D g2d, String s, int x, int y) {
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int asc = fontMetrics.getAscent();
        int dsc = fontMetrics.getDescent();
        g2d.drawString(s, x + (tileSize - fontMetrics.stringWidth(s)) / 2, y + (asc + (tileSize - (asc + dsc)) / 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g2d);
        drawStartMessage(g2d);
        g2d.setFont(getFont().deriveFont(Font.BOLD, 50));
        if (Time % 60 > 9) {
            g2d.drawString(Time / 60 + ":" + Time % 60, 425, 71);
        } else {
            g2d.drawString(Time / 60 + ":0" + Time % 60, 425, 71);
        }
    }

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        new Tags(4, 550, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Time++;
        repaint();
    }
}
