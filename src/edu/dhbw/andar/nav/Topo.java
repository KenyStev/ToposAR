package edu.dhbw.andar.nav;

import javax.microedition.khronos.opengles.GL10;

import android.R.integer;

import edu.dhbw.andar.pub.Global;
import edu.dhbw.andobjviewer.graphics.MiCharacter;
import edu.dhbw.andobjviewer.graphics.Model3D;
import edu.dhbw.andobjviewer.models.Model;

public class Topo extends Model3D {
	
	public boolean active=false;
	public boolean toch = false;
	int contDesactive=10;
	int contActive=10;
	public int tochs=0;
	//static int x = 15;

	static String model_str = "flotador1.obj";
	public static String[] patterns = {"1.patt","2.patt","3.patt","4.patt"};
	//public static String[] patterns = {"Wing.patt","Rampano.patt","Dreamspark.patt","Hunter.patt"};
	
	public Topo(int indice) {
		super(Global.getModel(model_str), patterns[indice]); //Integer.toString(indice+1)+".patt"
		this.model.scale=0;
		//this.contActive=(indice+10)/indice;
	}
	
	@Override
	public void draw(GL10 gl){
		super.draw(gl);
		if(active){
			
			if(toch){
				if(this.model.scale>12){
					this.tochs++;
				}
				this.model.setScale(-3f);
			}
			this.contActive--;
			if(this.contActive<0 || this.model.scale<0){
				this.contDesactive=10;
				this.active=!this.active;
				this.model.scale=0;
			}
		}else{
			this.contDesactive--;
			if(this.contDesactive<0){
				this.contActive=10;
				this.active=!this.active;
				this.toch=false;
				this.model.scale=15;
			}
		}
	}
}
