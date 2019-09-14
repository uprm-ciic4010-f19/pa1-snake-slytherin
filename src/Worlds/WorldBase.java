package Worlds;

import Game.Entities.Dynamic.Player;
import Game.Entities.Dynamic.Tail;
import Game.Entities.Static.Apple;
import Game.GameStates.State;
import Main.Handler;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Created by AlexVR on 7/2/2018.
 */
public abstract class WorldBase {
    private JFrame frame;

    //How many pixels are from left to right
    //How many pixels are from top to bottom
    //Must be equal
    public int GridWidthHeightPixelCount;

    //automatically calculated, depends on previous input.
    //The size of each box, the size of each box will be GridPixelsize x GridPixelsize.
    public int GridPixelsize;

    public Player player;

    protected Handler handler;


    public Boolean appleOnBoard;
    public Apple apple;
    public Boolean[][] appleLocation;


    public Boolean[][] playerLocation;
  

    public LinkedList<Tail> body = new LinkedList<>();
    



    public WorldBase(Handler handler){
        this.handler = handler;

        appleOnBoard = false;


    }
    public void tick(){



    }
    public void gameOver() {
    	
    	JOptionPane.showMessageDialog( frame, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
    	State.setState(handler.getGame().gameState);

    }


    public void render(Graphics g){

    	
//        for (int i = 0; i <= 800; i = i + GridPixelsize) {
//
//            g.setColor(Color.black);// grid line color
//            g.drawLine(0, i, handler.getWidth() , i);
//            g.drawLine(i,0,i,handler.getHeight());

        //}



    }

}
