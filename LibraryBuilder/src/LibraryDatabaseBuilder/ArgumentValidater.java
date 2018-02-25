package LibraryDatabaseBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Checks and validates input for library building and exporting
 * Read libraryprinting_instructions.txt for more information
 * @author RonZapp
 * 
 */
public class ArgumentValidater {
	private static final String[] flags = {"-input","-output","-order"};
	private static final String[] goodOrders = {"artist", "title", "tag"};
	private static final String GOODOUTPUTEXTENSION = ".txt";
	private static final char DIRECTORYSEPARATOR = '/';	// Change to '\' or '/' depending on unix or windows system
	
	private String[] args;
	private String[] values;
	private boolean inputValidated;
	private boolean outputValidated;
	private boolean orderValidated;
	
	/*
	 * Constructor
	 * @param args arguments to be checked
	 */
	public ArgumentValidater(String[] args) {
		this.args = args;
		this.values = new String[flags.length];
		this.inputValidated = false;
		this.outputValidated = false;
		this.orderValidated = false;
	}
	
	/*
	 * Validates that all flags were found and have valid value once each
	 * @return true if arguments were good, false otherwise
	 */
	public void validate() {
		for (int j = 0; j < args.length - 1; j++) {	// -1 because flag can't be last argument
			for (int i = 0; i < flags.length; i++) {
				if (args[j].equals(flags[i])) {
					switch (i) {
						case 0 :
							if (validateInput(args[j + 1])) {
								if (inputValidated == false) { 	
									inputValidated = true;
									values[i] = args[j + 1];
								}
							}
							break;
						case 1 :
							if (validateOutput(args[j + 1])) {
								if (outputValidated == false) { 
									outputValidated = true;
									values[i] = args[j + 1];
								}
							}
							break;
						case 2 :
							if (validateOrder(args[j + 1])) {
								if (orderValidated == false) { 	
									orderValidated = true;
									values[i] = args[j + 1];
								}
							}
							break;
					}
				}
			}
		}
	}
	
	/* Returns input if input has been validated, null otherwise */
	public String getInput() {
		if (inputValidated) {
			return values[0];
		} else {
			return null;
		}
	}
	
	/* Returns input if output has been validated, null otherwise */
	public String getOutput() {
		if (outputValidated) {
			return values[1];
		} else {
			return null;
		}
	}
	
	/* Returns order if order has been validated, null otherwise */
	public String getOrder() {
		if (orderValidated) {
			return values[2];
		} else {
			return null;
		}
	}
	
	/*
	 * Helper method validates argument after input flag is valid
	 * @param arg index of argument following input flag
	 */
	private boolean validateInput(String arg) {
		Path path = Paths.get(arg);
		if (Files.exists(path) && Files.isDirectory(path)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Helper method validates argument after output flag is valid
	 * @param arg: index of argument following output flag
	 */
	private boolean validateOutput(String arg) {
		Path path = Paths.get(arg);
		if (arg.endsWith(GOODOUTPUTEXTENSION)) {
			if (Files.exists(path)) {
				if (Files.isWritable(path)) {
					return true;
				}
			} else {
				String directorySubstring = arg.substring(0, arg.lastIndexOf(DIRECTORYSEPARATOR));
				Path directory = Paths.get(directorySubstring);
				if (Files.exists(directory) && Files.isDirectory(directory)) {
					try {
						Files.createFile(path);
					} catch (IOException e) {
						return false;
					}
					if (Files.isWritable(path)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/*
	 * Helper function validates argument after order flag is valid
	 * input: index of argument following order flag
	 */
	private boolean validateOrder(String arg) {
		for (String s: goodOrders) {
			if (s.equals(arg)) {
				return true;
			}
		}
		return false;
	}
}
