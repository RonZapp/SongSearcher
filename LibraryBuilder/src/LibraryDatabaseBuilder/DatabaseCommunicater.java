package LibraryDatabaseBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/*
 * Manages the interactions between java program and SQL server
 * @author RonZapp
 * 
 */
public class DatabaseCommunicater {
	private static final String LOGINURL = "jdbc:sqlserver://192.168.0.5:1401;"
			+ "databaseName=SongRecommender;"
			+ "user=SA;"
			+ "password=" + PasswordKeeper.getPassword() + ";"; // PasswordKeeper class hidden from GitHub. Contact RonZapp for password
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	/* Constructor */
	public DatabaseCommunicater() {
		this.con = null;
		this.stmt = null;
		this.rs = null;
	}
	
	/*
	 * Opens a connection to the SQL server
	 * @return true if successful or false if unsuccessful
	 */
	public boolean openConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(LOGINURL);
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println("DC caught exception");
			e.printStackTrace();
			// Attempt to close open resources
			if (con != null) {
				try { 
					con.close(); 
				} catch (Exception e2) {
					// Do nothing
				}
			}
			if (stmt != null) {
				try { 
					stmt.close();
				} catch (Exception e3) {
					// Do nothing
				}
			}
			return false;
		}
		return true;
	}
	
	/*
	 * Closes connection to SQL Server
	 */
	public void closeConnection() {
		try {
			con.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			// Do nothing
		}
	}
	
	/*
	 * Creates a new 'songs' table
	 * @return true if successful or false if unsuccessful
	 */
	public boolean createSongsTable() {
		try {
			if (tableExists("song_tag_maps")) {
				dropTable("song_tag_maps"); 			// song_tag_maps has foreign key and must be deleted first
			}
			if (tableExists("song_similar_maps")) {
				dropTable("song_similar_maps");	// song_similar_maps has foreign key and must be deleted first
			}
			if (tableExists("songs")) {
				dropTable("songs");
			}
			
			String SQL = "CREATE TABLE songs ("
					+ "track_id varchar(20) NOT NULL PRIMARY KEY,"
					+ "title varchar(100) NOT NULL,"
					+ "artist varchar(100) NOT NULL);";
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/*
	 * Creates a new 'song_tag_maps' table
	 * @return true if successful or false if unsuccessful
	 */
	public boolean createTagsTable() {
		try {
			if (tableExists("song_tag_maps")) {
				dropTable("song_tag_maps");
			}
			if (!tableExists("songs")) {
				createSongsTable();	// Foreign key dependent on songs table
			}
			
			String SQL = "CREATE TABLE song_tag_maps ("
					+ "track_id varchar(20) FOREIGN KEY REFERENCES songs(track_id),"
					+ "tag varchar(100) NOT NULL,"
					+ "title varchar(100) NOT NULL,"
					+ "artist varchar(100) NOT NULL);";
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/*
	 * Creates a new 'song_similar_maps' table
	 * @return true if successful or false if unsuccessful
	 */
	public boolean createSimilarsTable() {
		try {
			if (tableExists("song_similar_maps")) {
				dropTable("song_similar_maps");
			}
			if (!tableExists("songs")) {
				createSongsTable();	// Foreign key dependent on songs table
			}
			
			String SQL = "CREATE TABLE song_similar_maps ("
					+ "track_id varchar(20) FOREIGN KEY REFERENCES songs(track_id),"
					+ "title varchar(100) NOT NULL,"
					+ "artist varchar(100) NOT NULL,"
					+ "similar_track_id varchar(20) NOT NULL,"
					+ "similar_title varchar(100),"
					+ "similar_artist varchar(100));";
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/*
	 * Checks if table exists in database
	 * @param tableName
	 * @return true if table exists
	 */
	public boolean tableExists(String tableName) {
		try {
			String SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'";
			this.rs = stmt.executeQuery(SQL);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	/*
	 * Drops table
	 * @param tableName
	 * @return true if successful
	 */
	public boolean dropTable(String tableName) {
		try {
			String SQL = "DROP TABLE " + tableName;
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/*
	 * Clears all entries from table
	 * @param tableName
	 * @return true if successful
	 */
	public boolean clearTable(String tableName) {
		try {
			String SQL = "DELETE FROM " + tableName;
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/*
	 * Adds song to SongRecommender database
	 * @param song
	 * @param library song is from
	 * @return true if successful
	 */
	public boolean addSong(Song song, MusicLibrary library) {
		try {
			// Add song info to 'songs' table
			String songsTableSQL = "INSERT INTO songs VALUES ('" 
					+ song.getTrackId() + "', '" 
					+ song.getTitle() + "', '" 
					+ song.getArtist() + "');";
			stmt.executeUpdate(songsTableSQL);
			
			// Add song info to 'song_tag_maps' table
			String tagsTableSQL = "INSERT INTO song_tag_maps VALUES('" 
					+ song.getTrackId() + "', ?,'"
					+ song.getTitle() + "', '"
					+ song.getArtist() + "');";
			PreparedStatement tagsTableStmt = con.prepareStatement(tagsTableSQL);
			for (String tag: song.getTags()) {
				tagsTableStmt.setString(1, tag);
				tagsTableStmt.executeUpdate();
			}
			tagsTableStmt.close();
			
			// Add song info to 'song_similar_maps' table
			String similarsTableSQL = "INSERT INTO song_similar_maps VALUES('" 
					+ song.getTrackId() + "', '"
					+ song.getTitle() + "', '"
					+ song.getArtist() + "', ?, ?, ?);";
			PreparedStatement similarsTableStmt = con.prepareStatement(similarsTableSQL);
			for (String trackId: song.getSimilars()) {
				similarsTableStmt.setString(1, trackId);
				if (library.getSongById(trackId) == null) {
					similarsTableStmt.setNull(2, java.sql.Types.VARCHAR);
					similarsTableStmt.setNull(3, java.sql.Types.VARCHAR);
				} else {
					similarsTableStmt.setString(2, library.getSongById(trackId).getTitle());
					similarsTableStmt.setString(3, library.getSongById(trackId).getArtist());
				}
				similarsTableStmt.executeUpdate();
			}
			similarsTableStmt.close();
			
		} catch (SQLException e) {
			String SQL = "DELETE FROM songs WHERE track_id='" + song.getTrackId() + "';";
			try {
				stmt.executeUpdate(SQL);
			} catch (Exception e2) {
				// Do nothing
			}
			SQL = "DELETE FROM song_tag_maps WHERE track_id='" + song.getTrackId() + "';";
			try {
				stmt.executeUpdate(SQL);
			} catch (Exception e3) {
				// Do nothing
			}
			SQL = "DELETE FROM song_similar_maps WHERE track_id='" + song.getTrackId() + "';";
			try {
				stmt.executeUpdate(SQL);
			} catch (Exception e4) {
				// Do nothing
			}
			return false;
		}
		return true;
	}
	
	/*
	 * Adds every song in the library to SongRecommender database
	 * @param library
	 * @return true if successful
	 */
	public boolean addLibrary(MusicLibrary library) {
		final boolean printCount = true; // Determines whether count of songs sent will be printed
		final int increment = 50000; 	// Determines how often to print count
		int count = 0;					// Count to be printed
		
		HashMap<String, Song> allSongs = library.getAllSongs();
		PreparedStatement songsStmt = null;
		PreparedStatement tagsStmt = null;
		PreparedStatement similarsStmt = null;
		
		String songsSQL = "INSERT INTO songs VALUES(?, ?, ?);";
		String tagsSQL = "INSERT INTO song_tag_maps VALUES(?, ?, ?, ?);";
		String similarsSQL = "INSERT INTO song_similar_maps VALUES(?, ?, ?, ?, ?, ?);";
		
		try {
			songsStmt = con.prepareStatement(songsSQL);
			tagsStmt = con.prepareStatement(tagsSQL);
			similarsStmt = con.prepareStatement(similarsSQL);
			
			for (Song song: allSongs.values()) {
				if (printCount) {
					if (count % increment == 0) {
						System.out.printf("%d songs sent to database\n", count);
					}
					count++;
				}
				
				songsStmt.setString(1, song.getTrackId());
				songsStmt.setString(2, song.getTitle());
				songsStmt.setString(3, song.getArtist());
				
				try {
					songsStmt.executeUpdate();
				} catch (SQLException e) {
					// Probably a duplicate song
					System.out.println(e.toString());
					continue;
				}
				
				for (String tag: song.getTags()) {
					tagsStmt.setString(1, song.getTrackId());
					tagsStmt.setString(2, tag);
					tagsStmt.setString(3, song.getTitle());
					tagsStmt.setString(4, song.getArtist());
					tagsStmt.executeUpdate();
				}
				
				for (String similarTrackid: song.getSimilars()) {
					Song similarSong = library.getSongById(similarTrackid);
					similarsStmt.setString(1, song.getTrackId());
					similarsStmt.setString(2, song.getTitle());
					similarsStmt.setString(3, song.getArtist());
					similarsStmt.setString(4, similarTrackid);
					if (similarSong != null) {
						similarsStmt.setString(5, similarSong.getTitle());
						similarsStmt.setString(6, similarSong.getArtist());
					} else {
						// If similar song isn't in library, use null values for similar_artist and similar_title
						similarsStmt.setNull(5, java.sql.Types.VARCHAR);
						similarsStmt.setNull(6, java.sql.Types.VARCHAR);
					}
					similarsStmt.executeUpdate();
				}
			}
			songsStmt.close();
			tagsStmt.close();
			similarsStmt.close();
			
		} catch (SQLException e) {
			// Attempt to close open resources
			if (songsStmt != null) {
				try {
					songsStmt.close();
				} catch (Exception e2) {
					// Do nothing
				}
			}
			if (tagsStmt != null) {
				try {
					tagsStmt.close();
				} catch (Exception e3) {
					// Do nothing
				}
			}
			if (similarsStmt != null) {
				try {
					similarsStmt.close();
				} catch (Exception e4) {
					// Do nothing
				}
			}
			return false;
		}
		return true;
	}
	
	/*
	 * Adds every song in the library to SongRecommender database
	 * @param library
	 * @return true if successful
	 */
	public boolean addLibrary(MusicLibrarySkinny library) {
		final boolean printCount = true; // Determines whether count of songs sent will be printed
		final int increment = 50000; 	// Determines how often to print count
		int count = 0;					// Count to be printed
		int rejCount = 0;				// Count of songs that were rejected when sent to 'songs' table (but not other tables)
		
		HashMap<String, Song> allSongs = library.getAllSongs();
		PreparedStatement songsStmt = null;
		PreparedStatement tagsStmt = null;
		PreparedStatement similarsStmt = null;
		
		String songsSQL = "INSERT INTO songs VALUES(?, ?, ?);";
		String tagsSQL = "INSERT INTO song_tag_maps VALUES(?, ?, ?, ?);";
		String similarsSQL = "INSERT INTO song_similar_maps VALUES(?, ?, ?, ?, ?, ?);";
		
		try {
			songsStmt = con.prepareStatement(songsSQL);
			tagsStmt = con.prepareStatement(tagsSQL);
			similarsStmt = con.prepareStatement(similarsSQL);
			
			for (Song song: allSongs.values()) {
				if (printCount) {
					if (count % increment == 0) {
						System.out.printf("%d songs sent to database, %d songs rejected\n", count, rejCount);
					}
					count++;
				}
				
				/* will be stored in SQL Server in varchar(100) */
				String trackid = song.getTrackId();
				String title = song.getTitle();
				if (title.length() > 100) {
					title = title.substring(0, 100);
				}
				String artist = song.getArtist();
				if (artist.length() > 100) {
					artist = artist.substring(0, 100);
				}
				
				
				songsStmt.setString(1, trackid);
				songsStmt.setString(2, title);
				songsStmt.setString(3, artist);
				
				try {
					songsStmt.executeUpdate();
				} catch (SQLException e) {
					// Probably a duplicate song
					rejCount++;
//					System.out.printf("at trackid: %s, %s\n", trackid, e.toString());
					continue;
				}
				
				for (String tag: song.getTags()) {
					
					/* will be stored in SQL Server in varchar(100) */
					if (tag.length() > 100) {
						tag = tag.substring(0, 100);
					}
					tagsStmt.setString(1, trackid);
					tagsStmt.setString(2, tag);
					tagsStmt.setString(3, title);
					tagsStmt.setString(4, artist);
					try {
						tagsStmt.executeUpdate();
					} catch (SQLException e) {
						System.out.printf("at %s tag %s: %s\n", trackid, tag, e.toString());
					}
				}
				
				
				for (String similarTrackid: song.getSimilars()) {
					Song similarSong = library.getSongById(similarTrackid);
					
					similarsStmt.setString(1, trackid);
					similarsStmt.setString(2, title);
					similarsStmt.setString(3, artist);
					similarsStmt.setString(4, similarTrackid);
					if (similarSong != null) {
						
						/* will be stored in SQL Server in varchar(100) */
						String similarTitle = similarSong.getTitle();
						if (similarTitle.length() > 100) {
							similarTitle = similarTitle.substring(0, 100);
						}
						String similarArtist = similarSong.getArtist();
						if (similarArtist.length() > 100) {
							similarArtist = similarArtist.substring(0, 100);
						}
						
						similarsStmt.setString(5, similarTitle);
						similarsStmt.setString(6, similarArtist);
					} else {
						// If similar song isn't in library, use null values for similar_artist and similar_title
						similarsStmt.setNull(5, java.sql.Types.VARCHAR);
						similarsStmt.setNull(6, java.sql.Types.VARCHAR);
					}
					try {
						similarsStmt.executeUpdate();
					} catch (SQLException e) {
						System.out.printf("At trackid %s similar %s: %s\n", trackid, similarTrackid, e.toString());
					}
				}
			}
			
			System.out.printf("Finished sending songs to server. Count is %d, rejCount is %d", count, rejCount);
			
			songsStmt.close();
			tagsStmt.close();
			similarsStmt.close();
			
		} catch (SQLException e) {
			// Attempt to close open resources
			if (songsStmt != null) {
				try {
					songsStmt.close();
				} catch (Exception e2) {
					// Do nothing
				}
			}
			if (tagsStmt != null) {
				try {
					tagsStmt.close();
				} catch (Exception e3) {
					// Do nothing
				}
			}
			if (similarsStmt != null) {
				try {
					similarsStmt.close();
				} catch (Exception e4) {
					// Do nothing
				}
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
