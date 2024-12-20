package main.java.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    // TODO allow multiple directions (up/right, down/left, etc.)
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle hitbox;
    public int hitboxDefaultX, hitboxDefaultY;
}
