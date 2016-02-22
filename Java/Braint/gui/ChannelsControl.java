package Braint.gui;

import Braint.main.BraintMainApplet;
import Braint.settings.BigSettings;
import controlP5.ControlElement;
import controlP5.ControlP5;

public class ChannelsControl {

	private ControlP5 guiParent;
	private BraintMainApplet appletParent;
	
	
	private final int w = MainGui.default_GUI_Element_Width;
	
	private final int h = MainGui.default_GUI_Element_Height;

	public ChannelsControl(ControlP5 guiParent, BraintMainApplet appletParent, int absoluteX, int absoluteY) {
		
		this.guiParent = guiParent;

		this.appletParent = appletParent;		
		
	}
	
	
	
	@ControlElement(x = 0, y = 0, label = "alphaChannelsHeading", properties = { "type=textlabel" })
	String alphaChannelsHeading = "Select Alpha Channels";
	
	@ControlElement(x = w, y = 0, label = "betaChannelsHeading", properties = { "type=textlabel" })
	String betaChannelsHeading = "Select Beta Channels";
	
	
	// ALPHA
	@ControlElement(x = 0, y = 1*(h+2), label = "af3", properties = { "type=checkbox" })
	boolean alpha_af3 = BigSettings.instance().alphaChannelList2.contains("af3");
	public void alpha_af3(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("af3");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("af3");
		}
	}
	
	@ControlElement(x = 0, y = 3*(h+2), label = "af4", properties = { "type=checkbox" })
	boolean alpha_af4 = BigSettings.instance().alphaChannelList2.contains("af4");
	public void alpha_af4(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("af4");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("af4");
		}	
	}
	
	@ControlElement(x = 0, y = 5*(h+2), label = "f3", properties = { "type=checkbox" })
	boolean alpha_f3 = BigSettings.instance().alphaChannelList2.contains("f3");
	public void alpha_f3(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("f3");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("f3");
		}	
	}
	
	
	@ControlElement(x = 0, y = 7*(h+2), label = "f4", properties = { "type=checkbox" })
	boolean alpha_f4 = BigSettings.instance().alphaChannelList2.contains("f4");
	public void alpha_f4(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("f4");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("f4");
		}	
	}
	
	@ControlElement(x = 0, y = 9*(h+2), label = "f7", properties = { "type=checkbox" })
	boolean alpha_f7 = BigSettings.instance().alphaChannelList2.contains("f7");
	public void alpha_f7(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("f7");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("f7");
		}	
	}
	
	@ControlElement(x = 0, y = 11*(h+2), label = "f8", properties = { "type=checkbox" })
	boolean alpha_f8 = BigSettings.instance().alphaChannelList2.contains("f8");
	public void alpha_f8(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("f8");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("f8");
		}	
	}
	
	@ControlElement(x = 0, y = 13*(h+2), label = "fc6", properties = { "type=checkbox" })
	boolean alpha_fc6 = BigSettings.instance().alphaChannelList2.contains("fc6");
	public void alpha_fc6(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("fc6");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("fc6");
		}	
	}
	
	@ControlElement(x = 0, y = 15*(h+2), label = "fc5", properties = { "type=checkbox" })
	boolean alpha_fc5 = BigSettings.instance().alphaChannelList2.contains("fc5");
	public void alpha_fc5(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("fc5");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("fc5");
		}	
	}
	
	@ControlElement(x = 0, y = 17*(h+2), label = "p7", properties = { "type=checkbox" })
	boolean alpha_p7 = BigSettings.instance().alphaChannelList2.contains("p7");
	public void alpha_p7(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("p7");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("p7");
		}	
	}
	
	@ControlElement(x = 0, y = 19*(h+2), label = "p8", properties = { "type=checkbox" })
	boolean alpha_p8 = BigSettings.instance().alphaChannelList2.contains("p8");
	public void alpha_p8(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("p8");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("p8");
		}	
	}
	
	@ControlElement(x = 0, y = 21*(h+2), label = "o1", properties = { "type=checkbox" })
	boolean alpha_o1 = BigSettings.instance().alphaChannelList2.contains("o1");
	public void alpha_o1(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("o1");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("o1");
		}	
	}
		
	@ControlElement(x = 0, y = 23*(h+2), label = "o2", properties = { "type=checkbox" })
	boolean alpha_o2 = BigSettings.instance().alphaChannelList2.contains("o2");
	public void alpha_o2(boolean value){
		if(value){
			BigSettings.instance().alphaChannelList2.add("o2");		
		} else {
			BigSettings.instance().alphaChannelList2.remove("o2");
		}	
	}
	
	
	// beta
	@ControlElement(x = w, y = 1*(h+2), label = "AF3", properties = { "type=checkbox" })
	boolean beta_af3 = BigSettings.instance().betaChannelList2.contains("af3");
	public void beta_af3(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("af3");		
		} else {
			BigSettings.instance().betaChannelList2.remove("af3");
		}
		
	}
	
	@ControlElement(x = w, y = 3*(h+2), label = "af4", properties = { "type=checkbox" })
	boolean beta_af4 = BigSettings.instance().betaChannelList2.contains("af4");
	public void beta_af4(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("af4");		
		} else {
			BigSettings.instance().betaChannelList2.remove("af4");
		}	
	}
	
	@ControlElement(x = w, y = 5*(h+2), label = "f3", properties = { "type=checkbox" })
	boolean beta_f3 = BigSettings.instance().betaChannelList2.contains("f3");
	public void beta_f3(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("f3");		
		} else {
			BigSettings.instance().betaChannelList2.remove("f3");
		}	
	}
	
	
	@ControlElement(x = w, y = 7*(h+2), label = "f4", properties = { "type=checkbox" })
	boolean beta_f4 = BigSettings.instance().betaChannelList2.contains("f4");
	public void beta_f4(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("f4");		
		} else {
			BigSettings.instance().betaChannelList2.remove("f4");
		}	
	}
	
	@ControlElement(x = w, y = 9*(h+2), label = "f7", properties = { "type=checkbox" })
	boolean beta_f7 = BigSettings.instance().betaChannelList2.contains("f7");
	public void beta_f7(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("f7");		
		} else {
			BigSettings.instance().betaChannelList2.remove("f7");
		}	
	}
	
	@ControlElement(x = w, y = 11*(h+2), label = "f8", properties = { "type=checkbox" })
	boolean beta_f8 = BigSettings.instance().betaChannelList2.contains("f8");
	public void beta_f8(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("f8");		
		} else {
			BigSettings.instance().betaChannelList2.remove("f8");
		}	
	}
	
	@ControlElement(x = w, y = 13*(h+2), label = "fc6", properties = { "type=checkbox" })
	boolean beta_fc6 = BigSettings.instance().betaChannelList2.contains("fc6");
	public void beta_fc6(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("fc6");		
		} else {
			BigSettings.instance().betaChannelList2.remove("fc6");
		}	
	}
	
	@ControlElement(x = w, y = 15*(h+2), label = "fc5", properties = { "type=checkbox" })
	boolean beta_fc5 = BigSettings.instance().betaChannelList2.contains("fc5");
	public void beta_fc5(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("fc5");		
		} else {
			BigSettings.instance().betaChannelList2.remove("fc5");
		}	
	}
	
	@ControlElement(x = w, y = 17*(h+2), label = "p7", properties = { "type=checkbox" })
	boolean beta_p7 = BigSettings.instance().betaChannelList2.contains("p7");
	public void beta_p7(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("p7");		
		} else {
			BigSettings.instance().betaChannelList2.remove("p7");
		}	
	}
	
	@ControlElement(x = w, y = 19*(h+2), label = "p8", properties = { "type=checkbox" })
	boolean beta_p8 = BigSettings.instance().betaChannelList2.contains("p8");
	public void beta_p8(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("p8");		
		} else {
			BigSettings.instance().betaChannelList2.remove("p8");
		}	
	}
	
	@ControlElement(x = w, y = 21*(h+2), label = "o1", properties = { "type=checkbox" })
	boolean beta_o1 = BigSettings.instance().betaChannelList2.contains("o1");
	public void beta_o1(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("o1");		
		} else {
			BigSettings.instance().betaChannelList2.remove("o1");
		}	
	}
		
	@ControlElement(x = w, y = 23*(h+2), label = "o2", properties = { "type=checkbox" })
	boolean beta_o2 = BigSettings.instance().betaChannelList2.contains("o2");
	public void beta_o2(boolean value){
		if(value){
			BigSettings.instance().betaChannelList2.add("o2");		
		} else {
			BigSettings.instance().betaChannelList2.remove("o2");
		}	
	}
	
	



}
