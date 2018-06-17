package com.raaxxo.mph.installation.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * This class contains the logic to install the files required to run the MPH server in the JBoss folder
 */
public class InstallationUtility {
	
	String inputPath;

	static InstallationUtility anInstance = null;
	
	/**
	 * @return the unique instance
	 */
	static public InstallationUtility getInstance() {
		
		if ( anInstance == null ) {
			anInstance = new InstallationUtility();
		}
		
		return anInstance;
	}
	
	/**
	 * @param thePath the string representing the path of the JBoss server folder
	 * @throws IllegalArgumentException if the path given as parameter does not correspond to a JBoss server folder
	 */
	public void setInputPath(String thePath) throws IllegalArgumentException {
		
		System.out.println("Trying to set the input path to: " + thePath);
		

		if (new File(thePath).getName().equals("bin") == false) {
			throw new IllegalArgumentException("The directory selected is wrong. Please retry.");
		}
		
		if (new File(thePath+"/run.bat").exists() == false || new File(thePath+"/run.sh").exists() == false) {
			throw new IllegalArgumentException("The directory doesn't contain essential files. Please change directory or reinstall JBoss");
		}
		
		inputPath = thePath;
	}
	
	/**
	 * @return the path taken from the GUI
	 */
	public String getInputPath() {
		return new String(inputPath);
	}
	
	/**
	 * Install the required files into the path taken from the GUI
	 * @throws InstallationErrorException if an error occurs during the installation
	 * @throws FileNotFoundException if a required file or file path is not found
	 */
	public void runInstallation() throws InstallationErrorException, FileNotFoundException {
		
		
		// Destination directory
		//	File dir = new File(getInputPath());
		ArrayList<File> filePathList = new ArrayList<File>();

		File sourceDir = null;		
		//System.out.println(getResourcePath("deploy"));
		
		System.out.println("data/deploy files");
		
		sourceDir = new File("data/deploy");
		filePathList.clear();
		for (String aFilePath: sourceDir.list()) {
			
			File aFile = new File(aFilePath);
			File aFileS = new File("data/deploy/"+aFile.getName());
			filePathList.add(aFileS);
			System.out.println("detected file: " + aFileS.getAbsolutePath());
		}
		
		for (File aFile: filePathList) {
			copyfile(aFile.getAbsolutePath(), getInputPath()+"/../server/default/deploy/"+aFile.getName());
		}
		
		System.out.println("data/lib files");
		sourceDir = new File("data/lib");
		filePathList.clear();
		for (String aFilePath: sourceDir.list()) {
			
			File aFile = new File(aFilePath);
			File aFileS = new File("data/lib/"+aFile.getName());
			filePathList.add(aFileS);
			System.out.println("detected file: " + aFileS.getAbsolutePath());
		}
		
		for (File aFile: filePathList) {
			copyfile(aFile.getAbsolutePath(), getInputPath()+"/../server/default/lib/"+aFile.getName());
		}
		
		
		/*
		
		
		
		sourceDir = new File(getResourcePath("deploy"));//new File(getClass().getClassLoader().getResource("deploy").getPath());
		filePathList.clear();
		for (String aFilePath: sourceDir.list()) {
			
			File aFile = new File(aFilePath);
			File aFileS = new File(getResourcePath("deploy/"+aFile.getName())); //new File(getClass().getClassLoader().getResource("deploy/"+aFile.getName()).getPath());
			filePathList.add(aFileS);
			System.out.println("detected file: " + aFileS.getAbsolutePath());
		}
		
		for (File aFile: filePathList) {
			copyfile(aFile.getAbsolutePath(), getInputPath()+"/../server/default/deploy/"+aFile.getName());
		}
		
		System.out.println("data/lib files");
		//sourceDir = new File(getClass().getClassLoader().getResource("lib").getPath());
		sourceDir = new File(getResourcePath("lib"));
		filePathList.clear();
		for (String aFilePath: sourceDir.list()) {
			
			File aFile = new File(aFilePath);
			File aFileS =  new File(getResourcePath("lib/"+aFile.getName()));//new File(getClass().getClassLoader().getResource("lib/"+aFile.getName()).getPath());
			filePathList.add(aFileS);
			System.out.println("detected file: " + aFileS.getAbsolutePath());
		}
		
		for (File aFile: filePathList) {
			copyfile(aFile.getAbsolutePath(), getInputPath()+"/../server/default/lib/"+aFile.getName());
		}


*/
	}
	
