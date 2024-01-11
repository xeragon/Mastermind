package V1.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel
{
    private Image background;
    public BackgroundPanel()
    {
        Image background = Toolkit.getDefaultToolkit().createImage("/home/xeragon/Bureau/BUT_S3/A31/saeA31/a31-mastermind/V1/Ressource/scroll.jpg");
        this.background = background;
        setLayout( new BorderLayout() );
        // setBackground(Color.CYAN);
        setOpaque(true);

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