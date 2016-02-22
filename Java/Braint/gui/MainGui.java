package Braint.gui;

import java.util.LinkedList;

import Braint.drawMethods.IProcessingDrawable;
import Braint.main.BraintMainApplet;
import Braint.openVibe.OpenVibeCalibration;
import Braint.settings.BigSettings;
import controlP5.Button;
import controlP5.ColorWheel;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.Tab;

public class MainGui implements IProcessingDrawable {

	ControlP5 cp5;

	LinkedList<Tab> allTabs;

	CalibrationControl calib;

	AgentSettings agentSettings;

	private OpenVibeCalibration openVibeCalibration;

	public static final int guiHeight = 900;
	public static final int guiWidth = 1600;

	public static final int topLeftX = (1920 - 1600) / 2;
	public static final int topLeftY = (1080 - 900) / 2;

	// relative to topLeftX/Y
	public static final int topLeftXTabContent = 0;
	public static final int topLeftYTabContent = 20;

	public static final int defaultTabHeight = 50;
	public static int defaultTabWidth = 1600 / 6;

	public static int tabCount = 5;

	public static int default_GUI_Element_X = 10;
	public static int default_GUI_Element_Y = 10;

	public static final int default_GUI_Element_Width = 100;
	public static final int default_GUI_Element_Height = 15;

	private BraintMainApplet parent;


	private ChannelsControl channels;

	// @ControlElement
	// public int drawMode = 0;

	public MainGui(OpenVibeCalibration openVibeCalibration2, BraintMainApplet parent) {
		this.openVibeCalibration = openVibeCalibration2;
		this.parent = parent;
	}

	public void reset() {

		cp5.controlWindow.clear();

	}

	public void hide() {
		cp5.hide();
	}
	
	public void show() {
		cp5.show();
	}

	@Override
	public void setupDrawing(BraintMainApplet applet) {
		// applet.size(600, 400);
		// applet.noStroke();

		cp5 = new ControlP5(applet).setPosition(topLeftX, topLeftY);

		// cp5.getFont().setSize(18);
		// cp5.setFont(.setSize(18));

		// Annotations:
		// addControllersFor(PApplet) checks the main sketch for
		// annotations and adds controllers accordingly.
		cp5.addControllersFor(applet);

		allTabs = new LinkedList<Tab>();

		// Tabs: general settings, calibration, emo engine, agents settings,
		// attractor settings
		cp5.getDefaultTab().setLabel("General Settings");

//		allTabs.add(cp5.getDefaultTab());
//		allTabs.add(cp5.addTab("OpenVibe Calibration"));
//		// allTabs.add(cp5.addTab("Emo Engine Settings"));
//		allTabs.add(cp5.addTab("Draw with Agents"));

//		tabCount = allTabs.size();
//
//		defaultTabWidth = ((1600 - tabCount / 2 * 10) / tabCount);
//
//		for (Tab tab : allTabs) {
//
//			tab.setHeight(defaultTabHeight);
//			tab.setWidth(defaultTabWidth);
//			tab.getCaptionLabel().setSize(24);
//
//		}

		cp5.addControllersFor("mainGui", this);

		// TODO
		// cp5.loadProperties(("hello.properties"));

		// // set the Position of controllers contained within object tc1

		calib = new CalibrationControl(cp5, topLeftXTabContent, topLeftYTabContent + guiHeight / 2 + 150,
				openVibeCalibration, parent);

		cp5.addControllersFor("calib", calib);

		cp5.setPosition(topLeftXTabContent, topLeftYTabContent + guiHeight / 2 + 150, calib);

		channels = new ChannelsControl(cp5, applet, topLeftXTabContent, topLeftYTabContent + 30);

		cp5.addControllersFor("channels", channels);

		cp5.setPosition(topLeftXTabContent, topLeftYTabContent + 30, channels);

		agentSettings = new AgentSettings(cp5, parent, topLeftXTabContent + 3 * default_GUI_Element_Width,
				topLeftYTabContent + 30 + default_GUI_Element_Height + 2);

		cp5.addControllersFor("agentSettings", agentSettings);

		cp5.setPosition(topLeftXTabContent + 3 * default_GUI_Element_Width,
				topLeftYTabContent + 30 + default_GUI_Element_Height + 2, agentSettings);

		cp5.addColorWheel("Background", guiWidth - 250, 75, 200).setRGB(applet.color(128, 0, 255));

		cp5.get(ColorWheel.class, "Background").addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				coloWheelr(0);

			}
		});
		
		
		Button one = cp5.addButton("Start Drawing").setSize(100, 30).setPosition(guiWidth - 250, guiHeight - 250); // .setLabel("Start

		one.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				applet.startDrawing();
			}
		});
		
		Button two = cp5.addButton("Save Settings").setSize(100, 30).setPosition(guiWidth - 250, guiHeight - 200); // .setLabel("Start

		two.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				applet.saveSettings();
			}
		});
		
