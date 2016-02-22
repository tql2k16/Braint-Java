package Braint.gui;

import Braint.main.BraintMainApplet;
import Braint.settings.BigSettings;
import controlP5.ControlElement;
import controlP5.ControlEvent;
import controlP5.ControlListener;
import controlP5.ControlP5;
import controlP5.RadioButton;
import controlP5.Range;

public class AgentSettings implements ControlListener {
	private ControlP5 guiParent;
	private BraintMainApplet appletParent;

	Range strengthPowerRanges;
	Range scalePowerRanges;
	Range colorPowerRanges;

	Range strengthValuesRanges;
	Range scaleValuesRanges;
	Range colorValuesRanges;

	RadioButton strengthRadio;

	RadioButton scaleRadio;
	RadioButton colorRadio;

	RadioButton agentDrawMethod;

	private final int w = MainGui.default_GUI_Element_Width;

	private final int h = MainGui.default_GUI_Element_Height;

	public AgentSettings(ControlP5 guiParent, BraintMainApplet appletParent, int absoluteX, int absoluteY) {

		this.guiParent = guiParent;
		this.appletParent = appletParent;

		strengthPowerRanges = guiParent.addRange("Strength Power Ranges")
				// disable broadcasting since setRange and setRangeValues will
				// trigger an event
				.setBroadcast(false).setPosition(absoluteX, absoluteY + 2 * h).setSize(400, h).setHandleSize(h)
				.setRange(0, 150)
				.setRangeValues(BigSettings.instance().agentMinNoiseStrengthForPower,
						BigSettings.instance().agentMaxNoiseStrengthForPower)
				// after the initialization we turn broadcast back on again
				.setBroadcast(true).setColorForeground(appletParent.color(255, 40))
				.setColorBackground(appletParent.color(255, 40));

		strengthPowerRanges.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				BigSettings.instance().agentMinNoiseStrengthForPower = arg0.getArrayValue(0);
				BigSettings.instance().agentMaxNoiseStrengthForPower = arg0.getArrayValue(1);

			}
		});

		scalePowerRanges = guiParent.addRange("Scale Power Ranges")
				// disable broadcasting since setRange and setRangeValues will
				// trigger an event
				.setBroadcast(false).setPosition(absoluteX, absoluteY + 5 * h).setSize(400, h).setHandleSize(h)
				.setRange(0, 150)
				.setRangeValues(BigSettings.instance().agentMinNoiseScaleForPower,
						BigSettings.instance().agentMaxNoiseScaleForPower)
				// after the initialization we turn broadcast back on again
				.setBroadcast(true).setColorForeground(appletParent.color(255, 40))
				.setColorBackground(appletParent.color(255, 40));

		scalePowerRanges.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				BigSettings.instance().agentMinNoiseScaleForPower = arg0.getArrayValue(0);
				BigSettings.instance().agentMaxNoiseScaleForPower = arg0.getArrayValue(1);

			}
		});

		colorPowerRanges = guiParent.addRange("Color Power Ranges")
				// disable broadcasting since setRange and setRangeValues will
				// trigger an event
				.setBroadcast(false).setPosition(absoluteX, absoluteY + 8 * h).setSize(400, h).setHandleSize(h)
				.setRange(0, 150)
				.setRangeValues(BigSettings.instance().agentMinColorValueForPower,
						BigSettings.instance().agentMaxColorValueForPower)
				// after the initialization we turn broadcast back on again
				.setBroadcast(true).setColorForeground(appletParent.color(255, 40))
				.setColorBackground(appletParent.color(255, 40));

		colorPowerRanges.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				BigSettings.instance().agentMinColorValueForPower = arg0.getArrayValue(0);
				BigSettings.instance().agentMaxColorValueForPower = arg0.getArrayValue(1);

			}
		});

		// RANGES

		strengthValuesRanges = guiParent.addRange("Strength Values Ranges")
				// disable broadcasting since setRange and setRangeValues will
				// trigger an event
				.setBroadcast(false).setPosition(absoluteX, absoluteY + 14 * h).setSize(400, h).setHandleSize(h)
				.setRange(3, 60)
				.setRangeValues(BigSettings.instance().agentMinNoiseStrength,
						BigSettings.instance().agentMaxNoiseStrength)
				// after the initialization we turn broadcast back on again
				.setBroadcast(true).setColorForeground(appletParent.color(255, 40))
				.setColorBackground(appletParent.color(255, 40));

		strengthValuesRanges.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				BigSettings.instance().agentMinNoiseStrength = arg0.getArrayValue(0);
				BigSettings.instance().agentMaxNoiseStrength = arg0.getArrayValue(1);

			}
		});

		scaleValuesRanges = guiParent.addRange("Scale Values Ranges")
				// disable broadcasting since setRange and setRangeValues will
				// trigger an event
				.setBroadcast(false).setPosition(absoluteX, absoluteY + 17 * h).setSize(400, h).setHandleSize(h)
				.setRange(25, 600)
				.setRangeValues(BigSettings.instance().agentMinNoiseScale, BigSettings.instance().agentMaxNoiseScale)
				// after the initialization we turn broadcast back on again
				.setBroadcast(true).setColorForeground(appletParent.color(255, 40))
				.setColorBackground(appletParent.color(255, 40));

		scaleValuesRanges.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				BigSettings.instance().agentMinNoiseScale = arg0.getArrayValue(0);
				BigSettings.instance().agentMaxNoiseScale = arg0.getArrayValue(1);

			}
		});

		colorValuesRanges = guiParent.addRange("Color Values Ranges")
				// disable broadcasting since setRange and setRangeValues will
				// trigger an event
				.setBroadcast(false).setPosition(absoluteX, absoluteY + 20 * h).setSize(400, h).setHandleSize(h)
				.setRange(0, 1)
				.setRangeValues(BigSettings.instance().agentMinColorValue, BigSettings.instance().agentMaxColorValue)
				// after the initialization we turn broadcast back on again
				.setBroadcast(true).setColorForeground(appletParent.color(255, 40))
				.setColorBackground(appletParent.color(255, 40));

		colorValuesRanges.addListener(new ControlListener() {

			@Override
			public void controlEvent(ControlEvent arg0) {
				BigSettings.instance().agentMinColorValue = arg0.getArrayValue(0);
				BigSettings.instance().agentMaxColorValue = arg0.getArrayValue(1);

			}
		});

		// "Select which power should be used for noise strength"
		strengthRadio = guiParent.addRadioButton("strRadio").setPosition(absoluteX, absoluteY + 27 * h).setSize(40, 20)
				// .setColorForeground(appletParent.color(120))
				// .setColorActive(appletParent.color(255))
				// .setColorLabel(appletParent.color(255))
				.setItemsPerRow(3).setBarHeight(10).setItemHeight(10).setSpacingColumn(75).addItem("Alpha4Strength", 1)
				.addItem("Beta4Strength", 2).addItem("Alpha/Beta4Strength", 3);

		strengthRadio.setArrayValue(new float[] { BigSettings.instance().useAlphaForNoiseStrength ? 1.0f : 0.0f,
				BigSettings.instance().useBetaForNoiseStrength ? 1.0f : 0.0f,
				BigSettings.instance().useAlphaBetaRatioForNoiseStrength ? 1.0f : 0.0f });

		scaleRadio = guiParent.addRadioButton("scaRadio").setPosition(absoluteX, absoluteY + 29 * h).setSize(40, 20)
				// .setColorForeground(appletParent.color(120))
				// .setColorActive(appletParent.color(255))
				// .setColorLabel(appletParent.color(255))
				.setItemsPerRow(3).setBarHeight(10).setItemHeight(10).setSpacingColumn(75).addItem("Alpha4Scale", 1)
				.addItem("Beta4Scale", 2).addItem("Alpha/Beta4Scale", 3);

		scaleRadio.setArrayValue(new float[] { BigSettings.instance().useAlphaForNoiseScale ? 1.0f : 0.0f,
				BigSettings.instance().useBetaForNoiseScale ? 1.0f : 0.0f,
				BigSettings.instance().useAlphaBetaRatioForNoiseScale ? 1.0f : 0.0f });

		colorRadio = guiParent.addRadioButton("colRadio").setPosition(absoluteX, absoluteY + 31 * h).setSize(40, 20)
				// .setColorForeground(appletParent.color(120))
				// .setColorActive(appletParent.color(255))
				// .setColorLabel(appletParent.color(255))
				.setItemsPerRow(3).setBarHeight(10).setItemHeight(10).setSpacingColumn(75).addItem("Alpha4Color", 1)
				.addItem("Beta4Color", 2).addItem("Alpha/Beta4Color", 3);

		colorRadio.setArrayValue(new float[] { BigSettings.instance().useAlphaForColor ? 1.0f : 0.0f,
				BigSettings.instance().useBetaForColor ? 1.0f : 0.0f,
				BigSettings.instance().useAlphaBetaRatioForColor ? 1.0f : 0.0f });

		agentDrawMethod = guiParent.addRadioButton("agentDrawMethod").setPosition(absoluteX + 6*w, absoluteY + 2 * h)
				.setSize(40, 20)
				// .setColorForeground(appletParent.color(120))
				// .setColorActive(appletParent.color(255))
				// .setColorLabel(appletParent.color(255))
				.setItemsPerRow(2).setBarHeight(10).setItemHeight(10).setSpacingColumn(75).addItem("Method1", 1)
				.addItem("Method2", 2);

		agentDrawMethod.setArrayValue(new float[] { BigSettings.instance().useAgents ? 1.0f : 0.0f,
				BigSettings.instance().useAgents2 ? 1.0f : 0.0f });

		guiParent.addListener(this);

	}

	@ControlElement(x = 4 * w, y = 27 * h, label = "Reverse Strength Power", properties = { "height=10" })
	boolean reverseStrengthPower = BigSettings.instance().useAlphaForNoiseStrengthReversed
			|| BigSettings.instance().useBetaForNoiseStrengthReversed
			|| BigSettings.instance().useAlphaBetaRatioForNoiseStrengthReversed;

	public void reverseStrengthPower(boolean val) {

		BigSettings.instance().useAlphaForNoiseStrengthReversed = val && BigSettings.instance().useAlphaForNoiseStrength
				&& !BigSettings.instance().useBetaForNoiseStrength
				&& !BigSettings.instance().useAlphaBetaRatioForNoiseStrength;

		BigSettings.instance().useBetaForNoiseStrengthReversed = val && BigSettings.instance().useBetaForNoiseStrength
				&& !BigSettings.instance().useAlphaForNoiseStrength
				&& !BigSettings.instance().useAlphaBetaRatioForNoiseStrength;

		BigSettings.instance().useAlphaBetaRatioForNoiseStrengthReversed = val
				&& !BigSettings.instance().useBetaForNoiseStrength && !BigSettings.instance().useAlphaForNoiseStrength
				&& BigSettings.instance().useAlphaBetaRatioForNoiseStrength;

	}

	@ControlElement(x = 4 * w, y = 29 * h, label = "Reverse Scale Power", properties = { "height=10" })
	boolean reverseScalePower = BigSettings.instance().useAlphaForNoiseScaleReversed
			|| BigSettings.instance().useBetaForNoiseScaleReversed
			|| BigSettings.instance().useAlphaBetaRatioForNoiseScaleReversed;

	public void reverseScalePower(boolean val) {

		BigSettings.instance().useAlphaForNoiseScaleReversed = val && BigSettings.instance().useAlphaForNoiseScale
				&& !BigSettings.instance().useBetaForNoiseScale
				&& !BigSettings.instance().useAlphaBetaRatioForNoiseScale;

		BigSettings.instance().useBetaForNoiseScaleReversed = val && BigSettings.instance().useBetaForNoiseScale
				&& !BigSettings.instance().useAlphaForNoiseScale
				&& !BigSettings.instance().useAlphaBetaRatioForNoiseScale;

		BigSettings.instance().useAlphaBetaRatioForNoiseScaleReversed = val
				&& !BigSettings.instance().useBetaForNoiseScale && !BigSettings.instance().useAlphaForNoiseScale
				&& BigSettings.instance().useAlphaBetaRatioForNoiseScale;

	}

	@ControlElement(x = 4 * w, y = 31 * h, label = "Reverse Color Power", properties = { "height=10" })
	boolean reverseColorPower = BigSettings.instance().useAlphaForColorReversed
			|| BigSettings.instance().useBetaForColorReversed
			|| BigSettings.instance().useAlphaBetaRatioForColorReversed;

	public void reverseColorPower(boolean val) {

		BigSettings.instance().useAlphaForColorReversed = val && BigSettings.instance().useAlphaForColor
				&& !BigSettings.instance().useBetaForColor && !BigSettings.instance().useAlphaBetaRatioForColor;

		BigSettings.instance().useBetaForColorReversed = val && BigSettings.instance().useBetaForColor
				&& !BigSettings.instance().useAlphaForColor && !BigSettings.instance().useAlphaBetaRatioForColor;

		BigSettings.instance().useAlphaBetaRatioForColorReversed = val && !BigSettings.instance().useBetaForColor
				&& !BigSettings.instance().useAlphaForColor && BigSettings.instance().useAlphaBetaRatioForColor;

	}

	@Override
	public void controlEvent(ControlEvent theEvent) {
		// System.out.println("WHAT?" + theEvent.getName());

		if (theEvent.isFrom(strengthRadio)) {

			BigSettings.instance().useAlphaForNoiseStrength = theEvent.getGroup().getArrayValue()[0] == 1.0 ? true
					: false;

			BigSettings.instance().useBetaForNoiseStrength = theEvent.getGroup().getArrayValue()[1] == 1.0 ? true
					: false;

			BigSettings.instance().useAlphaBetaRatioForNoiseStrength = theEvent.getGroup().getArrayValue()[2] == 1.0
					? true : false;

			reverseStrengthPower(BigSettings.instance().useAlphaForNoiseStrengthReversed
					|| BigSettings.instance().useBetaForNoiseStrengthReversed
					|| BigSettings.instance().useAlphaBetaRatioForNoiseStrengthReversed);

		} else if (theEvent.isFrom(scaleRadio)) {

			BigSettings.instance().useAlphaForNoiseScale = theEvent.getGroup().getArrayValue()[0] == 1.0 ? true : false;

			BigSettings.instance().useBetaForNoiseScale = theEvent.getGroup().getArrayValue()[1] == 1.0 ? true : false;

			BigSettings.instance().useAlphaBetaRatioForNoiseScale = theEvent.getGroup().getArrayValue()[2] == 1.0 ? true
					: false;

			reverseScalePower(BigSettings.instance().useAlphaForNoiseScaleReversed
					|| BigSettings.instance().useBetaForNoiseScaleReversed
					|| BigSettings.instance().useAlphaBetaRatioForNoiseScaleReversed);

		} else if (theEvent.isFrom(colorRadio)) {

			BigSettings.instance().useAlphaForColor = theEvent.getGroup().getArrayValue()[0] == 1.0 ? true : false;

			BigSettings.instance().useBetaForColor = theEvent.getGroup().getArrayValue()[1] == 1.0 ? true : false;

			BigSettings.instance().useAlphaBetaRatioForColor = theEvent.getGroup().getArrayValue()[2] == 1.0 ? true
					: false;

			reverseScalePower(
					BigSettings.instance().useAlphaForColorReversed || BigSettings.instance().useBetaForColorReversed
							|| BigSettings.instance().useAlphaBetaRatioForColorReversed);

		} else if (theEvent.isFrom(agentDrawMethod)) {
			BigSettings.instance().useAgents = theEvent.getGroup().getArrayValue()[0] == 1.0 ? true : false;
			BigSettings.instance().useAgents2 = theEvent.getGroup().getArrayValue()[1] == 1.0 ? true : false;
		}
	}

	@ControlElement(x = 0, y = 0, label = "rangesHeadingPower", properties = { "type=textlabel" })
	String rangesHeadingPower = "Select Min/Max Power Values";

	@ControlElement(x = 0, y = h, label = "rangesHeadingPowerStrength", properties = { "type=textlabel" })
	String rangesHeadingPowerStrength = "Min/Max Power Values for Strength";

	@ControlElement(x = 0, y = 4 * h, label = "rangesHeadingPowerScale", properties = { "type=textlabel" })
	String rangesHeadingPowerScale = "Min/Max Power Values for Scale";

	@ControlElement(x = 0, y = 7 * h, label = "rangesHeadingPowerColor", properties = { "type=textlabel" })
	String rangesHeadingPowerColor = "Min/Max Power Values for Color";

	@ControlElement(x = 0, y = 12 * h, label = "RangesHeadingStrength", properties = { "type=textlabel" })
	String RangesHeadingStrength = "Select Min/Max Values";

	@ControlElement(x = 0, y = 13 * h, label = "aRangesHeading", properties = { "type=textlabel" })
	String aRangesHeadingStrength = "Min/Max Values for Strength";

	@ControlElement(x = 0, y = 16 * h, label = "bRangesHeading", properties = { "type=textlabel" })
	String bRangesHeadingStrength = "Min/Max Values for Scale";

	@ControlElement(x = 0, y = 19 * h, label = "abRangesHeading", properties = { "type=textlabel" })
	String abRangesHeadingStrength = "Min/Max Values for Color";

	@ControlElement(x = 0, y = 25 * h, label = "MappingHeading", properties = { "type=textlabel" })
	String MappingHeading = "Mapping of alpha & beta to the agent parameters";

	@ControlElement(x = 0, y = 26 * h, label = "StrengthMapping", properties = { "type=textlabel" })
	String StrengthMapping = "Mapping of alpha & beta to noise strength";

	@ControlElement(x = 0, y = 28 * h, label = "ScaleMapping", properties = { "type=textlabel" })
	String ScaleMapping = "Mapping of alpha & beta to noise Scale";

	@ControlElement(x = 0, y = 30 * h, label = "ColorMapping", properties = { "type=textlabel" })
	String ColorMapping = "Mapping of alpha & beta to noise Color";
	
			
	@ControlElement(x= 6* w, y= 3 * h, label = "Agent Count",properties = { "min=1",
	"max=200" })
	int agentCount = BigSettings.instance().agentCount;
	public void agentCount(int val) {
		BigSettings.instance().agentCount = val;
		
		
	
	}
	
	@ControlElement(x= 6* w, y= 4 * h, label = "Stroke Width",properties = { "min=1",
	"max=10" })
	float strokeWidth = BigSettings.instance().agentStrokeWidth;
	public void strokeWidth(float val) {
		BigSettings.instance().agentStrokeWidth = val;

	}
	
	@ControlElement(x= 6* w, y= 5 * h, label = "Color Mode (Method1)",properties = { "min=1",
	"max=5" })
	int colorMode = BigSettings.instance().colorMode;
	public void colorMode(int val) {

		BigSettings.instance().colorMode = val;


	}

}
