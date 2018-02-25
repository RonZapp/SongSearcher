package TestLibraryDatabaseBuilder;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import LibraryDatabaseBuilder.Driver;
/*
 * Tests MusicLibrary's export to .txt feature
 * @author RonZapp
 * 
 */
class TestLibraryPrint {
	public static final String INPUTFLAG = "-input";
	public static final String OUTPUTFLAG = "-output";
	public static final String ORDERFLAG = "-order";
	public static final String INPUT = "input/lastfm_subset";
	public static final String OUTPUT = "output/results.txt";
	public static final String[] orders = {"artist", "title", "tag"};
	public static final String[] expected = {"expected/libraryByArtist.txt", "expected/libraryByTitle.txt", "expected/libraryByTag.txt"};

	@Test
	/* Tests library export ordered by author from last_fm subset character-for-character against solution */
	void testLibraryPrintByArtist() {
		Driver.libraryPrint(new String[] {INPUTFLAG, INPUT, OUTPUTFLAG, OUTPUT, ORDERFLAG, orders[0]});
		try (BufferedReader resultsReader = new BufferedReader(new InputStreamReader(new FileInputStream(OUTPUT), "UTF-8"));
				BufferedReader expectedReader = new BufferedReader(new InputStreamReader(new FileInputStream(expected[0]), "UTF-8"))) {
			int line = 1;
			String result = resultsReader.readLine();
			String expected = expectedReader.readLine();
			while (result != null && expected != null) {
				if (!result.equals(expected)) {
					fail(String.format("testLibraryPrintByArtist: Error on line %d. Expected: %s, Result: %s", line, expected, result));
				}
				result = resultsReader.readLine();
				expected = expectedReader.readLine();
				line++;
			}
			if (result == null && expected != null) {
				fail("testLibraryPrintByArtist: result has too few entries");
			} else if (result != null && expected == null) {
				fail("testLibraryPrintByArtist: result has too many entries");
			}
		} catch (IOException e) {
			fail(String.format("testLibraryPrintByArtist: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests library export ordered by title from last_fm subset character-for-character against solution */
	void testLibraryPrintByTitle() {
		Driver.libraryPrint(new String[] {INPUTFLAG, INPUT, OUTPUTFLAG, OUTPUT, ORDERFLAG, orders[1]});
		try (BufferedReader resultsReader = 
				new BufferedReader(new InputStreamReader(new FileInputStream(OUTPUT), "UTF-8"));
				BufferedReader expectedReader = 
						new BufferedReader(new InputStreamReader(new FileInputStream(expected[1]), "UTF-8"))) {
			
			int line = 1;
			String result = resultsReader.readLine();
			String expected = expectedReader.readLine();
			while (result != null && expected != null) {
				if (!result.equals(expected)) {
					fail(String.format("testLibraryPrintByTitle: Error on line %d. Expected: %s, Result: %s", line, expected, result));
				}
				result = resultsReader.readLine();
				expected = expectedReader.readLine();
				line++;
			}
			if (result == null && expected != null) {
				fail("testLibraryPrintByTitle: result has too few entries");
			} else if (result != null && expected == null) {
				fail("testLibraryPrintByTitle: result has too many entries");
			}
		} catch (IOException e) {
			fail(String.format("testLibraryPrintByTitle: %s", e.toString()));
		}
	}
	
	@Test
	/* Tests library export ordered by author from last_fm subset character-for-character against solution */
	void testLibraryPrintByTag() {
		Driver.libraryPrint(new String[] {INPUTFLAG, INPUT, OUTPUTFLAG, OUTPUT, ORDERFLAG, orders[2]});
		try (BufferedReader resultsReader = 
				new BufferedReader(new InputStreamReader(new FileInputStream(OUTPUT), "UTF-8"));
				BufferedReader expectedReader = 
						new BufferedReader(new InputStreamReader(new FileInputStream(expected[2]), "UTF-8"))) {
			
			int line = 1;
			String result = resultsReader.readLine();
			String expected = expectedReader.readLine();
			while (result != null && expected != null) {
				if (!result.equals(expected)) {
					fail(String.format("testLibraryPrintByTag: Error on line %d. Expected: %s, Result: %s", line, expected, result));
				}
				result = resultsReader.readLine();
				expected = expectedReader.readLine();
				line++;
			}
			if (result == null && expected != null) {
				fail("testLibraryPrintByTag: result has too few entries");
			} else if (result != null && expected == null) {
				fail("testLibraryPrintByTag: result has too many entries");
			}
		} catch (IOException e) {
			fail(String.format("testLibraryPrintByTag: %s", e.toString()));
		}
	}

}