//		Button three = cp5.addButton("Load Settings").setSize(100, 30).setPosition(guiWidth - 250, guiHeight - 150); // .setLabel("Start
//
//		three.addListener(new ControlListener() {
//
//			@Override
//			public void controlEvent(ControlEvent arg0) {
//				applet.loadSettings();
//			}
//		});

	}

	public void coloWheelr(int value) {

		BigSettings.instance().backgroundColor = cp5.get(ColorWheel.class, "Background").getRGB();

	}

	@Override
	public void updateAndDraw(BraintMainApplet applet) {

		// if (applet.key == '1') {
		// cp5.saveProperties(("hello.properties"));
		// } else if (applet.key == '2') {
		// cp5.loadProperties(("hello.properties"));
		// }
		applet.background(BigSettings.instance().backgroundColor);

		// GUI Border
		applet.pushMatrix();
		applet.fill(40);
		applet.stroke(200);
		applet.rect(topLeftX - 10, topLeftY - 10, guiWidth + 10, guiHeight + 10);
		applet.popMatrix();

		// TODO Calibborder

		if (openVibeCalibration.calibrationFinisehd() && !openVibeCalibration.isCalibrating()) {
			calib.updateStatistics();
		} else if (openVibeCalibration.isCalibrating()) {
			calib.updateCalibTimer();
		}

		//
		// applet.pushMatrix();
		// if (tc1.b) {
		// applet.fill(tc1.x);
		// applet.translate(10, tc1.y);
		// applet.rect(topLeftX, topLeftY + 300, 100, 20);
		// }
		// applet.popMatrix();
		// applet.fill(255);
		// applet.text(tc1.in, topLeftX + 400, topLeftY + 100, 150, 300);
		//
		// applet.pushMatrix();
		// if (tc2.b) {
		// applet.fill(tc2.x);
		// applet.translate(200, tc2.y);
		// for (int i = 0; i < 1; i++) {
		// applet.rect(topLeftX + 0, topLeftY + 300, 100, 20);
		// }
		// }
		// applet.popMatrix();
		// applet.fill(255);
		// applet.text(tc2.in, topLeftX + 400, topLeftY + 300, 150, 300);
		//
		// // the variable x of object tt is controlled by the main program,
		// // the matching controller will update accordingly since it is
		// // listening for changes.
		// tc2.x = (int) applet.random(100);

	}

	// // to customize a controller with a CoontrolElement use the
	// // properties attribute. values passed using properties have a key and a
	// // value
	// // here the key corresponds with a function found within a controller
	// which
	// // starts with set followed by the name of the key. the value is then
	// // applied accordingly.
	// // e.g. min=10 will translate to controller.setMin(10)
	// @ControlElement(properties = { "min=0", "max=255", "type=numberbox",
	// "height=10",
	// "width=150" }, x = 600, y = 100, label = "Change Background")
	// float m = 40;

	public void setOpenVibeCalibration(OpenVibeCalibration openVibeCalibration) {
		this.openVibeCalibration = openVibeCalibration;
	}

	public OpenVibeCalibration OpenVibeCalibration() {
		return this.openVibeCalibration;
	}

	public void controlEvent(ControlEvent theEvent) {
		System.out.println(theEvent.getController().getName());
	}

}
