import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class playerData {
	double x = 50, xVel = 0;
	double y = 0;
	double yVel = 0;
	int size = 15;
	
	
	static boolean isBetween(double a, double b, double c){
		if((a >= b && a <= c) || (a >= c && a <= b)){
			return true;
		} else {
			return false;
		}
	}
	
	public void move(ArrayList<Integer> keys, boolean touchingGround){
		if(!touchingGround){
			yVel += 0.5;
		} else {
			yVel = 0;
			//touchGround = false;
		}
		if(keys.contains(KeyEvent.VK_RIGHT)){
			xVel += 1;
		}
		if(keys.contains(KeyEvent.VK_LEFT)){
			xVel -= 1;
		}
		if(keys.contains(KeyEvent.VK_UP) && touchingGround){
			System.out.println("Up");
			yVel = -15;
		}
		
		
		
		xVel *= 0.9;
		x += xVel;
		y += yVel;
	}
	
	ArrayList<Integer> pointsx = new ArrayList<>();
	ArrayList<Integer> pointsy = new ArrayList<>();
	ArrayList<Double> lineData = new ArrayList<>();
	
	public boolean touchingGround(){
		double yonline;
		double xonline;
		for(int i = 0; i < pointsx.size(); i += 2){
			if(Math.abs(y - (yonline = ((lineData.get(i) * x) + lineData.get(i + 1)))) < size){
				if(isBetween(x, pointsx.get(i), pointsx.get(i + 1))){
					if(y < yonline){
						if(isBetween(lineData.get(i), 0.5, -0.5)){
							while(Math.abs(y - ((lineData.get(i) * x) + lineData.get(i + 1))) < size-1){
								y--;
							}
							return true;
						} else {
							if(Math.abs(x - (xonline = ((y-lineData.get(i + 1))/lineData.get(i)))) < size ){
								xVel = -10;
								return false;
							}
							/***Do wall detection***/
						}
						//return true;
					} else {
						yVel = 1;
						return false;
					}
				}
			}
		}
		/*System.out.println("distance:  " + Math.abs(y - pointsy.get(0)));
		System.out.println("player y:  " + y);
		System.out.println("Line y:  " + pointsy.get(0));*/
		return false;
	}
	
	public void line(int x, int y, int x2, int y2){
		pointsx.add(x);
		pointsx.add(x2);
		pointsy.add(y);
		pointsy.add(y2);
		lineData.add(((double)(y - y2) / (double)(x - x2)));					//slope
		lineData.add(((double)y - (((double)(y - y2) / (double)(x - x2)) * (double)x)));		//y-intercept
		
		
	}
	
	private void collision(){
		
	}
	
	
	
}
