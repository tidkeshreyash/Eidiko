package org.example.adapterPattern;

public class AudioPlayerClient implements MediaPlayer{

    private MediaPlayer mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            mediaAdapter = new MediaAdapter();
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Unsupported audio type: " + audioType);
        }
    }

    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayerClient();
        player.play("mp3", "song.mp3");  // Outputs: Playing MP3 file: song.mp3
        player.play("wav", "sound.wav");
    }
}
