package Braint.settings;

import java.util.LinkedList;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import Braint.util.BraintUtil;

@Root(name = "root")
public class BigSettings {

	// TODO clear xml structure

	private static BigSettings instance;
	
	
	@Element(name = "colorMode")
	public int colorMode = 1;

	public BigSettings() {
	}

	public static BigSettings instance() {

		if (BigSettings.instance == null)
			BigSettings.instance = new BigSettings();

		return BigSettings.instance;

	}
	
	public void setSettingsFromXML(BigSettings s) {
		
		instance = s;
	}
	
	@Element(name = "agenCount")
	public int agentCount = 10000;
	
	@Element(name = "agentStrokeWidth")
	public float agentStrokeWidth = 1;

	@Element(name = "frameRate")
	public int THE_FRAMERATE = 128;

	@Element(name = "OSCPort")
	public int OSC_PORT = 12000;

	@Element(name = "CalibrationTime")
	public int CALIBRATION_TIME = 15;

	@Element(name = "openVibeAlphaSummary")
	public SummaryStatistics alphaStatistics = new SummaryStatistics();

	@Element(name = "openVibeBetaSummary")
	public SummaryStatistics betaStatistics = new SummaryStatistics();

	@Element(name = "openVibeAlphaBetaRatioSummary")
	public SummaryStatistics alphaBetaRatioStatistics = new SummaryStatistics();

	@Element(name = "backgroundColor")
	public int backgroundColor = 155;

	@Element(name = "useAgentDrawMethod")
	public boolean useAgents = true;
	
	@Element(name = "useAgentDrawMethod2")
	public boolean useAgents2 = false;

	@Element(name = "useAttractorsDrawMethod")
	public boolean useAttractors = false;

	// public boolean doCalibration = false;
	// public boolean guiMode = true;

	@Element(name = "useEmoEngineData")
	public boolean useEmoEngine = false;

	@Element(name = "useOpenVibeData")
	public boolean useOpenVibe = true;

	// use alpha / beta

	/**
	 * Whether alpha/beta should be used for determining the noise
	 * scale/strength. E.g If alpha is used for noise scale: IF reversed is true
	 * then high alpha will be mapped to low scale value, if false high alpha
	 * will be mapped to high scale.
	 */

	// alpha
	@Element(name = "useAlphaForNoiseScale")
	public boolean useAlphaForNoiseScale = true;

	@Element(name = "useAlphaForNoiseScaleReversed")
	public boolean useAlphaForNoiseScaleReversed = false;

	@Element(name = "useAlphaForNoiseStrength")
	public boolean useAlphaForNoiseStrength = true;

	@Element(name = "useAlphaForNoiseStrengthReversed")
	public boolean useAlphaForNoiseStrengthReversed = true;

	@Element(name = "useAlphaForColor")
	public boolean useAlphaForColor = false;

	@Element(name = "useAlphaForColorReversed")
	public boolean useAlphaForColorReversed = false;

	// beta
	@Element(name = "useBetaForNoiseScale")
	public boolean useBetaForNoiseScale = false;

	@Element(name = "useBetaForNoiseScaleReversed")
	public boolean useBetaForNoiseScaleReversed = false;

	@Element(name = "useBetaForNoiseStrength")
	public boolean useBetaForNoiseStrength = false;

	@Element(name = "useBetaForNoiseStrengthReversed")
	public boolean useBetaForNoiseStrengthReversed = false;

	@Element(name = "useBetaForColor")
	public boolean useBetaForColor = false;

	@Element(name = "useBetaForColorReversed")
	public boolean useBetaForColorReversed = false;

	// alpha/BetaRatio

	@Element(name = "useAlphaBetaRatioForNoiseScale")
	public boolean useAlphaBetaRatioForNoiseScale = false;

	@Element(name = "useAlphaBetaRatioForNoiseScaleReversed")
	public boolean useAlphaBetaRatioForNoiseScaleReversed = false;

	@Element(name = "useAlphaBetaRatioForNoiseStrength")
	public boolean useAlphaBetaRatioForNoiseStrength = false;

	@Element(name = "useAlphaBetaRatioForNoiseStrengthReversed")
	public boolean useAlphaBetaRatioForNoiseStrengthReversed = false;

	@Element(name = "useAlphaBetaRatioForColor")
	public boolean useAlphaBetaRatioForColor = false;

	@Element(name = "useAlphaBetaRatioForColorReversed")
	public boolean useAlphaBetaRatioForColorReversed = false;

