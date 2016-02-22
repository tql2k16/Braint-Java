package Braint.drawMethods.agents;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import Braint.emoEngine.EmoEngineOSCHandler;
import Braint.openVibe.OpenVibeCalibration;
import Braint.openVibe.OpenVibeOscEEGPowerDataHandler;
import Braint.settings.BigSettings;
import Braint.util.BraintUtil;

public class FancyBraintAgentParameterDeterminer {
	
	public static float[] ovGetNoiseScale(FancyBraintAgentDraw fancyBraintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {
		float currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2
				.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		float currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2
				.toArray(new String[BigSettings.instance().betaChannelList2.size()]));

		float currentAlpha4Electrodes[] = new float[12];
		float currentBeta4Electrodes[] = new float[12];
		
		
		for (int i = 0; i < BigSettings.instance().allChannelsList.length; i++) {
			currentAlpha4Electrodes[i] = data.alpha
					.getMeanForSpecificChannels(new String[] { BigSettings.instance().allChannelsList[i] });

		}

		for (int i = 0; i < BigSettings.instance().allChannelsList.length; i++) {
			currentBeta4Electrodes[i] = data.beta
					.getMeanForSpecificChannels(new String[] { BigSettings.instance().allChannelsList[i] });

		}
		
		
		// Scale
				float currentpower4ElectrodesScale[] = new float[12];
				if (BigSettings.instance().useAlphaForNoiseScale) {

					for (int i = 0; i < currentpower4ElectrodesScale.length; i++) {
						if (BigSettings.instance().useAlphaForNoiseScaleReversed) {

							currentpower4ElectrodesScale[i] = BigSettings.instance().agentMaxNoiseScaleForPower
									- currentAlpha4Electrodes[i];
						} else {
							currentpower4ElectrodesScale[i] = currentAlpha4Electrodes[i];
						}

					}

				} else if (BigSettings.instance().useBetaForNoiseScale) {
					for (int i = 0; i < currentpower4ElectrodesScale.length; i++) {
						if (BigSettings.instance().useBetaForNoiseScaleReversed) {
							currentpower4ElectrodesScale[i] = BigSettings.instance().agentMaxNoiseScaleForPower
									- currentAlpha4Electrodes[i];
						} else
							currentpower4ElectrodesScale[i] = currentAlpha4Electrodes[i];
					}
				}
				
				float res[] = new float[12];
				
				
				for (int i = 0; i < res.length; i++) {
					res[i] = (float) BraintUtil.getNormalizedValue(currentpower4ElectrodesScale[i],
							BigSettings.instance().agentMaxNoiseScaleForPower,
							BigSettings.instance().agentMinNoiseScaleForPower, BigSettings.instance().agentMaxNoiseScale,
							BigSettings.instance().agentMinNoiseScale);
				}



		return res;
	}

	public static float[] ovGetNoiseStrength(FancyBraintAgentDraw fancyBraintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {
		
		
		float currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2
				.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		float currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2
				.toArray(new String[BigSettings.instance().betaChannelList2.size()]));

		float currentAlpha4Electrodes[] = new float[12];
		float currentBeta4Electrodes[] = new float[12];

		for (int i = 0; i < BigSettings.instance().allChannelsList.length; i++) {
			currentAlpha4Electrodes[i] = data.alpha
					.getMeanForSpecificChannels(new String[] { BigSettings.instance().allChannelsList[i] });

		}

		for (int i = 0; i < BigSettings.instance().allChannelsList.length; i++) {
			currentBeta4Electrodes[i] = data.beta
					.getMeanForSpecificChannels(new String[] { BigSettings.instance().allChannelsList[i] });

		}

		float currentRatioAlphaBeta = currentAlpha / currentBeta;

		SummaryStatistics aSummary = calib.getAlphaStatisticsSummary();
		SummaryStatistics bSummary = calib.getBetaStatisticsSummary();
		SummaryStatistics abRSummary = calib.getRatioAlphaBetaStatisticsSummary();

		float strength = 0, scale = 0, color = 0;

		float strengthPowerValue = 0, scalePowerValue = 0f, colorPowerValue = 0;

		// Strength
		float currentpower4ElectrodesStrength[] = new float[12];
		if (BigSettings.instance().useAlphaForNoiseStrength) {

			for (int i = 0; i < currentpower4ElectrodesStrength.length; i++) {
				if (BigSettings.instance().useAlphaForNoiseStrengthReversed) {

					currentpower4ElectrodesStrength[i] = BigSettings.instance().agentMaxNoiseStrengthForPower
							- currentAlpha4Electrodes[i];
				} else {
					currentpower4ElectrodesStrength[i] = currentAlpha4Electrodes[i];
				}

			}

		} else if (BigSettings.instance().useBetaForNoiseStrength) {
			for (int i = 0; i < currentpower4ElectrodesStrength.length; i++) {
				if (BigSettings.instance().useBetaForNoiseStrengthReversed) {
					currentpower4ElectrodesStrength[i] = BigSettings.instance().agentMaxNoiseStrengthForPower
							- currentAlpha4Electrodes[i];
				} else
					currentpower4ElectrodesStrength[i] = currentAlpha4Electrodes[i];
			}
		}
		
		float res[] = new float[12];
		
		
		for (int i = 0; i < res.length; i++) {
			res[i] = (float) BraintUtil.getNormalizedValue(currentpower4ElectrodesStrength[i],
					BigSettings.instance().agentMaxNoiseStrengthForPower,
					BigSettings.instance().agentMinNoiseStrengthForPower, BigSettings.instance().agentMaxNoiseStrength,
					BigSettings.instance().agentMinNoiseStrength);
		}


		
		return res;
	}

