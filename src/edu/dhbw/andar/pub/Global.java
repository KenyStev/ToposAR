package edu.dhbw.andar.pub;

import java.io.BufferedReader;

import android.content.res.AssetManager;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.nav.Topo;
import edu.dhbw.andobjviewer.models.Model;
import edu.dhbw.andobjviewer.parser.ObjParser;
import edu.dhbw.andobjviewer.util.AssetsFileUtil;
import edu.dhbw.andobjviewer.util.BaseFileUtil;

public class Global {
	public static String player_action = "nada";
	
	public static ARToolkit artoolkit;
	public static AssetManager am;
	
	public static boolean turno_jugador_1=true;	
	
	//public static Topo topo1,topo2;
	public static Topo[] topos = new Topo[4];
	//public static Topo[] topos = new Topo[15];
	
	private static int xGlobalTochs = 0;
	
	public static Model getModel(String obj_path)
	{
		BaseFileUtil fileUtil = new AssetsFileUtil(am);
		fileUtil.setBaseFolder("models/");
		Model model = null;
		ObjParser parser = new ObjParser(fileUtil);
		if(fileUtil != null) {
			BufferedReader fileReader = fileUtil.getReaderFromName(obj_path);
			if(fileReader != null) {
				try {
					model = parser.parse("Model", fileReader);
					model.scale=4;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return model;
	}
	
	public static void setXTochs(){
		for(int i = 0; i < Global.topos.length; i++){
			xGlobalTochs+=Global.topos[i].tochs;
		}
	}
	
	
	
}
