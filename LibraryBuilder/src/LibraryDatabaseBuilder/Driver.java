package LibraryDatabaseBuilder;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * Driver class with various methods for running parts of SongRecommender project
 * @author RonZapp
 * 
 */
public class Driver {
	private static final String JSON = "json";
	private static final String LIBRARYPATH = "/Users/ronzappardino/lastfm_dataset/Part 4";
	
	/* 
	 * Prints library to external file based on arguments
	 * Read libraryprinting_instructions.txt for more information
	 */
	public static void libraryPrint(String[] args) {
		ArgumentValidater av = new ArgumentValidater(args);
		av.validate();
		DirectoryCrawler dc = new DirectoryCrawler(av.getInput(), JSON);
		MusicLibrary library = new MusicLibrary();
		library.addSong(dc.crawl());
		System.out.printf("\nlibrary had %d songs\n\n", library.getAllSongs().size());
		library.printLibrary(av.getOutput(), av.getOrder());
	}
	
	/* Returns new build of MusicLibrary from LIBRARYPATH */
	public static MusicLibrary getNewLibrary() {
		DirectoryCrawler dc = new DirectoryCrawler(LIBRARYPATH, JSON);
		MusicLibrary library = new MusicLibrary();
		library.addSong(dc.crawl());
		return library;
	}
	
	/* Returns a new build of MusicLibrary from specified path */
	public static MusicLibrary getNewLibrary(String path, String ext) {
		DirectoryCrawler dc = new DirectoryCrawler(path, ext);
		MusicLibrary library = new MusicLibrary();
		library.addSong(dc.crawl());
		return library;
	}
	
	/* 
	 * Prints to Java console the length of the longest instance of title, artist, trackid, and tag in MusicLibrary 
	 * Useful for determining size of varchar datatypes in SQL Server
	 */
	public static void printDataMaxLengths() {
		MusicLibrary library = getNewLibrary();
		HashMap<String, Song> allSongs = library.getAllSongs();
		int artistLength = 0;
		int titleLength = 0;
		int trackidLength = 0;
		int tagLength = 0;
		for (Song song: allSongs.values()) {
			if (song.getArtist().length() > artistLength) {
				artistLength = song.getArtist().length();
			}
			if (song.getTitle().length() > titleLength) {
				titleLength = song.getTitle().length();
			}
			if (song.getTrackId().length() > trackidLength) {
				trackidLength = song.getTrackId().length();
			}
			for (String tag: song.getTags()) {
				if (tag.length() > tagLength) {
					tagLength = tag.length();
				}
			}
			for (String similarTrackid: song.getSimilars()) {
				if (similarTrackid.length() > trackidLength) {
					trackidLength = similarTrackid.length();
				}
			}
		}
		System.out.printf("Longest trackid: %d\nLongest title: %d\nLongest artist: %d\nLongest tag: %d\n", 
				trackidLength, titleLength, artistLength, tagLength);
	}
	
	public static void main(String[] args) {
		MusicLibrarySkinny library = new MusicLibrarySkinny();
		DirectoryCrawler dirCrawl = new DirectoryCrawler("/Users/ronzappardino/lastfm_dataset/Part 3", "json");
		library.addSong(dirCrawl.crawl());
		DatabaseCommunicater dbCom = new DatabaseCommunicater();
		if (dbCom.openConnection()) {
			dbCom.addLibrary(library);
		} else {
			System.out.println("Failed to connect to database");
		}
	}
}