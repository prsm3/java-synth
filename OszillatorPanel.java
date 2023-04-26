import java.awt.*;
import javax.swing.*;
import javax.swing.JCheckBox;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.Border;

public class OszillatorPanel extends JPanel
{
    Oszillator core;
    
    JLabel ueberLabel;
    JLabel typLabel;
    JLabel frequenzLabel;
    JLabel amplitudeLabel;
    JLabel zeitLabel;
    
    JTextField frequenzFeld;
    JTextField ampluitudeFeld;
    JTextField zeitFeld;
    Choice wellentyp;
    
    // JButton startButton;
    
    public OszillatorPanel()
    {
        setBackground(Color.decode("#DEEDDE"));
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        gbl.setConstraints(this, gbc);
        setLayout(gbl);
        
        Border aussenlinie = BorderFactory.createLineBorder(Color.black);
        setBorder(aussenlinie);
        
        wellentyp = new Choice();
        wellentyp.add("sinus");
        wellentyp.add("dreieck");
        
        core = new Oszillator();
        
        frequenzFeld = new JTextField("440",3);
        ampluitudeFeld = new JTextField("1",3);
        zeitFeld = new JTextField("1",3);
        
        ueberLabel = new JLabel("Oszillator");
        ueberLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        typLabel = new JLabel("Typ:");
        typLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        frequenzLabel = new JLabel("Frequenz:");
        frequenzLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        amplitudeLabel = new JLabel("Amplitude:");
        amplitudeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        zeitLabel = new JLabel("Zeit:");
        zeitLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // startButton = new JButton("START");
        // startButton.addActionListener(new ActionListener()
        // {
            // public void actionPerformed(ActionEvent e)
            // {
                // starte();
            // }
        // });
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(ueberLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(frequenzLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(frequenzFeld, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(typLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(wellentyp, gbc);
        // gbc.gridx = 2;
        // gbc.gridy = 2;
        // add(startButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(amplitudeLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(ampluitudeFeld, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(zeitLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(zeitFeld, gbc);
    }
    
    public java.util.List getListe() {
        return core.getListe();
    }
    
    public java.util.List starte(int fall, int fall2) {
        if(fall==1) {
            core.loescheListe();
        }
        core.setAmplitude(Double.valueOf(ampluitudeFeld.getText()));
        core.setZeit(Double.valueOf(zeitFeld.getText()));
        if(fall2==1) {
            core.setFrequenz(Double.valueOf(frequenzFeld.getText()));
        }
        core.setModus(wellentyp.getSelectedItem());
        core.starte();
        return core.getListe();
    }
}