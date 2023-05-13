package blackjack.main;

import blackjack.api.Suit;
import blackjack.cards.Card;
import blackjack.dealing.DealersHand;
import blackjack.dealing.Deck;
import blackjack.dealing.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Blackjack extends Frame implements ActionListener {

    boolean won = false;
    boolean lost = false;
    boolean draw = false;

    static Dealer dealer;
    static Player player;
    static Deck deck;
    JButton hit, stand, playAgain;

    static BufferedImage bg;

    void sleep() {
        try {
            Thread.sleep(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Blackjack() {
        // Add Hit and Stand buttons
        hit = new JButton("Hit");
        hit.addActionListener(this);
        stand = new JButton("Stand");
        stand.addActionListener(this);
        playAgain = new JButton("Play Again");
        playAgain.addActionListener(this);

        // Closing functionality
        addWindowListener((new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }));

        add(hit);
        add(stand);
        add(playAgain);

        playAgain.setEnabled(false);
    }

    public static void main(String[] args) {
        // Init window
        Blackjack blackjack = new Blackjack();
        blackjack.setTitle("Blackjack");
        blackjack.setSize(1280, 720);
        blackjack.setLocationRelativeTo(null);
        blackjack.setLayout(new FlowLayout());
        blackjack.setResizable(false);
        blackjack.setVisible(true);

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
    }

    @Override
    public void paint(Graphics g) {
        /* TODO Paint Logic */
        // Set size of Cards
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 150));

        // Draws the player's hand
        if(!player.getHand().getHand().isEmpty()) {
            int playerX = (int) ((1280/2) - ((player.getHand().getHand().size() / 2.0) * 100));
            int playerY = 650;
            for(Card c : player.getHand().getHand()) {
                if(c.getSuit() == Suit.DIAMONDS || c.getSuit() == Suit.HEARTS) g.setColor(Color.RED);
                else g.setColor(Color.BLACK);
                g.drawString(c.getImg(), playerX, playerY);
                playerX += 100;
                sleep();
            }
        }

        // Draws the dealer's hand
        if(!dealer.getHand().getHand().isEmpty()) {
            int dealerX = (int) ((1280/2) - ((dealer.getHand().getHand().size() / 2.0) * 100));
            int dealerY = 250;
            for(int i = 0; i < dealer.getHand().getHand().size(); i++) {
                Card c = dealer.getHand().getHand().get(i);
                if(c.getSuit() == Suit.DIAMONDS || c.getSuit() == Suit.HEARTS) g.setColor(Color.RED);
                else g.setColor(Color.BLACK);
                if(i < dealer.getHand().getRevealed()) g.drawString(c.getImg(), dealerX, dealerY);
                else g.drawString(c.getBackImg(), dealerX, dealerY);
                dealerX += 100;
                sleep();
            }
        }
        super.paint(g);
    }

    public void won() {
        hit.setEnabled(false);
        stand.setEnabled(false);
        playAgain.setEnabled(true);
        System.out.println("You win!");
        repaint();
    }
    public void lost() {
        hit.setEnabled(false);
        stand.setEnabled(false);
        playAgain.setEnabled(true);
        dealer.getHand().setRevealed(dealer.getHand().getHand().size());
        System.out.println("You lost!");
        repaint();
    }
    public void draw() {
        hit.setEnabled(false);
        stand.setEnabled(false);
        playAgain.setEnabled(true);
        System.out.println("Draw!");
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("Hit") && player.getHand().getTotalValue() < 21) {
            player.getHand().hit(deck);
            if(player.hasBusted()) lost = true;
            repaint();
        }
        if(s.equals("Stand") || player.blackJack()) {
            hit.setEnabled(false);
            stand.setEnabled(false);

            dealer.getHand().reveal();

            while(dealer.getHand().getTotalValue() < 17) {
                dealer.getHand().hit(deck);
                dealer.getHand().reveal();
                repaint();
            }

            if(dealer.getHand().getTotalValue() < player.getHand().getTotalValue() || dealer.hasBusted()) won = true;
            else if(dealer.getHand().getTotalValue() == player.getHand().getTotalValue()) draw = true;
            else lost = true;
        }

        if(won) won();
        else if(lost) lost();
        else if(draw) draw();
    }
}
