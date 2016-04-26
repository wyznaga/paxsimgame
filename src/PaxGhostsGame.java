package wator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class PG extends JPanel implements ActionListener {

    private Image src = null;
    private SimWorker simWorker;
    private JSlider width, height, startPills, startPax, startGhosts,
            maxPills, pillsRespawnTime, framesPerSecond, paxReproduce, ghostsReproduce,
            ghostsTimeLimit;
    private JButton beginButton, endButton, continueButton;
    private JPanel controlPanel;
    private JFrame jf;

    public WaTor() {
        width = new JSlider(JSlider.HORIZONTAL, 100, 640, 200);
        height = new JSlider(JSlider.HORIZONTAL, 100, 480, 200);
        startPills = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        startPax = new JSlider(JSlider.HORIZONTAL, 0, 2000, 1000);
        startGhosts = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        maxPills = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        pillsRespawnTime = new JSlider(JSlider.HORIZONTAL, 1, 10, 6);
        framesPerSecond = new JSlider(JSlider.HORIZONTAL, 0, 60, 10);
        paxReproduce = new JSlider(JSlider.HORIZONTAL, 1, 20, 3);
        ghostsReproduce = new JSlider(JSlider.HORIZONTAL, 1, 40, 6);
        ghostsTimeLimit = new JSlider(JSlider.HORIZONTAL, 0, 20, 4);
        JSlider[] sliders = {
            width, height, startPills, startPax, startGhosts,
            maxPills, pillsRespawnTime, framesPerSecond, paxReproduce, ghostsReproduce,
            ghostsTimeLimit
        };
        String[] sliderNames = {
            "Width", "Height", "Starting Pills", "Starting Pax",
            "Starting Ghosts", "Max Pills", "Pills Respawn Time",
            "Frames per Second", "Pax Reproduce Time", "Ghosts Reproduce Time",
            "Ghosts Time Limit"
        };
        JLabel[] sliderLabels = new JLabel[sliders.length];
        for (int i = 0; i < sliders.length; i++) {
            sliders[i].setPaintTicks(true);
            sliders[i].setPaintLabels(true);
            sliders[i].setMajorTickSpacing(sliders[i].getMaximum() / 7);
            sliders[i].setAlignmentX(LEFT_ALIGNMENT);
            sliderLabels[i] = new JLabel(sliderNames[i]);
            sliderLabels[i].setAlignmentX(RIGHT_ALIGNMENT);
        }
        Box buttonBox = Box.createHorizontalBox();
        startButton = new JButton("Begin simulation");
        startButton.setActionCommand("begin");
        startButton.addActionListener(this);
        stopButton = new JButton("End simulation");
        stopButton.setActionCommand("end");
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);
        resumeButton = new JButton("Continue Simulation");
        resumeButton.setActionCommand("continue");
        resumeButton.addActionListener(this);
        resumeButton.setEnabled(false);
        buttonBox.add(startButton);
        buttonBox.add(stopButton);
        buttonBox.add(resumeButton);
        controlPanel = new JPanel();
        BoxLayout cpLayout = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
        controlPanel.setLayout(cpLayout);
        controlPanel.add(buttonBox);
        for (int i = 0; i < sliders.length; i++) {
            Box b = Box.createHorizontalBox();
            b.add(sliderLabels[i]);
            b.add(sliders[i]);
            controlPanel.add(b);
        }
        controlPanel.setVisible(true);
        simWorker = new SimWorker(new Simulation(width.getValue(),
                height.getValue(), startPills.getValue(),
                startPax.getValue(), startGhosts.getValue(),
                maxPills.getValue(), pillsRespawnTime.getValue(),
                paxReproduce.getValue(), ghostsReproduce.getValue(),
                ghostsTimeLimit.getValue()));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("begin".equals(e.getActionCommand())) {
            beginSimulation();
        } else if ("end".equals(e.getActionCommand())) {
            endSimulation();
        } else if ("continue".equals(e.getActionCommand())) {
            continueSimulation();
        }
    }
    
    public void startSimulation() {
        if(simWorker != null) {
            simWorker.cancel(true);
        }
        jf.setSize(width.getValue(), height.getValue());
        simWorker = new SimWorker(new Simulation(width.getValue(),
                height.getValue(), startPills.getValue(),
                startPax.getValue(), startGhosts.getValue(),
                maxPills.getValue(), pillsRespawnTime.getValue(),
                paxReproduce.getValue(), ghostsReproduce.getValue(),
                ghostsTimeLimit.getValue()));
        simWorker.fps = framesPerSecond.getValue();
        simWorker.execute();
        endButton.setEnabled(true);
        beginButton.setEnabled(false);
        continueButton.setEnabled(false);
    }
    
    public void stopSimulation() {
        if (simWorker != null && !(simWorker.isCancelled())) {
            simWorker.cancel(true);
        }
        assert(simWorker.isCancelled());
        endButton.setEnabled(false);
        beginButton.setEnabled(true);
        continueButton.setEnabled(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rectangle rect = new Rectangle();
        rect = g.getClipBounds(rect);
        if (src != null) {
            g.drawImage(src, 0, 0, rect.width, rect.height, this);
        }
    }

    private void continueSimulation() {
        if (simWorker != null && simWorker.isCancelled()) {
            simWorker = new SimWorker(simWorker.sim);
            simWorker.fps = framesPerSecond.getValue();
            simWorker.execute();
        }
        beginButton.setEnabled(false);
        endButton.setEnabled(true);
        continueButton.setEnabled(false);
    }

    private class SimWorker extends SwingWorker<Void, Image> {

        public Simulation sim;
        public int fps;
        
        public SimWorker(Simulation sim) {
            this.sim = sim;
        }

        @Override
        protected void process(List<Image> chunks) {
            for (Image bufferedImage : chunks) {
                src = bufferedImage;
                repaint();
            }
        }

        @Override
        protected Void doInBackground() throws Exception {
            int frames = 0;
            int[] mem = new int[sim.width * sim.height];
            long start = System.currentTimeMillis();
            long last = start;
            SimObject obj;
            Color col = null;
            int refreshIntervalMS;
            if (fps == 0) {
                refreshIntervalMS = 0;
            } else {
                refreshIntervalMS = 1000 / fps;
            }
            long time;


            int numColors = sim.getGrid().maxPills + 1;
            Color[] pillsColors = new Color[numColors];
            for (int i = 0; i < numColors; i++) {
                pillsColors[i] = new Color(0, 255/ numColors * i, 0);
            }

            while (!isCancelled()) {//last < end){
                time = System.currentTimeMillis();
                sim.update();
                for (int y = 0; y < sim.height; y++) {
                    for (int x = 0; x < sim.width; x++) {
                        Position pos = new Position(x, y);
                        obj = sim.getGrid().get(new Position(x, y));
                        if (obj == null) {
                            col = pillsColors[sim.getGrid().getPills(pos)];
                        } else if (obj instanceof Pax) {
                            col = Color.yellow;
                        } else if (obj instanceof Ghosts) {
                            col = Color.blue;
                        }
                        mem[x + y * sim.width] = col.getRGB();
                    }
                }
                Image img = createImage(new MemoryImageSource(sim.width,
                        sim.height, mem, 0, sim.width));
                BufferedImage bi = new BufferedImage(
                        sim.width, sim.height,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = bi.createGraphics();
                g2.drawImage(img, 0, 0, null);
                g2.dispose();
                publish(bi);
                last = System.currentTimeMillis();
                if (fps != 0) {
                    try {
                        Thread.sleep(
                                Math.max(0, refreshIntervalMS - (last - time)));
                     } catch (InterruptedException e) {
                     }
                }

                frames++;
                System.out.println(sim.numPills + "," + sim.numPax + ","
                        + sim.numGhosts);
            }
            System.err.println("Frames = " + frames
                    + ", fps = " + ((double) frames / (last - start) * 1000));
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame simFrame = new JFrame("Pax vs. Ghosts");
                JFrame controlFrame = new JFrame("P vs. G Control Panel");
                final PaxGhostsGame w = new PaxGhostsGame();
                w.jf = simFrame;
                simFrame.getContentPane().add(w, BorderLayout.CENTER);
                controlFrame.getContentPane().add(w.controlPanel);
                simFrame.setSize(200, 200);
                controlFrame.setSize(500, 550);
                simFrame.setVisible(true);
                controlFrame.setVisible(true);
                simFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                simFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        w.simWorker.cancel(true);
                    }
                });
            }
        });
    }
}