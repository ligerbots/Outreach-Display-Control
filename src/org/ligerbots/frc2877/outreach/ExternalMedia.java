package org.ligerbots.frc2877.outreach;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class ExternalMedia {

	/** This method finds external media devices with the proper configuration **/
	public static ExternalMedia findMedia() {
		//Get mounted devices
		File[] mounted = new File("/mount").listFiles();
		//Check each device
		for(File device : mounted) {
			//Get the configuration file
			File configuration = new File(device, "ligerbots.ini");
			//Check if the file exists
			if(configuration.exists()) {
				//Create and return new ExternalMedia object
				return new ExternalMedia(configuration);
			}
		}
		//No external device found
		return null;
	}
	
	/** Configuration **/
	private Properties configuration;
	
	/** Error **/
	public boolean somethingWentWrong = false;
	public Exception thatThingThatWentWrong = null;
	
	/** Processes **/
	private ArrayList<Process> processes = new ArrayList<Process>();
	
	/** Constructor **/
	public ExternalMedia(File cfile) {
		//Catch any fuck-ups in the configuration
		try {
			//Create a new configuration
			configuration = new Properties();
			//Load the thing
			configuration.load(new FileInputStream(cfile));
		} catch(Exception e) {
			//Something went wrong
			somethingWentWrong = true;
			thatThingThatWentWrong = e;
		}
	}
	
	/** Shows the main menu **/
	public void showMainMenu() {
		//Kill processes
		killAll();
		//Catch errors caused by process spawning
		try {
			//Create processes
			Process mainmenu = Runtime.getRuntime().exec("omxplayer " + configuration.getProperty("mainmenu"));
			//Save process
			processes.add(mainmenu);
		} catch(Exception e) {
			//Bad
			somethingWentWrong = true;
			thatThingThatWentWrong = e;
		}		
	}
	
	/** Shows one of the videos **/
	public void showVideo(int number) {
		//Kill processes
		killAll();
		//Catch errors caused by process spawning
		try {
			//Create processes
			Process preview = Runtime.getRuntime().exec("fbi " + configuration.getProperty("preview" + number));
			Process video = Runtime.getRuntime().exec("omxplayer " + configuration.getProperty("video" + number));
			//Save processes
			processes.add(preview);
			processes.add(video);
		} catch(Exception e) {
			//Bad
			somethingWentWrong = true;
			thatThingThatWentWrong = e;
		}
	}
	
	/** Kills everything **/
	public void killAll() {
		//Iterate through each process
		for(Process p : processes) {
			//Kill the process
			p.destroy();
			//Remove from the list
			processes.remove(p);
		}
	}
}
