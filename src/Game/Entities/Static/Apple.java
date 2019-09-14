package Game.Entities.Static;

import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Apple {

    private Handler handler;

    public int xCoord;
    public int yCoord;
    public boolean good;
   

    public Apple(Handler handler,int x, int y){
        this.handler=handler;
        this.xCoord=x;
        this.yCoord=y;
        this.good = true;
    }
    public boolean isGood(int steps) {
    	
		if(steps==400) {
			this.setGood(false);
			return false;
			
		}
		
		return true;
	
    }
    
    public void setGood(boolean good) {
    	this.good = good;
    	
    }
    
   }
