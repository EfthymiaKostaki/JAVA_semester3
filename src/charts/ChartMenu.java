package charts;
import java.util.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*; 
import java.io.File;
import javafx.*;

public class ChartMenu {
	protected ArrayList<ArrayList<Object>> databaseTable;
	protected JFrame frame, generalframe, displayFrame;
	protected JButton okbutton, goBackButton;
	protected JRadioButton lineChart, areaChart, barChart, pieChart;
	
	public ChartMenu(ArrayList<ArrayList<Object>> table) {
		databaseTable = table;
		frame = new JFrame(" Charts Menu");
		frame.setLayout(null);
		frame.setResizable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel question = new JLabel("Select which chart you want to create:");
		question.setBounds(65, 50, 350, 60);
		frame.add(question);
	
		lineChart = new JRadioButton("1) Line Chart");
		lineChart.setBounds(70,100,350,60);
		frame.add(lineChart);

		areaChart = new JRadioButton("2) Area Chart");
		areaChart.setBounds(70, 150, 350, 60);
		frame.add(areaChart);

		barChart = new JRadioButton("3) Bar Chart");
		barChart.setBounds(70, 200, 350, 60);
		frame.add(barChart);
	
		pieChart = new JRadioButton("4) Pie Chart");
		pieChart.setBounds(70,250,350,60);
		frame.add(pieChart);

		okbutton = new JButton("OK");
	
		okbutton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				DatabaseCharts DbC = new DatabaseCharts(table);
				if (lineChart.isSelected()) {
					DbC.lineChart();
				} else if (areaChart.isSelected()) {
					DbC.areaChart();
				} else if (barChart.isSelected()) {
					DbC.barChart();
				} else {
					DbC.pieChart();
				}
			}
		});
	
		goBackButton = new JButton("Go Back");	
		goBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				return;
			}
		});
		okbutton.setBounds(150, 350, 150, 30);
		frame.add(okbutton);
		frame.add(goBackButton);
	
		ButtonGroup bg = new ButtonGroup();
		bg.add(lineChart);
		bg.add(areaChart);
		bg.add(barChart);
		bg.add(pieChart);
		bg.add(goBackButton);
		lineChart.setSelected(true); 
	}
}
