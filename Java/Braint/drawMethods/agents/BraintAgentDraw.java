package Braint.drawMethods.agents;

import Braint.drawMethods.IProcessingDrawable;
import Braint.main.BraintMainApplet;
import Braint.openVibe.OpenVibeCalibration;
import Braint.openVibe.OpenVibeOscEEGPowerDataHandler;
import Braint.settings.BigSettings;
import Braint.util.BraintUtil;

/**
 * Created by Saitama on 18.01.2016.
 *
 *
 */
public class BraintAgentDraw implements IProcessingDrawable {

	/**
	 * @param strokeWidthScale
	 *            Value between 0 and 1, modifies the strokeWidth of the Agents
	 *            in agents.update1()
	 *
	 * @param noiseScale
	 *            change angle properties of Agents Value between 200 and 1000
	 *            is good
	 *
	 * @param noiseStrength
	 *            change angle properties of Agents Value between 6 and 30 is
	 *            good
	 */
	BraintAgent[] agents;

	float strokeWidthScale;
	float noiseScale, noiseStrength;
	float oscNoiseScale, oscNoiseStrength, oscValueToRGB;
	float oscNoiseScaleMax, oscNoiseStrengthMax, oscValueToRGBMax;
	float oscNoiseScaleMin, oscNoiseStrengthMin, oscValueToRGBMin;
	int timerA, timerB, timerC;
	int rgb;
	float valueToRGB;
	int x = 0;
	int a = 2;

	// 30 seconds calibration
	boolean calibrated = false;
	int calibrationTime = 15000;
	long startTime = -1;

	// TODO
	private boolean useThresholds;

	public void setIntensityThresholds(boolean useThresholds) {
		this.useThresholds = useThresholds;
	}
	
	
	public  BraintAgentDraw(int size) {
		agents = new BraintAgent[size];
	}

	@Override
	public void setupDrawing(BraintMainApplet applet) {

		for (int i = 0; i < agents.length; i++) {
			agents[i] = new BraintAgent(this);
			agents[i].setupDrawing(applet);
		}
		strokeWidthScale = 0.3f;
		noiseStrength = 6f;
		noiseScale = 80f;
		valueToRGB = 1;
		oscNoiseScale = 80f;
		oscNoiseStrength = 13f;
		oscValueToRGB = 0f;

		oscNoiseScaleMax = 1f;
		oscNoiseStrengthMax = 1f;
		oscValueToRGBMax = 1f;

		oscNoiseScaleMin = 0f;
		oscNoiseStrengthMin = 0f;
		oscValueToRGBMin = 0f;

		rgb = BraintUtil.decideRGBValue(valueToRGB, 2);

		timerA = 0;
		timerB = 0;
		timerC = 0;

		useThresholds = false;

	}

	/**
	 * takes one array with values through osc and returns this array normalizes
	 * every value to values between 0.3 and 1(values that are greater than the
	 * global maximum will be between 1 and 1.5) with a global maximum and
	 * minimum.
	 * 
	 * @return float array with values
	 */
	// public float[] getEmotivValues(){
	// return
	// }

	/**
	 * set all variable parameters for drawing, depending on choosed array slots
	 * 
	 * @param emotivValues
	 * @param a
	 *            Arrayslot for noiseScale value
	 * @param b
	 *            Arrayslot for noiseStrength value
	 * @param c
	 *            Arrayslot for strokeWidthScale value
	 * @param d
	 *            Arrayslot for rgb value
	 */

	public void setVariables(float[] emotivValues, int a, int b, int c, int d) {

		// **Begin NoiseScale**

		// **End NoiseScale**

		// **Begin NoiseStrength**

		// **EndNoiseStrength**

		// **Begin strokeWidthScale**

		// **End strokeWidthScale**

		// **Begin valueToRGB value**

		// **End valueToRGB value**

	}

	public float getStrokeWidthScale() {
		return strokeWidthScale;
	}

	public float getNoiseScale() {
		return noiseScale;
	}

	public float getNoiseStrength() {
		return noiseStrength;
	}

	public int getRGB() {
		return rgb;
	}

	public void setOscNoiseScale(float x) {
		oscNoiseScale = x;
	}

	public void setOscNoiseStrength(float x) {
		oscNoiseStrength = x;
	}

	public void setValueToRGB(float x) {
		oscValueToRGB = x;
	}

	@Override
	public void updateAndDraw(BraintMainApplet applet) {
		x++;

		if (BigSettings.instance().useAgents) {
			OpenVibeOscEEGPowerDataHandler data = applet.getPowerDataHandler();
			OpenVibeCalibration calib = applet.getCalibrationData();

//			if (useThresholds)
//				BraintAgentParameterDeterminer.determineBasedOnOpenVibeSignalsWithIntensityThresholds(this, data,
//						calib);
//			else{
//				
//				//BraintAgentParameterDeterminer.determineBasedOnOpenVibeSignalsITHINKTHISLOOKSQUITEOK(this, data, calib);
//			}
			
//			BraintAgentParameterDeterminer.determineBasedOnOpenVibeSignalsOnlyAlpha(this, data, calib);
//			BraintAgentParameterDeterminer.useTestValues(this);
			
			BraintAgentParameterDeterminer.ovDetermineNoiseValues(this, data, calib);

		} else if (BigSettings.instance().useEmoEngine) {

			BraintAgentParameterDeterminer.determineBasedOnEmoEngine(this, applet.getEmoEngineHandler());

		}

		// System.out.println("noise strength:" + noiseStrength + "--- scale:" +
		// noiseScale);

		// draw everything with the changed values
		for (int i = 0; i < agents.length; i++) {
			agents[i].updateAndDraw(applet);
		}

	}

	private boolean useOpenVibe = false;
	private boolean useEmoEngine = false;

	public void useOpenVibe(boolean useOpenVibe) {
		this.useOpenVibe = useOpenVibe;
		this.useEmoEngine = !useOpenVibe;

	}

	public void useEmoengine(boolean useEmoEngine) {
		// TODO Auto-generated method stub
		this.useEmoEngine = useEmoEngine;
		this.useOpenVibe = !useEmoEngine;

	}

}