	 /**
	  * Copies the source file into the destination
	 * @param srFile the source file
	 * @param dtFile the destination
	 * @throws FileNotFoundException if a required file or file path is not found
	 * @throws InstallationErrorException if an error occurs during the installation
	 */
	private void copyfile(String srFile, String dtFile) throws FileNotFoundException, InstallationErrorException {
		  
		 try{
			 File f1 = new File(srFile);
			 File f2 = new File(dtFile);
			 
			 if (f1.exists() == false) {
				 f2.createNewFile();
			 }
			 
			 
			 if (f2.exists() == false) {
				 f2.createNewFile();
			 }
			 
			 InputStream in = new FileInputStream(f1);

			 //For Append the file.
			 //  OutputStream out = new FileOutputStream(f2,true);

			 //For Overwrite the file.
			 OutputStream out = new FileOutputStream(f2);

			 byte[] buf = new byte[1024];
			 int len;
			 while ((len = in.read(buf)) > 0){
				 out.write(buf, 0, len);
			 }
			 in.close();
			 out.close();
			 System.out.println("File copied the specified directory (" +dtFile + ").");
		 }
		 catch(FileNotFoundException ex){
			 throw new FileNotFoundException(ex.getMessage() + " in the specified directory (" +dtFile + ")");
		 }
		 catch(IOException e){
			 throw new InstallationErrorException("The installation of MPH was not completed. Please retry.");
		 }
	 }

	String getResourcePath(String theDirectory) {
		
		String PROGRAM_DIRECTORY;
		
		try {
		    //Attempt to get the path of the actual JAR file, because the working directory is frequently not where the file is.
		    //Example: file:/D:/all/Java/TitanWaterworks/TitanWaterworks-en.jar!/TitanWaterworks.class
		    //Another example: /D:/all/Java/TitanWaterworks/TitanWaterworks.class
		    PROGRAM_DIRECTORY = getClass().getClassLoader().getResource(theDirectory).getPath(); // Gets the path of the class or jar.

		    System.out.println("STRING TO BE EDITED: " + PROGRAM_DIRECTORY);
		    
		    //Find the last ! and cut it off at that location. If this isn't being run from a jar, there is no !, so it'll cause an exception, which is fine.
		    try {
		    	PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(0, PROGRAM_DIRECTORY.lastIndexOf('!'));
		    	PROGRAM_DIRECTORY = PROGRAM_DIRECTORY + "/" + theDirectory;
		    } catch (Exception e) { }
		    
		    

		    //Find the last / and cut it off at that location.
		    //PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(0, PROGRAM_DIRECTORY.lastIndexOf('/') + 1);
		    //If it starts with /, cut it off.
		 //   if (PROGRAM_DIRECTORY.startsWith("/")) PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(1, PROGRAM_DIRECTORY.length());
		    //If it starts with file:/, cut that off, too.
		    if (PROGRAM_DIRECTORY.startsWith("file:/")) PROGRAM_DIRECTORY = PROGRAM_DIRECTORY.substring(5, PROGRAM_DIRECTORY.length());
		} catch (Exception e) {
		    PROGRAM_DIRECTORY = ""; //Current working directory instead.
		}
		
	    System.out.println("STRING TO BE RETURNED: " + PROGRAM_DIRECTORY);
		return PROGRAM_DIRECTORY;
	}
	
}
