package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import rep.Bid;
import rep.BidHistory;
import rep.Card.Suit;
import rep.Deal;
import rep.HandView;
import rep.Player;

public class GUI {
	static JFrame mainFrame;
	static JLabel statusBar;
	static JPanel dealPanel;
	static JPanel auctionPanel;
	static Deal currentDeal;
	static int currentCell;
	static JButton nextBidButton;
	
	public static void createAndShowGUI() {
		
		// Create the frame
		mainFrame = new JFrame("Deal");
		
		// Change function of close button to terminate the program 
		mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// Get the main container of the window to add stuff to
		Container mainPane = mainFrame.getContentPane();
		
		// Create the menu bar to add to the main container
		JMenuBar menuBar = new JMenuBar();
		
		// Create the menu to add the the menu bar
		JMenu fileMenu = new JMenu("File");
		
		// Create new deal option in the file menu and add functionality to it
		final JMenuItem newDeal = fileMenu.add("Create new deal");
		newDeal.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dealPanel.removeAll();
				auctionPanel.removeAll();
				currentCell = 0;
				
				HandView hv = new HandView(null);
				hv.points_hc_low = 15;
				hv.points_hc_high = 17;
				hv.balanced_high = 1;
				
				HandView hv2 = new HandView(null);
				hv2.lmaj_low = 5;
				
				currentDeal = new Deal();
				
				while(!hv.matchesHand(currentDeal.getNorth().getHand()) ||
						!hv2.matchesHand(currentDeal.getSouth().getHand()))
					currentDeal = new Deal();
				
				nextBidButton.setEnabled(true);
				
				dealPanel.add(new JLabel(""));
				
				JPanel northPanel = new JPanel();
				northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
				addCardsFromDeal(currentDeal.getNorth(), northPanel);
				dealPanel.add(northPanel);
				
				dealPanel.add(new JLabel(""));
				
				JPanel westPanel = new JPanel();
				westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
				addCardsFromDeal(currentDeal.getWest(), westPanel);
				dealPanel.add(westPanel);
				
				dealPanel.add(new JLabel(""));
				
				JPanel eastPanel = new JPanel();
				eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
				addCardsFromDeal(currentDeal.getEast(), eastPanel);
				dealPanel.add(eastPanel);
				
				dealPanel.add(new JLabel(""));
				
				JPanel southPanel = new JPanel();
				southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
				addCardsFromDeal(currentDeal.getSouth(), southPanel);
				dealPanel.add(southPanel);
				
				dealPanel.add(new JLabel(""));
				
				GridBagConstraints anchor_top = new GridBagConstraints();
				anchor_top.anchor = GridBagConstraints.PAGE_START;
				
				JLabel northLabel = new JLabel("  North  ");
				northLabel.setHorizontalAlignment(JLabel.CENTER);
				auctionPanel.add(northLabel, anchor_top);
				JLabel eastLabel = new JLabel("  East  ");
				eastLabel.setHorizontalAlignment(JLabel.CENTER);
				auctionPanel.add(eastLabel, anchor_top);
				JLabel southLabel = new JLabel("  South  ");
				southLabel.setHorizontalAlignment(JLabel.CENTER);
				auctionPanel.add(southLabel, anchor_top);
				JLabel westLabel = new JLabel("  West  ");
				westLabel.setHorizontalAlignment(JLabel.CENTER);
				auctionPanel.add(westLabel, anchor_top);
				
				GridBagConstraints row_num = new GridBagConstraints();
				row_num.gridy = 1;
				row_num.anchor = GridBagConstraints.PAGE_START;
				
				currentCell = Deal.getPosition(currentDeal.getDealer()) - 1 + 4;
				for(int i=0; i<currentCell-4; ++i) auctionPanel.add(new JLabel(" "), row_num);
				
				
				
				mainFrame.pack();
			}

			private void addCardsFromDeal(Player p, JPanel cardPanel) {
				cardPanel.add(new JLabel(p.getposition().toUpperCase()));
				cardPanel.add(new JLabel("S: " + p.createStringForGUI(Suit.SPADE)));
				cardPanel.add(new JLabel("H: " + p.createStringForGUI(Suit.HEART)));
				cardPanel.add(new JLabel("D: " + p.createStringForGUI(Suit.DIAMOND)));
				cardPanel.add(new JLabel("C: " + p.createStringForGUI(Suit.CLUB)));
			}
			
		});
		
		// Add quit option
		JMenuItem quit = fileMenu.add("Quit");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		
		// Add the file menu to the menu bar
		menuBar.add(fileMenu);
		
		// add the menu bar to the main container
		mainPane.add(menuBar, BorderLayout.PAGE_START);
		
		// Create status bar
		statusBar = new JLabel("Not started yet");
		mainPane.add(statusBar, BorderLayout.PAGE_END);
		
		dealPanel = new JPanel(new GridLayout(3, 3));
		dealPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainFrame.setBackground( Color.white );
		mainFrame.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		mainPane.add(dealPanel, BorderLayout.WEST);
		
		auctionPanel = new JPanel();
		auctionPanel.setLayout(new GridBagLayout());
		dealPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 10));
		mainPane.add(auctionPanel, BorderLayout.CENTER);
		
		// Create generate next bid button and add functionality to it
		nextBidButton = new JButton("Next bid");
		nextBidButton.setPreferredSize(new Dimension(85, 30));
		nextBidButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Bid b = currentDeal.askForNextBid();
				JLabel newBidLabel = new JLabel("  " + b.toString() + "  ");
				newBidLabel.setHorizontalAlignment(JLabel.CENTER);
				
				currentCell++;
				GridBagConstraints row_num = new GridBagConstraints();
				row_num.anchor = GridBagConstraints.CENTER;
				row_num.gridy = currentCell/4;
				auctionPanel.add(newBidLabel, row_num);
				
				statusBar.setText("Current cell: " + currentCell);
				
				BidHistory bh = currentDeal.getAuction().getBidHistory(); 
				if(bh.getNumBidsMade() >= 4) {
					if(bh.getBid(0).pass &&
							bh.getBid(1).pass &&
							bh.getBid(2).pass) {
						statusBar.setText("Auction completed");
						nextBidButton.setEnabled(false);
					}
					
				}
				mainFrame.pack();
			}
		});	mainPane.add(nextBidButton, BorderLayout.EAST);
		
		mainFrame.pack();
		
		mainFrame.setVisible(true);
	}
	
}
