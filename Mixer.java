import java.util.*;

public class Mixer
{
    List ergebnisListe;
    String modus; //valide Modi: addieren und subtrahieren

    public Mixer()
    {
        // Erzeugung der Liste
        ergebnisListe = new ArrayList();
    }

    public void starte(List l1, List l2) // addiert oder subtrahiert zwei Oszillatorlisten
    {
        clearListe();
        if(modus == "addieren") // addieren
        {
            // Größenvergleich
            if(l1.size()>l2.size())
            {
                for(int i=0; i<l2.size(); i++)
                {
                    ergebnisListe.add((Double)l1.get(i)+(Double)l2.get(i));
                }
                // falls eine Liste größer ist wird hier der Rest dieser Liste angefügt
                for(int i=ergebnisListe.size(); i<l1.size(); i++)
                {
                    ergebnisListe.add((Double)l1.get(i));
                }
            }
            else
            {
                for(int i = 0; i<l1.size(); i++)
                {
                    ergebnisListe.add((Double)l1.get(i)+(Double)l2.get(i));
                }
                // falls eine Liste größer ist wird hier der Rest dieser Liste angefügt
                for(int i=ergebnisListe.size(); i<l2.size(); i++)
                {
                    ergebnisListe.add((Double)l2.get(i));
                }
            }
        }
        // subtrahiert zwei Oszillatorlisten
        else if(modus == "subtrahieren")
        {
            // Größenvergleich
            if(l1.size()>l2.size())
            {
                for(int i=0; i < l2.size(); i++)
                {
                    // Sichergehen das die Vorzeichen richtig sind
                    if(Math.abs((Double)l1.get(i))>Math.abs((Double)l2.get(i)))
                    {
                        // ausrechnen des Wertes zum Zeitpunkt i
                        ergebnisListe.add((Double)l1.get(i)-(Double)l2.get(i));
                    }
                    else
                    {
                        // ausrechnen des Wertes zum Zeitpunkt i
                        ergebnisListe.add((Double)l2.get(i)-(Double)l1.get(i));
                    }
                }
                // falls eine Liste größer ist wird hier der Rest dieser Liste angefügt
                for(int i=ergebnisListe.size(); i<l1.size(); i++)
                {
                    ergebnisListe.add((Double)l1.get(i));
                }
            }
            else
            {
                for(int i = 0; i<l1.size(); i++)
                {
                    // Sichergehen das die Vorzeichen richtig sind
                    if(Math.abs((Double)l1.get(i))>Math.abs((Double)l2.get(i))){
                        // ausrechnen des Wertes zum Zeitpunkt i
                        ergebnisListe.add((Double)l1.get(i)-(Double)l2.get(i));
                    }
                    else
                    {
                        // ausrechnen des Wertes zum Zeitpunkt i
                        ergebnisListe.add((Double)l2.get(i)-(Double)l1.get(i));
                    }
                }
                // falls eine Liste größer ist wird hier der Rest dieser Liste angefügt
                for(int i=ergebnisListe.size(); i<l2.size(); i++)
                {
                    ergebnisListe.add((Double)l2.get(i));
                }
            }
        }
        else System.out.println("Fehler beim Mixer");
    }

    public void setModus(String m)
    {
        modus = m;
    }

    public String getModus()
    {
        return modus;
    }

    public java.util.List getListe()
    {
        //gibt Ergebnis als Liste aus
        return(ergebnisListe);
    }

    public void printListe(List l)
    {
        //schreibt die Liste auf
        for(int i=0; i < l.size(); i++)
        {
            System.out.println(l.get(i));
        }
    }

    public void clearListe()
    {
        ergebnisListe.clear();
    }
}