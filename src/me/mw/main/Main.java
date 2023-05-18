package me.mw.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import me.mw.gui.GUI;

/**
 * 
 * @author Matthew Whitney
 *
 */
public class Main {
	
	// TTS Version 1.2
	
	/**
	 * <ul>
	 * <p>	<b><i>main</i></b>
	 * <p>	<code>public static void main(String[] args)</code>
	 * <p>	The Main method for TTS. 
	 * @param args - the arguments.
	 * </ul>
	 */
	public static void main(String[] args) {
		/** Starts the GUI. */
		GUI.main(args);
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>speak</i></b>
	 * <p>	<code>public static void speak()</code>
	 * <p>	Takes the text from the text area and plays it out of the speakers.
	 * </ul>
	 */
	public static void speak(String text) {
		text = transformToRawString(text);
		
		String fileName = System.getProperty("user.home") + "/AppData/Local/Temp/TempTTS" + (int) ((Math.random() * 999) + 1) + ".vbs";
		while(new File(fileName).exists()) {
			fileName = System.getProperty("user.home") + "/AppData/Local/Temp/TempTTS" + (int) ((Math.random() * 999) + 1) + ".vbs";
		}
		File tempVBSFile = new File(fileName);
		try {
			tempVBSFile.createNewFile();
			FileWriter writer = new FileWriter(tempVBSFile);
			writer.write("Dim text, sapi\r\n" + 
						 "text=\""+text+"\"\r\n" + 
						 "Set sapi=Createobject(\"sapi.spvoice\")\r\n" + 
						 "sapi.Speak text");
			writer.close();
			writer = null;
			tempVBSFile.createNewFile();
			Runtime.getRuntime().exec(new String[] {
				"wscript.exe",
				fileName
			});
			/*
			 *	This is the old code for running the VBS file.
			 *	However, it did not work if there were spaces in the path to the file.
			 *	The new code fixes this issue.
			 *
			 *	Runtime.getRuntime().exec("wscript "+fileName);
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
		tempVBSFile.deleteOnExit();
		fileName = null;
		tempVBSFile = null;
	}
	
	/**
	 * <ul>
	 * <p>	<b><i>transformToRawString</i></b>
	 * <p>	<code>public String transformToRawString(String originalString)</code>
	 * <p>	Transforms the given <tt>String</tt>, so it no longer contains line breaks and indents.
	 * 		This is to prevent syntax errors with the VBScript file.
	 * @param originalString - the <tt>String</tt> to transform.
	 * @return the transformed <tt>String</tt>
	 * </ul>
	 */
	public static String transformToRawString(String originalString) {
		originalString = originalString.replaceAll("\n", " ").replaceAll("\t", " ").replaceAll("\r", " ").replaceAll("\"", "");
		return originalString;
	}
}
