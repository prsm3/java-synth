import java.awt.*;
import javax.swing.*;
import javax.swing.JCheckBox;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.Border;

public class SequenzPanel extends JPanel {
    Sequenzer sq1;
    JTextField fq0;
    JTextField fq1;
    JTextField fq2;
    JTextField fq3;
    JTextField speed1;
    
    JButton btnupdate;
    JButton btnnext;
    JButton btnstartstop;
    JLabel ueberLabel;

    String fqv0;
    String fqv1;
    String fqv2;
    String fqv3;
    String speedv1;
    
    Cycler thread;
    boolean running = true;
    
    public SequenzPanel() {
        setBackground(Color.decode("#DEEDDE"));
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        gbl.setConstraints(this, gbc);
        setLayout(gbl);

        Border aussenlinie = BorderFactory.createLineBorder(Color.black);
        setBorder(aussenlinie);

        btnupdate = new JButton("update");
        btnnext = new JButton("next");;
        btnstartstop = new JButton("start/stop");;
        
        sq1 = new Sequenzer();
        
        fqv0 = ""+sq1.getFrequenz(0);
        fqv1 = ""+sq1.getFrequenz(1);
        fqv2 = ""+sq1.getFrequenz(2);
        fqv3 = ""+sq1.getFrequenz(3);
        speedv1 = ""+sq1.getTime();
        
        fq0 = new JTextField(fqv0, 3);
        fq0.setFont(new Font("Arial", Font.PLAIN, 12));
        fq1 = new JTextField(fqv1, 3);
        fq1.setFont(new Font("Arial", Font.PLAIN, 12));
        fq2 = new JTextField(fqv2, 3);
        fq2.setFont(new Font("Arial", Font.PLAIN, 12));
        fq3 = new JTextField(fqv3, 3);
        fq3.setFont(new Font("Arial", Font.PLAIN, 12));
        speed1 = new JTextField(speedv1, 3);
        speed1.setFont(new Font("Arial", Font.PLAIN, 12));

        ueberLabel = new JLabel("Sequenzer");
        ueberLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        thread = new Cycler();
        thread.start();
        
        btnupdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fqi0 = Integer.parseInt(fq0.getText());
                int fqi1 = Integer.parseInt(fq1.getText());
                int fqi2 = Integer.parseInt(fq2.getText());
                int fqi3 = Integer.parseInt(fq3.getText());
                int speedi1 = Integer.parseInt(speed1.getText());
                sq1.setFrequenz(0, fqi0);
                sq1.setFrequenz(1, fqi1);
                sq1.setFrequenz(2, fqi2);
                sq1.setFrequenz(3, fqi3);
                sq1.setTime(speedi1);
            }
        });
        
        btnnext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sq1.next();
            }
        });
        
        btnstartstop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean statebool = sq1.c1.state();
                // pausieren wenn thread läuft
                if(statebool==false) {
                    sq1.c1.pause();
                }
                // entpausieren wenn er pausiert ist
                if(statebool==true) {
                    sq1.c1.unpause();
                }
            }
        });
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(ueberLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fq0, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fq1, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(fq2, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        add(fq3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnupdate, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(btnnext, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(btnstartstop, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        add(speed1, gbc);
    }
    
    public void stopCycle() {
        running = false;
    }
    
    private class Cycler extends Thread {
        public void run() {
            while(running) {
                int index = sq1.c1.getStep();
                // System.out.println(index);
                if(index==0) {
                    fq0.setForeground(Color.decode("#FF0000"));
                    fq1.setForeground(Color.decode("#000000"));
                    fq2.setForeground(Color.decode("#000000"));
                    fq3.setForeground(Color.decode("#000000"));
                }
                if(index==1) {
                    fq0.setForeground(Color.decode("#000000"));
                    fq1.setForeground(Color.decode("#FF0000"));
                    fq2.setForeground(Color.decode("#000000"));
                    fq3.setForeground(Color.decode("#000000"));
                }
                if(index==2) {
                    fq0.setForeground(Color.decode("#000000"));
                    fq1.setForeground(Color.decode("#000000"));
                    fq2.setForeground(Color.decode("#FF0000"));
                    fq3.setForeground(Color.decode("#000000"));
                }
                if(index==3) {
                    fq0.setForeground(Color.decode("#000000"));
                    fq1.setForeground(Color.decode("#000000"));
                    fq2.setForeground(Color.decode("#000000"));
                    fq3.setForeground(Color.decode("#FF0000"));
                }
            }
        }
    }
}