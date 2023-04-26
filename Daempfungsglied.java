import java.util.*;

public class Daempfungsglied
{
    double zuwachs = 100;
    
    public Daempfungsglied()
    {
    }
    
    public void setZuwachs(double z)
    {
        zuwachs = z;
    }
    
    public double getZuwachs()
    {
        return zuwachs;
    }
    
    public void starte(List l)
    {
        //Iterator
        for(int i=0; i < l.size(); i++)
        {
            l.set(i, (Double)l.get(i) * (zuwachs/100));
        }
    }
}