package Braint.util;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class BraintUtil {

	static public int OPENVIBE_CUT_OFF_NOISE_VALUE = 150;

	// OSC Emotiv EmoEngine
	static public String OSC_EMO_ENGINE = "/emoengine/";

	// OSC Message Adress OpenVibe
	static public String OSC_OPENVIBE_ALPHA = "/openvibe/alpha/";
	static public String OSC_OPENVIBE_BETA = "/openvibe/beta/";

	// OSC Channel Names OpenVibe

	static public final String OSC_OPENVIBE_AF3 = "af3";
	static public final String OSC_OPENVIBE_AF4 = "af4";
	static public final String OSC_OPENVIBE_F3 = "f3";
	static public final String OSC_OPENVIBE_F4 = "f4";
	static public final String OSC_OPENVIBE_F7 = "f7";
	static public final String OSC_OPENVIBE_F8 = "f8";
	static public final String OSC_OPENVIBE_FC5 = "fc5";
	static public final String OSC_OPENVIBE_FC6 = "fc6";
	static public final String OSC_OPENVIBE_P7 = "p7";
	static public final String OSC_OPENVIBE_P8 = "p8";
	static public final String OSC_OPENVIBE_O1 = "o1";
	static public final String OSC_OPENVIBE_O2 = "o2";

	// NOT USED TODO
	static public String OSC_OPENVIBE_T7 = "af3";
	static public String OSC_OPENVIBE_T8 = "af3";

	static public double getNormalizedValue(double value, double max, double min, double maxScaled, double minScaled) {

		double res = minScaled + (value - min) * (maxScaled - minScaled) / (max - min);

		res = checkNormalizationResult(maxScaled, minScaled, res);

		return res;
	}

	private static double checkNormalizationResult(double maxScaled, double minScaled, double res) {
		if (res > maxScaled)
			res = maxScaled;
		else if (res < minScaled)
			res = minScaled;
		return res;
	}

	
	// TODO KP UM EHRLICH ZU SEIN WIE DAS MIT DEM LOG GEHEN SOLL WENN MAN SCALEN WILL
	static public double getNormalizedValueLog(double value, double max, double min, double maxScaled,
			double minScaled) {

		double res = minScaled + (Math.log10(value) - Math.log10(min))
				* (maxScaled - minScaled) / (Math.log10(max) - Math.log10(min));
		
		
		res = checkNormalizationResult(maxScaled, minScaled, res);

		return res;
	}

	static public double getNormalizedValueWuzzel(double value, double max, double min, double maxScaled,
			double minScaled) {
		
		double res = Math.sqrt(minScaled) + (Math.sqrt(value) - Math.sqrt(min))
				* (Math.sqrt(maxScaled) - Math.sqrt(minScaled)) / (Math.sqrt(max) - Math.sqrt(min));
		
		res = checkNormalizationResult(Math.sqrt(maxScaled), Math.sqrt(minScaled), res);

		return res;

	}

	/**
	 * Chooses the colour in which the next strokes should be drawn depending on
	 * given value and colour mode,
	 * 
	 * @param value
	 *            value for which the colour should be picked
	 * @param mode
	 *            colour mode: 2 = yellow/red , 1 = green
	 * @return hex value for stroke function todo: change mode 2
	 */
	static public int decideRGBValue(float value, int mode) {
		
		System.out.println(mode);

		if (mode == 1) { //green
			System.out.println("decide color rgb value" + value);
			if (value < 0.1666f)
				return 0xffccece6;
			if (value < 0.3333f)
				return 0xff99d8c9;
			if (value < 0.5f)
				return 0xff66c2a4;
			if (value < 0.6666f)
				return 0xff41ae76;
			if (value < 0.8333f)
				return 0xff238b45;
			if (value < 1f)
				return 0xff006d2c;
			return 0xff00441b;

		} else if (mode == 2) { //red
			if (value < 0.1666f)
				return 0xfffdd49e;
			if (value < 0.3333f)
				return 0xfffdbb84;
			if (value < 0.5f)
				return 0xfffc8d59;
			if (value < 0.6666f)
				return 0xffef6548;
			if (value < 0.8333f)
				return 0xffd7301f;
			if (value < 1f)
				return 0xffb30000;
			return 0xff7f0000;

		} else if (mode == 3) { //violett
			if (value < 0.1666f)
				return 0xffbfd3e6;
			if (value < 0.3333f)
				return 0xff9ebcda;
			if (value < 0.5f)
				return 0xff8c96c6;
			if (value < 0.6666f)
				return 0xff8c6bb1;
			if (value < 0.8333f)
				return 0xff88419d;
			if (value < 1f)
				return 0xff6e016b;
			return 0xffedf8fb;

		} else if (mode == 4) { //more pink than violett
			if (value < 0.1666f)
				return 0xffd4b9da;
			if (value < 0.3333f)
				return 0xffc994c7;
			if (value < 0.5f)
				return 0xffdf65b0;
			if (value < 0.6666f)
				return 0xffe7298a;
			if (value < 0.8333f)
				return 0xffce1256;
			if (value < 1f)
				return 0xff91003f;
			return 0xfff1eef6;

		} else if (mode == 5) { //blue
			if (value < 0.1666f)
				return 0xffc6dbef;
			if (value < 0.3333f)
				return 0xff9ecae1;
			if (value < 0.5f)
				return 0xff6baed6;
			if (value < 0.6666f)
				return 0xff4292c6;
			if (value < 0.8333f)
				return 0xff2171b5;
			if (value < 1f)
				return 0xff084594;
			return 0xffeff3ff;

		}

		return 0;
	
	}
	
	
	public static String getDecentStatisticsString(SummaryStatistics s){
		StringBuilder sb = new StringBuilder();
		
		sb.append("n:\t" + s.getN());
		sb.append("\n");
		
		sb.append("max:\t" + s.getMax());
		sb.append("\n");
		
		sb.append("min:\t" + s.getMin());
		sb.append("\n");
		
		sb.append("mean:\t" + s.getMean());
		sb.append("\n");
		
		sb.append("variance:\t" + s.getVariance());
		sb.append("\n");
		
		sb.append("std. deviation:\t" + s.getStandardDeviation());
		sb.append("\n");		
		
		return sb.toString();
		
	}

}
