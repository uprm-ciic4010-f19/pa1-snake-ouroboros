package Game.Entities.Dynamic;

import Main.Handler;





import java.awt.*;
import java.awt.event.KeyEvent;

import java.util.Random;

import Display.DisplayScreen;
import Game.GameStates.State;












/**
 * Created by AlexVR on 7/2/2018.
 */public class Player{
	 
	
    
    
	public static  int lenght;
   
    public boolean justAte;
    public static Handler handler;

    public static int  xCoord;
    public static int  yCoord;

    public static int moveCounter;

	
    

    String direction;//is your first name one?
    
    

   public Player(Handler handler) {
	  
        this.handler = handler;
        xCoord = 1;
        yCoord = 1;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;
        

    }

   
	// TODO Auto-generated method stub
	


	public void tick(){
        moveCounter++;
        if(moveCounter>=State.speed)//where speed is change ----- new >=5
        {
            checkCollisionAndMove();
            moveCounter=0;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
            direction= "Up";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)){
            direction="Down";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)){
            direction="Left";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)){
            direction="Right";
        }

    }

    public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;
        switch (direction){
            case "Left":
                if(xCoord==0){
                	xCoord =59;
                    //kill();
                   
                }else{
                    xCoord--;
                }
               
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	xCoord = 0;
                    //kill();
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                	yCoord = 59;
                    //kill();
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                	yCoord = 0;
                    //kill();
                }else{
                    yCoord++;
                }
                break;
                
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;

        
     
        
        
        if(handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat();
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler)); // hace q se anada la cola
           
        }

    }

    public void render(Graphics g,Boolean [][] playeLocation) {
    	
       /* if(playeLocation[xCoord][yCoord] ==  ){
        	moveCounter = 0;
             }*/
    	  
    	
        
    	
        Random r = new Random();
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
            	
                g.setColor(Color.GREEN);//Cambio 
                
                 if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                   
                }
              
            
            }
        }


    }
    
   

  
    	
		
	


		


		public void Eat() {
        
    	State.score= Math.sqrt(2*State.score + 1);//cambiooooooo
    	State.speed --;//new
        lenght++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false; //hace que se coja la manzana
        handler.getWorld().appleOnBoard=false; // hace q aparezcan mas manzanas
        switch (direction){
        
            
             case "Left" : 
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                        if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
       
        }
       
        handler.getWorld().body.addLast(tail);// hace que se añada la cola y no se deje alimento
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
       
      
        
    }
    	
    
			// TODO Auto-generated method stub
			
		


	public void kill(){
        lenght = 0;
        //moveCounter = 0;//new
         
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
    }

    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }
}
