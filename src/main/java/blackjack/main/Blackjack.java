package blackjack.main;

import blackjack.api.Suit;
import blackjack.cards.Card;
import blackjack.dealing.DealersHand;
import blackjack.dealing.Deck;
import blackjack.dealing.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Blackjack extends Frame implements ActionListener {

    boolean won = false;
    boolean lost = false;
    boolean draw = false;

    boolean wantToPlay = false;

    static Dealer dealer;
    static Player player;
    static Deck deck;
    JButton start, hit, stand, restart;

    static BufferedImage bg;

    public Blackjack() {
        // Add Hit, Stand, Play again, Start buttons
        hit = new JButton("Hit");
        hit.setBounds(0, 33, 426, 33);
        hit.addActionListener(this);

        stand = new JButton("Stand");
        stand.setBounds(426, 33, 427, 33);
        stand.addActionListener(this);

        restart = new JButton("Restart");
        restart.setBounds(853, 33, 427, 33);
        restart.addActionListener(this);

        start = new JButton("Play");
        start.setBounds(540, 450, 200, 50);
        start.addActionListener(this);

        // Closing functionality
        addWindowListener((new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }));

        add(start);
        add(hit);
        add(stand);
        add(restart);

        restart.setEnabled(false);
        hit.setVisible(false);
        stand.setVisible(false);
        restart.setVisible(false);
    }

    public void initGame() {
        // Creates and shuffles deck
        deck = new Deck();
        deck.shuffle();

        // Creates dealer and player with their own hands
        dealer = new Dealer(new DealersHand());
        player = new Player(new Hand());

        // Adds 2 cards to the dealer's and player's hands
        for(int i = 0; i < 2; i++) {
            dealer.getHand().hit(deck);
            player.getHand().hit(deck);
        }

        // Reveals hit, stand, play again buttons. Hides start button
        hit.setVisible(true);
        stand.setVisible(true);
        restart.setVisible(true);
        start.setVisible(false);
    }

    public static void main(String[] args) {
        // Initializes window
        Blackjack blackjack = new Blackjack();
        blackjack.setTitle("Blackjack");
        blackjack.setSize(1280, 720);
        blackjack.setLocationRelativeTo(null);
        blackjack.setLayout(null);
        blackjack.setResizable(false);
        blackjack.setVisible(true);

        // Adds background image to program
        try {
             bg = ImageIO.read(new File("src/main/resources/images/gui/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Makes background green
        blackjack.setBackground(new Color(0, 125, 15));
    }

    // Draws splash text for win/loss/draw
    public void drawSplashText(String text, Graphics2D g2d) {
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.setColor(Color.WHITE);

        // Calculate position
        int width = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int height = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getHeight();
        int x = (1280/2) - (width/2);
        int y = (720/2) + (height/2);
        
        g2d.drawString(text, x, y);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Changes to Graphics2D class to enable anti-aliasing
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints antiAliasing = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.addRenderingHints(antiAliasing);

        // Draws background
        g2d.drawImage(bg, 0, 0, null);

        if(wantToPlay) {
            // Draws the player's hand
            if (!player.getHand().getHand().isEmpty()) {
                // Set size of Cards
                g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 150));
                // Find position of cards
                int playerX = (int) ((1280 / 2) - ((player.getHand().getHand().size() / 2.0) * 100)); // Starting point for drawing cards so that they're centered
                int playerY = 550;
                for (Card c : player.getHand().getHand()) {
                    // Getting width and height of card to draw white rect
                    int w = g2d.getFontMetrics().stringWidth(c.getImg());
                    LineMetrics ln = g2d.getFont().getLineMetrics(c.getImg(), g2d.getFontRenderContext());
                    int h = (int) ln.getHeight();

                    // Drawing white rect
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(playerX, (int) ((playerY / 2) + ln.getAscent()), w, h - 50);

                    if (c.getSuit() == Suit.DIAMONDS || c.getSuit() == Suit.HEARTS)
                        g2d.setColor(Color.RED); // Makes Diamonds and Hearts red
                    else g2d.setColor(Color.BLACK); // Makes Spades, Clubs, and Backs black

                    g2d.drawString(c.getImg(), playerX, playerY);
                    playerX += 100;
                }
                // Draw total value of hand
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 50));
                String draw = "Player hand value: " + player.getHand().getTotalValue();
                int stringX = (1280 / 2) - (g2d.getFontMetrics().stringWidth(draw) / 2);
                g.drawString("Player hand value: " + player.getHand().getTotalValue(), stringX, 650);
            }

            // Draws the dealer's hand
            if (!dealer.getHand().getHand().isEmpty()) {
                // Set size of Cards
                g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 150));
                // Find position of cards
                int dealerX = (int) ((1280 / 2) - ((dealer.getHand().getHand().size() / 2.0) * 100));
                int dealerY = 300;
                for (int i = 0; i < dealer.getHand().getHand().size(); i++) {
                    Card c = dealer.getHand().getHand().get(i);
                    // Getting width and height of card to draw white rect
                    int w = g2d.getFontMetrics().stringWidth(c.getImg());
                    LineMetrics ln = g2d.getFont().getLineMetrics(c.getImg(), g2d.getFontRenderContext());
                    int h = (int) ln.getHeight();

                    // Drawing white rect
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(dealerX, (int) ((dealerY / 2) - ln.getDescent() + 71), w, h - 50);

                    if (c.getSuit() == Suit.DIAMONDS || c.getSuit() == Suit.HEARTS) g2d.setColor(Color.RED);
                    else g2d.setColor(Color.BLACK);

                    if (i < dealer.getHand().getRevealed())
                        g2d.drawString(c.getImg(), dealerX, dealerY); // Draws card if it is revealed
                    else g2d.drawString(c.getBackImg(), dealerX, dealerY); // If not, draws back of card

                    dealerX += 100;
                }
                // Draw total value of visible hand
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 50));
                String draw = "Dealer hand value: " + dealer.getHand().getPublicHand().getTotalValue();
                int stringX = (1280 / 2) - (g2d.getFontMetrics().stringWidth(draw) / 2);
                g.drawString(draw, stringX, 133);
            }

            // Sets text for win/loss/draw
            if (won) drawSplashText("You won!", g2d);
            if (lost) drawSplashText("You lost!", g2d);
            if (draw) drawSplashText("Draw!", g2d);
        }
        else {
            g2d.setColor(new Color(0, 0, 0, 50));
            g2d.fillRect(0, 0, 1280, 720);
            drawSplashText("Welcome to Blackjack!", g2d);
        }

        super.paint(g);
    }

    public void won() {
        // Disables buttons and enables Play Again
        hit.setEnabled(false);
        stand.setEnabled(false);
        restart.setEnabled(true);
        repaint();
    }
    public void lost() {
        // Disables buttons and enables Play Again
        hit.setEnabled(false);
        stand.setEnabled(false);
        restart.setEnabled(true);
        dealer.getHand().setRevealed(dealer.getHand().getHand().size());
        repaint();
    }
    public void draw() {
        // Disables buttons and enables Play Again
        hit.setEnabled(false);
        stand.setEnabled(false);
        restart.setEnabled(true);
        repaint();
    }

    public void playAgain() {
        // Enables buttons and disables Play Again
        hit.setEnabled(true);
        stand.setEnabled(true);
        restart.setEnabled(false);

        // Sets won, lost, and draw to false
        won = false;
        lost = false;
        draw = false;

        initGame();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("Play")) {
            wantToPlay = true;
            initGame();
            repaint();
        }

        if(s.equals("Hit") && player.getHand().getTotalValue() < 21) {
            player.getHand().hit(deck);
            if(player.hasBusted()) lost = true;
            repaint();
        }
        if(s.equals("Stand") || player.blackJack()) {
            hit.setEnabled(false);
            stand.setEnabled(false);

            dealer.getHand().reveal();
            repaint();

            while(dealer.getHand().getTotalValue() < 17) {
                dealer.getHand().hit(deck);
                dealer.getHand().reveal();
                repaint();
            }

            if(dealer.getHand().getTotalValue() < player.getHand().getTotalValue() || dealer.hasBusted()) won = true;
            else if(dealer.getHand().getTotalValue() == player.getHand().getTotalValue()) draw = true;
            else lost = true;
        }
        if(s.equals("Restart")) playAgain();

        if(won) won();
        else if(lost) lost();
        else if(draw) draw();
    }
}
