package Game.Entities.Dynamic;

import Main.Handler;
import Worlds.WorldBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
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


	 private String direction;//is your first name one?



	 public Player(Handler handler) {

		 this.handler = handler;
		 xCoord = 0;
		 yCoord = 0;
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
		 if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && direction != "Down"){ //direction != prevents backtracking
			 direction= "Up";
		 }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && direction != "Up"){
			 direction="Down";
		 }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && direction != "Right"){
			 direction="Left";
		 }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && direction != "Left"){
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

		 if(!handler.getWorld().body.isEmpty()) {// to not move all pieces. i just take last one and put it in empty body
			 handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
			 handler.getWorld().body.removeLast();
			 handler.getWorld().body.addFirst(new Tail(x, y,handler)); // hace q se anada la cola


		 }

	 }



	 public void render(Graphics g,Boolean [][] playeLocation) {


		 for(int i =3; i<WorldBase.body.size(); i++) {//i=1 da un error leve
			 if(xCoord == WorldBase.body.get(i).x && yCoord == WorldBase.body.get(i).y){
				 moveCounter = 0;

				 g.setColor(Color.RED);
				 g.setFont(new Font("arial", Font.BOLD, 80));
				 g.drawString("Game Over", 180, 350);

				 g.setColor(Color.WHITE);
				 g.setFont(new Font ("arial", Font.BOLD, 20));
				 g.drawString("Press Space to Restart", 280, 400);  

			 }
		 }


		 Random r = new Random();
		 for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
			 for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {


				 if(playeLocation[i][j]){//split this playeLocation[i][j] and handler.getWorld().appleLocation[i][j]
					 g.setColor(Color.GREEN);// snake color
					 g.fillRect((i*handler.getWorld().GridPixelsize),
							 (j*handler.getWorld().GridPixelsize),
							 handler.getWorld().GridPixelsize,
							 handler.getWorld().GridPixelsize);
				 }
				 if(handler.getWorld().appleLocation[i][j]){
					 g.setColor(handler.getWorld().apple.color);//apple color
					 g.fillRect((i*handler.getWorld().GridPixelsize),
							 (j*handler.getWorld().GridPixelsize),
							 handler.getWorld().GridPixelsize,
							 handler.getWorld().GridPixelsize); 
				 }
				 //split if statement
				 // if player and down if apple
				 //imagesdraw.myappleimage
				 //if(playeLocation[i][j] || handler.getWorld().appleLocation[i][j]){




			 }
		 }


	 }





	 public void Eat() {

		 if(handler.getWorld().apple.isGood) {

			 State.score= State.score + 5;//score
			 //    	State.speed --;//new
			 lenght++;
			 Tail tail= null;
			 handler.getWorld().appleLocation[xCoord][yCoord]=false; //when eat apple get rid of it.. maybe usar algo parecido para la cola
			 handler.getWorld().appleOnBoard=false; // hace q aparezcan mas manzanas generate apple
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
			 // i can call eat method to add tail so maybe it dont give me error
			 handler.getWorld().body.addLast(tail);// hace que se añada la cola y no se deje alimento
			 handler.getWorld().playerLocation[tail.x][tail.y] = true;

		 }else {
			 handler.getWorld().appleLocation[xCoord][yCoord]=false;
			 handler.getWorld().appleOnBoard=false;
			 State.score=State.score-5;

			 if(!handler.getWorld().body.isEmpty()) {
				 handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y]=false;
				 handler.getWorld().body.removeLast();

			 }
		 }

	 }


	 public void kill(){
		 lenght = 0;
		 //new

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
