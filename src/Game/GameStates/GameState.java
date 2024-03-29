package Game.GameStates;

import Game.Entities.Dynamic.Player;

import Main.Handler;
import Worlds.WorldBase;
import Worlds.WorldOne;

import java.awt.*;


/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State{
    
    private WorldBase world;
	

    public GameState(Handler handler){
        super(handler);
        world = new WorldOne(handler);
        handler.setWorld(world);
        handler.getWorld().player= new Player(handler);
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false; //poner player pos = (0,0) maybe
                handler.getWorld().appleLocation[i][j]=false;
                

            }
        }
        handler.getWorld().playerLocation[handler.getWorld().player.xCoord][handler.getWorld().player.yCoord] =true;//maybe pos (0,0)


    }
    
    @Override
    
    public void tick() {

        handler.getWorld().tick();//take it

    }
    
    @Override
    
    public void render(Graphics g) {
    
	 
     handler.getWorld().render(g);//render it
    
    }
    
}
