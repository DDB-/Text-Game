package util;

public class LineParser {
	
	public static final String DEFAULT_DELIMETER = " ";
	
	public static String[] parseLine(String line){
		return parseLine(line, DEFAULT_DELIMETER);
	}
	
	public static String[] parseLine(String line, String delimeter) {
		return line.split(delimeter);
	}
}