	/**
	 * Determines Noise Values based on the BigSettings singelton instance using
	 * the power data from open vibe.
	 * 
	 * @param fancyBraintAgentDraw
	 * @param data
	 * @param calib
	 */
	public static void ovDetermineNoiseValues(FancyBraintAgentDraw fancyBraintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

		float currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2
				.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		float currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2
				.toArray(new String[BigSettings.instance().betaChannelList2.size()]));

		float currentAlpha4Electrodes[] = new float[12];
		float currentBeta4Electrodes[] = new float[12];

		for (int i = 0; i < BigSettings.instance().allChannelsList.length; i++) {
			currentAlpha4Electrodes[i] = data.alpha
					.getMeanForSpecificChannels(new String[] { BigSettings.instance().allChannelsList[i] });

		}

		for (int i = 0; i < BigSettings.instance().allChannelsList.length; i++) {
			currentBeta4Electrodes[i] = data.beta
					.getMeanForSpecificChannels(new String[] { BigSettings.instance().allChannelsList[i] });

		}

		float currentRatioAlphaBeta = currentAlpha / currentBeta;

		SummaryStatistics aSummary = calib.getAlphaStatisticsSummary();
		SummaryStatistics bSummary = calib.getBetaStatisticsSummary();
		SummaryStatistics abRSummary = calib.getRatioAlphaBetaStatisticsSummary();

		float strength = 0, scale = 0, color = 0;

		float strengthPowerValue = 0, scalePowerValue = 0f, colorPowerValue = 0;

		// Strength
		float currentpower4ElectrodesStrength[] = new float[12];
		if (BigSettings.instance().useAlphaForNoiseStrength) {

			for (int i = 0; i < currentpower4ElectrodesStrength.length; i++) {
				if (BigSettings.instance().useAlphaForNoiseStrengthReversed) {

					currentpower4ElectrodesStrength[i] = BigSettings.instance().agentMaxNoiseStrengthForPower
							- currentAlpha4Electrodes[i];
				} else {
					currentpower4ElectrodesStrength[i] = currentAlpha4Electrodes[i];
				}

			}

		} else if (BigSettings.instance().useBetaForNoiseStrength) {
			for (int i = 0; i < currentpower4ElectrodesStrength.length; i++) {
				if (BigSettings.instance().useBetaForNoiseStrengthReversed) {
					currentpower4ElectrodesStrength[i] = BigSettings.instance().agentMaxNoiseStrengthForPower
							- currentAlpha4Electrodes[i];
				} else
					currentpower4ElectrodesStrength[i] = currentAlpha4Electrodes[i];
			}
		}

		strength = (float) BraintUtil.getNormalizedValue(strengthPowerValue,
				BigSettings.instance().agentMaxNoiseStrengthForPower,
				BigSettings.instance().agentMinNoiseStrengthForPower, BigSettings.instance().agentMaxNoiseStrength,
				BigSettings.instance().agentMinNoiseStrength);

		// Scale

		currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2
				.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2
				.toArray(new String[BigSettings.instance().betaChannelList2.size()]));

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
				BigSettings.instance().agentMaxNoiseScaleForPower, BigSettings.instance().agentMinNoiseScaleForPower,
				BigSettings.instance().agentMaxNoiseScale, BigSettings.instance().agentMinNoiseScale);

		// Color
		currentAlpha = data.alpha.getMeanForSpecificChannels(BigSettings.instance().alphaChannelList2
				.toArray(new String[BigSettings.instance().alphaChannelList2.size()]));
		currentBeta = data.beta.getMeanForSpecificChannels(BigSettings.instance().betaChannelList2
				.toArray(new String[BigSettings.instance().betaChannelList2.size()]));
		if (BigSettings.instance().useAlphaForColor) {
			if (BigSettings.instance().useAlphaForColorReversed) {
				currentAlpha = BigSettings.instance().agentMaxColorValueForPower - currentAlpha;
			}
			colorPowerValue = currentAlpha;

		} else if (BigSettings.instance().useBetaForColor) {

			if (BigSettings.instance().useBetaForColorReversed) {
				currentBeta = BigSettings.instance().agentMaxColorValueForPower - currentBeta;
			}

			colorPowerValue = currentBeta;

		}

		color = (float) BraintUtil.getNormalizedValue(colorPowerValue,
				BigSettings.instance().agentMaxColorValueForPower, BigSettings.instance().agentMinColorValueForPower,
				BigSettings.instance().agentMaxColorValue, BigSettings.instance().agentMinColorValue);

		// set in the draw agents

		fancyBraintAgentDraw.noiseScale = scale;
		fancyBraintAgentDraw.noiseStrength = strength;
		fancyBraintAgentDraw.valueToRGB = color;
		fancyBraintAgentDraw.rgb = BraintUtil.decideRGBValue(fancyBraintAgentDraw.valueToRGB,
				BigSettings.instance().colorMode);

	}

	public static void ovDetermineWithAlphaOnly(FancyBraintAgentDraw fancyBraintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

	}

	public static void ovDetermineWithBetaOnly(FancyBraintAgentDraw fancyBraintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

	}

	public static void ovDetermineWithAlphaBeta(FancyBraintAgentDraw fancyBraintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

	}

	public static void determineBasedOnOpenVibeSignals(FancyBraintAgentDraw fancyBraintAgentDraw,
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
		fancyBraintAgentDraw.valueToRGB = Math.abs((float) BraintUtil.getNormalizedValue(currentRatioAlphaBeta,
				abRSummary.getMean() + 3 * abRSummary.getVariance(),
				abRSummary.getMean() - abRSummary.getStandardDeviation(), 1, 0));
		fancyBraintAgentDraw.rgb = BraintUtil.decideRGBValue(fancyBraintAgentDraw.valueToRGB, 1);
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
		fancyBraintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				(aSummary.getMean() + 3 * aSummary.getStandardDeviation()) - currentAlpha,
				aSummary.getMean() + 3 * aSummary.getStandardDeviation(),
				aSummary.getMean() - aSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseStrength,
				BigSettings.instance().agentMinNoiseStrength);

		fancyBraintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(currentBeta,
				bSummary.getMean() + 3 * bSummary.getStandardDeviation(),
				bSummary.getMean() - bSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);

		//
		// System.out.println("continous:(strength,scale) = (" +
		// braintAgentDraw.noiseStrength + "," + braintAgentDraw.noiseScale +
		// ")");

	}

	public static void determineBasedOnOpenVibeSignalsOnlyAlpha(FancyBraintAgentDraw fancyBraintAgentDraw,
			OpenVibeOscEEGPowerDataHandler data, OpenVibeCalibration calib) {

		// BigSettings.instance().agentMaxNoiseStrength = 25;
		// BigSettings.instance().agentMinNoiseStrength = 6;
		// BigSettings.instance().agentMaxNoiseScale = 500;
		// BigSettings.instance().agentMinNoiseScale = 50;

		float currentAlpha = data.alpha.getMeanAllChannels();
		float currentBeta = data.beta.getMeanAllChannels();
		float currentRatioAlphaBeta = currentAlpha / currentBeta;

		SummaryStatistics aSummary = calib.getAlphaStatisticsSummary();
		SummaryStatistics bSummary = calib.getBetaStatisticsSummary();
		SummaryStatistics abRSummary = calib.getRatioAlphaBetaStatisticsSummary();

		determineWithAlphaOnly(fancyBraintAgentDraw, currentAlpha, aSummary);

	}

	private static void determineWithAlphaOnly(FancyBraintAgentDraw fancyBraintAgentDraw, float currentAlpha,
			SummaryStatistics aSummary) {
		/**
		 * with Variance
		 */
		fancyBraintAgentDraw.valueToRGB = (float) BraintUtil.getNormalizedValue(currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(),
				aSummary.getMean() - 1 * aSummary.getStandardDeviation(), 1, 0);
		fancyBraintAgentDraw.rgb = BraintUtil.decideRGBValue(fancyBraintAgentDraw.valueToRGB, 2);
		fancyBraintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				(aSummary.getMean() + 3 * aSummary.getVariance()) - currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(),
				aSummary.getMean() - 1 * aSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseStrength,
				BigSettings.instance().agentMinNoiseStrength);

		fancyBraintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(),
				aSummary.getMean() - 1 * aSummary.getStandardDeviation(), BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);
	}

	public static void useTestValues(FancyBraintAgentDraw fancyBraintAgentDraw) {
		fancyBraintAgentDraw.noiseStrength = 12;
		fancyBraintAgentDraw.noiseScale = 250;
	}

	public static void determineBasedOnOpenVibeSignalsITHINKTHISLOOKSQUITEOK(FancyBraintAgentDraw fancyBraintAgentDraw,
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

		determineWithAlphaAndBetaVariance(fancyBraintAgentDraw, currentAlpha, currentBeta, currentRatioAlphaBeta,
				aSummary, bSummary, abRSummary);

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
		System.out.println("continous:(strength,scale) = (" + fancyBraintAgentDraw.noiseStrength + ","
				+ fancyBraintAgentDraw.noiseScale + ")");

	}

	private static void determineWithAlphaAndBetaVariance(FancyBraintAgentDraw fancyBraintAgentDraw, float currentAlpha,
			float currentBeta, float currentRatioAlphaBeta, SummaryStatistics aSummary, SummaryStatistics bSummary,
			SummaryStatistics abRSummary) {
		/**
		 * with Variance
		 */
		fancyBraintAgentDraw.valueToRGB = Math.abs((float) BraintUtil.getNormalizedValue(currentRatioAlphaBeta,
				abRSummary.getMean() + 3 * abRSummary.getVariance(),
				abRSummary.getMean() - abRSummary.getStandardDeviation(), 1, 0));
		fancyBraintAgentDraw.rgb = BraintUtil.decideRGBValue(fancyBraintAgentDraw.valueToRGB, 1);
		fancyBraintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
				(aSummary.getMean() + 3 * aSummary.getVariance()) - currentAlpha,
				aSummary.getMean() + 3 * aSummary.getVariance(), aSummary.getMean() - aSummary.getStandardDeviation(),
				BigSettings.instance().agentMaxNoiseStrength, BigSettings.instance().agentMinNoiseStrength);

		fancyBraintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(currentBeta,
				bSummary.getMean() + 3 * bSummary.getVariance(), bSummary.getMean() - bSummary.getStandardDeviation(),
				BigSettings.instance().agentMaxNoiseScale, BigSettings.instance().agentMinNoiseScale);
	}

	public static void determineBasedOnEmoEngine(FancyBraintAgentDraw fancyBraintAgentDraw,
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

		fancyBraintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(scale,
				BigSettings.instance().allChannelsList.length, 0, BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);

		// strength
		float strength = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);

		fancyBraintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
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

		fancyBraintAgentDraw.valueToRGB = Math.abs(
				(float) BraintUtil.getNormalizedValue(sumAll, BigSettings.instance().allChannelsList.length, 0, 1, 0));
		fancyBraintAgentDraw.rgb = BraintUtil.decideRGBValue(fancyBraintAgentDraw.valueToRGB, 1);

	}

	public static void determineBasedOnEmoEngine2(FancyBraintAgentDraw fancyBraintAgentDraw,
			EmoEngineOSCHandler emoEngineHandler) {

		float scale = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);
		// (float) (msg.get(3).doubleValue() + msg.get(16).doubleValue() +
		// msg.get(5).doubleValue()
		// + msg.get(14).doubleValue() + msg.get(4).doubleValue() +
		// msg.get(15).doubleValue()
		// + msg.get(13).doubleValue() + msg.get(6).doubleValue()); // AF3

		// scale = scale / 8f;

		// setOscNoiseScale(scale);

		fancyBraintAgentDraw.noiseScale = (float) BraintUtil.getNormalizedValue(scale,
				BigSettings.instance().allChannelsList.length, 0, BigSettings.instance().agentMaxNoiseScale,
				BigSettings.instance().agentMinNoiseScale);

		// strength
		float strength = emoEngineHandler.getRaw().getMeanForSpecificChannels(BigSettings.instance().allChannelsList);

		fancyBraintAgentDraw.noiseStrength = (float) BraintUtil.getNormalizedValue(
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

		fancyBraintAgentDraw.valueToRGB = Math.abs(
				(float) BraintUtil.getNormalizedValue(sumAll, BigSettings.instance().allChannelsList.length, 0, 1, 0));
		fancyBraintAgentDraw.rgb = BraintUtil.decideRGBValue(fancyBraintAgentDraw.valueToRGB, 2);

	}



}
