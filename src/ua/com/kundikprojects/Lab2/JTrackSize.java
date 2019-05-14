package ua.com.kundikprojects.Lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class JTrackSize extends JFrame {

    private JEditorPane label;

    private JTrackSize() {
        super("Tracking width and height");
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label = new JEditorPane("text/html", "");
        label.setEditable(false);
        label.setText("<h1><b>Height: " + this.getBounds().height + "\n" + "Width: " + this.getBounds().width + "</b></h1>");

        class ResizeListener implements ComponentListener {

            public void componentHidden(ComponentEvent e) {
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentShown(ComponentEvent e) {
            }

            public void componentResized(ComponentEvent e) {
                Rectangle newSize = e.getComponent().getBounds();
                label.setText("<h1><b>Height: " + newSize.height + "\n" + "Width: " + newSize.width + "</b></h1>");

            }
        }

        this.addComponentListener(new ResizeListener());

        this.add(label);
        this.pack();
        this.setVisible(true);
    }


    public static void main(String[] args) {
        // write your code here

        new JTrackSize();

    }
}
