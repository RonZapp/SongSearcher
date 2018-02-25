package LibraryDatabaseBuilder;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Organizes a music library of Songs
 * @author RonZapp
 *
 */
public class MusicLibrary {
	private HashMap<String, Song> songsById;
	private HashMap<String, TreeSet<Song>> songsByArtist;
	private HashMap<String, TreeSet<Song>> songsByTitle;
	private HashMap<String, TreeSet<Song>> songsByTag;
	
	/* Basic constructor */
	public MusicLibrary() {
		songsById = new HashMap<String, Song>();
		songsByArtist = new HashMap<String, TreeSet<Song>>();
		songsByTitle = new HashMap<String, TreeSet<Song>>();
		songsByTag = new HashMap<String, TreeSet<Song>>();
	}

	/*
	 * Adds song to MusicLibrary
	 * @param song
	 */
	public void addSong(Song song) {
		songsById.put(song.getTrackId(), song);
		
		if (!songsByArtist.containsKey(song.getArtist())) {
			songsByArtist.put(song.getArtist(), new TreeSet<Song>(Song.ArtistTitleTrackidComparator));
		}
		songsByArtist.get(song.getArtist()).add(song);
		
		if (!songsByTitle.containsKey(song.getTitle())) {
			songsByTitle.put(song.getTitle(), new TreeSet<Song>(Song.TitleArtistTrackidComparator));
		}
		songsByTitle.get(song.getTitle()).add(song);
		
		for (String tag: song.getTags()) {
			if (!songsByTag.containsKey(tag)) {
				songsByTag.put(tag, new TreeSet<Song>());
			}
			songsByTag.get(tag).add(song);
		}
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
	}
	
	public Song getSongById(String trackId) {
		return songsById.get(trackId);
	}
	
	public TreeSet<Song> getSongsByTitle(String title) {
		return songsByTitle.get(title);
	}
	
	public TreeSet<Song> getSongsByArtist(String artist) {
		return songsByArtist.get(artist);
	}
	
	public TreeSet<Song> getSongsByTag(String tag) {
		return songsByTag.get(tag);
	}
	
	public HashMap<String, Song> getAllSongs() {
		return songsById;
	}
	
	/* Returns tag which has been assigned to largest number of songs */
	public String getMostPopularTag() {
		String popularTag = "";
		int popularTagOccurance = 0;
		for (String tag: songsByTag.keySet()) {
			int occurance = songsByTag.get(tag).size();
			if (occurance > popularTagOccurance) {
				popularTag = tag;
				popularTagOccurance = occurance;
			}
		}
		return popularTag;
	}
	
	/*
	 * Exports library to .txt file based on specified order
	 * Read libraryprinting_instructions.txt for more info
	 * @param dest location of .txt file to print
	 * @param order currently accepts artist, title, or tag
	 */
	public void printLibrary(String dest, String order) {
		Path path = Paths.get(dest);
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		if (!Files.isWritable(path)) {
			System.out.printf("File at %s is not writable", dest);
			return;
		}
		
		switch (order) {
			case ("artist") :
				printLibraryByArtist(dest);
				break;
			case ("title") :
				printLibraryByTitle(dest);
				break;
			case ("tag") :
				printLibraryByTag(dest);
				break;
		}
	}
	
	/*
	 * Helper function for printLibrary
	 * Exports in order based on artist
	 * @param dest location of .txt file to export to
	 */
	private void printLibraryByArtist(String dest) {
		List<String> artists = new ArrayList<String>(songsByArtist.keySet());
		Collections.sort(artists);
		
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "UTF-8"))) {
			for (String artist: artists) {
				TreeSet<Song> songs = songsByArtist.get(artist);
				for (Song song: songs) {
					String s = song.getArtist() + " - " + song.getTitle();
					bw.write(s);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Helper function for printLibrary
	 * Exports in order based on title
	 * @param dest location of .txt file to export to
	 */
	private void printLibraryByTitle(String dest) {
		List<String> titles = new ArrayList<String>(songsByTitle.keySet());
		Collections.sort(titles);
		
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "UTF-8"))) {
			for (String title: titles) {
				TreeSet<Song> songs = songsByTitle.get(title);
				for (Song song: songs) {
					String s = song.getArtist() + " - " + song.getTitle();
					bw.write(s);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Helper function for printLibrary
	 * Exports in order based on tag
	 * @param dest location of .txt file to export to
	 */
	private void printLibraryByTag(String dest) {
		List<String> tags = new ArrayList<String>(songsByTag.keySet());
		Collections.sort(tags);
		
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest), "UTF-8"))) {
			for (String tag: tags) {
				TreeSet<Song> songs = songsByTag.get(tag);
				String s = tag + ":";
				bw.write(s);
				for (Song song: songs) {
					s = " " + song.getTrackId();
					bw.write(s);
				}
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
