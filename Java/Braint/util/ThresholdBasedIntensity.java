package Braint.util;

public class ThresholdBasedIntensity {

	private float lowerThres;
	private float midThres;
	private float upperThres;
	private int lastIntensity;

	public ThresholdBasedIntensity(float first, float second, float third) {
		lowerThres = first;
		midThres = second;
		upperThres = third;
	}

	// percent please
	public void setThresholds(float first, float second, float third) {
		lowerThres = first;
		midThres = second;
		upperThres = third;
	}

	/**
	 * 
	 * @param power
	 * @return A value ranging from 0 to 3 where 0 indicates not enough power
	 *         and 1-3 indicate the intensity of the power
	 */
	public int determineIntensity(float power) {
		int intensity = 0;

		if (power < lowerThres)
			intensity = 0;
		else if (power >= lowerThres && power < midThres)
			intensity = 1;
		else if (power >= midThres && power < upperThres)
			intensity = 2;
		else if (power >= upperThres)
			intensity = 3;
		else
			intensity = 0;

		lastIntensity = intensity;

		return intensity;
	}

}
