package xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class XMLUtil {
	public static final String DEFAULT_BASE_ADVENTURE_DIR = "baseAdventures/";
	public static final String DEFAULT_SAVES_DIR = "saves/";
	public static final String XML_EXTENSION = ".xml";
	
	/**
	 * This method writes passed in XML to a specified save name
	 * in the default game save directory 
	 * 
	 * @param fileName Name of the file to save
	 * @param xmlContent XML Content to be written
	 * @return True on success, False on Exception/Error
	 */
	public static boolean WriteSave(String fileName, String xmlContent) {
		File file = new File(DEFAULT_SAVES_DIR + fileName + XML_EXTENSION);
		
		if(file.exists()){
			System.out.println("ERROR: Save already exists with this name");
			return false;
		}
		
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.flush();
			pw.print(xmlContent);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error writing to file " + file.toString());
			return false;
		}
		
		return true;
	}
}
