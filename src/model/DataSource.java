package model;

import java.sql.*;
import java.util.*;

public class DataSource {

	public static final String DB_NAME = "music.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:E:\\04BIT16_HaagaHelia\\@@@JobApp\\tasks\\selfStudy\\MusicDB\\src\\" + DB_NAME;
		
	public static final String TABLE_ARTISTS = "artists";
	public static final String COLUMN_ARTISTS_ID = "_id";
	public static final String COLUMN_ARTISTS_NAME = "name";
	public static final int INDEX_ARTISTS_ID = 1; //column 1
	public static final int INDEX_ARTISTS_NAME = 2; //column 2
	
	public static final String TABLE_ALBUMS = "albums";
	public static final String COLUMN_ALBUMS_ID = "_id";
	public static final String COLUMN_ALBUMS_NAME = "name";
	public static final String COLUMN_ALBUMS_ARTISTS = "artist";
	public static final int INDEX_ALBUMS_ID = 1;
	public static final int INDEX_ALBUMS_NAME = 2;
	public static final int INDEX_ALBUMS_ARTIST = 3;
	
	public static final String TABLE_SONGS = "songs";
	public static final String COLUMN_SONGS_ID = "_id";
	public static final String COLUMN_SONGS_TRACK = "track";
	public static final String COLUMN_SONGS_TITLE = "title";
	public static final String COLUMN_SONGS_ALBUM = "album";
	public static final int INDEX_SONG_ID = 1;
	public static final int INDEX_SONG_TRACK = 2;
	public static final int INDEX_SONG_TITLE = 3;
	public static final int INDEX_SONG_ALBUM = 4;
	
	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;
	
	public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUMS_NAME + " FROM " + TABLE_ALBUMS +
                    " INNER JOIN " +TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_ARTISTS +
                    " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID +
                    " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " ORDER BY " + TABLE_ALBUMS + "." + COLUMN_ALBUMS_NAME + " COLLATE NOCASE ";
    
	private Connection conn;
	
	public boolean open(){
		try{
			conn = DriverManager.getConnection(CONNECTION_STRING);
			return true;
		} catch(SQLException e){
			System.out.println("There's sth wrong: " + e.getMessage());
			e.getStackTrace();
			return false;
		}
	}
	
	public void close(){
		try{
			if(conn != null){
				conn.close();
			}
		} catch(SQLException e){
			System.out.println("There's sth wrong: " + e.getMessage());
		}
	}
	
	public List<Artist> queryArtists(int sortOrder){
		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(TABLE_ARTISTS);
		
		if(sortOrder != ORDER_BY_NONE){
			sb.append(" ORDER BY ");
			sb.append(COLUMN_ARTISTS_NAME);
			sb.append(" COLLATE NOCASE ");
			if(sortOrder == ORDER_BY_DESC){
				sb.append("DESC");
			} else{
				sb.append("ASC");
			}
		}
		
		//The try-with-resources Statement
		//Statement and ResultSet will be closed automatically
		try(Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sb.toString())){			
			List<Artist> artists = new ArrayList<Artist>();
			
			while(results.next()){
				Artist newArtist = new Artist();
				newArtist.setId(results.getInt(INDEX_ARTISTS_ID));
				newArtist.setName(results.getString(INDEX_ARTISTS_NAME));
				artists.add(newArtist);
			}
			return artists;
			
		} catch(SQLException e){
			System.out.println("Query failed: " + e.getMessage());
			return null;
		}
	}
	
	public List<String> queryAlbumsForArtist(String artistName, int sortOrder){
		StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
		sb.append(artistName);
		sb.append("\"");
		
		if(sortOrder != ORDER_BY_NONE){
			sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
			if(sortOrder == ORDER_BY_DESC){
				sb.append(" DESC");
			} else{
				sb.append(" ASC");
			}
		}
		
		System.out.println("SQL Statement: " + sb.toString());
		
		try(Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sb.toString())){
			
			List<String> albums = new ArrayList<String>();
			
			while(results.next()){				
				albums.add(results.getString(1)); //return just 1 column albums.name in this query
			}
			return albums;
			
		} catch(SQLException e){
			System.out.println("Error with queryAlbumsForArtists: " + e.getMessage());
			return null;
		}
	}
	
}
