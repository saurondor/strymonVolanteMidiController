/*
 * Created by JFormDesigner on Tue Dec 01 20:04:58 CST 2020
 */

package com.strymon.volante;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import javax.swing.event.*;

import org.apache.log4j.Logger;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Gerardo Esteban Tasistro Giubetic
 */
public class JVolanteConsole extends JFrame {
	private static final Logger logger = Logger.getLogger(JVolanteConsole.class);
	Volante volante = new Volante();
	/**
	 * 
	 */
	private static final long serialVersionUID = 4423775700554092228L;

	public JVolanteConsole() {
		initComponents();
		
	}

	public static void main(String[] args) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		JVolanteConsole console = new JVolanteConsole();
		console.connect();
		console.setVisible(true);
	}

	private void connect() {
		volante.connect();
	}

	private void recLevelSliderPropertyChange(PropertyChangeEvent e) {
		logger.info(recLevelSlider.getValue());
		setTime();
	}

	private void setTime() {
		try {
			int value = recLevelSlider.getValue();
			ShortMessage speedMsg = new ShortMessage();
			if (halfSpeedRadioButton.isSelected()) {
				double slider = Integer.valueOf(value).doubleValue();
				double range = 4000d - 400d;
				double delay = range * (slider / 127d) + 400d;
				timeLabel.setText(Double.valueOf(delay).intValue() + " ms");
				speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, Volante.CC_SPEED, 2);
				volante.sendMsg(speedMsg);
			}
			if (normalSpeedRadioButton.isSelected()) {
				double slider = Integer.valueOf(value).doubleValue();
				double range = 2000d - 200d;
				double delay = range * (slider / 127d) + 200d;
				timeLabel.setText(Double.valueOf(delay).intValue() + " ms");
				speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, Volante.CC_SPEED, 3);
				volante.sendMsg(speedMsg);
			}
			if (doubleSpeedRadioButton.isSelected()) {
				double slider = Integer.valueOf(value).doubleValue();
				double range = 1000d - 100d;
				double delay = range * (slider / 127d) + 100d;
				timeLabel.setText(Double.valueOf(delay).intValue() + " ms");
				speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, Volante.CC_SPEED, 1);
				volante.sendMsg(speedMsg);
			}

			speedMsg.setMessage(ShortMessage.CONTROL_CHANGE, 0, Volante.CC_TIME, value);
			logger.info("Sent speed msg " + value);
			volante.sendMsg(speedMsg);
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void halfSpeedRadioButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setTime();
		}
	}

	private void normalSpeedRadioButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setTime();
		}
	}

	private void doubleSpeedRadioButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setTime();
		}
	}

	private void recLevelSliderStateChanged(ChangeEvent e) {
		setTime();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("com.strymon.volante.volante");
		label6 = new JLabel();
		label16 = new JLabel();
		label11 = new JLabel();
		label14 = new JLabel();
		separator1 = new JSeparator();
		label15 = new JLabel();
		separator2 = new JSeparator();
		separator3 = new JSeparator();
		separator4 = new JSeparator();
		halfSpeedRadioButton = new JRadioButton();
		normalSpeedRadioButton = new JRadioButton();
		doubleSpeedRadioButton = new JRadioButton();
		radioButton1 = new JRadioButton();
		radioButton2 = new JRadioButton();
		radioButton3 = new JRadioButton();
		label1 = new JLabel();
		label9 = new JLabel();
		label8 = new JLabel();
		label10 = new JLabel();
		label4 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label13 = new JLabel();
		label12 = new JLabel();
		recLevelSlider = new JSlider();
		label5 = new JLabel();
		slider1 = new JSlider();
		slider2 = new JSlider();
		slider3 = new JSlider();
		slider4 = new JSlider();
		slider5 = new JSlider();
		slider6 = new JSlider();
		slider7 = new JSlider();
		slider8 = new JSlider();
		label7 = new JLabel();
		timeLabel = new JLabel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"10dlu, 3*($lcgap, 40dlu), 8*($lcgap, 50dlu)",
			"20dlu, 5*($lgap, default), $lgap, 51dlu, 5*($lgap, default)"));

		//---- label6 ----
		label6.setText(bundle.getString("JVolanteConsole.label6.text"));
		label6.setFont(new Font("Tahoma", Font.BOLD, 16));
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label6, CC.xywh(3, 3, 11, 1));

		//---- label16 ----
		label16.setText(bundle.getString("JVolanteConsole.label16.text"));
		label16.setFont(new Font("Tahoma", Font.BOLD, 16));
		label16.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label16, CC.xywh(15, 3, 5, 1));

		//---- label11 ----
		label11.setText(bundle.getString("JVolanteConsole.label11.text"));
		label11.setFont(new Font("Tahoma", Font.BOLD, 16));
		label11.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label11, CC.xywh(21, 3, 3, 1));

		//---- label14 ----
		label14.setText(bundle.getString("JVolanteConsole.label14.text"));
		label14.setHorizontalAlignment(SwingConstants.CENTER);
		label14.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label14, CC.xy(3, 5));
		contentPane.add(separator1, CC.xywh(5, 5, 3, 1));

		//---- label15 ----
		label15.setText(bundle.getString("JVolanteConsole.label15.text"));
		label15.setHorizontalAlignment(SwingConstants.CENTER);
		label15.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label15, CC.xy(9, 5));
		contentPane.add(separator2, CC.xywh(11, 5, 3, 1));
		contentPane.add(separator3, CC.xywh(15, 5, 5, 1));
		contentPane.add(separator4, CC.xywh(21, 5, 3, 1));

		//---- halfSpeedRadioButton ----
		halfSpeedRadioButton.setText(bundle.getString("JVolanteConsole.halfSpeedRadioButton.text"));
		halfSpeedRadioButton.setSelected(true);
		halfSpeedRadioButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				halfSpeedRadioButtonItemStateChanged(e);
			}
		});
		contentPane.add(halfSpeedRadioButton, CC.xy(3, 7));

		//---- normalSpeedRadioButton ----
		normalSpeedRadioButton.setText(bundle.getString("JVolanteConsole.normalSpeedRadioButton.text"));
		normalSpeedRadioButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				normalSpeedRadioButtonItemStateChanged(e);
			}
		});
		contentPane.add(normalSpeedRadioButton, CC.xy(5, 7));

		//---- doubleSpeedRadioButton ----
		doubleSpeedRadioButton.setText(bundle.getString("JVolanteConsole.doubleSpeedRadioButton.text"));
		doubleSpeedRadioButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				doubleSpeedRadioButtonItemStateChanged(e);
			}
		});
		contentPane.add(doubleSpeedRadioButton, CC.xy(7, 7));

		//---- radioButton1 ----
		radioButton1.setText(bundle.getString("JVolanteConsole.radioButton1.text"));
		contentPane.add(radioButton1, CC.xy(9, 7));

		//---- radioButton2 ----
		radioButton2.setText(bundle.getString("JVolanteConsole.radioButton2.text"));
		contentPane.add(radioButton2, CC.xy(11, 7));

		//---- radioButton3 ----
		radioButton3.setText(bundle.getString("JVolanteConsole.radioButton3.text"));
		contentPane.add(radioButton3, CC.xy(13, 7));

		//---- label1 ----
		label1.setText(bundle.getString("JVolanteConsole.label1.text"));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(label1, CC.xywh(3, 9, 5, 1));

		//---- label9 ----
		label9.setText(bundle.getString("JVolanteConsole.label9.text"));
		label9.setFont(new Font("Tahoma", Font.BOLD, 12));
		label9.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label9, CC.xy(9, 9));

		//---- label8 ----
		label8.setText(bundle.getString("JVolanteConsole.label8.text"));
		label8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label8, CC.xy(11, 9));

		//---- label10 ----
		label10.setText(bundle.getString("JVolanteConsole.label10.text"));
		label10.setFont(new Font("Tahoma", Font.BOLD, 12));
		label10.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label10, CC.xy(13, 9));

		//---- label4 ----
		label4.setText(bundle.getString("JVolanteConsole.label4.text"));
		label4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label4, CC.xy(15, 9));

		//---- label2 ----
		label2.setText(bundle.getString("JVolanteConsole.label2.text"));
		label2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label2, CC.xy(17, 9));

		//---- label3 ----
		label3.setText(bundle.getString("JVolanteConsole.label3.text"));
		label3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label3, CC.xy(19, 9));

		//---- label13 ----
		label13.setText(bundle.getString("JVolanteConsole.label13.text"));
		label13.setFont(new Font("Tahoma", Font.BOLD, 12));
		label13.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label13, CC.xy(21, 9));

		//---- label12 ----
		label12.setText(bundle.getString("JVolanteConsole.label12.text"));
		label12.setFont(new Font("Tahoma", Font.BOLD, 12));
		label12.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label12, CC.xy(23, 9));

		//---- recLevelSlider ----
		recLevelSlider.setMaximum(127);
		recLevelSlider.setValue(64);
		recLevelSlider.setOrientation(SwingConstants.VERTICAL);
		recLevelSlider.setPaintTicks(true);
		recLevelSlider.setMinorTickSpacing(1);
		recLevelSlider.setMajorTickSpacing(10);
		recLevelSlider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				recLevelSliderPropertyChange(e);
			}
		});
		recLevelSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				recLevelSliderStateChanged(e);
			}
		});
		contentPane.add(recLevelSlider, CC.xywh(5, 11, 1, 5));

		//---- label5 ----
		label5.setText(bundle.getString("JVolanteConsole.label5.text"));
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label5, CC.xy(7, 11));

		//---- slider1 ----
		slider1.setOrientation(SwingConstants.VERTICAL);
		slider1.setMaximum(127);
		slider1.setMinorTickSpacing(1);
		slider1.setPaintTicks(true);
		slider1.setMajorTickSpacing(10);
		contentPane.add(slider1, CC.xywh(9, 11, 1, 5));

		//---- slider2 ----
		slider2.setOrientation(SwingConstants.VERTICAL);
		slider2.setMinorTickSpacing(1);
		slider2.setMajorTickSpacing(10);
		slider2.setMaximum(127);
		slider2.setPaintTicks(true);
		contentPane.add(slider2, CC.xywh(11, 11, 1, 5));

		//---- slider3 ----
		slider3.setOrientation(SwingConstants.VERTICAL);
		slider3.setMinorTickSpacing(1);
		slider3.setMajorTickSpacing(10);
		slider3.setMaximum(127);
		slider3.setPaintTicks(true);
		contentPane.add(slider3, CC.xywh(13, 11, 1, 5));

		//---- slider4 ----
		slider4.setOrientation(SwingConstants.VERTICAL);
		slider4.setMinorTickSpacing(1);
		slider4.setMajorTickSpacing(10);
		slider4.setMaximum(127);
		slider4.setPaintTicks(true);
		contentPane.add(slider4, CC.xywh(15, 11, 1, 5));

		//---- slider5 ----
		slider5.setOrientation(SwingConstants.VERTICAL);
		slider5.setMinorTickSpacing(1);
		slider5.setMajorTickSpacing(10);
		slider5.setMaximum(127);
		slider5.setPaintTicks(true);
		contentPane.add(slider5, CC.xywh(17, 11, 1, 5));

		//---- slider6 ----
		slider6.setOrientation(SwingConstants.VERTICAL);
		slider6.setMinorTickSpacing(1);
		slider6.setMajorTickSpacing(10);
		slider6.setMaximum(127);
		slider6.setPaintTicks(true);
		contentPane.add(slider6, CC.xywh(19, 11, 1, 5));

		//---- slider7 ----
		slider7.setOrientation(SwingConstants.VERTICAL);
		slider7.setMinorTickSpacing(1);
		slider7.setMajorTickSpacing(10);
		slider7.setMaximum(127);
		slider7.setPaintTicks(true);
		contentPane.add(slider7, CC.xywh(21, 11, 1, 5));

		//---- slider8 ----
		slider8.setOrientation(SwingConstants.VERTICAL);
		slider8.setMinorTickSpacing(1);
		slider8.setMajorTickSpacing(10);
		slider8.setMaximum(127);
		slider8.setPaintTicks(true);
		contentPane.add(slider8, CC.xywh(23, 11, 1, 5));

		//---- label7 ----
		label7.setText(bundle.getString("JVolanteConsole.label7.text"));
		label7.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label7, CC.xy(7, 15));

		//---- timeLabel ----
		timeLabel.setText(bundle.getString("JVolanteConsole.timeLabel.text"));
		timeLabel.setFont(new Font("Source Code Pro Black", Font.PLAIN, 26));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(timeLabel, CC.xywh(3, 17, 5, 1));
		setSize(915, 385);
		setLocationRelativeTo(getOwner());

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(halfSpeedRadioButton);
		buttonGroup1.add(normalSpeedRadioButton);
		buttonGroup1.add(doubleSpeedRadioButton);

		//---- buttonGroup2 ----
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(radioButton1);
		buttonGroup2.add(radioButton2);
		buttonGroup2.add(radioButton3);
		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	private JLabel label6;
	private JLabel label16;
	private JLabel label11;
	private JLabel label14;
	private JSeparator separator1;
	private JLabel label15;
	private JSeparator separator2;
	private JSeparator separator3;
	private JSeparator separator4;
	private JRadioButton halfSpeedRadioButton;
	private JRadioButton normalSpeedRadioButton;
	private JRadioButton doubleSpeedRadioButton;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JLabel label1;
	private JLabel label9;
	private JLabel label8;
	private JLabel label10;
	private JLabel label4;
	private JLabel label2;
	private JLabel label3;
	private JLabel label13;
	private JLabel label12;
	private JSlider recLevelSlider;
	private JLabel label5;
	private JSlider slider1;
	private JSlider slider2;
	private JSlider slider3;
	private JSlider slider4;
	private JSlider slider5;
	private JSlider slider6;
	private JSlider slider7;
	private JSlider slider8;
	private JLabel label7;
	private JLabel timeLabel;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
