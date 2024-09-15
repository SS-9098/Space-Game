import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Enemy extends JPanel implements ActionListener
{
	Image enemy;
	Timer timer;
	Timer direction;
	int panel_height=400;
	int panel_width=500;
	int xVelo=1;
	int yVelo=1;
	int x=0,y=0;

	public Enemy() 
	{
		timer=new Timer(10,this);
		direction=new Timer(1000,this);
		
		this.setBounds(0,100,panel_width,panel_height);
		enemy=new ImageIcon("enemy.png").getImage();
		this.setOpaque(false);
		
		timer.start();
		direction.start();
		
	}
	
	public void paint(Graphics g)
	{
		Graphics2D G=(Graphics2D) g;
		
		super.paint(G);
		G.drawImage(enemy, x, y, 50, 50, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==direction) 
		{
			xVelo=(int) (Math.random()*(4+4)-4);
			yVelo=(int) (Math.random()*(3+3)-3);
		}
			if(x>=panel_width-50)
				xVelo=(int) (Math.random()*(0-7));
			if(x<0)
				xVelo=(int) (Math.random()*(7-0)+0);
			if(y>=panel_height-50)
				yVelo=(int) (Math.random()*(0-5)+0);
			if(y<0)
				yVelo=(int) (Math.random()*(5-0)+0);
			x=x+xVelo;
			y=y+yVelo;
			repaint();// Calls paint method again
		
		
	}
	
	public void reset()
	{
		enemy=new ImageIcon("blast.png").getImage();
		repaint();
		
		x=0;
		y=0;
		enemy=new ImageIcon("enemy.png").getImage();
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,50,50);
	}
}