	// max min for alpha/beta
	/**
	 * E.g. agentMaxNoiseScaleForPower is max value the alpha/beta power can be
	 * in relation to noise scale (hence any power higher than that will be
	 * cropped). The power max min values in combination with the current power
	 * value will then be scaled to the defined values for the agents noise
	 * scales and strengths etc.
	 */
	// power
	@Element(name = "agentMaxNoiseScaleForPower")
	public float agentMaxNoiseScaleForPower = 100;

	@Element(name = "agentMinNoiseScaleForPower")
	public float agentMinNoiseScaleForPower = 0;

	@Element(name = "agentMaxNoiseStrengthForPower")
	public float agentMaxNoiseStrengthForPower = 100;

	@Element(name = "agentMinNoiseStrengthForPower")
	public float agentMinNoiseStrengthForPower = 0;

	@Element(name = "agentMaxColorValueForPower")
	public float agentMaxColorValueForPower = 5;

	@Element(name = "agentMinColorValueForPower")
	public float agentMinColorValueForPower = 0;

	// // beta
	// @Element(name="agentMaxNoiseScaleForBeta")
	// public float agentMaxNoiseScaleForBeta = 100;
	//
	// @Element(name="agentMinNoiseScaleForBeta")
	// public float agentMinNoiseScaleForBeta = 0;
	//
	// @Element(name="agentMaxStrengthScaleForBeta")
	// public float agentMaxStrengthScaleForBeta = 100;
	//
	// @Element(name="agentMinStrengthScaleForBeta")
	// public float agentMinStrengthScaleForBeta = 0;
	//
	// @Element(name="agentMaxColorValueForBeta")
	// public float agentMaxColorValueForBeta = 5;
	//
	// @Element(name="agentMinColorValueForBeta")
	// public float agentMinColorValueForBeta = 0;
	//
	// alpha/beta/ratio

	// todo ratio

	// agent scales

	@Element(name = "agentMaxNoiseScale")
	public float agentMaxNoiseScale = 500;

	@Element(name = "agentMinNoiseScale")
	public float agentMinNoiseScale = 50;

	@Element(name = "agentMaxNoiseStrength")
	public float agentMaxNoiseStrength = 26;

	@Element(name = "agentMinNoiseStrength")
	public float agentMinNoiseStrength = 6;

	// color is usually between 1 and 0
	@Element(name = "agentMaxColorValue")
	public float agentMaxColorValue = 1;

	@Element(name = "agentMinColorValue")
	public float agentMinColorValue = 0;

	/**
	 * Channels which should be used to compute alpha / beta power, however to
	 * apply u need to calibrate again.
	 */
//	@ElementArray(name = "alphaChannelList")
//	public String[] alphaChannelList = new String[] { BraintUtil.OSC_OPENVIBE_O1, BraintUtil.OSC_OPENVIBE_O2,
//			BraintUtil.OSC_OPENVIBE_P7, BraintUtil.OSC_OPENVIBE_P8 };
//	
	@ElementList(name = "alphaChannelList2")
	public LinkedList<String> alphaChannelList2 =  new LinkedList<String>();
	
	@ElementList(name = "betaChannelList2")
	public LinkedList<String> betaChannelList2 =  new LinkedList<String>();
	
//	@ElementArray(name = "betaChannelList")
//	public String[] betaChannelList = new String[] { BraintUtil.OSC_OPENVIBE_AF3, BraintUtil.OSC_OPENVIBE_AF4,
//			BraintUtil.OSC_OPENVIBE_F3, BraintUtil.OSC_OPENVIBE_F4, BraintUtil.OSC_OPENVIBE_F7,
//			BraintUtil.OSC_OPENVIBE_F8, BraintUtil.OSC_OPENVIBE_FC5, BraintUtil.OSC_OPENVIBE_FC6 };
//	
	@ElementArray(name = "allChannelsList")
	public String[] allChannelsList = new String[] {BraintUtil.OSC_OPENVIBE_O1, BraintUtil.OSC_OPENVIBE_O2,
			BraintUtil.OSC_OPENVIBE_P7, BraintUtil.OSC_OPENVIBE_P8, BraintUtil.OSC_OPENVIBE_AF3, BraintUtil.OSC_OPENVIBE_AF4,
			BraintUtil.OSC_OPENVIBE_F3, BraintUtil.OSC_OPENVIBE_F4, BraintUtil.OSC_OPENVIBE_F7,
			BraintUtil.OSC_OPENVIBE_F8, BraintUtil.OSC_OPENVIBE_FC5, BraintUtil.OSC_OPENVIBE_FC6 };
	
	
	

}
