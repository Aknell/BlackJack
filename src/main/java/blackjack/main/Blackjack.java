package blackjack.main;

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
    JButton hit, stand;

    static BufferedImage bg;

    public Blackjack() {
        hit = new JButton("Hit");
        hit.addActionListener(this);
        stand = new JButton("Stand");
        stand.addActionListener(this);

        addWindowListener((new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        }));
        add(hit);
        add(stand);
    }

    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        blackjack.setTitle("Blackjack");
        blackjack.setSize(640, 480);
        blackjack.setLocationRelativeTo(null);
        blackjack.setLayout(new FlowLayout());
        blackjack.setResizable(false);
        blackjack.setVisible(true);

        deck = new Deck();
        deck.shuffle();
        dealer = new Dealer(new DealersHand());
        player = new Player(new Hand());

        for(int i = 0; i < 2; i++) {
            dealer.getHand().hit(deck);
            player.getHand().hit(deck);
        }
    }

    @Override
    public void paint(Graphics g) {
        /* TODO Paint Logic */
        super.paint(g);
    }

    public void won() {
        hit.setEnabled(false);
        stand.setEnabled(false);
        System.out.println("You win!");
        repaint();
    }
    public void lost() {
        hit.setEnabled(false);
        stand.setEnabled(false);
        System.out.println("You lost!");
        repaint();
    }
    public void draw() {
        hit.setEnabled(false);
        stand.setEnabled(false);
        System.out.println("Draw!");
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("Hit") && player.getHand().getTotalValue() < 21) {
            player.getHand().hit(deck);
            if(player.hasBusted()) lost = true;
        }
        if(s.equals("Stand") || player.blackJack()) {
            hit.setEnabled(false);
            stand.setEnabled(false);

            dealer.getHand().reveal();

            while(dealer.getHand().getTotalValue() < 17) {
                dealer.getHand().hit(deck);
                dealer.getHand().reveal();
            }

            if(dealer.getHand().getTotalValue() < player.getHand().getTotalValue()) won = true;
            else if(dealer.getHand().getTotalValue() == player.getHand().getTotalValue()) draw = true;
            else lost = true;
        }

        if(won) won();
        else if(lost) lost();
        else if(draw) draw();
    }
}
