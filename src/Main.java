import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    final static String WaveFileName = "audio.wav";
    final static float SampleRate = 48000f; // sample rate
    final static float volume = 1.0f; // amplitude of the standing wave
    final static float standardPitch = 440.0f; // StandardPitch
    final static float bpm = 120.0f; // Standard Beats per minute
    final static ArrayList<Float> BEATS = new ArrayList<Float>();
    final static HashMap<String, Integer> SEMITONES = new HashMap<String, Integer>(); // Mapping of Semitone Name to Semitone number
    final static ArrayList<Integer> MIDIS = new ArrayList<Integer>();

    public static void main(String[] args) {

        Wave myWave = new Wave();
//        for (int tone : sequence)
//            myWave.addNote(new Note(tone, 1.0f));
        int[] pentatonicMajr = {0, 2, 4, 7, 9, 12};
        int[] pentatonicMinr = {0, 3, 5, 7, 10, 12};
        for (int i = 0; i < 50; ++i) {
            int sIndex = MyUtil.random(0, 5);
            int bIndex = MyUtil.random(0, 6);
            int octave = MyUtil.random(0, 2);
            for (int j = 0; j < MyUtil.random(1, 4); ++j)
                myWave.addNote(new Note(pentatonicMajr[sIndex] - 12 * octave, bIndex));
        }
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 1));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 1));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 0));
//        myWave.addNote(new Note("A4", 1));
//        myWave.addNote(new Note("C5", 0));
//        myWave.addNote(new Note("C5", 0));
//        myWave.addNote(new Note("C5", 0));
//        myWave.addNote(new Note("C5", 0));
//        myWave.addNote(new Note("C5", 0));
//        myWave.addNote(new Note("C5", 0));
//        myWave.addNote(new Note("C5", 1));
//        myWave.addNote(new Note("G4", 0));
//        myWave.addNote(new Note("G4", 0));
//        myWave.addNote(new Note("G4", 0));
//        myWave.addNote(new Note("G4", 0));
//        myWave.addNote(new Note("G4", 0));
//        myWave.addNote(new Note("G4", 0));
//        myWave.addNote(new Note("G4", 1));
        MyUtil.convertFloatsToWav(myWave, WaveFileName);
        SoundPlayer mySoundPlayer = new SoundPlayer(WaveFileName);
        new Thread() {
            @Override
            public void run() {
                mySoundPlayer.start();
            }
        }.start();
        try {
            Thread.sleep((int) myWave.totalDuration * 1000 + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
