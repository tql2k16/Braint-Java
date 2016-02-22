package Braint.drawMethods.agents;


import Braint.drawMethods.IProcessingDrawable;
import Braint.main.BraintMainApplet;
import Braint.settings.BigSettings;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by Ygdrasil on 18.02.2016.
 */
public class FancyBraintAgent implements IProcessingDrawable {


    public PVector p, pOld;
    public float stepSize, angle;
    public boolean isOutside = false;
    public Integer electrode = 0;
    public Integer electrodeRGB = 0;

    private FancyBraintAgentDraw fancyBraintAgent;

    public FancyBraintAgent(FancyBraintAgentDraw bad, Integer emotivElectrode) {

        fancyBraintAgent = bad;
        electrode = emotivElectrode;
        //setRGB per Electrode
        if (electrode == 1){
            electrodeRGB = 0xffa6cee3;
        } else if (electrode == 2){
            electrodeRGB = 0xff1f78b4;
        } else if (electrode == 3){
            electrodeRGB = 0xffb2df8a;
        } else if (electrode == 4){
            electrodeRGB = 0xff33a02c;
        } else if (electrode == 5){
            electrodeRGB = 0xfffb9a99;
        } else if (electrode == 6){
            electrodeRGB = 0xffe31a1c;
        } else if (electrode == 7){
            electrodeRGB = 0xfffdbf6f;
        } else if (electrode == 8){
            electrodeRGB = 0xffff7f00;
        } else if (electrode == 9){
            electrodeRGB = 0xffcab2d6;
        } else if (electrode == 10){
            electrodeRGB = 0xff6a3d9a;
        } else if (electrode == 11){
            electrodeRGB = 0xffffff99;
        } else if (electrode == 12) {
            electrodeRGB = 0xffb15928;
        }


        // stepSize = 0.1f;
    }

    @Override
    public void updateAndDraw(BraintMainApplet applet) {

        if(fancyBraintAgent == null)
            System.out.println("BRAINT DRAW IS NULL");


        if(p == null)
            System.out.println("p DRAW IS NULL");
        angle = applet.noise(p.x / fancyBraintAgent.getNoiseScale(), p.y / fancyBraintAgent.getNoiseScale())
                * fancyBraintAgent.getNoiseStrength();
        p.x += PApplet.cos(angle) * stepSize;
        p.y += PApplet.sin(angle) * stepSize;

        if (p.x < -10)
            isOutside = true;
        else if (p.x > applet.width + 10)
            isOutside = true;
        else if (p.y < -10)
            isOutside = true;
        else if (p.y > applet.height + 10)
            isOutside = true;

        if (isOutside) {

            p.x = applet.random(applet.width);
            p.y = applet.random(applet.height);
            pOld.set(p);

        }



        float strokeWidth = BigSettings.instance().agentStrokeWidth;
        applet.strokeWeight(strokeWidth * fancyBraintAgent.getStrokeWidthScale());
        applet.stroke(this.electrodeRGB);
        applet.line(pOld.x, pOld.y, p.x, p.y);
        pOld.set(p);
        isOutside = false;

    }

    @Override
    public void setupDrawing(BraintMainApplet applet) {
        p = new PVector(applet.random(applet.width), applet.random(applet.height));
        pOld = new PVector(p.x, p.y);
        stepSize = applet.random(1, 5);

    }

}
