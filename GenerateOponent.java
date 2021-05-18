package Proyecto_batalla_races;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GenerateOponent extends JFrame{
	Warrior oponent;
	Weapon oponent_weapon;
	ArrayList<Weapon> weapon_available = new ArrayList<Weapon>();
	ArrayList<Weapon> weapons = new WeaponContainer().getWeapons();
	int rand ;
	int length;
    int cycle;
    int cont;
    JPanel jp2, jp1, jp01, jp02;
    static JLabel jl ;
	public GenerateOponent(){
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		setLocation(275,50);
		setSize(950,450);
		
		JPanelBackground jp = new JPanelBackground();
		jp.setBackgroundI("images/back2.jpg");
		jp.setPreferredSize(new Dimension(getWidth(),getHeight()));
        add(jp);
		
        jp01 = new JPanel();
        jp01.setLayout(new BoxLayout(jp01, BoxLayout.Y_AXIS));
        jp01.setOpaque(false);
        jp02 = new JPanel();
        jp02.setLayout(new BoxLayout(jp02, BoxLayout.Y_AXIS));
        jp02.setOpaque(false);

        
		jl = new JLabel("Oponent");
		jl.setForeground( Color.WHITE);
		jl.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		jp01.add(jl);
		jl.setAlignmentX(CENTER_ALIGNMENT);
		
		jp1 = new JPanel();
		jp1.setOpaque(false);
		jp01.add(jp1);
		
		JLabel jl2 = new JLabel("Oponents weapon");
		jl2.setForeground( Color.WHITE);
		jl2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		jp02.add(jl2);
		jl2.setAlignmentX(CENTER_ALIGNMENT);
		
		jp2 = new JPanel();
		jp2.setOpaque(false);
		jp2.setPreferredSize(new Dimension(300,340));
		jp02.add(jp2);
		
		jp.add(jp01);
		jp.add(Box.createHorizontalStrut(50));
		jp.add(jp02);

		getRandomWarrior(this, jp1, jp2);

		
		
       
        setVisible(true);
        
       
	}
		
	public void getRandomWarrior(GenerateOponent window ,JPanel warrior_wind, JPanel weapon_wind ) {
		ArrayList<Warrior> warrior_list = new WarriorContainer().getWarriors();
		setVisible(true);
		rand = (int)(Math.random()*12 + 36);
		cont =12;
        JLabel jb = new JLabel();
        warrior_wind.add(jb);
        
        
        	
        
            
            Timer tt = new Timer();
            
            // Creamos un objeto de la clase TimerTask para definir la tarea en el metodo run
            TimerTask animation= new TimerTask() {
            	
                public void run() {
                	
                	if (cont == rand) {
                		tt.cancel();
                		tt.purge();
                		oponent = warrior_list.get(cont%12);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                		
                        window.getRandomWeapon(window, weapon_wind);
                		return;

                	}

                	Toolkit herramientas = Toolkit.getDefaultToolkit();
                    Image icono = herramientas.getImage(warrior_list.get(cont%12).getImage());
                    Image corregida = icono.getScaledInstance(500, 340, Image.SCALE_SMOOTH);
                    ImageIcon imagenIcono = new ImageIcon(corregida);
                    cont++;
                    jb.setIcon(imagenIcono);
                    setVisible(true);
                    
                }
            };
            // programamos la tarea
            tt.schedule(animation, 0, 50);                       
        
	}
	
	public void getRandomWeapon(GenerateOponent window ,JPanel jp) {
        weaponAvailable();
        length = weapon_available.size();
        rand = (int)(Math.random()*length + length*5);
        cont = length;
        cycle = 0;
        JLabel jb = new JLabel();
        jp.add(jb);
        Timer tt = new Timer();
        
        // Creamos un objeto de la clase TimerTask para definir la tarea en el metodo run
        TimerTask animation= new TimerTask() {
        	
            public void run() {
            	
            	if (cont == rand) {
            		tt.cancel();
            		tt.purge();
            		oponent_weapon = weapon_available.get(cont%length);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                    window.dispose();
            		return;

            	}

            	Toolkit herramientas = Toolkit.getDefaultToolkit();
                Image icono = herramientas.getImage(weapon_available.get(cont%length).getImage());
                Image corregida = icono.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                ImageIcon imagenIcono = new ImageIcon(corregida);
                cont++;
                jb.setIcon(imagenIcono);
                setVisible(true);
                
            }
        };
        // programamos la tarea
        tt.schedule(animation, 0, 50); 
	}	    

	public void weaponAvailable() {
		weapon_available.clear();
		for (Weapon weap: weapons) {
			if (weap.getRace().contains(oponent.getRace())) {
				weapon_available.add(weap);
			}
		}
	}
}
