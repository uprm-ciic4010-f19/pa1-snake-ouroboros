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

                handler.getWorld().playerLocation[i][j]=false;
                handler.getWorld().appleLocation[i][j]=false;
                

            }
        }
        handler.getWorld().playerLocation[handler.getWorld().player.xCoord][handler.getWorld().player.yCoord] =true;


    }
    
    @Override
    
    public void tick() {

        handler.getWorld().tick();

    }
    
    @Override
    
    public void render(Graphics g) {
     g.setColor(Color.WHITE);
     g.setFont(new Font("arial", Font.PLAIN,14));//newwwww
	 g.drawString("Scores: "+score, 550,20);
	 
     handler.getWorld().render(g);
    
    }
    
}
