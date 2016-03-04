package org.ligerbots.frc2877.outreach;

public class FBI {

	/** Process Handle **/
	private Process p;
	
	/** Displays an image to the screen using the FBI package **/
	public FBI(String file) {
		//Try to create the process
		try {
			//Launch process
			p = Runtime.getRuntime().exec("fbi " + file);
		} catch(Exception e) {
			//Failed to create process
			System.out.println("Warning: Failed to display picture \"" + file + "\"");
		}
	}
	
	/** Removes the picture **/
	public void close() {
		//Kill the process
		p.destroy();
	}
}
