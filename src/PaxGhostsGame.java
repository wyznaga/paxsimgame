// Property of B3r5t Team: Jacob Sarver-Verhey & William Yznaga - 4/26/2016

impot java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaxGhostsGame extends JFrame {
    private static final long serialVersionUID = 1L;
    private class DisplayCanvas {
        public DisplayCanvas() {
            setBackground(Color.BLACK);
            setSize(WORLD_SIZE, WORLD_SIZE);
        }
        public void paint(Graphics gPaint)
        {
            Graphics2D gPaint2 = (Graphics2D) gPaint;
        }
        public void repaint(Graphics gRepaint)
        {
            Graphcis2D gRepaint2 = (Graphics2D) gPaint;
        }
    }
    private JPanel controlPanel = new JPanel();
    private JPanel statisticsPanel = new JPanel();
    private JPanel ghostPanel = new JPanel();
    private JPanel paxPanel = new JPanel();
    private JPanel runPanel = new JPanel();
    private JPanel speedControlPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JSlider speedControl = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 100);
    private JButton runButton = new JButton("Run");
    private JButton populateButton = new JButton("Populate");
    private JTextField ghostCount = new JTextField("100");
    private JTextField paxCount = new JTextField("500");
    
    private JTextField ghostGestationPeriod = new JTextField("3");
    private JTextField paxGestationPeriod = new JTextField("5");
    private JTextField ghostStarvationPeriod = new JTextField("8");
    private TitledBorder ghostBorder = new TitledBorder("Ghosts");
    private TitledBorder paxBorder = new TitledBorder("Pax");
    
    private final static int WORLD_SIZE = 100;
    private WorldGrid world;
    private boolean running = false;
    
    public static void main(String[] args) {
        private PaxGhostsGame thisFrame = new PaxGhostsGame();
        while (true)
        {
            if (world.isRunning)
            {
                thisFrame.populate();
                world.simulate();
                thisFrame.loop();
            }
            else if (!(world.isRunning))
            {
                continue;
            }
        }
    }
    
    public void loop()
    {
        while (world.isRunning)
        {
            /* if populate() values changed,
             reset grid, then break loop to
             game.populate again
             in the main() while loop */
            /* else if populate() values have not changed,
             simulate one more step and return to beginning of
             this loop */
        }
    }
        
    class ImagePanel extends JPanel {
        private Image img;
        public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
        }
    
        public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }
        
        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }

    public PaxGhostsGame() {
        thisFrame = this;
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
        }
        catch (ClassNotFoundException e) {
        }
        catch (InstantiationException e) {
        }
        catch (IllegalAccessException e) {
        }
        
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.CENTER);
        ImagePanel panelBGImage = new ImagePanel(new ImageIcon("BGP.jpg").getImage());
        thisFrame.getContentPane().add(panelBGImage);
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
        //display.setWorld(world);
        attachListeners();
        //world.addObserver(display);
        
        this.pack();
        this.setSize(640, 640);
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
            	else {
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
            
            int countPax = Integer.valueOf(paxCount.getText());
            int gestationPax = Integer.valueOf(paxGestationPeriod.getText());
            
            world.populate(countGhost, gestationGhost, starvationGhost, countPax, gestationPax);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(thisFrame, ex.getMessage());
        }
    }
}
