package Braint.drawMethods.attractors;

import Braint.drawMethods.IProcessingDrawable;
import Braint.main.BraintMainApplet;
import GenerativeDesignSources.Node;

public class BraintNode implements IProcessingDrawable {

	private Node myNode;
	private int color;

	public BraintNode(BraintAttractorsDraw bad, int x, int y, int i) {
		color = i;
		myNode = new Node(x, y);
		myNode.x = x;
		myNode.y = y;

		// myNode.setStrength(-2);
		// myNode.setDamping(0.1f);
		// myNode.setBoundary(0, 0, -300, width, height, 300);
		// myNode.velocity.x = bad.random(-3,3);
		// myNode.velocity.y = bad.random(-3,3);

	}

	public Node getNode() {
		return myNode;
	}

	@Override
	public void setupDrawing(BraintMainApplet applet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAndDraw(BraintMainApplet applet) {
		
		float oldX = myNode.x;
		float oldY = myNode.y;
		myNode.update();
		
		if(oldX != myNode.x || oldY != myNode.y) {

//		int loc = (int) (myNode.x + myNode.y * applet.width);

		
		}
		applet.pushMatrix();
		applet.fill(color);
		applet.rect(myNode.x, myNode.y, BraintAttractorsDraw.rectangleSize, BraintAttractorsDraw.rectangleSize);
		applet.popMatrix();
		// try {
		// bad.pixels[loc] = color;
		//
		// } catch (Exception e) {
		//
		// }

	}

}
