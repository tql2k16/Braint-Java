package Braint.drawMethods.agents;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import Braint.emoEngine.EmoEngineOSCHandler;
import Braint.openVibe.OpenVibeCalibration;
import Braint.openVibe.OpenVibeOscEEGPowerDataHandler;
import Braint.settings.BigSettings;
import Braint.util.BraintUtil;
import Braint.util.ThresholdBasedIntensity;

public class BraintAgentParameterDeterminer {

	private static ThresholdBasedIntensity alphaThres, betaThres, alphaBetaRatioThres;

	
	/**
	 * Determines Noise Values based on the BigSettings singelton instance using the power data from open vibe.
	 * @param braintAgentDraw
	 * @param data
	 * @param calib
	 */
	public static void ovDetermineNoiseValues(BraintAgentDraw braintAgentDraw, OpenVibeOscEEGPowerDataHandler data,
			OpenVibeCalibration calib) {

		float currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		float currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2.toArray(new String[BigSettings.instance().betaChannelList2.size()]));


				
				
		float currentRatioAlphaBeta = currentAlpha / currentBeta;

		SummaryStatistics aSummary = calib.getAlphaStatisticsSummary();
		SummaryStatistics bSummary = calib.getBetaStatisticsSummary();
		SummaryStatistics abRSummary = calib.getRatioAlphaBetaStatisticsSummary();

		float strength = 0, scale = 0, color = 0;
		
		float strengthPowerValue = 0, scalePowerValue = 0f, colorPowerValue = 0;

		// Strength

		if (BigSettings.instance().useAlphaForNoiseStrength) {

			if (BigSettings.instance().useAlphaForNoiseStrengthReversed) {
				currentAlpha = BigSettings.instance().agentMaxNoiseStrengthForPower - currentAlpha;
			}

			strengthPowerValue = currentAlpha;

		} else if (BigSettings.instance().useBetaForNoiseStrength) {
			if (BigSettings.instance().useBetaForNoiseStrengthReversed) {
				currentBeta = BigSettings.instance().agentMaxNoiseStrengthForPower - currentBeta;
			}

			strengthPowerValue = currentBeta;
		}
		
		strength = (float) BraintUtil.getNormalizedValue(strengthPowerValue,
				BigSettings.instance().agentMaxNoiseStrengthForPower,
				BigSettings.instance().agentMinNoiseStrengthForPower, BigSettings.instance().agentMaxNoiseStrength,
				BigSettings.instance().agentMinNoiseStrength);
		
		

		// Scale
		
		currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2.toArray(new String[BigSettings.instance().betaChannelList2.size()]));

		if (BigSettings.instance().useAlphaForNoiseScale) {
			if (BigSettings.instance().useAlphaForNoiseScaleReversed) {
				currentAlpha = BigSettings.instance().agentMaxNoiseScaleForPower - currentAlpha;
			}
			
			scalePowerValue = currentAlpha;

		} else if (BigSettings.instance().useBetaForNoiseScale) {

			if (BigSettings.instance().useBetaForNoiseScaleReversed) {
				currentBeta = BigSettings.instance().agentMaxNoiseScaleForPower - currentBeta;
			}
			scalePowerValue = currentBeta;
		}
		
		scale = (float) BraintUtil.getNormalizedValue(scalePowerValue,
				BigSettings.instance().agentMaxNoiseScaleForPower,
				BigSettings.instance().agentMinNoiseScaleForPower, BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale); 

		// Color
		currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2.toArray(new String[BigSettings.instance().betaChannelList2.size()]));
		if (BigSettings.instance().useAlphaForColor) {
			if (BigSettings.instance().useAlphaForColorReversed) {
				currentAlpha = BigSettings.instance().agentMaxColorValueForPower- currentAlpha;
			}
			colorPowerValue = currentAlpha;
			

		} else if (BigSettings.instance().useBetaForColor) {

			if (BigSettings.instance().useBetaForColorReversed) {
				currentBeta = BigSettings.instance().agentMaxColorValueForPower- currentBeta;
			}
			
			colorPowerValue = currentBeta;

		}
		
		color = (float) BraintUtil.getNormalizedValue(colorPowerValue,
				BigSettings.instance().agentMaxColorValueForPower,
				BigSettings.instance().agentMinColorValueForPower, BigSettings.instance().agentMaxColorValue,
				BigSettings.instance().agentMinColorValue); 
		
		
		// set in the draw agents
		
		braintAgentDraw.noiseScale = scale;
		braintAgentDraw.noiseStrength = strength;
		braintAgentDraw.valueToRGB = color;
		braintAgentDraw.rgb = BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, BigSettings.instance().colorMode);

	}

	public static void ovDetermineWithAlphaOnly(BraintAgentDraw braintAgentDraw, OpenVibeOscEEGPowerDataHandler data,
			OpenVibeCalibration calib) {

	}

	public static void ovDetermineWithBetaOnly(BraintAgentDraw braintAgentDraw, OpenVibeOscEEGPowerDataHandler data,
			OpenVibeCalibration calib) {

	}

	public static void ovDetermineWithAlphaBeta(BraintAgentDraw braintAgentDraw, OpenVibeOscEEGPowerDataHandler data,
			OpenVibeCalibration calib) {

	}

	public static void determineBasedOnOpenVibeSignals(BraintAgentDraw braintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

		float currentAlpha = data.alpha.getMeanAllChannels();
		float currentBeta = data.beta.getMeanAllChannels();
		float currentRatioAlphaBeta = currentAlpha / currentBeta;

		SummaryStatistics aSummary = calib.getAlphaStatisticsSummary();
		SummaryStatistics bSummary = calib.getBetaStatisticsSummary();
		SummaryStatistics abRSummary = calib.getRatioAlphaBetaStatisticsSummary();

		BigSettings.instance().agentMaxNoiseStrength = 25;
		BigSettings.instance().agentMinNoiseStrength = 6;
		BigSettings.instance().agentMaxNoiseScale = 250;
		BigSettings.instance().agentMinNoiseScale = 50;

		/**
		 * with Variance
		 */
		braintAgentDraw.valueToRGB = Math.abs((float) BraintUtil.getNormalizedValue(currentRatioAlphaBeta,
				abRSummary.getMean() + 3 * abRSummary.getVariance(),
				abRSummary.getMean() - abRSummary.getStandardDeviation(), 1, 0));
		braintAgentDraw.rgb = BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, 1);
		// braintAgentDraw.noiseStrength = (float)
		// BraintUtil.getNormalizedValue(
		// (aSummary.getMean() + 3 * aSummary.getVariance()) - currentAlpha,
		// aSummary.getMean() + 3 * aSummary.getVariance(),
		// aSummary.getMean() - aSummary.getStandardDeviation(),
		// BigSettings.instance().agentMaxNoiseStrength,
		// BigSettings.instance().agentMinNoiseStrength);
		//
		// braintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(
		// currentBeta,
		// bSummary.getMean() + 3 * bSummary.getVariance(),
		// bSummary.getMean() - bSummary.getStandardDeviation(),
		// BigSettings.instance().agentMaxNoiseScale,
		// BigSettings.instance().agentMinNoiseScale);

		/**
		 * with Deviation
		 */
		// braintAgentDraw.valueToRGB = Math.abs((float)
		// BraintUtil.getNormalizedValue(currentRatioAlphaBeta,
		// abRSummary.getMean() + 3 * abRSummary.getStandardDeviation(),
		// abRSummary.getMean() - abRSummary.getStandardDeviation(), 1, 0));
		// braintAgentDraw.rgb =
		// BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, 1);
		braintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				(aSummary.getMean() + 3 * aSummary.getStandardDeviation()) - currentAlpha,
				aSummary.getMean() + 3 * aSummary.getStandardDeviation(),
				aSummary.getMean() - aSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseStrength,
				BigSettings.instance().agentMinNoiseStrength);

		braintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(currentBeta,
				bSummary.getMean() + 3 * bSummary.getStandardDeviation(),
				bSummary.getMean() - bSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);

		//
		// System.out.println("continous:(strength,scale) = (" +
		// braintAgentDraw.noiseStrength + "," + braintAgentDraw.noiseScale +
		// ")");

	}

	public static void determineBasedOnOpenVibeSignalsOnlyAlpha(BraintAgentDraw braintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

//		BigSettings.instance().agentMaxNoiseStrength = 25;
//		BigSettings.instance().agentMinNoiseStrength = 6;
//		BigSettings.instance().agentMaxNoiseScale = 500;
//		BigSettings.instance().agentMinNoiseScale = 50;

		float currentAlpha = data.alpha.getMeanAllChannels();
		float currentBeta = data.beta.getMeanAllChannels();
		float currentRatioAlphaBeta = currentAlpha / currentBeta;

		SummaryStatistics aSummary = calib.getAlphaStatisticsSummary();
		SummaryStatistics bSummary = calib.getBetaStatisticsSummary();
		SummaryStatistics abRSummary = calib.getRatioAlphaBetaStatisticsSummary();

		determineWithAlphaOnly(braintAgentDraw, currentAlpha, aSummary);

	}

	private static void determineWithAlphaOnly(BraintAgentDraw braintAgentDraw, float currentAlpha,
			SummaryStatistics aSummary) {
		/**
		 * with Variance
		 */
		braintAgentDraw.valueToRGB = (float) BraintUtil.getNormalizedValue(currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(),
				aSummary.getMean() - 1 * aSummary.getStandardDeviation(), 1, 0);
		braintAgentDraw.rgb = BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, 2);
		braintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				(aSummary.getMean() + 3 * aSummary.getVariance()) - currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(),
				aSummary.getMean() - 1 * aSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseStrength,
				BigSettings.instance().agentMinNoiseStrength);

		braintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(),
				aSummary.getMean() - 1 * aSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);
	}
	
	public static void useTestValues(BraintAgentDraw braintAgentDraw) {
		braintAgentDraw.noiseStrength = 12;
		braintAgentDraw.noiseScale = 250;
	}

	public static void determineBasedOnOpenVibeSignalsITHINKTHISLOOKSQUITEOK(BraintAgentDraw braintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

		BigSettings.instance().agentMaxNoiseStrength = 25;
		BigSettings.instance().agentMinNoiseStrength = 6;
		BigSettings.instance().agentMaxNoiseScale = 500;
		BigSettings.instance().agentMinNoiseScale = 50;

		float currentAlpha = data.alpha.getMeanAllChannels();
		float currentBeta = data.beta.getMeanAllChannels();
		float currentRatioAlphaBeta = currentAlpha / currentBeta;

		SummaryStatistics aSummary = calib.getAlphaStatisticsSummary();
		SummaryStatistics bSummary = calib.getBetaStatisticsSummary();
		SummaryStatistics abRSummary = calib.getRatioAlphaBetaStatisticsSummary();

		determineWithAlphaAndBetaVariance(braintAgentDraw, currentAlpha, currentBeta, currentRatioAlphaBeta, aSummary,
				bSummary, abRSummary);

		/**
		 * with Deviation
		 */
		// braintAgentDraw.valueToRGB = Math.abs((float)
		// BraintUtil.getNormalizedValue(currentRatioAlphaBeta,
		// abRSummary.getMean() + 3 * abRSummary.getStandardDeviation(),
		// abRSummary.getMean() - abRSummary.getStandardDeviation(), 1, 0));
		// braintAgentDraw.rgb =
		// BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, 1);
		// braintAgentDraw.noiseStrength = (float)
		// BraintUtil.getNormalizedValue(
		// (aSummary.getMean() + 3 * aSummary.getStandardDeviation()) -
		// currentAlpha,
		// aSummary.getMean() + 3 * aSummary.getStandardDeviation(),
		// aSummary.getMean() - aSummary.getStandardDeviation(),
		// BigSettings.instance().agentMaxNoiseStrength,
		// BigSettings.instance().agentMinNoiseStrength);
		//
		// braintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(
		// currentBeta,
		// bSummary.getMean() + 3 * bSummary.getStandardDeviation(),
		// bSummary.getMean() - bSummary.getStandardDeviation(),
		// BigSettings.instance().agentMaxNoiseScale,
		// BigSettings.instance().agentMinNoiseScale);
		//
		//
		System.out.println("continous:(strength,scale) = (" + braintAgentDraw.noiseStrength + ","
				+ braintAgentDraw.noiseScale + ")");

	}

	private static void determineWithAlphaAndBetaVariance(BraintAgentDraw braintAgentDraw, float currentAlpha,
			float currentBeta, float currentRatioAlphaBeta, SummaryStatistics aSummary, SummaryStatistics bSummary,
			SummaryStatistics abRSummary) {
		/**
		 * with Variance
		 */
		braintAgentDraw.valueToRGB = Math.abs((float) BraintUtil.getNormalizedValue(currentRatioAlphaBeta,
				abRSummary.getMean() + 3 * abRSummary.getVariance(),
				abRSummary.getMean() - abRSummary.getStandardDeviation(), 1, 0));
		braintAgentDraw.rgb = BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, 1);
		braintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				(aSummary.getMean() + 3 * aSummary.getVariance()) - currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(), aSummary.getMean() - aSummary.getStandardDeviation(),
				BigSettings.instance().agentMaxNoiseStrength, BigSettings.instance().agentMinNoiseStrength);

		braintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(currentBeta,
				bSummary.getMean() + 3 * bSummary.getVariance(), bSummary.getMean() - bSummary.getStandardDeviation(),
				BigSettings.instance().agentMaxNoiseScale, BigSettings.instance().agentMinNoiseScale);
	}

	public static void determineBasedOnEmoEngine(BraintAgentDraw braintAgentDraw,
			EmoEngineOSCHandler emoEngineHandler) {

		BigSettings.instance().agentMaxNoiseStrength = 25;
		BigSettings.instance().agentMinNoiseStrength = 6;
		BigSettings.instance().agentMaxNoiseScale = 500;
		BigSettings.instance().agentMinNoiseScale = 50;

		float scale = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);
		// (float) (msg.get(3).doubleValue() + msg.get(16).doubleValue() +
		// msg.get(5).doubleValue()
		// + msg.get(14).doubleValue() + msg.get(4).doubleValue() +
		// msg.get(15).doubleValue()
		// + msg.get(13).doubleValue() + msg.get(6).doubleValue()); // AF3

		// scale = scale / 8f;

		// setOscNoiseScale(scale);

		braintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(scale,
				BigSettings.instance().allChannelsList.length, 0, BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);

		// strength
		float strength = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);

		braintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				BigSettings.instance().allChannelsList.length - scale, BigSettings.instance().allChannelsList.length, 0,
				BigSettings.instance().agentMaxNoiseStrength, BigSettings.instance().agentMinNoiseStrength);

		// (float) (msg.get(8).doubleValue() + msg.get(11).doubleValue() +
		// msg.get(10).doubleValue()
		// + msg.get(9).doubleValue()); // O2
		//
		// strength /= 4f;

		// setOscNoiseStrength(strength);
		// rgb

		float sumAll = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);

		braintAgentDraw.valueToRGB = Math.abs(
				(float) BraintUtil.getNormalizedValue(sumAll, BigSettings.instance().allChannelsList.length, 0, 1, 0));
		braintAgentDraw.rgb = BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, 1);

	}

	public static void determineBasedOnEmoEngine2(BraintAgentDraw braintAgentDraw,
			EmoEngineOSCHandler emoEngineHandler) {

		float scale = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);
		// (float) (msg.get(3).doubleValue() + msg.get(16).doubleValue() +
		// msg.get(5).doubleValue()
		// + msg.get(14).doubleValue() + msg.get(4).doubleValue() +
		// msg.get(15).doubleValue()
		// + msg.get(13).doubleValue() + msg.get(6).doubleValue()); // AF3

		// scale = scale / 8f;

		// setOscNoiseScale(scale);

		braintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(scale,
				BigSettings.instance().allChannelsList.length, 0, BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);

		// strength
		float strength = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);

		braintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				BigSettings.instance().allChannelsList.length - scale, BigSettings.instance().allChannelsList.length, 0,
				BigSettings.instance().agentMaxNoiseStrength, BigSettings.instance().agentMinNoiseStrength);

		// (float) (msg.get(8).doubleValue() + msg.get(11).doubleValue() +
		// msg.get(10).doubleValue()
		// + msg.get(9).doubleValue()); // O2
		//
		// strength /= 4f;

		// setOscNoiseStrength(strength);
		// rgb

		float sumAll = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);

		braintAgentDraw.valueToRGB = Math.abs(
				(float) BraintUtil.getNormalizedValue(sumAll, BigSettings.instance().allChannelsList.length, 0, 1, 0));
		braintAgentDraw.rgb = BraintUtil.decideRGBValue(braintAgentDraw.valueToRGB, 2);

	}

}
