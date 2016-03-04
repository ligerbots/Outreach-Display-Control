package org.ligerbots.frc2877.outreach;

public class Main {

	public static void main(String[] args) {
		//Show the loading image
		FBI loading = new FBI("loading.png");
		//Find external media
		ExternalMedia media = ExternalMedia.findMedia();
		//Check if no media found
		if(media == null) {
			//Remove loading image
			loading.close();
			//Show no media error
			new FBI("nomedia.png");
			//Hang processing
			hang();
		}
		//Main loop
		while(true) {
			
			//Show the 
			
		}
	}
	
	/** Hangs the process **/
	private static void hang() {
		//Sleep for a reasonably long time
		try { Thread.sleep(Long.MAX_VALUE); } catch(Exception e) {}
	}
}
