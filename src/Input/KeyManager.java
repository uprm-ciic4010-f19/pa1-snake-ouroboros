package Input;

import java.awt.event.KeyEvent;



import java.awt.event.KeyListener;

import Game.Entities.Dynamic.Player;
import Game.Entities.Dynamic.Tail;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Main.GameSetUp;
import Main.Handler;
import Worlds.WorldBase;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class KeyManager implements KeyListener {

	
	
	private boolean[] keys,justPressed,cantPress;
	public boolean up=false, down=false, left=false, right=false;
	public boolean pbutt=false;
	


	public KeyManager(){

		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];

	}

	public void tick(){
		for(int i =0; i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i]=false;

			}else if(justPressed[i]){
				cantPress[i]=true;//cant pressed no se puede usar tecla
				justPressed[i] =false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i]=true;
			}
		}
		
		
		

		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];

		

	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){//pauses game
			
			State.setState(Handler.getGame().pauseState);

			}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) { //restarts game
			
			State.score = 0;
			State.speed = 5;
			Player.lenght = 1;
			Player.xCoord = 0;
			Player.yCoord = 0;
			if(!Player.handler.getWorld().body.isEmpty()) {
				 Player.handler.getWorld().playerLocation[Player.handler.getWorld().body.getLast().x][Player.handler.getWorld().body.getLast().y]=false;
				 Player.handler.getWorld().body.removeLast();

			 }

			repaint();
	    }
		
		
        if(e.getKeyCode() == KeyEvent.VK_N) {//adds tail
          
          Player.lenght++;
          Player.handler.getWorld().body.addFirst(new Tail(Player.xCoord,Player.yCoord,Player.handler));//anade la cola nuevoooo           
           
		   repaint();	
	   }
        if(e.getKeyCode() == KeyEvent.VK_ADD) {//increases speed maybe poner = also for the another key
            State.speed --;
            repaint();	
 		}
        if(e.getKeyCode() == KeyEvent.VK_SUBTRACT) {//reduces speed
            State.speed ++;
            repaint();	
 		}
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	

	private void repaint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
	
		return justPressed[keyCode];
		
		
	
	}
   
	

}
