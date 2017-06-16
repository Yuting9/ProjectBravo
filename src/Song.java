/**
 * Contains the song (Deprecated?)
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.Image;
import java.io.*;
import java.util.ArrayList;


public class Song implements Serializable{
	
	
	public String title;
	public ArrayList<Note> map;
	public Image img;
	public File song;
	public Audio audio;
	
	public Song(String name){
		title = name;
		map = new ArrayList<Note>();
		audio = new Audio("Songs/"+title+"/"+title+".wav");
	}
	/*
	public Song(String name, BeatMap beat, File songFile){
		title = name;
		map = beat.inportMap(title);
		song = songFile;
		audio = new Audio("src/Songs/"+title+"/"+title+".wav");
	}
	*/
	public void addMap(){
		//System.out.println("start of getmap");
		BufferedReader input=null;
		try{
			input= new BufferedReader(new FileReader("Songs/"+title+"/"+title+".songMap"));
		}catch(Exception e){
			e.printStackTrace();
		}
		String line = null;
		String[] strNums;
		int parameters[] = new int[3];
		try {
			while((line=input.readLine()) != null){
				strNums=line.split("\\s");
				for(int i=0;i<3;i++)
				{
					parameters[i]=Integer.parseInt(strNums[i]);
				}
				//System.out.println(parameters);
				map.add(new Note(parameters[0],parameters[1],parameters[2]));
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	public void export(){
		FileOutputStream fileOut;
		ObjectOutputStream outStream;
		try{
			fileOut = new FileOutputStream("Songs/"+title+".songMap");
			outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String toString(){
		return this.title;
	}

}
