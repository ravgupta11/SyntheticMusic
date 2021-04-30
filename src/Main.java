import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    final static String WaveFileName = "audio.wav";
    final static String SemitoneFile = "SemitonesAndMidi.txt";
    final static String ScaleFileName = "Scales.txt";
    final static float SampleRate = 48000f; // sample rate
    final static float volume = 1.0f; // amplitude of the standing wave
    final static float standardPitch = 440.0f; // StandardPitch
    final static float bpm = 120.0f; // Standard Beats per minute
    final static ArrayList<Float> BEATS = new ArrayList<Float>();
    final static HashMap<String, Integer> SEMITONES = new HashMap<String, Integer>(); // Mapping of Semitone Name to Semitone number
    final static ArrayList<Integer> MIDIS = new ArrayList<Integer>();
    final static HashMap<Integer, ArrayList<Integer>> SCALES = new HashMap<>();
    final static ArrayList<String> SCALES_NAMES = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    static {
        try (BufferedReader myreader = new BufferedReader(new FileReader(SemitoneFile))) {
            String s;
            while ((s = myreader.readLine()) != null) {
                String[] data = s.split(",");
                SEMITONES.put(data[0], Integer.parseInt(data[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        MIDIS.addAll(Main.SEMITONES.values());

        try (BufferedReader myreader = new BufferedReader(new FileReader(ScaleFileName))) {
            String s;
            int count = 0;
            while ((s = myreader.readLine()) != null) {
                String[] data = s.split(",");
                ArrayList<Integer> myArray = new ArrayList<>();
                for (int rep = 1; rep < data.length; ++rep) myArray.add(Integer.parseInt(data[rep]));
                SCALES.put(count++, myArray);
                SCALES_NAMES.add(data[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playTrack(Wave myWave) {
        MyUtil.convertFloatsToWav(myWave, WaveFileName);
        SoundPlayer mySoundPlayer = new SoundPlayer(WaveFileName);
        mySoundPlayer.start();
        try {
            System.out.println("Playing ...");
            Thread.sleep((long) (myWave.getTotalDuration() * 1100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Wave randomMusic(Wave myWave) {
        int option;
        do {
            System.out.println("Select one of the scales:");
            for (int rep = 0; rep < SCALES_NAMES.size(); ++rep)
                System.out.println(rep + ": " + SCALES_NAMES.get(rep));
            option = sc.nextInt();
        }
        while (option >= SCALES_NAMES.size());
        ArrayList<Integer> myScale = SCALES.get(option);
        for (int i = 0; i < 50; ++i) {
            int sIndex = MyUtil.random(0, myScale.size() - 1);
            int bIndex = MyUtil.random(0, 2);
            int st = myScale.get(sIndex) - 12;
            myWave.addNote(new Note(st, bIndex));
        }
        playTrack(myWave);
        return myWave;
    }

    public static void happyBirthday(Wave myWave) {
        myWave.play("D4", 1);
        myWave.play("D4", 1);
        myWave.play("E4", 1);
        myWave.play("D4", 1);
        myWave.play("G4", 1);
        myWave.play("F#4", 1);
        myWave.rest();
        myWave.play("D4", 1);
        myWave.play("D4", 1);
        myWave.play("E4", 1);
        myWave.play("D4", 1);
        myWave.play("A4", 1);
        myWave.play("G4", 1);
        myWave.rest();
        myWave.play("D4", 1);
        myWave.play("D4", 1);
        myWave.play("D5", 1);
        myWave.play("B4", 1);
        myWave.play("G4", 1);
        myWave.play("F#4", 1);
        myWave.play("E4", 1);
        myWave.rest();
        myWave.play("C5", 1);
        myWave.play("C5", 1);
        myWave.play("B4", 1);
        myWave.play("G4", 1);
        myWave.play("A4", 1);
        myWave.play("G4", 2);
        playTrack(myWave);

    }

    public static Wave jingleBells(Wave myWave) {
        myWave.play("D4", 2);
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.play("B4", 2);
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.play("B4", 2);
        myWave.play("B4", 1);
        myWave.play("D5", 1);
        myWave.play("G4", 2);
        myWave.play("A4", 2);
        myWave.play("B4", 2);
        myWave.rest();
        myWave.play("C5", 2);
        myWave.play("C5", 1);
        myWave.play("C5", 1);
        myWave.play("C5", 1);
        myWave.play("C5", 1);
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.rest();
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.play("A4", 2);
        myWave.play("A4", 2);
        myWave.play("B4", 1);
        myWave.play("A4", 1);
        myWave.play("D5", 2);
        myWave.rest();
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.play("B4", 2);
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.play("B4", 2);
        myWave.play("B4", 1);
        myWave.play("D5", 1);
        myWave.play("G4", 2);
        myWave.play("A4", 2);
        myWave.play("B4", 2);
        myWave.rest();
        myWave.play("C5", 2);
        myWave.play("C5", 1);
        myWave.play("C5", 1);
        myWave.play("C5", 1);
        myWave.play("C5", 1);
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.rest();
        myWave.play("B4", 1);
        myWave.play("B4", 1);
        myWave.play("D5", 2);
        myWave.play("C5", 1);
        myWave.play("A4", 1);
        myWave.play("G4", 1);
        myWave.rest();

        playTrack(myWave);
        return myWave;
    }

    public static void sampleTune(Wave myWave) {
        myWave.play("A4", 0);
        myWave.play("A4", 1);
        myWave.play("A4", 0);
        myWave.play("A4", 0);
        myWave.play("A4", 0);
        myWave.play("A4", 0);
        myWave.play("A4", 1);
        myWave.play("C5", 0);
        myWave.play("C5", 0);
        myWave.play("C5", 0);
        myWave.play("C5", 0);
        myWave.play("C5", 0);
        myWave.play("C5", 0);
        myWave.play("C5", 1);
        myWave.play("G4", 0);
        myWave.play("G4", 0);
        myWave.play("G4", 0);
        myWave.play("G4", 0);
        myWave.play("G4", 0);
        myWave.play("G4", 0);
        myWave.play("G4", 1);
        playTrack(myWave);
    }

    public static void generate(Wave myWave, int option, float beat, int tempo) {
        ArrayList<Integer> myScale = SCALES.get(option);
        int mod = myScale.size();
        int fi = 0, se = 1;
        for (int rep = 0; rep < 10; ++rep) {
            int index = (fi + se) % mod;
            myWave.addNote(new Note(myScale.get(index) - tempo, beat));
            fi = se;
            se = index;
        }
    }

    public static void fibonacci(Wave w1) {
        int option;
        do {
            System.out.println("Select one of the scales:");
            for (int rep = 0; rep < SCALES_NAMES.size(); ++rep)
                System.out.println(rep + ": " + SCALES_NAMES.get(rep));
            option = sc.nextInt();
        }
        while (option >= SCALES_NAMES.size());

        Wave w2 = new Wave(), w3 = new Wave(), w4 = new Wave(), w5 = new Wave(), w6 = new Wave(), w7 = new Wave(), w8 = new Wave(), w9 = new Wave(), w10 = new Wave();
        generate(w3, option, 0.5f, 6);
        generate(w4, option, 1.0f, 8);
        generate(w5, option, 1.0f, 6);
        generate(w6, option, 0.5f, 6);
        generate(w7, option, 2.0f, 12);
        generate(w8, option, 1.5f, 6);
        generate(w9, option, 1.5f, 6);
        w1.merge(w2);
        w1.merge(w3);
        w4.merge(w5);
        w4.merge(w6);
        w7.merge(w8);
        w7.merge(w9);
        w10.concat(w1);
        w10.concat(w1);
        w10.concat(w4);
        w10.concat(w4);
        w10.concat(w7);
        w10.concat(w7);
        playTrack(w10);
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.mainMenu(obj);

    }

    public void mainMenu(Main obj) {
        System.out.println("Welcome to SyntheticMusic Project: ");
        System.out.println("Select one of the following options: ");
        System.out.println("1. Create music using Dice Music Algorithm");
        System.out.println("2. Play some pre-defined Music");
        System.out.println("3. Play some fibonacci Music");
        System.out.println("4. Exit");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                randomMusic(new Wave());
                obj.mainMenu(obj);
                return;
            case 2:
                obj.sampleMenu(obj);
                obj.mainMenu(obj);
                return;
            case 3:
                fibonacci(new Wave());
                obj.mainMenu(obj);
                return;
            case 4:
                System.out.println("Bye...");
                return;
            default:
                System.out.println("Wrong Option try again!!");
                obj.mainMenu(obj);
        }
    }

    public void sampleMenu(Main obj) {
        System.out.println("Select one of the following options: ");
        System.out.println("1. play sample music #1 ");
        System.out.println("2. play Happy Birthday song");
        System.out.println("3. play Jingle Bells song");
        System.out.println("4. Go back to main menu");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                sampleTune(new Wave());
                obj.sampleMenu(obj);
                return;
            case 2:
                happyBirthday(new Wave());
                obj.sampleMenu(obj);
                return;
            case 3:
                jingleBells(new Wave());
                obj.sampleMenu(obj);
                return;
            case 4:
                return;
            default:
                System.out.println("Wrong Option! try again");
                obj.sampleMenu(obj);
        }
    }

}
