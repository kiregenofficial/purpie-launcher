package Games.PURPIE.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class TwentyFourtyEight extends JPanel implements ActionListener {
	// VARIABLES ----------------------------------------------------------------------------------
	ImageIcon PurpieIcon = new ImageIcon(Snek.GEF + ":\\Kira\\Games\\PURPIE\\img\\Icon.png");
	private static final int[][] Colors = {{255, 255, 243, 231, 216, 200, 192, 184, 186, 187, 193}, {214, 206, 198, 190, 182, 187, 192, 200, 208, 212, 222}};
	private static final Color FOREGROUND_COLOR = Color.white; // Константа цвета
	private static final Color BACKGROUND_COLOR = new Color(210, 200, 255); // Константа цвета
	private static final Color EMPTINESS_COLOR = new Color(230, 227, 255); // e4e0f5, 230, 227, 255
	private static final Color DETAILS_COLOR = new Color(79, 79, 79); // Константа цвета
	private static final Color BORDER_COLOR = new Color(128, 128, 128);
	private static final Font BEAUTIFUL_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 50);
	private final int tileSize = 542 / 4;
	private final Random Random = new Random();
	private final int[][] Tiles = new int[4][4];
	private static long Scor = 2;
	private static int Time = 0;
	Timer Millisecundomer = new Timer(990, this);
	public static final JFrame frame = new JFrame("2048");
	// CONSTRUCTOR --------------------------------------------------------------------------------
	public TwentyFourtyEight() {
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

		reset();

		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent keyEvent) {
			}

			@Override
			public void keyPressed(KeyEvent keyEvent) {
				int KeyCode = keyEvent.getKeyCode();
				try {
					for (int k = 0; k < 4; k++) {
						if (KeyCode == KeyEvent.VK_DOWN) {
							for (int a = 2; a >= 0; a--) {
								for (int b = 0; b < 4; b++) {
									if (Tiles[a + 1][b] == 0) {
										Tiles[a + 1][b] = Tiles[a][b];
										Tiles[a][b] = 0;
									} else if (Tiles[a + 1][b] == Tiles[a][b]) {
										Tiles[a + 1][b] *= 2;
										Tiles[a][b] = 0;
									}
								}
							}
						} else if (KeyCode == KeyEvent.VK_UP) {
							for (int a = 1; a <= 3; a++) {
								for (int b = 0; b < 4; b++) {
									if (Tiles[a - 1][b] == 0) {
										Tiles[a - 1][b] = Tiles[a][b];
										Tiles[a][b] = 0;
									} else if (Tiles[a - 1][b] == Tiles[a][b]) {
										Tiles[a - 1][b] *= 2;
										Tiles[a][b] = 0;
									}
								}
							}
						} else if (KeyCode == KeyEvent.VK_RIGHT) {
							for (int a = 0; a < 4; a++) {
								for (int b = 2; b >= 0; b--) {
									if (Tiles[a][b + 1] == 0) {
										Tiles[a][b + 1] = Tiles[a][b];
										Tiles[a][b] = 0;
									} else if (Tiles[a][b + 1] == Tiles[a][b]) {
										Tiles[a][b + 1] *= 2;
										Tiles[a][b] = 0;
									}
								}
							}
						} else if (KeyCode == KeyEvent.VK_LEFT) {
							for (int a = 0; a < 4; a++) {
								for (int b = 1; b <= 3; b++) {
									if (Tiles[a][b - 1] == 0) {
										Tiles[a][b - 1] = Tiles[a][b];
										Tiles[a][b] = 0;
									} else if (Tiles[a][b - 1] == Tiles[a][b]) {
										Tiles[a][b - 1] *= 2;
										Tiles[a][b] = 0;
									}
								}
							}
						}
					}

					if (KeyCode > 36 && KeyCode < 41) {
						getScore();
						add();
					}

					if (KeyCode == KeyEvent.VK_R) {
						reset();
					}

					repaint();

				} catch (StackOverflowError soe) {
					System.out.println("YOU LOSE!");
					reset();
				}
			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {

			}
		});
	}

	// CLEAR --------------------------------------------------------------------------------------
	private void reset() {
		Scor = 2;
		Time = 0;
		Millisecundomer.restart();
		for (int a = 0; a < 4; a++)
			for (int b = 0; b < 4; b++)
				Tiles[a][b] = 0;
		add();
	}

	private void add() {
		int x = Random.nextInt(4);
		int y = Random.nextInt(4);
		if (Tiles[y][x] == 0) {
			Tiles[y][x] = 2;
		} else add();
	}

	private void getScore() {
		Scor = 2;
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				Scor += Tiles[a][b];
			}
		}
	}

	// DRAW ---------------------------------------------------------------------------------------
	private void drawGrid(Graphics2D g2d) {
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				int x = 10 + b * tileSize;
				int y = 15 + 542 / 7 + 10 + a * tileSize;

				if (Tiles[a][b] != 0) {
					g2d.setColor(BORDER_COLOR);
					g2d.fillRoundRect(x + 5, y + 5, tileSize - 10, tileSize - 10, 25, 25);
					int col;
					switch (Tiles[a][b]) {
						case 2 -> col = 0;
						case 4 -> col = 1;
						case 8 -> col = 2;
						case 16 -> col = 3;
						case 32 -> col = 4;
						case 64 -> col = 5;
						case 128 -> col = 6;
						case 256 -> col = 7;
						case 512 -> col = 8;
						case 1024 -> col = 9;
						default -> col = 10;
					}
					g2d.setColor(new Color(Colors[0][col], Colors[1][col], 255));
					g2d.fillRoundRect(x + 7, y + 7, tileSize - 14, tileSize - 14, 21, 21);
					g2d.setColor(DETAILS_COLOR);
					drawCenteredString(g2d, String.valueOf(Tiles[a][b]), x, y);
				} else {
					g2d.setColor(EMPTINESS_COLOR);
					g2d.fillRoundRect(x + 5, y + 5, tileSize - 10, tileSize - 10, 25, 25);
				}
			}
		}
		g2d.setColor(EMPTINESS_COLOR.brighter());
		g2d.fillRoundRect(15, 15, tileSize * 4 - 10, 542 / 7, 25, 25);
		g2d.setColor(BACKGROUND_COLOR.darker());
		g2d.drawString("Score: " + Scor, 32, 71);
	}

	private void drawCenteredString(Graphics2D g2d, String s, int x, int y) {
		FontMetrics fontMetrics = g2d.getFontMetrics();
		int asc = fontMetrics.getAscent();
		int dsc = fontMetrics.getDescent();
		g2d.drawString(s, x + (tileSize - fontMetrics.stringWidth(s)) / 2, y + (asc + (tileSize - (asc + dsc)) / 2));
	}

	// PAINT --------------------------------------------------------------------------------------
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawGrid(g2d);
		if (Time % 60 > 9) {
			g2d.drawString(Time / 60 + ":" + Time % 60, 425, 71);
		} else {
			g2d.drawString(Time / 60 + ":0" + Time % 60, 425, 71);
		}
	}

	// ACTION LISTENER ----------------------------------------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		Millisecundomer.start();
		Time++;
		repaint();
	}

	// MAIN ---------------------------------------------------------------------------------------
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		new TwentyFourtyEight();
	}
}