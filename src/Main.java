import java.util.*;

import model.Artist;
import model.DataSource;

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
		
		List<String> albums = dataSource.queryAlbumsForArtist("Man", 2);
		
		//Print all albums for selected artist
		for(String album : albums){
			System.out.println("Album name: " + album);
		}
		
		dataSource.close();
		
	}

}
