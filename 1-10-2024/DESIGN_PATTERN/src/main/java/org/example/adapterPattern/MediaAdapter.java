package org.example.adapterPattern;

public class MediaAdapter implements MediaPlayer{

    private AudioPlayer audioPlayer;

    public MediaAdapter() {
        this.audioPlayer = new AudioPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            audioPlayer.playMp3(fileName);
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}
