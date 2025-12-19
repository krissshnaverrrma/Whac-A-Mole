package util;

import javax.swing.*;

public class GameTimer {
    private int time;
    private Timer timer;

    public GameTimer(int seconds, JLabel label, Runnable onEnd) {
        time = seconds;
        timer = new Timer(1000, e -> {
            label.setText("Time: " + time);
            time--;
            if (time < 0) {
                timer.stop();
                onEnd.run();
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
