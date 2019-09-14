package Game.GameStates;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

    private int count = 0;
    private UIManager uiManager;
    private boolean counter = true;

    public PauseState(Handler handler) {
        super(handler);
     
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);

        uiManager.addObjects(new UIImageButton(275, 330, 225, 83, Images.Resume, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
        }));

        uiManager.addObjects(new UIImageButton(275, 330+(64+16), 225, 83, Images.Options, () -> {
            handler.getMouseManager().setUimanager(null);
            new GameState(handler);
            State.setState(handler.getGame().gameState);
        }));

        uiManager.addObjects(new UIImageButton(275, (330+(64+16))+(64+16), 225, 83, Images.BTitle, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));
        
        uiManager.addObjects(new UIImageButton(275, (330+(64+16))+(64+16)+(64+16), 225, 83, Images.BTitle, () -> {
            handler.getMouseManager().setUimanager(null);
            if (counter) {
            	GameSetUp.getAudioClip().stop();
            	counter = false;
			}else {
				GameSetUp.getAudioClip().start();
				counter = true;
            	
			}
            
        }));





    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.Pause,-20,0,800,800,null);
        uiManager.Render(g);

    }
}
