package elenaTest;

import java.util.*;
import java.io.*;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import media.ArtistName;
import media.Value;

public final class Publisher {
    private HashMap<ArtistName,ArrayList<String>> map = new HashMap<>();            //Maps Artists to their songs
    private String dataFolder;

    public Publisher(String dataFolder){
        this.dataFolder = dataFolder;
    }


    private void init() {                                 //initializes publisher's data
        File folder = new File(dataFolder);
        File[] mp3s = folder.listFiles();

        ArtistName artist = null;
        String title = "";

        try {
            for (File mp3 : mp3s) {

                String name = mp3.getName();
                Mp3File song = new Mp3File(name);
                if (song.hasId3v1Tag()) {
                    title = song.getId3v1Tag().getTitle();
                    artist = new ArtistName(song.getId3v1Tag().getArtist());

                } else if (song.hasId3v2Tag()) {
                    title = song.getId3v2Tag().getTitle();
                    artist = new ArtistName(song.getId3v2Tag().getArtist());
                }
                else {
                    title = null;
                    artist = null;
                }

                if (title != null && artist.getArtistName()!= null) {
                    //System.out.println("Artist: " + artist.getArtistName() + " Title: " + title);
                    if (map.get(artist) == null) {
                        map.put(artist, new ArrayList<String>());
                    }
                    map.get(artist).add(title);
                }
            }

        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            System.err.println("Exception");
        }

        for (ArtistName name: map.keySet()){                //test
            String key = name.getArtistName().toUpperCase();
            System.out.println(key);
            for(String songTitleList: map.get(name)) {
                System.out.println(songTitleList);
            }
            System.out.println(" ");
        }
    }
    public void informBroker () {          //sends all Artists' name to each broker through socket communication
        for (ArtistName artist: map.keySet()){
            String key = artist.getArtistName();

            //variable key is send to broker
        }

    }


    public void getBrokerList() {

    }


    public void push(ArtistName artistName, Value value) {
    }

    //public void notifyFailure(Broker broker) {}

    public static void main(String args[]) {

        Publisher p = new Publisher("C:\\Users\\elena\\Desktop\\mp3_dataset");

        p.init();


    }
}
