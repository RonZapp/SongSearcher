package LibraryDatabaseBuilder;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * Holds the data for an individual song
 * @author RonZapp
 * 
 */
public class Song implements Comparable<Song> {
	private String trackId;	// Supposed to be unique for each song
	private String artist;
	private String title;
	private ArrayList<String> similars;
	private ArrayList<String> tags;
	
	/*
	 * Constructor that takes individual data members as arguments
	 * @param artist
	 * @param trackId
	 * @param title
	 * @param similars
	 * @param tags
	 */
	public Song(String artist, String trackId, String title, ArrayList<String> similars, ArrayList<String> tags ) {
		this.trackId = trackId;
		this.artist = artist;
		this.title = title;
		this.similars = similars;
		this.tags = tags;
	}
	
	/*
	 * Constructor that takes JSONObject and parses data from it
	 * @param object
	 */
	public Song(JSONObject object) {
		if (object.containsKey("track_id")) {
			this.trackId = (String) object.get("track_id");
		}
		if (object.containsKey("artist")) {
			this.artist = (String) object.get("artist");
		}
		if (object.containsKey("title")) {
			this.title = (String) object.get("title");
		}
		if (object.containsKey("similars")) {
			this.similars = new ArrayList<String>();
			JSONArray similarsLevelOne = (JSONArray) object.get("similars");
			for (int i = 0; i < similarsLevelOne.size(); i++) {
				JSONArray similarsLevelTwo = (JSONArray) similarsLevelOne.get(i);
				// Format for similars has the trackId as first member of nested array
				this.similars.add((String) similarsLevelTwo.get(0));  
			}
		}
		if (object.containsKey("tags")) {
			this.tags = new ArrayList<String>();
			JSONArray tagsLevelOne = (JSONArray) object.get("tags");
			for (int i = 0; i < tagsLevelOne.size(); i++) {
				JSONArray tagsLevelTwo = (JSONArray) tagsLevelOne.get(i);
				// Format for tags has the tag name as the first member of nested array
				this.tags.add((String) tagsLevelTwo.get(0)); 
			}
		}
	}
	
	public String getTrackId() {
		return this.trackId;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public ArrayList<String> getSimilars() {
		return this.similars;
	}
	
	public ArrayList<String> getTags() {
		return this.tags;
	}
	
	/*
	 * Orders songs alphabetically first by artist, then by title, then by trackId
	 */
	public static Comparator<Song> ArtistTitleTrackidComparator = new Comparator<Song>() {
		
		@Override
		public int compare(Song s1, Song s2) {
			if (s1.getArtist().compareTo(s2.getArtist()) == 0) {
				if (s1.getTitle().compareTo(s2.getTitle()) == 0) {
					return s1.getTrackId().compareTo(s2.getTrackId());
				} else {
					return s1.getTitle().compareTo(s2.getTitle());
				}
			} else {
				return s1.getArtist().compareTo(s2.getArtist());
			}
		}
		
	};
	
	/*
	 * Orders songs alphabetically first by title, then by artist, then by trackId
	 */
	public static Comparator<Song> TitleArtistTrackidComparator = new Comparator<Song>() {
		
		@Override
		public int compare(Song s1, Song s2) {
			if (s1.getTitle().compareTo(s2.getTitle()) == 0) {
				if (s1.getArtist().compareTo(s2.getArtist()) == 0) {
					return s1.getTrackId().compareTo(s2.getTrackId());
				} else {
					return s1.getArtist().compareTo(s2.getArtist());
				}
			} else {
				return s1.getTitle().compareTo(s2.getTitle());
			}
		}
	
	};
	
	@Override
	public int compareTo(Song otherSong) {
		return this.trackId.compareTo(otherSong.trackId);
	}
	
	@Override
	public String toString() {
		return this.trackId;
	}
}