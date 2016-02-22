package Braint.osc;

import oscP5.OscMessage;

public interface IOSCMessageHandler {
	
	public void handleOSCMessage(OscMessage msg);
}
