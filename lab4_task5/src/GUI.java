import javax.swing.*;
import java.awt.*;

public class GUI {

    public GUI() {
        // frame
        JFrame frame = new JFrame("Fractal Tree");

        // panel
        JPanel controlPanel = new JPanel(); // container for sliders
        controlPanel.setLayout(new FlowLayout()); // sliders placed in a row

        // create tree object
        FractalTree treePanel = new FractalTree();

        // sliders
        // changes how deep the branches extend
        JSlider depthSlider = new JSlider(1, 15, 10);
        depthSlider.addChangeListener(e -> {
            treePanel.setMaxDepth(depthSlider.getValue()); // change depth on slider change value
        });
        // changes the angle at which the branches split
        JSlider angleSlider = new JSlider(15, 30, 25);
        angleSlider.addChangeListener(e -> {
            treePanel.setAngleSpread(angleSlider.getValue()); // change angle modifier on slider change value
        });

        // changes the length of branches
        JSlider lengthSlider = new JSlider(50, 85, 70);
        lengthSlider.addChangeListener(e -> {
            treePanel.setLengthFactor((double) lengthSlider.getValue() /100); // change length of branch on slider change value
        });

        // add sliders to panel with labels
        controlPanel.add(new JLabel("Depth:"));
        controlPanel.add(depthSlider);
        controlPanel.add(new JLabel("Branch Spread:"));
        controlPanel.add(angleSlider);
        controlPanel.add(new JLabel("Length:"));
        controlPanel.add(lengthSlider);

        // interface layout
        frame.setLayout(new BorderLayout());
        frame.add(treePanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}