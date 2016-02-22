package Braint.emoEngine;

import Braint.openVibe.RawEEGDataSampleContainer;
import Braint.osc.IOSCMessageHandler;
import Braint.settings.BigSettings;
import Braint.util.BraintUtil;
import oscP5.OscMessage;

public class EmoEngineOSCHandler implements IOSCMessageHandler {

	private EmoEngineAffectiveState affState;
	private EmoEngineExpressivState exprState;
	
	
	private RawEEGDataSampleContainer raw;
	

	public EmoEngineOSCHandler() {

		affState = new EmoEngineAffectiveState();
		exprState = new EmoEngineExpressivState();
		
		
//		 string header = "COUNTER;INTERPOLATED;RAW_CQ;
//		 3 - AF3;F7;F3; FC5;
//		 7 - T7; P7; O1; O2;P8" +
//		 12 - T8; FC6; F4;F8; AF4;
//		 GYROX; GYROY;

		
		raw = new RawEEGDataSampleContainer(BigSettings.instance().allChannelsList);

	}

	@Override
	public void handleOSCMessage(OscMessage msg) {
		// TODO BraintCSharpAppHandler

		
		if (msg.checkAddrPattern("/emoengine/affective")) {

			/*
			 * typetag sfdddddd [0] emotivTimeStamp, boredom, excitement,
			 * frustration, mediation, valence, excitementLongTerm [1] 40.09696
			 * [2] 0.5487005114555359 [3] 0.0 [4] 0.7110533118247986 [5]
			 * 0.3333112597465515 [6] 0.625 [7] 0.0
			 */
			for (int i = 0; i < 6; i++) {

			}

			affState.timeStamp = msg.get(1).floatValue();
			affState.boredom = (float) msg.get(2).doubleValue();
			affState.excitement = (float) msg.get(3).doubleValue();
			affState.frustration = (float) msg.get(4).doubleValue();
			affState.mediation = (float) msg.get(5).doubleValue();
			affState.valence = (float) msg.get(6).doubleValue();
			affState.longtermExcitement = (float) msg.get(7).doubleValue();

			// setValueToRGB(excitement);

		} else if (msg.checkAddrPattern("/emoengine/expressiv")) {

			/*
			 * addrpattern /emoengine/expressiv typetag sfsfsfffffFTFFFFFF [0]
			 * EmoState_Timestamp;LowerFaceAction;LowerFaceActionPower;
			 * UpperFaceAction;UpperFaceActionPower;
			 * ExpressivEyelidStateX;ExpressivEyelidStateY;ExpressivEyeLocationX
			 * ;ExpressivEyeLocationY
			 * IsBlink;AreEyesOpen;IsLeftWink;IsRightWink;
			 * IsLookingLeft;IsLookingRight;IsLookingDown;IsLookingUp [1]
			 * 154.8831 [2] EXP_SMILE [3] 0.7 [4] EXP_FURROW [5] 0.6 [6] 0.0 [7]
			 * 0.0 [8] 0.0 [9] 0.0 [10] null [11] null [12] null [13] null [14]
			 * null [15] null [16] null [17] null
			 */
			
			exprState.emotivTimeStamp = msg.get(1).floatValue();
			exprState.lowerFaceAction = msg.get(2).stringValue();
			exprState.lowerFaceActionPower = msg.get(3).floatValue();
			exprState.upperFaceAction = msg.get(4).stringValue();
			exprState.upperFaceActionPower = msg.get(5).floatValue();
			exprState.xEyelidState = msg.get(6).floatValue();
			exprState.yEyelidState = msg.get(7).floatValue();
			exprState.posXEyeLocation = msg.get(8).floatValue();
			exprState.posYEyeLocation = msg.get(9).floatValue();
			
			exprState.isBlink = msg.get(10).intValue() == 1 ? true : false;
			exprState.areEyesOpen = msg.get(11).intValue()== 1 ? true : false;
			exprState.isLeftWink = msg.get(12).intValue()== 1 ? true : false;
			exprState.isRightWink  = msg.get(13).intValue()== 1 ? true : false;
			exprState.isLookingLeft = msg.get(14).intValue()== 1 ? true : false;
			exprState.isLookingRight = msg.get(15).intValue()== 1 ? true : false;
			exprState.isLookingDown = msg.get(16).intValue()== 1 ? true : false;
			exprState.isLookingUp = msg.get(17).intValue()== 1 ? true : false;
			

			//msg.print();

		} else 
		if (msg.checkAddrPattern("/emoengine/rawEEG")) {
			
			// TODO

			// string header = "COUNTER;INTERPOLATED;RAW_CQ;
			// 3 - AF3;F7;F3; FC5;
			// 7 - T7; P7; O1; O2;P8" +
			// 12 - T8; FC6; F4;F8; AF4;
			// GYROX; GYROY; TIMESTAMP; ES_TIMESTAMP" +
			// "FUNC_ID; FUNC_VALUE; MARKER; SYNC_SIGNAL;";

			// get(3 - 16) for all electrodes
			// msg.print();
			// scale
			
			
//			BraintUtil.OSC_OPENVIBE_O1, BraintUtil.OSC_OPENVIBE_O2, BraintUtil.OSC_OPENVIBE_P7, BraintUtil.OSC_OPENVIBE_P8, BraintUtil.OSC_OPENVIBE_AF3, BraintUtil.OSC_OPENVIBE_AF4, BraintUtil.OSC_OPENVIBE_F3, BraintUtil.OSC_OPENVIBE_F4, 
//			BraintUtil.OSC_OPENVIBE_F7, BraintUtil.OSC_OPENVIBE_F8, BraintUtil.OSC_OPENVIBE_FC5, BraintUtil.OSC_OPENVIBE_FC6;
			
		
			
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_AF3, (float) msg.get(3).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_F7, (float) msg.get(4).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_F3, (float) msg.get(5).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_FC5, (float) msg.get(6).doubleValue());
//			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_T7, (float) msg.get(7).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_P7, (float) msg.get(8).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_O1, (float) msg.get(9).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_O2, (float) msg.get(10).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_P8, (float) msg.get(11).doubleValue());
//			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_T8, (float) msg.get(12).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_FC6, (float) msg.get(13).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_F4, (float) msg.get(14).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_F8, (float) msg.get(15).doubleValue());
			raw.addChannelValue(BraintUtil.OSC_OPENVIBE_AF4, (float) msg.get(16).doubleValue());
			
		}

	}

	public EmoEngineAffectiveState getAffState() {
		return affState;
	}

	public EmoEngineExpressivState getExprState() {
		return exprState;
	}
	
	public RawEEGDataSampleContainer getRaw() {
		return raw;
	}


}
