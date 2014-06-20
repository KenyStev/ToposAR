package edu.dhbw.andobjviewer.graphics;

import javax.microedition.khronos.opengles.GL10;

import edu.dhbw.andobjviewer.models.Group;
import edu.dhbw.andobjviewer.models.Material;
import edu.dhbw.andobjviewer.models.Model;

public class HP extends Model3D{
	public int maximum=100;
	public int current=100;
	public HP(Model model, String pattern_file) {
		super(model, pattern_file);
		// TODO Auto-generated constructor stub
	}
	
	public void decrese(int x)
	{
		current-=x;
		if(current<0)
			current=0;
	}
	
	public void increse(int x)
	{
		current+=x;
		if(current>maximum)
			current=maximum;
	}
	
	@Override
	public void draw(GL10 gl) {
		super.superDraw(gl);
		
		//gl = (GL10) GLDebugHelper.wrap(gl, GLDebugHelper.CONFIG_CHECK_GL_ERROR, log);
		//do positioning:
		gl.glScalef(10, 10, 40.0f*((float)current/maximum));
		gl.glTranslatef(model.xpos, model.ypos, model.zpos);
		gl.glRotatef(model.xrot, 1, 0, 0);
		gl.glRotatef(model.yrot, 0, 1, 0);
		gl.glRotatef(model.zrot, 0, 0, 1);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		
		//first draw non textured groups
		gl.glDisable(GL10.GL_TEXTURE_2D);
		int cnt = nonTexturedGroups.length;
		for (int i = 0; i < cnt; i++) {
			Group group = nonTexturedGroups[i];
			Material mat = group.getMaterial();
			if(mat != null) {
				gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat.specularlight);
				gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat.ambientlight);
				gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat.diffuselight);
				gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat.shininess);
			}
			gl.glVertexPointer(3,GL10.GL_FLOAT, 0, group.vertices);
	        gl.glNormalPointer(GL10.GL_FLOAT,0, group.normals);	        
	        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, group.vertexCount);
		}
		
		//now we can continue with textured ones
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		cnt = texturedGroups.length;
		for (int i = 0; i < cnt; i++) {
			Group group = texturedGroups[i];
			Material mat = group.getMaterial();
			if(mat != null) {
				gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, mat.specularlight);
				gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, mat.ambientlight);
				gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, mat.diffuselight);
				gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, mat.shininess);
				if(mat.hasTexture()) {
					gl.glTexCoordPointer(2,GL10.GL_FLOAT, 0, group.texcoords);
					gl.glBindTexture(GL10.GL_TEXTURE_2D, textureIDs.get(mat).intValue());
				}
			}
			gl.glVertexPointer(3,GL10.GL_FLOAT, 0, group.vertices);
	        gl.glNormalPointer(GL10.GL_FLOAT,0, group.normals);	        
	        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, group.vertexCount);
		}
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
}
