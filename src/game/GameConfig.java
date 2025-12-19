package game;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GameConfig {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int SCREEN_WIDTH = (int) screenSize.getWidth();
    public static final int SCREEN_HEIGHT = (int) screenSize.getHeight();
    public static final int ROWS = 3;
    public static final int COLS = 3;
    public static final int MOLE_WIDTH = 150;
    public static final int MOLE_HEIGHT = 150;
    public static final int GAME_TIME = 30;
    public static final int START_SPEED = 900;
    public static final int SPEED_STEP = 100;
    public static final int MIN_SPEED = 300;
    public static final int SCORE_PER_LEVEL = 5;
    public static final int BOMB_CHANCE = 4;
    public static final int BOMB_PENALTY = 3;
}