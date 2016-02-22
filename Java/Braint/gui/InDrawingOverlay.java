package Braint.gui;

import Braint.drawMethods.IProcessingDrawable;
import Braint.main.BraintMainApplet;
import controlP5.ControlP5;
import controlP5.Textlabel;

public class InDrawingOverlay implements IProcessingDrawable {

	ControlP5 cp5;
	
	Textlabel alpha;

	Textlabel beta;
	
	@Override
	public void setupDrawing(BraintMainApplet applet) {
		// TODO Auto-generated method stub
		
		
		cp5 = new ControlP5(applet).setPosition(applet.width - 200, 0);
				
		cp5.addControllersFor(applet);
		
		cp5.addControllersFor("overlay", this);
		
		
		alpha = cp5.addLabel("", 5, 15);
//		alpha.setUpdate(true);
		beta = cp5.addLabel(currentBeta, 5, 45);
		
	}

	@Override
	public void updateAndDraw(BraintMainApplet applet) {
		applet.pushMatrix();
		applet.fill(155);
		applet.stroke(200);
		applet.rect(applet.width - 200, 0 + 10, 150, 25 );
		applet.rect(applet.width - 200, 0 + 40, 150, 25 );
//		applet.text(openVibeAlphaBetaPower.alpha.getMeanAllChannels(), width - 200, 0 + 50);
//		applet.text(openVibeAlphaBetaPower.beta.getMeanAllChannels(), width - 200, 0 + 110);
		applet.popMatrix();
		
		
		if(applet.frameCount % 24 == 0) {}
		
//		alpha.remove();
//		
//		alpha = cp5.addLabel(currentAlpha, 5, 15);
		
		alpha.setValue(currentAlpha);
		
		
		beta.setValue(currentBeta);
//		beta.remove();
//		beta = cp5.addLabel(currentBeta, 5, 45);
		
		
		
		
 		
	}
	
	
//	@ControlElement(x = 5, y = 75, label = "currentAlpha", properties = { "type=textlabel" })
	public String currentAlpha;
	
//	@ControlElement(x = 5, y = 125, label = "Control", properties = { "type=textlabel" })
	public String currentBeta = "beta";
	

}
