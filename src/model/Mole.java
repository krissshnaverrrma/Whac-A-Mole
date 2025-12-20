package model;

import java.awt.*;

public class Mole {
    protected int x, y;
    protected int width, height;
    protected Image image;
    protected boolean visible;
    protected boolean isHit = false;

    public Mole(int x, int y, int w, int h, Image image) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.image = image;
        this.visible = true;
    }

    public void draw(Graphics g) {
        if (visible && image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    public boolean contains(int mx, int my) {
        if (!visible)
            return false;
        return (mx >= x && mx <= x + width && my >= y && my <= y + height);
    }

    public void setVisible(boolean v) {
        this.visible = v;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setImage(Image img) {
        this.image = img;
    }

    public void setHit(boolean hit) {
        this.isHit = hit;
    }

    public boolean isHit() {
        return isHit;
    }
}