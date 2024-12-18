package com.it.dashboard.print;

import java.applet.Applet;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrintApplet extends Applet implements ActionListener { 
	Label label1 = new Label("Hello World."); 
	Button button1 = new Button("OK");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(){}
	
	public PrintApplet()throws HeadlessException
	{		 
		this.setLayout(new FlowLayout());
		this.add(label1);
		this.add(button1);
		button1.addActionListener(this); 

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
