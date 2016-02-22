package Braint.emoEngine;

public class EmoEngineExpressivState {
	
	
	/*
	 * Lower / Upper Face Actions
	 * From the EDK DLL, however they are strings here
	 */
	
	 /*
          EXP_NEUTRAL     = 0x0001,
          EXP_BLINK       = 0x0002,
          EXP_WINK_LEFT   = 0x0004,
          EXP_WINK_RIGHT  = 0x0008,
          EXP_HORIEYE     = 0x0010,
          EXP_EYEBROW     = 0x0020,
          EXP_FURROW      = 0x0040,
          EXP_SMILE       = 0x0080,
          EXP_CLENCH      = 0x0100,
          EXP_LAUGH       = 0x0200,
          EXP_SMIRK_LEFT  = 0x0400,
          EXP_SMIRK_RIGHT = 0x0800
      */
	
	
	public String lowerFaceAction;
    public float lowerFaceActionPower;

    public String upperFaceAction;
    public float upperFaceActionPower;

    // EYES
    public float xEyelidState;
    public float yEyelidState;
    public float posXEyeLocation;
    public float posYEyeLocation;
    public boolean isBlink;
    public boolean areEyesOpen;
    public boolean isLeftWink;
    public boolean isRightWink;
    public boolean isLookingLeft;
    public boolean isLookingRight;
    public boolean isLookingDown;
    public boolean isLookingUp;

    public float emotivTimeStamp;
    
    
    

}
