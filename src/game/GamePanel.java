package game;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import model.Bomb;
import model.Mole;
import util.GameTimer;
import util.SoundPlayer;

public class GamePanel extends JPanel {
    private Mole[] slots;
    private Point[] holeCoordinates;
    private Random random = new Random();
    private int score = 0;
    private int level = 1;
    private int speed = GameConfig.START_SPEED;
    private boolean isRunning = false;
    private boolean isGameOver = false;
    private Timer moleTimer;
    private GameTimer gameTimer;
    private Image bgImg = new ImageIcon("assets/bg.png").getImage();
    private Image holeImg = new ImageIcon("assets/hole.png").getImage();
    private Image moleImg = new ImageIcon("assets/mole.png").getImage();
    private Image bombImg = new ImageIcon("assets/bomb.png").getImage();
    private Image hammerImg = new ImageIcon("assets/hammer.png").getImage();
    private Image hitMoleImg = new ImageIcon("assets/hit_mole.png").getImage();
    private Rectangle startButtonRect;

    public GamePanel() {
        setPreferredSize(new Dimension(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT));
        initHolePositions();
        slots = new Mole[9];
        setCursor(Cursor.getDefaultCursor());
        int btnW = 300;
        int btnH = 80;
        startButtonRect = new Rectangle(
                (GameConfig.SCREEN_WIDTH - btnW) / 2,
                (GameConfig.SCREEN_HEIGHT - btnH) / 2,
                btnW, btnH);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleInput(e.getX(), e.getY());
            }
        });
    }

    private void initHolePositions() {
        holeCoordinates = new Point[9];
        int cellWidth = GameConfig.SCREEN_WIDTH / 3;
        int cellHeight = GameConfig.SCREEN_HEIGHT / 3;
        int count = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int x = (col * cellWidth) + (cellWidth / 2) - 60;
                int y = (row * cellHeight) + (cellHeight / 2) - 40;
                holeCoordinates[count] = new Point(x, y);
                count++;
            }
        }
    }

    private void setHammerCursor() {
        try {
            Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(
                    hammerImg, new Point(0, 0), "Hammer");
            setCursor(c);
        } catch (Exception e) {
        }
    }

    private void handleInput(int mx, int my) {
        if (!isRunning) {
            if (startButtonRect.contains(mx, my))
                startGame();
            return;
        }
        if (isGameOver) {
            resetGame();
            return;
        }
        boolean hitSomething = false;

        for (int i = 0; i < 9; i++) {
            Mole m = slots[i];
            if (m != null && m.isVisible() && !m.isHit() && m.contains(mx, my)) {
                hitSomething = true;
                if (m instanceof Bomb) {
                    score -= GameConfig.BOMB_PENALTY;
                    SoundPlayer.play("assets/bomb.wav");
                    m.setVisible(false);
                } else {
                    score++;
                    SoundPlayer.play("assets/hit.wav");
                    if (score % GameConfig.SCORE_PER_LEVEL == 0)
                        levelUp();
                    m.setHit(true);
                    m.setImage(hitMoleImg);
                    repaint();
                    Timer animTimer = new Timer(300, e -> {
                        m.setVisible(false);
                        repaint();
                    });
                    animTimer.setRepeats(false);
                    animTimer.start();
                }
                break;
            }
        }
        if (!hitSomething)
            SoundPlayer.play("assets/miss.wav");
    }

    private void startGame() {
        isRunning = true;
        isGameOver = false;
        score = 0;
        level = 1;
        speed = GameConfig.START_SPEED;
        setHammerCursor();
        if (moleTimer != null)
            moleTimer.stop();
        moleTimer = new Timer(speed, e -> updateGameLoop());
        moleTimer.start();
        if (gameTimer != null)
            gameTimer.stop();
        gameTimer = new GameTimer(GameConfig.GAME_TIME, new JLabel(), this::endGame);
        gameTimer.start();
        repaint();
    }

    private void resetGame() {
        isRunning = false;
        isGameOver = false;
        setCursor(Cursor.getDefaultCursor());
        repaint();
    }

    private void updateGameLoop() {
        for (int i = 0; i < 9; i++) {
            if (slots[i] != null && !slots[i].isHit()) {
                slots[i] = null;
            }
        }
        int index = random.nextInt(9);
        int x = holeCoordinates[index].x;
        int y = holeCoordinates[index].y;
        int size = GameConfig.MOLE_WIDTH;
        if (random.nextInt(GameConfig.BOMB_CHANCE) == 0) {
            slots[index] = new Bomb(x + 15, y - 40, size, size, bombImg);
        } else {
            slots[index] = new Mole(x + 10, y - 50, size + 10, size + 10, moleImg);
            SoundPlayer.play("assets/mole.wav");
        }
        repaint();
    }

    private void levelUp() {
        level++;
        speed = Math.max(GameConfig.MIN_SPEED, speed - GameConfig.SPEED_STEP);
        moleTimer.setDelay(speed);
    }

    private void endGame() {
        moleTimer.stop();
        isGameOver = true;
        setCursor(Cursor.getDefaultCursor());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (bgImg.getWidth(null) > 0) {
            g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(34, 139, 34));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        if (!isRunning) {
            drawMenu(g2d);
        } else if (isGameOver) {
            drawGameOver(g2d);
        } else {
            drawGameRunning(g2d);
        }
    }

    private void drawMenu(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());
        int rX = startButtonRect.x;
        int rY = startButtonRect.y;
        int rW = startButtonRect.width;
        int rH = startButtonRect.height;
        int arc = 30;
        g.setColor(new Color(180, 100, 0));
        g.fillRoundRect(rX, rY + 8, rW, rH, arc, arc);
        g.setColor(new Color(255, 165, 0));
        g.fillRoundRect(rX, rY, rW, rH, arc, arc);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(4));
        g.drawRoundRect(rX, rY, rW, rH, arc, arc);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 45));
        String text = "PLAY GAME";
        FontMetrics fm = g.getFontMetrics();
        int tx = rX + (rW - fm.stringWidth(text)) / 2;
        int ty = rY + (rH + fm.getAscent() - 15) / 2;
        g.drawString(text, tx, ty);
    }

    private void drawGameRunning(Graphics2D g) {
        for (int i = 0; i < 9; i++) {
            Mole m = slots[i];
            if (m != null && m.isVisible()) {
                Point p = holeCoordinates[i];
                if (holeImg.getWidth(null) > 0) {
                    g.drawImage(holeImg, p.x, p.y, 130, 80, null);
                } else {
                    g.setColor(new Color(60, 40, 20));
                    g.fillOval(p.x, p.y, 120, 60);
                }
                g.setColor(new Color(0, 0, 0, 70));
                g.fillOval(p.x + 30, p.y + 60, 100, 30);
                m.draw(g);
            }
        }
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRoundRect(20, 20, 250, 100, 30, 30);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 35));
        g.drawString("Score: " + score, 40, 70);
        g.setFont(new Font("Segoe UI", Font.BOLD, 30));
        g.drawString("Level: " + level, 40, 110);
    }

    private void drawGameOver(Graphics2D g) {
        drawGameRunning(g);
        g.setColor(new Color(100, 0, 0, 200));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 80));
        String msg = "GAME OVER";
        FontMetrics fm = g.getFontMetrics();
        g.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2, getHeight() / 2 - 50);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        String scoreMsg = "Final Score: " + score;
        g.drawString(scoreMsg, (getWidth() - g.getFontMetrics().stringWidth(scoreMsg)) / 2, getHeight() / 2 + 30);
        String retry = "Click anywhere to Restart";
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString(retry, (getWidth() - g.getFontMetrics().stringWidth(retry)) / 2, getHeight() / 2 + 100);
    }
}