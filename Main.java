package guoyifan.advancedStudentSystem.main;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.TextField;
import java.io.IOException;
import java.sql.DatabaseMetaData;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import guoyifan.advancedStudentSystem.beans.Storage;

public class Main {
	public static void main(String[] args) {
		try {
			Storage.readin();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Storage.setFrame();
	}
}
