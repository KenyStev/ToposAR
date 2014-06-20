package edu.dhbw.andar.nav;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andar.pub.Global;
import edu.dhbw.andobjviewer.graphics.MiCharacter;
import edu.dhbw.andobjviewer.graphics.Model3D;
import edu.dhbw.andobjviewer.models.Model;

public class Topos extends Model3D {
	
	public boolean active=false;
	public int x=0;

	static String model_str = "flotador1.obj";
	static String pattern_str = "Wing.patt";
	
	public Topos() {
		super(Global.getModel(model_str), pattern_str);
		this.model.scale=15;
	}
	
	@Override
	public void setPatternName(String patt_name){
		switch(x){
		case 0: super.setPatternName("Wing.patt"); break;
		case 1: super.setPatternName("Rampano.patt"); break;
		case 2: super.setPatternName("Hunter.patt"); break;
		case 3: super.setPatternName("Dreamspark.patt"); break;
		}
//		this.model.setScale(15);
//		super.setPatternName(patt_name);
	}
	
	@Override
	public void draw(GL10 gl){
		super.draw(gl);
		if(active){
			this.model.scale=0;
//			switch(x){
//			case 0: this.setPatternName("Wing.patt"); break;
//			case 1: this.setPatternName("Rampano.patt"); break;
//			case 2: this.setPatternName("Hunter.patt"); break;
//			case 3: this.setPatternName("Drespark.patt"); break;
//			}
//			
			this.setPatternName(pattern_str);
//			
			this.active=!this.active;
		}else{
			this.model.scale=15;
		}
		
	}
	
}
