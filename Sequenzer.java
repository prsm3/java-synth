import java.util.*;

public class Sequenzer {
    Counter c1;
    ArrayList fqlist;
    
    // Konstruktor
    public Sequenzer() {
        c1 = new Counter();
        c1.start();
        fqlist = new ArrayList();
        for(int i=0; i<4;i++) {
            fqlist.add(440);
        }
    }
    
    // gibt die Zeit zurück
    public int getTime() {
        return c1.getTime();
    }
    
    // setzt Frequenz am index
    public void setFrequenz(int i, int fq) {
        fqlist.set(i, fq);
    }
    
    // gibt Frequenz am aktuellen Schritt aus
    public int getFrequenz() {
        int step = c1.getStep();
        int frequenz = (int)fqlist.get(step);
        return frequenz;
    }
    
    // gibt Frequenz am index aus
    public int getFrequenz(int i) {
        int frequenz = (int)fqlist.get(i);
        return frequenz;
    }
    
    // geschwindigkeit der clock
    public void setTime(int a) {
        c1.setTime(a);
    }
    
    // startet den Zähler
    public void startCycle() {
        c1.start();
    }
    
    // next trigger
    public void next() {
        c1.next();
    }
}
