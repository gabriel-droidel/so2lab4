import javax.swing.*;
import java.awt.*;

public class FractalTree extends JPanel {

    private int maxDepth = 10;
    private int angleSpread = 25;
    private double lengthFactor = 0.7;

    // setters for different values
    // depth
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        repaint(); // redraw after updated value
    }

    // angle
    public void setAngleSpread(int angle) {
        this.angleSpread = angle;
        repaint();
    }

    // length
    public void setLengthFactor(double length) {
        this.lengthFactor = length;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int startX = getWidth() / 2;
        int startY = getHeight() - 30;

        drawBranch(g2d, startX, startY, 100, -90, maxDepth);
    }

    private void drawBranch(Graphics2D g2d,
                            int x1, int y1,
                            double length,
                            double angle,
                            int depth) {

        if (depth == 0 || length < 2) return;

        int x2 = x1 + (int) (length * Math.cos(Math.toRadians(angle)));
        int y2 = y1 + (int) (length * Math.sin(Math.toRadians(angle)));


        g2d.drawLine(x1, y1, x2, y2);

        double newLength = length * lengthFactor;
        drawBranch(g2d, x2, y2, newLength, angle - angleSpread, depth - 1);
        drawBranch(g2d, x2, y2, newLength, angle + angleSpread, depth - 1);
    }
}
