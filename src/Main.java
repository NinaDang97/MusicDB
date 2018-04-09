import java.util.*;

import model.Artist;
import model.DataSource;
import model.SongArtist;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataSource dataSource = new DataSource();
		
		if(!dataSource.open()){
			System.out.println("Cannot open datasource!");
			return;
		} 
	
		List<Artist> artists = dataSource.queryArtists(9);
		if(artists == null){
			System.out.println("There is 0 artist");
			return;
		}
				
		//Print all artists
//		for(Artist artist : artists){
//			System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
//		}
		
		List<String> albums = dataSource.queryAlbumsForArtist("Pink Floyd", 2);
		
		//Print all albums for selected artist
//		for(String album : albums){
//			System.out.println("Album name: " + album);
//		}
		
		List<SongArtist> songArtists = dataSource.queryArtistsForSong("Go Your Own Way", 3);
		
		if(songArtists == null){
			System.out.println("Couln't find the artist for the song");
			return;
		}
		//Print all songs for selected artist
//		System.out.println("Artist Name | Album Name | Track");
//		for(SongArtist songArtist : songArtists){
//			System.out.println(songArtist.getArtistName()
//							+ " | " + songArtist.getAlbumName()
//							+ " | " + songArtist.getTrack());
//		}
		
		//Print metadata - get column name from table songs
		dataSource.querySongsMetadata();
		
		dataSource.close();
		
	}

}
