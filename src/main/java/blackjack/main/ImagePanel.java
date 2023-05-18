package blackjack.main;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    Image img;

    public ImagePanel(String src) {
        this.img = new ImageIcon(src).getImage();
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));

        setSize(size);
        setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
