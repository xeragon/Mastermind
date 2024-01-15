package V1.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class BackgroundPanel extends JPanel
{
    private Image background;
    public BackgroundPanel()
    {
        //Image background = Toolkit.getDefaultToolkit().createImage("V1/View/scroll.jpg");

        ImageIcon background = new ImageIcon(getClass().getClassLoader().getResource("scroll.jpg"));
        this.background = background.getImage();
        setLayout( new BorderLayout() );
        setOpaque(true);
        revalidate();
        repaint();
        // setBackground(Color.CYAN);
    }
 
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // g.drawImage(background, 0, 0, null); // image full size
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null); // image scaled
    }
 
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }
}