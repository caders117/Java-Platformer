import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ui extends JPanel{
	
	static ArrayList<Integer> keys = new ArrayList<>();
	static Timer timer;
	static boolean touchingGround;
	static Engine engine = new Engine();
	public static playerData player = new playerData();
	static ui game = new ui();
	static double playerx, playery;

	public static void main(String[] args){
		
		initFrame();
		init();
		game.initGame();
	}
	
	private void initGame(){
		setBackground(Color.RED);
		timer = new Timer();
		timer.scheduleAtFixedRate(new scheduleTask(), 100, 25);
	}
	
	private static void init(){
		player.line(0, 200, 200, 200);
		player.line(0, 400, 200, 400);
		player.line(300, 200, 600, 100);
		player.line(0, 600, 600, 600);
		player.line(500, 600, 550, 300);
		
		System.out.println(player.lineData);
	}
	
	private  static void initFrame(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.addKeyListener(new keyListen());
		frame.pack();
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
	private static class keyListen implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			//player.move(e.getKeyCode());
			if(!keys.contains(e.getKeyCode())){
				keys.add(e.getKeyCode());
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			keys.remove(keys.indexOf(e.getKeyCode()));
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillOval((int)player.x, (int)player.y, player.size, player.size);
		for(int i = 0; i < player.lineData.size(); i += 2){
			g2d.drawLine(player.pointsx.get(i), player.pointsy.get(i), player.pointsx.get(i + 1), player.pointsy.get(i + 1));
			
		}
		repaint();
	}
	
	static class scheduleTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			player.move(keys, player.touchingGround());
			/*System.out.println("Slope: " + player.lineData.get(4));
			System.out.println("Y-intercept " + player.lineData.get(5));
			System.out.println(player.yVel);
			System.out.println("Touching ground? " + player.touchingGround());*/
			System.out.println(keys);
		}
		
	}
}
