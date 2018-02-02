package secondPlatformer;

import javax.swing.JFrame;

public class UI {
	public static void main(String[] args){
		Game game = new Game();
		
		JFrame frame = new JFrame("platform");
		
		//frame.addKeyListener(new Game());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();                                        
		frame.setSize(1000, 1000);                           
		frame.setVisible(true);
		frame.add(game);
		game.requestFocus();
	}
}
