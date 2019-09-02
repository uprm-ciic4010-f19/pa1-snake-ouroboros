package Game.Entities.Static;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Game.Entities.Dynamic.Player;
import Game.GameStates.State;
import Main.Handler;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Apple {

    private Handler handler;

    public int xCoord;
    public int yCoord;

    public Apple(Handler handler,int x, int y){
        this.handler=handler;
        this.xCoord=x;
        this.yCoord=y;
       
    }
    
   /* Apple goodApple = new Apple(handler,5,5);
    Apple badApple =  new Apple(handler,10,10);
    Random myApples = new Random();
    
     

	public void isGood() {
	     State.score = Math.sqrt(2*State.score + 1);
	     State.speed--;
	 }
	public void isBad(){
		State.score = State.score - Math.sqrt(2*State.score + 1);
		
	}*/
    	
    
    }
    


