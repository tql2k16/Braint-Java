package Braint.openVibe;

import java.util.HashMap;

public class RawEEGDataSampleContainer {

	private HashMap<String, Float> channels;

	public RawEEGDataSampleContainer(String channelNames[]) {
		channels = new HashMap<String, Float>();

		for (String string : channelNames) {
			addChannel(string);
		}
	}

	public RawEEGDataSampleContainer() {
		channels = new HashMap<String, Float>();

	}

	public void setChannels(HashMap<String, Float> newChannels) {
		channels = newChannels;
	}

	public void addChannel(String name) {

		if (!channels.containsKey(name))
			addChannelValue(name, 0.0f);

	}

	public void removeChannel(String name) {

		if (channels.containsKey(name))
			channels.remove(name);

	}

	public void addChannelValue(String name, float value) {

		channels.put(name, value);
//		System.out.println(name + ":" + value + "(m:" + getMeanAllChannels());

	}

	public float getMeanAllChannels() {

		return computeChannelSum() / channels.keySet().size();

	}

	public float getMeanForSpecificChannels(String channelNames[]) {

		return computeChannelSum(channelNames) / channelNames.length;
	}

	private float computeChannelSum() {
		return computeChannelSum(channels.keySet().toArray(new String[channels.keySet().size()]));
	}

	private float computeChannelSum(String channelNames[]) {
		float sum = 0f;

		for (String name : channelNames) {
			sum += channels.get(name);
		}

		return sum;

	}

	@Override
	public String toString() {
		String res = "";

		for (String key : channels.keySet()) {
			res += key + ":" + channels.get(key);
			res += "\n";

		}

		return res;

	}
}