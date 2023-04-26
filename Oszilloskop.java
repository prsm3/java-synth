import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;

class Oszilloskop extends Canvas
{
    java.util.List zwischenListe;

    public Oszilloskop()
    {
        zwischenListe = new ArrayList();
        setBackground(Color.decode("#161927"));
        setSize(300,300);
    }

    public void printListe(java.util.List l)
    {
        //schreibt die Liste auf
        for(int i=0; i < l.size(); i++)
        {
            System.out.println((int)Math.round((double)l.get(i)));
        }
    }

    public void setListe(java.util.List l)
    {
        // zwischenListe = l;
        // System.out.println("Länge nach set im Oszilloskop: "+zwischenListe.size());
        if(zwischenListe.size()!=0)
        {
            zwischenListe.clear();
        }
        for(int i=0; i<l.size(); i++)
        {
            zwischenListe.add((double)l.get(i)*(-1));
        }
    }

    public void paint(Graphics g)
    {
        if(zwischenListe.size()!=0)
        {
            normalisieren(zwischenListe);
            // System.out.println("normalisieren");
            // printListe(zwischenListe);
        }
        g.setColor(Color.decode("#DEEDDE"));
        g.drawLine(0,150,300,150); //X-Achse
        g.drawLine(150,0,150,300); //Y-Achse
        g.setColor(Color.decode("#5A68B0"));
        // System.out.println("Länge vor zeichnen des Graphen: "+zwischenListe.size());

        if(zwischenListe.size()!=0)
        {
            // System.out.println("xx");
            int y=0;
            for(int x=0;x<300;x=x+1)
            {
                // System.out.println("zeichnen");
                g.drawLine(x,(int)Math.round((double)zwischenListe.get(y)),(x+1),(int)Math.round((double)zwischenListe.get(y+1)));
                y++;
            }
        }
    }

    private java.util.List normalisieren(java.util.List liste)
    {
        double minimum = 0;
        double maximum = 0;
        for(int i=0; i<liste.size();i++)
        {
            if(minimum > (double)liste.get(i))
            {
                minimum = (double)liste.get(i);
            }
            if(maximum < (double)liste.get(i))
            {
                maximum = (double)liste.get(i);
            }
        }
        for(int i=0; i<liste.size();i++)
        {
            liste.set(i, ((double)liste.get(i)-minimum)*(290-10)/(maximum-minimum)+10);
        }
        return liste;
    }
}
