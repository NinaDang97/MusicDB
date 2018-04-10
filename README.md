# MusicDB
### Overview
- Program runs on executing RDBMS SQLite queries, getting values from tables and assigned into a corresponding object to print out on console window in ArrayList data type.

### Install and running app
- Build path with external library: sqlite-jdbc jar 
- System.out.println on console window 

### Folder structure 
- Database file is in src/music.db, composed of 3 tables: songs, albums, artists
- Currently, DAO interface: DataSource.java
- Object constructors: Album.java, Artist.java, Song.java, SongArtist.java

```
MusicDB/
  bin/
    model/
      Album.class
      Artist.class
      DataSource.class
      Song.class
      SongArtist.class
    Main.class
    music.db
  src/
    model/
      Album.java
      Artist.java
      DataSource.java
      Song.java
      SongArtist.java
    Main.java
    music.db
  .classpath.file
  .gitattributes.file
  .project.file
  README.md
```
  
