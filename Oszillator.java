import java.lang.Math;
import java.util.*;

// Dies ist die Klasse für das Oszillator-Modul, hiermit kann ein Oszillator erstellt werden der eine
// Sinus- bzw. Dreickswellenform mit einigen Parametern erzeugen kann. Parameter sind Frequenz, Amplitude 
// und die Länge der genrierten Liste.

public class Oszillator
{
    private double pi = 3.14159;
    private double frequenz;
    private double amplitude;
    private double zeit;
    private List ergebnisListe;
    private String modus; //valide Modi: "dreieck" und "sinus"

    public Oszillator()
    {
        // erzeugt die Urliste
        ergebnisListe = new ArrayList();
    }

    public void starte()
    {
        // generiert Sinsuwelle
        if(modus == "sinus")
        {
            for(double i=0; i < zeit; i=i+0.0001)
            {
                ergebnisListe.add(amplitude*Math.sin(2*pi*frequenz*i));
            }
        }
        // generiert Dreieckswelle
        else if(modus == "dreieck")
        {
            for(double i=0; i < zeit; i=i+0.0001)
            {
                ergebnisListe.add(2*amplitude/pi*Math.asin(Math.sin(2*pi*i*frequenz)));
            }
        }
        // falls eine invalide Eingabe für den String getätigt wird
        else
        {
            System.out.println("invalider Modus");
        }
    }

    public void printListe()
    {
        // druckt die Liste aus, hauptsächlich für debugging verwendet
        for(int i=0; i < ergebnisListe.size(); i++)
        {
            System.out.println(ergebnisListe.get(i));
        }
    }

    public void loescheListe()
    {
        // Löscht die Liste
        ergebnisListe.clear();
    }

    public List getListe()
    {
        // gibt Ergebnis als Liste aus
        return(ergebnisListe);
    }
    
    public void setFrequenz(double f)
    {
        // Frequenzsetzung
        frequenz = f;
    }
    
    public void setAmplitude(double a)
    {
        // Amplitudensetzung
        amplitude = a;
    }
    
    public void setZeit(double z)
    {
        // Zeitsetzung
        zeit = z;
    }
    
    public void setModus(String m)
    {
        // setzt den Modus
        modus = m;
    }
}