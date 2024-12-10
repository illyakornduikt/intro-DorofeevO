package main.java;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.java.object.Key;

public class UI {
    private GamePanel gamePanel;
    private Font arial40, arial60B;

    private BufferedImage keyImage;

    private boolean showMessage = false;
    private String message = "";
    private int messageCounter = 0;
    private int messageTime = 80;

    public boolean gameFinished = false;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial60B = new Font("Arial", Font.BOLD, 60);

        keyImage = new Key(gamePanel.tileSize, gamePanel.tileSize).image;
    }

    public void showMessage(String text) {
        message = text;
        showMessage = true;
    }

    public void draw(Graphics2D g2d) {
        if (gameFinished) {
            showEndText(g2d);
            return;
        }

        g2d.setFont(arial40);
        g2d.setColor(Color.WHITE);
        g2d.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, null);
        g2d.drawString("x " + gamePanel.player.keys, 74, 65);

        if (showMessage) {
            // TODO nonlinear transform
            float opacity = (messageTime - messageCounter) / (float) messageTime;
            g2d.setFont(g2d.getFont().deriveFont(30f));
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 5 - messageCounter);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            messageCounter++;
            if (messageCounter > messageTime) {
                showMessage = false;
                messageCounter = 0;
            }
        }
    }

    private void showEndText(Graphics2D g2d) {
        String text;
        int textLen, x, y;

        g2d.setFont(arial40);
        g2d.setColor(Color.WHITE);
        text = "You found the treasure!";
        textLen = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        x = gamePanel.screenWidth / 2 - textLen / 2;
        y = gamePanel.screenHeight / 2 - gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);

        g2d.setFont(arial60B);
        g2d.setColor(Color.YELLOW);
        text = "Congratulations!";
        textLen = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        x = gamePanel.screenWidth / 2 - textLen / 2;
        y = gamePanel.screenHeight / 2 + gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);

        return;
    }
}
