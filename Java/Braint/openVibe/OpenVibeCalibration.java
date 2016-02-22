package Braint.openVibe;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import Braint.drawMethods.IProcessingDrawable;
import Braint.main.BraintMainApplet;
import Braint.settings.BigSettings;

public class OpenVibeCalibration implements IProcessingDrawable {

	private OpenVibeOscEEGPowerDataHandler data;
	private int timeInMS;
	private boolean startCalibration;
	private boolean calibrated;
	private long startTime;
	
	private long time;
	
	public int getCurrentTimeInSeconds() {
		if(isCalibrating)
			return (int) time / 1000;
		else
			return 0;
	}

	private boolean isCalibrating;

	private SummaryStatistics alphaStatisticsSummary, betaStatisticsSummary, ratioAlphaBetaStatisticsSummary;

	public SummaryStatistics getAlphaStatisticsSummary() {
		return alphaStatisticsSummary;
	}

	public SummaryStatistics getBetaStatisticsSummary() {
		return betaStatisticsSummary;
	}

	public SummaryStatistics getRatioAlphaBetaStatisticsSummary() {
		return ratioAlphaBetaStatisticsSummary;
	}

	public void setTimeInMS(int timeInMS) {
		this.timeInMS = timeInMS;
	}

	public OpenVibeCalibration(OpenVibeOscEEGPowerDataHandler oscData) {

		data = oscData;
		timeInMS = BigSettings.instance().CALIBRATION_TIME;
		startCalibration = false;
		calibrated = false;
		startTime = -1;
		isCalibrating = false;

	}

	public void startCalibrationInNextDraw() {

		if(!startCalibration) {
		
		startCalibration = true;
		startTime = -1;
		calibrated = false;
		isCalibrating = true;
		time = 0;
		
		} else {
			startCalibration = false;
		}

	}

	public void setCalibrated(boolean calibrated) {
		this.calibrated = calibrated;
	}

	public boolean isCalibrating() {
		return isCalibrating;
	}

	@Override
	public void setupDrawing(BraintMainApplet applet) {
		// TODO Auto-generated method stub

	}

	// TODO draw something?
	@Override
	public void updateAndDraw(BraintMainApplet applet) {

		if (startCalibration && !calibrated) {

			timeInMS = BigSettings.instance().CALIBRATION_TIME * 1000;
			if (startTime < 0) {
				startTime = System.currentTimeMillis();
				alphaStatisticsSummary = new SummaryStatistics();
				betaStatisticsSummary = new SummaryStatistics();
				ratioAlphaBetaStatisticsSummary = new SummaryStatistics();
				isCalibrating = true;
			}

			
			float currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
			float currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2.toArray(new String[BigSettings.instance().betaChannelList2.size()]));
			
			System.out.println(currentAlpha);
			System.out.println(currentBeta);
			
			if (!Double.isNaN(currentAlpha
					/ currentBeta)) {
				ratioAlphaBetaStatisticsSummary
						.addValue(currentAlpha
								/ currentBeta);
			}
			if (!Double.isNaN(currentBeta)) {

				alphaStatisticsSummary
						.addValue(currentAlpha);
			}
			if (!Double.isNaN(currentBeta)) {
				betaStatisticsSummary
						.addValue(currentBeta);
			}

			time = System.currentTimeMillis() - startTime;
//			applet.pushMatrix();
//			applet.text("the time: " + time, applet.width / 2, applet.height / 2);
//			applet.popMatrix();
			if (time > timeInMS) {

				calibrated = true;
				isCalibrating = false;

				// println("Alpha");
				// println(alphaStatistics.toString());
				//
				// println("Beta");
				// println(betaStatistics.toString());
				//
				//
				// println("Alpha/Beta");
				// println(alphaBetaRatioStatistics.toString());

			}

		}

	}

	public boolean calibrationFinisehd() {
		return calibrated;
	}

}