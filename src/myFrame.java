import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class myFrame implements ActionListener
{
	Image ship;
	int x=0,y=0;
	int x1=0,y1=500;
	int ammo=3;
	int score=0;
	int delay;
	JPanel panel;
	JPanel player;
	JPanel Scores;
	JLabel Ammo;
	JLabel Score;
	JButton reset;
	Enemy obj;
	Timer timer;
	Image Background;
	public JFrame frame;
	
	public myFrame()
	{
		frame=new JFrame();
		frame.setLayout(null);
		frame.setTitle("Space Game");
		frame.setSize(500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		Background=new ImageIcon("spaceship.png").getImage();
		
		Scores=new JPanel();
		Scores.setBounds(0, 0, 500, 70);
		Scores.setLayout(null);
		Scores.setOpaque(false);
		
		Score=new JLabel("Score: 0");
		Score.setBounds(0, 0, 200, 100);
		Score.setFont(new Font("",Font.PLAIN,30));
		
		reset=new JButton("RESET");
		reset.setBounds(195,40,80,30);
		reset.setForeground(Color.red);
		reset.setBackground(Color.blue);
		reset.setOpaque(false);
		reset.setFocusable(false);
		reset.addActionListener(this);
		
		Ammo=new JLabel("Ammo: 3");
		Ammo.setBounds(350, 0, 200, 100);
		Ammo.setFont(new Font("",Font.PLAIN,30));
		
		obj=new Enemy();
		panel=obj;
		player=new Player();
		
		Scores.add(Ammo);
		Scores.add(Score);
		Scores.add(reset);
		frame.add(Scores);
		frame.add(panel);
		frame.add(player);
		frame.setVisible(true);
	}
	public class Player extends JPanel implements KeyListener
	{
		JPanel beam=new Beam();
		public Player() 
		{
			frame.add(beam);
			frame.addKeyListener(this);
			this.setBounds(0, 500, 500, 100);
			this.setOpaque(false);
			ship=new ImageIcon("spaceship.png").getImage();
			
		}
		
		public void paint(Graphics g)
		{
			Graphics2D G=(Graphics2D) g;
			
			super.paint(G);
			G.drawImage(ship, x, y, 70, 70, null);
		}

		public int XPosition()
		{
			return x;
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
			
			switch(e.getKeyCode())
			{
				case 65:
					if(x>0)
						x=x-8;
					repaint();
					break;
				case 68:
					if(x<=500-86)
					x=x+8;
					repaint();
					break;
				case 32:
					if(ammo>0)
					{
						timer.stop();
						ammo--;
						Ammo.setText("Ammo: "+ammo);
						new Beam();
					}
				
			}
			
		}
		
		public class Beam extends JPanel implements ActionListener
		{
			
			
			
			
			public Beam() 
			{
				y1=500;
				x1=x+35;
				timer=new Timer(1,this);
				this.setOpaque(false);
				this.setBackground(Color.black);
				this.setBounds(0, 0, 500, 500);
				
				timer.start();
				
			}
			
			public void paint(Graphics g)
			{
				Graphics2D G=(Graphics2D) g;
				
				super.paint(G);
				
				G.setPaint(Color.yellow);
				G.setStroke(new BasicStroke(5));
				G.drawLine(x1, y1, x1, y1+30);
			}

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				y1=y1-15;
				delay++;
				if(ammo==0)
				{
					Score.setText("GAME OVER");
					
				}
				collision();
				repaint();
				
				
			}
			public void collision()
			{
				if(obj.getBounds().intersects(x1,y1,5,30))
				{
					if(delay>50)
					{
						score++;
						Score.setText("Score: "+ score);
						delay=0;
						obj.reset();
						ammo=3;
						Ammo.setText("Ammo: "+ ammo);
					}
					
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

	}
	
	void paint(Graphics g)
	{
		Graphics2D G= (Graphics2D) g;
		
		G.drawImage(Background, 0, 0, 500, 600, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		score=0;
		ammo=3;
		Score.setText("Score: "+score);
		Ammo.setText("Ammo "+ammo);
		
	}

}
