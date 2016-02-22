package Braint.drawMethods.attractors;

import Braint.drawMethods.IProcessingDrawable;
import Braint.main.BraintMainApplet;
import GenerativeDesignSources.Attractor;
import GenerativeDesignSources.Node;
import processing.core.PConstants;
import processing.core.PImage;

public class BraintAttractorsDraw implements IProcessingDrawable {

	BraintNode[] pixelNodes;
	Node[] theNodes;

	// CLEAN UP
	PImage img;
	private boolean initAttractors = true;
	private Attractor attractor, attractor2;

	public static int rectangleSize = 4;

	@Override
	public void setupDrawing(BraintMainApplet applet) {

		// ATTRACTORS // TEST.jpg
		img = applet.loadImage("eminem.jpg");
		pixelNodes = new BraintNode[(applet.width / rectangleSize) * (applet.height / rectangleSize)];
		theNodes = new Node[applet.width * applet.height];

		// pixelNodes = new AttractorTest[10];
		// theNodes = new Node[10];

		attractor = new Attractor(applet.width / 2, applet.height / 2, 0);
		attractor.setMode(Attractor.BASIC);
		attractor.setRadius(200);
		attractor.setStrength(5);
		attractor.ramp = 0.4f;

		attractor2 = new Attractor(applet.width / 2, applet.height / 2, 0);
		attractor2.setMode(Attractor.BASIC);
		attractor2.setRadius(200);
		attractor2.setStrength(-5);
		attractor2.ramp = 0.4f;
		attractor2.damping = 1;

		applet.loadPixels();
		// Since we are going to access the image's pixels too
		img.loadPixels();
		int c = 0;
		for (int y = 0; y < applet.height; y += rectangleSize) {
			for (int x = 0; x < applet.width; x += rectangleSize) {

				// if(c == 10)

				int loc = x + y * applet.width;

				// The functions red(), green(), and blue() pull out
				// the 3 color components from a pixel.
				float r = applet.red(img.pixels[loc]);
				float g = applet.green(img.pixels[loc]);
				float b = applet.blue(img.pixels[loc]);

				BraintNode tmp = new BraintNode(this, x, y, applet.color(r, g, b));

				pixelNodes[c] = tmp;
				// theNodes[loc] = tmp.getNode();
				applet.pushMatrix();
				applet.fill(applet.color(r, g, b));
				applet.rect(x, y, rectangleSize, rectangleSize);
				applet.popMatrix();

				c++;
			}
		}
		applet.updatePixels();
		initAttractors = false;
	}

	@Override
	public void updateAndDraw(BraintMainApplet applet) {
		if (!initAttractors) {

			// attractor 1
			//
			// float yMin = (attractor.y - attractor.radius);
			// yMin = yMin < 0f ? 0 : yMin;
			// yMin = yMin > applet.height ? applet.height : yMin;
			//
			// float yMax = (attractor.y + attractor.radius);
			// yMax = yMax < 0f ? 0 : yMax;
			// yMax = yMax > applet.height ? applet.height : yMax;
			//
			// float xMin = (attractor.x - attractor.radius);
			// xMin = xMin < 0f ? 0 : xMin;
			// xMin = xMin > applet.width ? applet.width : xMin;
			//
			// float xMax = (attractor.x + attractor.radius);
			// xMax = xMax < 0f ? 0 : xMax;
			// xMax = xMax > applet.width ? applet.width : xMax;
			//
			// for (int y = (int) yMin ; y < (int) yMax ; y++) {
			// for (int x = (int) xMin; x < (int) xMax; x++) {
			//
			// int loc = x + y * applet.width;
			// attractor.attract(pixelNodes[loc].getNode());
			//// pixelNodes[loc].updateAndDraw(applet);
			// }
			//
			// }

			attractor.x = applet.mouseX;
			attractor.y = applet.mouseY;

			attractor2.x = applet.mouseX;
			attractor2.y = applet.mouseY;

			for (BraintNode t : pixelNodes) {
				if (applet.mousePressed) {
					if (applet.mouseButton == PConstants.LEFT)
						attractor.attract(t.getNode());
					else if (applet.mouseButton == PConstants.RIGHT)
						attractor2.attract(t.getNode());

				}

				t.updateAndDraw(applet);
			}
		}
	}

}
