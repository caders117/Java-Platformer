package secondPlatformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener{
	
	private ArrayList<Integer> keys = new ArrayList<>();	   
	private ArrayList<double[]> pointsData = new ArrayList<>();
	private Player p1 = new Player();
	private int controls[] = new int[3];
	private int playernumber = 0;
	private Player p2 = new Player();
	private Player[] players = {p1, p2};
	
	/*public static void main(String[] args){
		Game game = new Game();
		game.init();
		
		JFrame frame = new JFrame("platform");
		
		frame.add(game);
		frame.addKeyListener(new Game());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();                                        
		frame.setSize(1000, 1000);                           
		frame.setVisible(true);
	}*/
	
	public Game(){
		init();
	}
	
	void init(){
		setBackground(Color.WHITE);
		this.addKeyListener(this);
		
		lineAt(0, 200, 400, 200);
		lineAt(400, 200, 400, 300);
		lineAt(200, 400, 600, 400);
		lineAt(400, 400, 800, 200);
		lineAt(600, 450, 900, 550);
		lineAt(200, 200, 200, 0);
		
		//creation of a player
		p1.setPoints(pointsData);
		p1.setControls(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP);
		playernumber++;
		
		p2.setPoints(pointsData);
		p2.setControls(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W);
		playernumber++;
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new schedule(), 100, 25);
	}
	
	class schedule extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(keys);
			
			//loop through players and update
			for(int i = 0; i < playernumber; i++){
				players[i].update(keys);
			}
			
		}
	}
	
	void lineAt(double x, double y, double x1, double y1){
		//Add points to list of arrays
		double ar[] = {x, y, x1, y1};
		pointsData.add(ar);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//Draw players
		for(int i = 0; i < playernumber; i++){
			g2d.fillOval((int)players[i].playerx, (int)players[i].playery, 15, 15);
		}
		
		//Draw Lines
		for(int i = 0; i < pointsData.size(); i++){
			//g2d.
			g2d.drawLine((int)pointsData.get(i)[0], (int)pointsData.get(i)[1], (int)pointsData.get(i)[2], (int)pointsData.get(i)[3]);
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!keys.contains(e.getKeyCode())){
			keys.add(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(keys.contains(e.getKeyCode())){
			keys.remove(keys.indexOf(e.getKeyCode()));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
