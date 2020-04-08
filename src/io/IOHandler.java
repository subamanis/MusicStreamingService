package io;

import assist.Utilities;
import media.MusicFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class IOHandler
{
    public static final String TRACKS_DIR = "files/Tracks/";
    public static final String DESTINATION_DIR = "saves/";
    public static final int STANDARD_CHUNK_SIZE = 512 * 1024; //512KB


    public static void writeToFile(MusicFile mf)
    {
        writeToFile(mf.getArtistName(), mf.getTrackName(), String.valueOf(mf.getChunkNumber()), mf.getMusicFileExtract());
    }

    public static void writeToFile(String artistName, String trackName, String part, byte[] data)
    {
        createDestinationFolderIfMissing();

        String fileName = DESTINATION_DIR + trackName + "_" + artistName;
        fileName += (Utilities.isNumeric(part)) ? "_part" + part + ".mp3" : "_" + part + ".mp3";
        try (FileOutputStream stream = new FileOutputStream(fileName)) {
            stream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<byte[]> readMp3(String path)
    {
        List<byte[]> chunks = new ArrayList<>();

        File mp3File = new File(path);
        int remainingSize = (int) mp3File.length();
        int chunkCounter = 0;

        try (BufferedInputStream buf = new BufferedInputStream(new FileInputStream(mp3File)))
        {
            while (remainingSize > 0){
                int currentChunkSize = Math.min(remainingSize, STANDARD_CHUNK_SIZE);

                chunks.add(new byte[currentChunkSize]);
                buf.read(chunks.get(chunkCounter++));

                remainingSize -= currentChunkSize;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chunks;
    }


    private synchronized static void createDestinationFolderIfMissing()
    {
        File dir = new File(DESTINATION_DIR);
        if(!dir.isDirectory()){
            dir.mkdir();
        }
    }


    //just testing
    public static void main(String[] args) {
        List<byte[]> chunks = IOHandler.readMp3(TRACKS_DIR + "3-Doors-Down-Be-Like-That.mp3");
        IOHandler.writeToFile(new MusicFile("testSong", "testArtist","-","-",1,13,chunks.get(0)));
    }

}
