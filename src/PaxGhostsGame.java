// Property of B3r5t Team: Jacob Sarver-Verhey & William Yznaga - 4/26/2016

package paxGhostsGame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaxGhostsGame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Display display = new Display();
    private JPanel controlPanel = new JPanel();
    private JPanel statisticsPanel = new JPanel();
    private JPanel sharkPanel = new JPanel();
    private JPanel fishPanel = new JPanel();
    private JPanel runPanel = new JPanel();
    private JPanel speedControlPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JSlider speedControl = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 100);
    private JButton runButton = new JButton("Run");
    private JButton populateButton = new JButton("Populate");
    private JTextField ghostCount = new JTextField("1");
    private JTextField paxCount = new JTextField("5000");
    
    private JTextField ghostGestationPeriod = new JTextField("200");
    private JTextField paxGestationPeriod = new JTextField("10");
    private JTextField ghostStarvationPeriod = new JTextField("150");
    private TitledBorder ghostBorder = new TitledBorder("Ghosts");
    private TitledBorder paxBorder = new TitledBorder("Pax");
    
    private PaxGhostsGame thisFrame;
    private final static int WORLD_SIZE = 100;
    private WorldGrid world;
    private boolean running = false;
    
    public static void main(String[] args) {
        PaxGhostsGame game = new PaxGhostsGame();
    }
    
    public PaxGhostsGame() {
        thisFrame = this;
        
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(statisticsPanel, BorderLayout.NORTH);
        controlPanel.add(runPanel, BorderLayout.SOUTH);
        statisticsPanel.setLayout(new GridLayout(1, 2));
        statisticsPanel.add(ghostPanel);
        statisticsPanel.add(paxPanel);
        runPanel.setLayout(new FlowLayout());
        ghostPanel.setLayout(new GridLayout(6, 1));
        paxPanel.setLayout(new GridLayout(6, 1));
        
        ghostPanel.setBorder(sharkBorder);
        ghostPanel.add(new JLabel("Initial number:"));
        ghostPanel.add(ghostCount);
        ghostPanel.add(new JLabel("Gestation period:"));
        ghostPanel.add(ghostGestationPeriod);
        ghostPanel.add(new JLabel("Starvation period:"));
        ghostPanel.add(ghostStarvationPeriod);
        
        paxPanel.setBorder(paxBorder);
        paxPanel.add(new JLabel("Initial number:"));
        paxPanel.add(paxCount);
        paxPanel.add(new JLabel("Gestation period:"));
        paxPanel.add(paxGestationPeriod);
        // paxPanel.add(new JLabel("Starvation period:"));
        // paxPanel.add(paxStarvationPeriod);
        
        runPanel.setLayout(new BorderLayout());
        runPanel.add(speedControlPanel, BorderLayout.CENTER);
        runPanel.add(buttonPanel, BorderLayout.EAST);
        
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(populateButton);
        buttonPanel.add(runButton);
        
        speedControlPanel.add(speedControl);
        speedControl.setMajorTickSpacing(20);
        speedControl.setMinorTickSpacing(5);
        speedControl.setPaintTicks(true);
        speedControl.setPaintLabels(true);
        speedControl.setValueIsAdjusting(true);
        
        world = new WorldGrid(WORLD_SIZE, WORLD_SIZE);
        display.setWorld(world);
        attachListeners();
        world.addObserver(display);
        
        pack();
        this.setSize(550, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void attachListeners() {
        populateButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (Integer.parseInt(ghostStarvationPeriod.getText()) >= Integer.parseInt(ghostGestationPeriod.getText())) {

            		JOptionPane.showMessageDialog(thisFrame, "Ghost gestation period allowed should be greater than Ghost starvation enforced.");
            	}
            	else {s
            		
            		populate();
            	}
                display.repaint();
            }      
        });
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = !running;
                runButton.setText(running ? "Stop" : "Run");
                world.setRunning(running);
                
            }      
        });
        speedControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int sliderValue = speedControl.getValue();
                int delay = 100 - (sliderValue);
                world.setDelay(delay);
                world.setRunning(running);
            }
            
        });
    }
    
    public static int getWorldSize() {
    	return WORLD_SIZE;
    }

    private void populate() {
        try {
            int countGhost = Integer.valueOf(ghostCount.getText());
            int gestationGhost = Integer.valueOf(ghostGestationPeriod.getText());
            int starvationGhost = Integer.valueOf(ghostStarvationPeriod.getText());
            
            int countGhost = Integer.valueOf(paxCount.getText());
            int gestationGhost = Integer.valueOf(paxGestationPeriod.getText());
            
            world.populate();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(thisFrame, ex.getMessage());
        }
    }
}