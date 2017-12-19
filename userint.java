package penpaint;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.text.DecimalFormat;
import java.util.*;

public class userint extends JFrame{
	JButton brushbut,ellibut,rectbut,linebut,strokebut,fillbut;
	static JSlider transSlider;
	static JLabel transLabel;
	
	static DecimalFormat dec =new DecimalFormat("#.##");
	static Graphics2D graphsettings;
	
	static int currentAction=1;
	static float transval =1.0f;
	
	static Color strokeColor =Color.BLACK;
	static Color fillColor=Color.black;
	public static void main(String[] args ){
		new userint();
		
	}
	public userint(){
		this.setSize(800, 500);
		this.setTitle("PEN-PAINT");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 JPanel buttonPanel =new JPanel();
		 
		 Box thebox =Box.createHorizontalBox();
		 JButton brushbut,ellibut,rectbut,linebut,erasebut,strokebut,fillbut;
		 
		brushbut=makeMeButtons("C:/Java48/brush.png",1);
		ellibut=makeMeButtons("C:/Java48/Line.png",2);
		linebut=makeMeButtons("C:/Java48/Ellipse.png",3);
		rectbut=makeMeButtons("C:/Java48/Rectangle.png",4);
		erasebut=makeMeButtons("C:/Java48/4.png",7);
		
		strokebut=makeMeColorButtons("C:/Java48/Stroke.png",5,true);
		fillbut=makeMeColorButtons("C:/Java48/Fill.png",6,false);
		
		thebox.add(brushbut);
		thebox.add(ellibut);
		thebox.add(linebut);
		thebox.add(rectbut);
		thebox.add(strokebut);
		thebox.add(fillbut);
		
		transLabel =new JLabel("transparent:1");
		transSlider =new JSlider(1,99,99);
		ListenForSlider lfs =new ListenForSlider();
		 
		transSlider.addChangeListener(lfs);
		thebox.add(transLabel);
		thebox.add(transSlider);
		
		buttonPanel.add(thebox);
		this.add(buttonPanel,BorderLayout.SOUTH);
		this.add(new penpaint.DrawingBoard(), BorderLayout.CENTER);
		//this.add(new DrawingBoard(),BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	public JButton makeMeButtons(String fileadd,final int actnum){
		JButton thebut=new JButton();
		Icon buticon =new ImageIcon(fileadd);
		thebut.setIcon(buticon);
		
		thebut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentAction=actnum;
			}
		});
		return thebut;
	}
	public JButton makeMeColorButtons(String fileadd,final int actnum,final boolean stroke){
		JButton thebut=new JButton();
		Icon buticon =new ImageIcon(fileadd);
		thebut.setIcon(buticon);
		
		thebut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(stroke)
				{
					strokeColor=JColorChooser.showDialog(null,"Choose a color", Color.black);
				}else{
					fillColor=JColorChooser.showDialog(null,"Choose a color", Color.black);
				}
			}
		});
		return thebut;
		
	}
	
}
