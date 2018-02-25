package LibraryDatabaseBuilder;
import java.util.HashMap;
import java.util.HashSet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Organizes a music library of Songs
 * @author RonZapp
 *
 */
public class MusicLibrarySkinny {
	private HashMap<String, Song> songsById;
	
	/* Basic constructor */
	public MusicLibrarySkinny() {
		songsById = new HashMap<String, Song>(1200000);
	}

	/*
	 * Adds song to MusicLibrary
	 * @param song
	 */
	public void addSong(Song song) {
		songsById.put(song.getTrackId(), song);
	}
	
	/*
	 * Takes as input HashSet of JSONs in String form, creates songs from jsonStrings, and adds songs to library
	 * @param jsonStrings
	 */
	public void addSong(HashSet<String> jsonStrings) {
		JSONParser parser = new JSONParser();
		for (String jsonString: jsonStrings) {
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
				addSong(new Song(jsonObject));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Library built. Has %d songs.\n", songsById.size());
	}
	
	public Song getSongById(String trackId) {
		return songsById.get(trackId);
	}
	
	public HashMap<String, Song> getAllSongs() {
		return songsById;
	}
}
