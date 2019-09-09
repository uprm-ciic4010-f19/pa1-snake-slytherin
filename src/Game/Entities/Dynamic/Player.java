package Game.Entities.Dynamic;

import Main.GameSetUp;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;

import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {
	
	int speed = 7; //last digit of student id
    public int lenght;
    public boolean justAte;
    private Handler handler;

    public int xCoord;
    public int yCoord;

    public int moveCounter;

    public String direction;//is your first name one?

    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = 0;
        direction= "Right";
        justAte = false;
        lenght= 1;

    }

    public void tick(){
        moveCounter++;
        if(moveCounter>=speed) {
            checkCollisionAndMove();
            moveCounter=0;
        }
        // Move snake and prevent backtracking
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)&& direction != "Down"){
            direction="Up";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)&& direction != "Up"){
            direction="Down";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)&& direction != "Right"){
            direction="Left";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)&& direction != "Left"){
            direction="Right";
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){ //snake gets bigger when N is clicked
            System.out.println("Tail added");
        	Eat(true, false);
        }if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)){ //speed increase when + is clicked
        	speed--;
            System.out.println("speed aumentada: " + speed);
        }if(handler.getKeyManager().keyJustPressed(45)){ //speed decrease when - is clicked
        	speed++;
            System.out.println("speed decreased: " + speed);
        }

    }

    public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;
        switch (direction){
            case "Left":
                if(xCoord==0){
                    kill();
                    xCoord=handler.getWorld().GridWidthHeightPixelCount-1; //snake teleports in the opposite direction
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    kill();
                    xCoord=0;
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                    kill();
                    yCoord=handler.getWorld().GridWidthHeightPixelCount-1;

                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    kill();
                    yCoord=0;
                }else{
                    yCoord++;
                }
                break;
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;
        
        	
        

        if(handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat(false, true); //false parameter added
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }

    }

    public void render(Graphics g,Boolean[][] playeLocation){
        Random r = new Random();
        Color color = new Color(r.nextInt()); //multicolor
        
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
                g.setColor(color);//snake multicolor
               

                if(playeLocation[i][j]||handler.getWorld().appleLocation[i][j]){
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }

            }
        }

    }

    public void Eat(boolean appleOnBoardBool, boolean speedActivate){ //parameter added
    	if (speedActivate) { //manage the speed increaser 
    		speed--;
    	}
    	System.out.println("Speed: " + speed); //print speed
        lenght++;
        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=appleOnBoardBool;
        switch (direction){
            case "Left":
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
                        } System.out.println("Tu biscochito"); // easter egg?
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
        handler.getWorld().body.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
    }

    public void kill(){
        lenght = 0;
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
