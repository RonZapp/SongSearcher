package TestLibraryDatabaseBuilder;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import LibraryDatabaseBuilder.DatabaseCommunicater;
import LibraryDatabaseBuilder.Driver;
import LibraryDatabaseBuilder.MusicLibrary;
import LibraryDatabaseBuilder.Song;
/*
 * Tests DatabaseCommunicator class
 * @author RonZapp
 */
class TestDatabaseCommunicator {

	@Test
	/* Tests that connection can be opened */
	void testOpenConnection() {
		DatabaseCommunicater dc = new DatabaseCommunicater();
		assertTrue(dc.openConnection());
		dc.closeConnection();
	}
	
	@Test
	/* Tests that 'songs' table can be created */
	void testCreateSongsTable() {
		DatabaseCommunicater dc = new DatabaseCommunicater();
		if (dc.openConnection()) {
			try {
				if (!dc.createSongsTable()) {
					fail("testCreateSongsTable: method returned failure");
				} else {
					assertTrue(dc.tableExists("songs"));
				}
			} finally {
				dc.closeConnection();
			}
		} else {
			fail("testCreateSongsTable: couldn't open connection to server");
		}
	}
	
	@Test
	/* Tests that 'song_tag_maps' table can be created */
	void testCreateTagsTable() {
		DatabaseCommunicater dc = new DatabaseCommunicater();
		if (dc.openConnection()) {
			try {
				if (!dc.createTagsTable()) {
					fail("testCreateTagsTable: method returned failure");
				} else {
					assertTrue(dc.tableExists("song_tag_maps"));
				}
			} finally {
				dc.closeConnection();
			}
		} else {
			fail("testCreateTagsTable: couldn't open connection to server");
		}
	}
	
	@Test
	/* Tests that 'song_similar_maps' table can be created */
	void testCreateSimilarsTable() {
		DatabaseCommunicater dc = new DatabaseCommunicater();
		if (dc.openConnection()) {
			try {
				if (!dc.createSimilarsTable()) {
					fail("testCreateTagsTable: method returned failure");
				} else {
					assertTrue(dc.tableExists("song_similar_maps"));
				}
			} finally {
				dc.closeConnection();
			}
		} else {
			fail("testCreateSimilarsTable: couldn't open connection to server");
		}
	}
	
	@Test
	/* Tests that a song can be added to the database */
	void testAddSong() {
		MusicLibrary library = Driver.getNewLibrary();
		Song song = library.getSongById("TRABBBV128F42967D7");
		DatabaseCommunicater dc = new DatabaseCommunicater();
		if (dc.openConnection()) {
			try {
				assertTrue(dc.addSong(song, library));
			} finally {
				dc.closeConnection();
			}
		} else {
			fail("testAddSong: couldn't open connection to server");
		}
	}
	
	@Test
	/* Tests that an entire MusicLibrary build from the lastfm_subset can be added to the database */
	void testAddLibrary() {
		MusicLibrary library = Driver.getNewLibrary();
		DatabaseCommunicater dc = new DatabaseCommunicater();
		if (dc.openConnection() ) {
			try {
				assertTrue(dc.addLibrary(library));
			} finally {
				dc.closeConnection();
			}
		} else {
			fail("testAddLibrary: couldn't open connection to server");
		}
	}
	
	/*
	 * Helper function generates a new song for tests to use
	 * Not currently in use. Was used by old version of tests
	 */
	private static Song generateSong() {
		String s = 
				"{\"artist\": \"sampleArtist\", \"title\": \"sampleTitle\", \"track_id\": \"sampleTrack_id"
				+ ((int) (Math.random() * 10000) / 10)
				+ "\", \"similars\": [[\"sampleSimilar1\", 1], [\"sampleSimilar2\", 1]], "
				+ "\"tags\": [[\"sampleTag1\", 1], [\"sampleTag2\", 1]]}";
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(s);
		} catch (ParseException e) {
			fail("testAddSong: could not parse json");
		}
		return new Song(obj);
	}
}
