import java.util.ArrayList;

public class Wave {
    ArrayList<Float> myFloats = null;
    float totalDuration;

    Wave() {
        this.myFloats = new ArrayList<Float>();
        this.totalDuration = 0.0f;
    }

    public void addNote(Note myNote) {
        this.myFloats.addAll(MyUtil.generatePulse(myNote.mySemitone.getHz(), myNote.myBeat.getDuration()));
        this.totalDuration += myNote.myBeat.getDuration();
    }

    public void play(String Semitone, int Beat) {
        this.addNote(new Note(Semitone, Beat));
    }

    public void rest() {
        this.addNote(new Note(0.0f, 0.20f));
    }

    public ArrayList<Float> getFloats() {
        return this.myFloats;
    }

    public float getTotalDuration() {
        return this.totalDuration;
    }
}
