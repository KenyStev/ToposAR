package edu.dhbw.andobjviewer.graphics;

import javax.microedition.khronos.opengles.GL10;

import android.view.MotionEvent;

import edu.dhbw.andar.pub.Global;
import edu.dhbw.andobjviewer.models.Model;

public class MiCharacter extends Model3D{
	public Model3D selected;
	public HP hp;
	
	public MiCharacter(Model model, String pattern_file) {
		super(model, pattern_file);
		this.selected = new Model3D(Global.getModel("seleccion.obj"),pattern_file);
		this.hp = new HP(Global.getModel("hp.obj"),pattern_file);
		hp.model.setYpos(5f);
		
		try
		{
			Global.artoolkit.registerARObject(selected);
			Global.artoolkit.registerARObject(hp);
			Global.artoolkit.registerARObject(this);
		}catch(Exception e)
		{
			
		}
	}
	
	@Override
	public void draw(GL10 gl) {
		super.draw(gl);
		selected.model.setYrot(10.0f);
//		Global.explosion.model.setScale(0.02f);
	}
	

}
