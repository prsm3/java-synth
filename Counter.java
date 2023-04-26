public class Counter extends Thread {
    int step = 0;
    int timer = 1000;
    Thread thread;
    boolean paused = false;
    Object lock = new Object();
    
    
    public void run() {
        while(true) {
            synchronized(lock) {
                while(paused) {
                    try
                    {
                        lock.wait();
                    }
                    catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
                }
            }
            try {
                next();
                Thread.sleep(timer);
            }
            catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    // checht ob das lebt
    public boolean lebt() {
        return thread.isAlive();
    }
    
    // checkt ob das läuft
    public boolean state() {
        return paused;
    }
    
    // zählt den Zähler eins hoch
    public void next() {
        if(step+1==4) {
            step = 0;
        }
        else {
            step += 1;
        }
    }
    
    // setzt die Wartezeit
    public void setTime(int a) {
        timer = a;
    }
    
    // gibt Zeit
    public int getTime() {
        return timer;
    }
    
    public void pause() {
        paused = true;
    }
    
    public void unpause() {
        synchronized(lock) {
            paused = false;
            lock.notifyAll();
        }
    }
    
    // gibt den akutellen Schritt zurück
    public int getStep() {
        return step;
    }
}