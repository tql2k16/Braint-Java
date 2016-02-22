package Braint.gui;

import Braint.main.BraintMainApplet;
import Braint.openVibe.OpenVibeCalibration;
import Braint.settings.BigSettings;
import Braint.util.BraintUtil;
import controlP5.Button;
import controlP5.ControlElement;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.Textlabel;

public class CalibrationControl {

	// @ControlElement(x = 50, y = 40)
	// public void toggle(boolean b) {
	// System.out.println("hello world");
	// toggle(true);
	// }

	public OpenVibeCalibration calib;
	private ControlP5 guiParent;
	private BraintMainApplet appletParent;

	public CalibrationControl(ControlP5 parent, int absoluteX, int absoluteY, OpenVibeCalibration calib,
			BraintMainApplet appletParent) {
		this.guiParent = parent;
		this.calib = calib;
		this.appletParent = appletParent;
		Button one = parent.addButton("Start Calibration").setSize(100, 30).setPosition(absoluteX,
				absoluteY + 2 * MainGui.default_GUI_Element_Height); // .setLabel("Start

		one.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				startCalibration(arg0, 0);
			}
		});

		// aSummary = "asd";

		// set statistics size

	}

	public void updateStatistics() {
		// TODO Auto-generated method stub

		((Textlabel) guiParent.get("/calib/aSummary")).setLineHeight(15);
		((Textlabel) guiParent.get("/calib/aSummary")).getValueLabel().getFont().setSize(14);

		((Textlabel) guiParent.get("/calib/bSummary")).setLineHeight(15);
		((Textlabel) guiParent.get("/calib/bSummary")).getValueLabel().getFont().setSize(14);

		((Textlabel) guiParent.get("/calib/abSummary")).setLineHeight(15);
		((Textlabel) guiParent.get("/calib/abSummary")).getValueLabel().getFont().setSize(14);

		((Textlabel) guiParent.get("/calib/aSummaryHeading")).setLineHeight(15);
		((Textlabel) guiParent.get("/calib/aSummaryHeading")).getValueLabel().getFont().setSize(14);

		((Textlabel) guiParent.get("/calib/bSummaryHeading")).setLineHeight(15);
		((Textlabel) guiParent.get("/calib/bSummaryHeading")).getValueLabel().getFont().setSize(14);

		((Textlabel) guiParent.get("/calib/abSummaryHeading")).setLineHeight(15);
		((Textlabel) guiParent.get("/calib/abSummaryHeading")).getValueLabel().getFont().setSize(14);

		guiParent.get("/calib/aSummary")
				.setStringValue(BraintUtil.getDecentStatisticsString(BigSettings.instance().alphaStatistics));
		guiParent.get("/calib/bSummary")
				.setStringValue(BraintUtil.getDecentStatisticsString(BigSettings.instance().betaStatistics));
		guiParent.get("/calib/abSummary")
				.setStringValue(BraintUtil.getDecentStatisticsString(BigSettings.instance().alphaBetaRatioStatistics));

		// Textlabel a = (Textlabel) parent.get("/calib/aSummary");
		// a.setLineHeight(15);
		// a.getValueLabel().getFont().setSize(14);

		//
		// Textlabel b = (Textlabel) parent.get("/calib/bSummary");
		//// b.getValueLabel().getFont().setSize(14);
		//
		// parent.getController("alphaStats");
		//
		// parent.get("alphaStats");

		// List<ControllerInterface<?>> hey = parent.getAll();
		//
		// for (ControllerInterface<?> controllerInterface : hey) {
		// System.out.println(controllerInterface.getAddress());
		// }

		// System.out.println(parent.get("/calib/aSummary"));
		//
		// Textlabel asd;

	}

	public void startCalibration(ControlEvent arg0, int val) {
		if (BigSettings.instance().useOpenVibe && !calib.isCalibrating()) {
			System.out.println("start");
			appletParent.setDoCalibration(true);
			calib.setCalibrated(false);
			guiParent.get("/calib/currentTimeCalib").setStringValue("Elapsed Time: " + calib.getCurrentTimeInSeconds());

		} else if (calib.isCalibrating()) {
			System.out.println("running");
			guiParent.get("/calib/currentTimeCalib").setStringValue("currently calibrating");
		} else {

			System.err.println("not using open vibe!!! enable it!!!");

			guiParent.get("/calib/currentTimeCalib").setStringValue("not using open vibe!!! enable it!!!");
		}

	}

	public void updateCalibTimer() {
		guiParent.get("/calib/currentTimeCalib").setStringValue("Elapsed Time: " + calib.getCurrentTimeInSeconds());

	}

	@ControlElement(x = 0, y = 0, label = "calibHeading", properties = { "type=textlabel" })
	String CalibrationHeading = "Calibration with OpenVibe";

	@ControlElement(properties = { "min=5",
			"max=60" }, x = 0, y = MainGui.default_GUI_Element_Height, label = "Calibration Time (seconds)")
	public int calibrationTime = BigSettings.instance().CALIBRATION_TIME;

	public void calibrationTime(int value) {

		BigSettings.instance().CALIBRATION_TIME = value ;
		calibrationTime = value;
	}

	// BUTTON IS HERE
	@ControlElement(x = MainGui.default_GUI_Element_Width, y = 2 * MainGui.default_GUI_Element_Height
			+ MainGui.default_GUI_Element_Height, label = "currentTimeCalib", properties = { "type=textlabel" })
	String currentTimeCalib = "click to start calibration";

	@ControlElement(x = 0, y = 5 * MainGui.default_GUI_Element_Height, label = "alphaStatsHeading", properties = {
			"type=textlabel" })
	String aSummaryHeading = "Alpha Statistics";

	@ControlElement(x = 0, y = 6 * MainGui.default_GUI_Element_Height, label = "alphaStats", properties = {
			"type=textlabel" })
	String aSummary = BigSettings.instance().alphaStatistics.toString();

	@ControlElement(x = 3 * MainGui.default_GUI_Element_Width, y = 5
			* MainGui.default_GUI_Element_Height, label = "betaStatsHeading", properties = { "type=textlabel",
					"lineheight=15" })
	String bSummaryHeading = "Beta Statistics";
	@ControlElement(x = 3 * MainGui.default_GUI_Element_Width, y = 6
			* MainGui.default_GUI_Element_Height, label = "betaStats", properties = { "type=textlabel" })
	String bSummary = BigSettings.instance().betaStatistics.toString();

	@ControlElement(x = 6 * MainGui.default_GUI_Element_Width, y = 5
			* MainGui.default_GUI_Element_Height, label = "alphabetaStatsHeading", properties = { "type=textlabel" })
	String abSummaryHeading = "Alpha/Beta Statistics";
	@ControlElement(x = 6 * MainGui.default_GUI_Element_Width, y = 6
			* MainGui.default_GUI_Element_Height, label = "alphaBetaStats", properties = { "type=textlabel" })
	String abSummary = BigSettings.instance().betaStatistics.toString();

}
