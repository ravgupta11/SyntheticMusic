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

    public ArrayList<Float> getFloats() {
        return this.myFloats;
    }

    public float getTotalDuration() {
        return this.totalDuration;
    }
}
