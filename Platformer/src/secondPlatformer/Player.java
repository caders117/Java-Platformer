package secondPlatformer;

import java.util.ArrayList;

public class Player {
	double playerx = 0, playery = 0, xvel = 0, yvel = 0;
	boolean touchGround = false;
	ArrayList<Integer> keys = new ArrayList<>();
	ArrayList<double[]> pointsData = new ArrayList<>();
	int left, right, jump;
	
	void setPoints(ArrayList<double[]> points){
		pointsData = points;
	}
	
	void setControls(int l, int r, int j){
		left = l;
		right = r; 
		jump = j;
	}
	
	void update(ArrayList<Integer> list){
		keys = list;
		movement();
		collisions();
	}
	
	private void movement(){
		if(keys.contains(right)){
			xvel += 1;
		}
		if(keys.contains(left)){
			xvel -= 1;
		}
		if(keys.contains(jump) && touchGround){
			yvel = -15;
		}
		
		xvel *= 0.9;
		yvel += 1;
		
		playerx += xvel;
		playery += yvel;
	}
	
	private void collisions(){
		touchGround = false;
		for(int i = 0; i < pointsData.size(); i++){
			double slope, b;
			double x1 = pointsData.get(i)[0], 
					y1 = pointsData.get(i)[1], 
					x2 = pointsData.get(i)[2], 
					y2 = pointsData.get(i)[3];
			if(x1 == x2){
				slope = (y1 - y2) / (x1 - (x2 + 1));
			} else {
				slope = (y1 - y2) / (x1 - x2);			// (y1 -y2) / (x1 - x2)
			}
			
			b = (y1 - slope * x1);						// y = mx + b --> b = y - mx
			
			if(slope >= -0.5 && slope <= 0.5){
				if(Math.abs((playery + yvel) - ((slope * playerx) + b)) < 15){
					if(isBetween(playerx, x1, x2)){
							if(playery < ((slope * playerx) + b) -2){
								touchGround = true;
								yvel = 0;
								while(Math.abs(playery - ((slope * playerx) + b)) < 15){
									playery--;
								}
							} else {
								while(Math.abs(playery - ((slope * playerx) + b)) < 15){
									playery++;
								}
								yvel = 0;
							}
						} else {
					}
				}
			} else {
				if(Math.abs(playerx - ((playery - b) / slope)) < 15){
					if(isBetween(playery, y1, y2)){
						if(playerx > (playery - b) / slope){
							while(Math.abs(playerx - ((playery - b) / slope)) < 15){
								playerx++;
							}
							if(keys.contains(jump)){
								yvel = -10;
								xvel = 5;
							} else {
								xvel = 0;
							}
						} else {
							while(Math.abs(playerx - ((playery - b) / slope)) < 15){
								playerx--;
							}
							if(keys.contains(jump)){
								yvel = -10;
								xvel = -5;
							} else {
								xvel = 0;
							}
						}
					}
				}
			}
		}
	}
	
	boolean isBetween(double a, double b, double c){
		if((a >= b && a <= c) || (a >= c && a <= b)){
			return true;
		}
		return false;
	}
}
