import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.*;
import java.lang.Math;

public class SynthGUI {
    JFrame fenster;

    JButton test;
    Oszilloskop oszkop1;
    OszillatorPanel oszPanel1;
    OszillatorPanel oszPanel2;
    MixerPanel mixerPanel1;
    SequenzPanel sequenzPanel1;
    
    public SynthGUI() {
        JPanel mainPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,3,3,3);
        gbl.setConstraints(mainPanel, gbc);
        mainPanel.setLayout(gbl);

        fenster = new JFrame();

        oszkop1 = new Oszilloskop();
        oszPanel1 = new OszillatorPanel();
        oszPanel2 = new OszillatorPanel();
        mixerPanel1 = new MixerPanel();
        sequenzPanel1 = new SequenzPanel();
    
        fenster.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                sequenzPanel1.sq1.c1.pause();
                sequenzPanel1.stopCycle();
                System.exit(0);
            }
        });
        
        mixerPanel1.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int frq1 = sequenzPanel1.sq1.getFrequenz(0);
                int frq2 = sequenzPanel1.sq1.getFrequenz(1);
                int frq3 = sequenzPanel1.sq1.getFrequenz(2);
                int frq4 = sequenzPanel1.sq1.getFrequenz(3);
                System.out.println(frq1);
                System.out.println(frq2);
                System.out.println(frq3);
                System.out.println(frq4);
                oszPanel1.core.setFrequenz(frq1);
                oszPanel1.starte(1,0);
                oszPanel2.starte(1,1);
                oszPanel1.core.setFrequenz(frq2);
                oszPanel1.starte(0,0);
                oszPanel2.starte(0,1);
                oszPanel1.core.setFrequenz(frq3);
                oszPanel1.starte(0,0);
                oszPanel2.starte(0,1);
                oszPanel1.core.setFrequenz(frq4);
                oszPanel1.starte(0,0);
                oszPanel2.starte(0,1);
                
                mixerPanel1.setListe(oszPanel1.getListe(), oszPanel2.getListe());
                mixerPanel1.starte();
                
                oszkop1.setListe(mixerPanel1.mixer.getListe());
                oszkop1.update(oszkop1.getGraphics());
                
                try {
                    File textfile = new File("stream.txt");
                    if(textfile.createNewFile()) {
                        System.out.println("Datei erstellt.");
                    }
                    else {
                        System.out.println("Datei existiert schon.");
                    }
                }
                catch(IOException error) {
                    System.out.println("error");
                    error.printStackTrace();
                }
                try {
                    System.out.println("schreibe...");
                    FileWriter schreiber = new FileWriter("stream.txt");
                    java.util.List liste = mixerPanel1.mixer.getListe();
                    //min und max finden
                    java.util.List liste2 = new ArrayList(liste);
                    Collections.sort(liste2);
                    Double max = (Double)liste2.get(0);
                    Double min = (Double)liste2.get(liste2.size() - 1);
                    for(int i=0; i<liste.size(); i++) {
                        Double a = (Double)liste.get(i);
                        long b = Math.round((a - min) * ((256)/(max-min)));
                        liste.set(i, b);
                    }
                    String str = "";
                    for(int i=0; i<liste.size(); i++) {
                        str = str + String.valueOf(liste.get(i));
                        str = str + ",";
                    }
                    System.out.println(str);
                    schreiber.write(str);
                    schreiber.close();
                    System.out.println("Schreiben erfolgreich.");
                }
                catch(IOException error) {
                    System.out.println("error");
                    error.printStackTrace();
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(oszPanel1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(oszPanel2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(mixerPanel1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(oszkop1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(sequenzPanel1, gbc);

        fenster.add(mainPanel);
        fenster.setSize(1000,1000);
        mainPanel.setBackground(Color.decode("#698769"));
        fenster.setVisible(true);
    }
}