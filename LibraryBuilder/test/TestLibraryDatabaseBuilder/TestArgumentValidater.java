package TestLibraryDatabaseBuilder;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import LibraryDatabaseBuilder.ArgumentValidater;

/*
 * Test class focusing on ArgumentValidater class
 * @author RonZapp
 * 
 */
class TestArgumentValidater {
	private static final String INPUTFLAG = "-input";
	private static final String OUTPUTFLAG = "-output";
	private static final String ORDERFLAG = "-order";
	private static final String GOODINPUT = "input";
	private static final String GOODOUTPUT = "output/results.txt";
	private static final String GOODORDER = "artist";
	private static final String GOODORDER2 = "title";
	
	@Test
	/* Tests that validater rejects bad input value */
	void testBadInput() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] 
					{INPUTFLAG, "bad/input/path", OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, GOODORDER});
			if (av.getInput() != null) {
				fail("testBadInput: InputValidator validated bad input");
			}
		} catch (Exception e) {
			fail(String.format("testBadInput: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests that validater validates and returns correct value for input */
	void testGoodInput() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] 
					{INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, GOODORDER});
			av.validate();
			if (av.getInput() == null) {
				fail("testGoodInput: Failed to validate good input");
			}
			if (!av.getInput().equals(GOODINPUT)) {
				fail("testGoodInput: returned incorrect input value");
			}
		} catch (Exception e) {
			fail(String.format("testGoodInput: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests that validater rejects bad output value */
	void testBadOutput() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] 
					{INPUTFLAG, GOODINPUT, OUTPUTFLAG, "bad/output/path.txt", ORDERFLAG, GOODORDER});
			av.validate();
			if (av.getOutput() != null) {
				fail("testBadOutput: validated bad output");
			}
		} catch (Exception e) {
			fail(String.format("testBadOutput: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests that validater validates and returns correct output value when output file already exists */
	void testGoodOutputFileExists() {
		try {
			Path path = Paths.get(GOODOUTPUT);
			if (!Files.exists(path)) {
				Files.createFile(path);	// Create file if it does not exist
			}
			if (!Files.isReadable(path) || !Files.isWritable(path)) {
				fail("testGoodOutputFileExists: Could not create good output file");
			}
			
			ArgumentValidater av = new ArgumentValidater(new String[] 
					{INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, GOODORDER});
			av.validate();
			if (av.getOutput() == null) {
				fail("testGoodOutputFileExists: failed to validate good output");
			}
			if (!av.getOutput().equals(GOODOUTPUT)) {
				fail("testGoodOutputFileExists: returned incorrect output value");
			}
		} catch (Exception e) {
			fail(String.format("testGoodOutputFileExists: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests that validater validates and returns correct output value when output does NOT already exist */
	void testGoodOutputFileDoesntExist() {
		try {
			Path path = Paths.get(GOODOUTPUT);
			if (Files.exists(path)) {
				Files.delete(path); // Delete file if it does exist
			}

			ArgumentValidater av = new ArgumentValidater(new String[] 
					{INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, GOODORDER});
			av.validate();
			if (av.getOutput() == null) {
				fail("testGoodOutputFileDoesntExist: failed to validate good output");
			}
			if (!av.getOutput().equals(GOODOUTPUT)) {
				fail("testGoodOutputFileDoesntExist: returned incorrect output value");
			}
		} catch (Exception e) {
			fail(String.format("testGoodOutputFileDoesntExist: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests that validater rejects bad order value */
	void testBadOrder() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] 
					{INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, "badOrder"});
			av.validate();
			if (av.getOrder() != null) {
				fail("testBadOrder: validated bad order");
			}
		} catch (Exception e) {
			fail(String.format("testBadOrder: %s", e.toString()));
		}
	}
	
	@Test
	/* Test that validater validates and returns good order value */
	void testGoodOrder() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] {INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, GOODORDER});
			av.validate();
			if (av.getOrder() == null) {
				fail("testGoodOrder: failed to validate good order");
			}
			if (!av.getOrder().equals(GOODORDER)) {
				fail("testGoodOrder: returned incorrect order value");
			}
		} catch (Exception e) {
			fail(String.format("testGoodOrder: %s", e.toString()));
		}
	}
	
	@Test
	/* 
	 * Tests that validater validates and returns correct values when it finds more than one valid flag
	 */
	void testMultipleFlagsOneGoodValue() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] {INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, ORDERFLAG, GOODORDER});
			av.validate();
			if (av.getInput() == null || av.getOrder() == null || av.getOrder() == null) {
				fail("testMultipleFlagsOneGoodValue: failed to validate");
			}
			if (!av.getOrder().equals(GOODORDER)) {
				fail("testMultipleFlagsOneGoodValue: returned incorrect value for duplicate flag");
			}
		} catch (Exception e) {
			fail(String.format("testMultipleFlagsOneGoodValue: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests that validater rejects arguments when it finds more than one valid set of data */
	void testMultipleFlagsMultipleGoodValues() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] {
					INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG, GOODORDER, ORDERFLAG, GOODORDER2});
			av.validate();
			if (av.getInput() == null || av.getOrder() == null || av.getOrder() == null) {
				fail("testMultipleFlagsMultipleGoodValues: failed to validate");
			}
			if (!av.getOrder().equals(GOODORDER)) {
				fail("testMultipleFlagsMultipleGoodValues: returned incorrect value for duplicate flag");
			}
		} catch (Exception e) {
			fail(String.format("testMultipleMultipleOneGoodValues: %s", e.toString()));
		}
	}
	
	@Test
	/* 
	 * Tests that validater rejects argument when it finds all three flags but one flag is final argument
	 * This should reject because if flag is final argument there is no value that can follow flag
	 */
	void testFinalArgIsFlagNoGoodValue() {
		try {
			ArgumentValidater av = new ArgumentValidater(new String[] {INPUTFLAG, GOODINPUT, OUTPUTFLAG, GOODOUTPUT, ORDERFLAG});
			av.validate();
			if (av.getOrder() != null) {
				fail("testFinalArgIsFlagNoGoodValue: validated without value for flag");
			}
		} catch (Exception e) {
			fail(String.format("testFinalArgIsBadNoGoodValue: %s", e.toString()));
		}
	}
	
}
