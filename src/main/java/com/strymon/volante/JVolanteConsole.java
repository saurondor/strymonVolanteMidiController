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

	private void setSpeed() {
		int speed = 0;
		int value = recLevelSlider.getValue();
		if (halfSpeedRadioButton.isSelected()) {
			speed = 2;
		}
		if (normalSpeedRadioButton.isSelected()) {
			speed = 3;
		}
		if (doubleSpeedRadioButton.isSelected()) {
			speed = 1;
		}
		try {
			updateSpeedSlider(value);
			volante.updateSpeed(speed);
		} catch (InvalidMidiDataException e) {
			JOptionPane.showMessageDialog(this, "Couldn't update speed. " + e.getMessage(), "MIDI Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateSpeedSlider(int value) {
		double range;
		double maxRange = 4000d;
		double minRange = 400d;
		double slider = Integer.valueOf(value).doubleValue();
		if (halfSpeedRadioButton.isSelected()) {
			maxRange = 4000d;
			minRange = 400d;
		}
		if (normalSpeedRadioButton.isSelected()) {
			maxRange = 2000d;
			minRange = 200d;
		}
		if (doubleSpeedRadioButton.isSelected()) {
			maxRange = 1000d;
			minRange = 100d;
		}
		maxTimeRangeLabel.setText(String.format("%,.0f ms", maxRange));
		minTimeRangeLabel.setText(String.format("%,.0f ms", minRange));
		range = maxRange - minRange;
		double delay = range * (slider / 127d) + minRange;
		timeLabel.setText(Double.valueOf(delay).intValue() + " ms");
	}

	private void setTime() {
		try {
			int value = recLevelSlider.getValue();
			logger.info("Sent speed msg " + value);
			updateSpeedSlider(value);
			volante.updateTime(value);
		} catch (InvalidMidiDataException e) {
			JOptionPane.showMessageDialog(this, "Couldn't update time. " + e.getMessage(), "MIDI Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void halfSpeedRadioButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setSpeed();
		}
	}

	private void normalSpeedRadioButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setSpeed();
		}
	}

	private void doubleSpeedRadioButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setSpeed();
		}
	}

	private void recLevelSliderStateChanged(ChangeEvent e) {
		setTime();
	}

	private void levelHead1SliderPropertyChange(PropertyChangeEvent e) {
		try {
			volante.updateLevelHead1(levelHead1Slider.getValue());
		} catch (InvalidMidiDataException e1) {
			JOptionPane.showMessageDialog(this, "Couldn't update head level. " + e1.getMessage(), "MIDI Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void levelHead2SliderPropertyChange(PropertyChangeEvent e) {
		try {
			volante.updateLevelHead2(levelHead2Slider.getValue());
		} catch (InvalidMidiDataException e1) {
			JOptionPane.showMessageDialog(this, "Couldn't update head level. " + e1.getMessage(), "MIDI Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void levelHead3SliderPropertyChange(PropertyChangeEvent e) {
		try {
			volante.updateLevelHead3(levelHead3Slider.getValue());
		} catch (InvalidMidiDataException e1) {
			JOptionPane.showMessageDialog(this, "Couldn't update head level. " + e1.getMessage(), "MIDI Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void levelHead4SliderPropertyChange(PropertyChangeEvent e) {
		try {
			volante.updateLevelHead4(levelHead4Slider.getValue());
		} catch (InvalidMidiDataException e1) {
			JOptionPane.showMessageDialog(this, "Couldn't update head level. " + e1.getMessage(), "MIDI Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void panHead1SliderPropertyChange(PropertyChangeEvent e) {
		// TODO add your code here
	}

	private void panHead2SliderPropertyChange(PropertyChangeEvent e) {
		// TODO add your code here
	}

	private void panHead3SliderPropertyChange(PropertyChangeEvent e) {
		// TODO add your code here
	}

	private void panHead4SliderPropertyChange(PropertyChangeEvent e) {
		// TODO add your code here
	}

	private void head1PlaybackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head1PlaybackButton);
		} else {
			setHeadOff(head1PlaybackButton);
		}
	}

	private void setHeadOn(JToggleButton head) {
		head.setBackground(new Color(240, 140, 040));
		head.setForeground(new Color(240, 0, 0));
		head.setText("On");
	}

	private void setHeadOff(JToggleButton head) {
		head.setBackground(new Color(240, 240, 240));
		head.setForeground(new Color(0, 0, 0));
		head.setText("Off");
	}

	private void head2PlaybackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head2PlaybackButton);
		} else {
			setHeadOff(head2PlaybackButton);
		}
	}

	private void head3PlaybackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head3PlaybackButton);
		} else {
			setHeadOff(head3PlaybackButton);
		}
	}

	private void head4PlaybackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head4PlaybackButton);
		} else {
			setHeadOff(head4PlaybackButton);
		}
	}

	private void head1FeedbackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head1FeedbackButton);
		} else {
			setHeadOff(head1FeedbackButton);
		}
	}

	private void head2FeedbackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head2FeedbackButton);
		} else {
			setHeadOff(head2FeedbackButton);
		}
	}

	private void head3FeedbackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head3FeedbackButton);
		} else {
			setHeadOff(head3FeedbackButton);
		}
	}

	private void head4FeedbackButtonItemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			setHeadOn(head4FeedbackButton);
		} else {
			setHeadOff(head4FeedbackButton);
		}
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
		maxTimeRangeLabel = new JLabel();
		slider1 = new JSlider();
		slider2 = new JSlider();
		slider3 = new JSlider();
		slider4 = new JSlider();
		slider5 = new JSlider();
		slider6 = new JSlider();
		slider7 = new JSlider();
		slider8 = new JSlider();
		minTimeRangeLabel = new JLabel();
		timeLabel = new JLabel();
		label17 = new JLabel();
		label5 = new JLabel();
		label7 = new JLabel();
		label19 = new JLabel();
		label20 = new JLabel();
		label21 = new JLabel();
		levelHead1Slider = new JSlider();
		levelHead2Slider = new JSlider();
		levelHead3Slider = new JSlider();
		levelHead4Slider = new JSlider();
		label22 = new JLabel();
		panHead1Slider = new JSlider();
		panHead2Slider = new JSlider();
		panHead3Slider = new JSlider();
		panHead4Slider = new JSlider();
		label23 = new JLabel();
		head1PlaybackButton = new JToggleButton();
		head2PlaybackButton = new JToggleButton();
		head3PlaybackButton = new JToggleButton();
		head4PlaybackButton = new JToggleButton();
		label24 = new JLabel();
		head1FeedbackButton = new JToggleButton();
		head2FeedbackButton = new JToggleButton();
		head3FeedbackButton = new JToggleButton();
		head4FeedbackButton = new JToggleButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"10dlu, $lcgap, 40dlu, 4*($lcgap, 45dlu), 6*($lcgap, 50dlu)",
			"20dlu, 5*($lgap, default), $lgap, 51dlu, 8*($lgap, default), $lgap, 19dlu"));

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

		//---- maxTimeRangeLabel ----
		maxTimeRangeLabel.setText(bundle.getString("JVolanteConsole.maxTimeRangeLabel.text"));
		maxTimeRangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(maxTimeRangeLabel, CC.xy(7, 11));

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

		//---- minTimeRangeLabel ----
		minTimeRangeLabel.setText(bundle.getString("JVolanteConsole.minTimeRangeLabel.text"));
		minTimeRangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(minTimeRangeLabel, CC.xy(7, 15));

		//---- timeLabel ----
		timeLabel.setText(bundle.getString("JVolanteConsole.timeLabel.text"));
		timeLabel.setFont(new Font("Source Code Pro Black", Font.PLAIN, 26));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(timeLabel, CC.xywh(3, 17, 5, 1));

		//---- label17 ----
		label17.setText(bundle.getString("JVolanteConsole.label17.text"));
		label17.setFont(new Font("Tahoma", Font.BOLD, 16));
		label17.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label17, CC.xywh(2, 19, 12, 1));

		//---- label5 ----
		label5.setText(bundle.getString("JVolanteConsole.label5.text"));
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label5, CC.xy(5, 21));

		//---- label7 ----
		label7.setText(bundle.getString("JVolanteConsole.label7.text"));
		label7.setHorizontalAlignment(SwingConstants.CENTER);
		label7.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label7, CC.xy(7, 21));

		//---- label19 ----
		label19.setText(bundle.getString("JVolanteConsole.label19.text"));
		label19.setHorizontalAlignment(SwingConstants.CENTER);
		label19.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label19, CC.xy(9, 21));

		//---- label20 ----
		label20.setText(bundle.getString("JVolanteConsole.label20.text"));
		label20.setHorizontalAlignment(SwingConstants.CENTER);
		label20.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label20, CC.xy(11, 21));

		//---- label21 ----
		label21.setText(bundle.getString("JVolanteConsole.label21.text"));
		label21.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label21, CC.xy(3, 23));

		//---- levelHead1Slider ----
		levelHead1Slider.setOrientation(SwingConstants.VERTICAL);
		levelHead1Slider.setMaximum(127);
		levelHead1Slider.setMinorTickSpacing(1);
		levelHead1Slider.setPaintTicks(true);
		levelHead1Slider.setMajorTickSpacing(10);
		levelHead1Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				levelHead1SliderPropertyChange(e);
			}
		});
		contentPane.add(levelHead1Slider, CC.xy(5, 23));

		//---- levelHead2Slider ----
		levelHead2Slider.setOrientation(SwingConstants.VERTICAL);
		levelHead2Slider.setMaximum(127);
		levelHead2Slider.setMinorTickSpacing(1);
		levelHead2Slider.setPaintTicks(true);
		levelHead2Slider.setMajorTickSpacing(10);
		levelHead2Slider.setValue(127);
		levelHead2Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				levelHead2SliderPropertyChange(e);
			}
		});
		contentPane.add(levelHead2Slider, CC.xy(7, 23));

		//---- levelHead3Slider ----
		levelHead3Slider.setOrientation(SwingConstants.VERTICAL);
		levelHead3Slider.setMaximum(127);
		levelHead3Slider.setMinorTickSpacing(1);
		levelHead3Slider.setPaintTicks(true);
		levelHead3Slider.setMajorTickSpacing(10);
		levelHead3Slider.setValue(20);
		levelHead3Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				levelHead3SliderPropertyChange(e);
			}
		});
		contentPane.add(levelHead3Slider, CC.xy(9, 23));

		//---- levelHead4Slider ----
		levelHead4Slider.setOrientation(SwingConstants.VERTICAL);
		levelHead4Slider.setMaximum(127);
		levelHead4Slider.setMinorTickSpacing(1);
		levelHead4Slider.setPaintTicks(true);
		levelHead4Slider.setMajorTickSpacing(10);
		levelHead4Slider.setValue(110);
		levelHead4Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				levelHead4SliderPropertyChange(e);
			}
		});
		contentPane.add(levelHead4Slider, CC.xy(11, 23));

		//---- label22 ----
		label22.setText(bundle.getString("JVolanteConsole.label22.text"));
		label22.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label22, CC.xy(3, 25));

		//---- panHead1Slider ----
		panHead1Slider.setMaximum(127);
		panHead1Slider.setMinorTickSpacing(1);
		panHead1Slider.setPaintTicks(true);
		panHead1Slider.setMajorTickSpacing(10);
		panHead1Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				panHead1SliderPropertyChange(e);
			}
		});
		contentPane.add(panHead1Slider, CC.xy(5, 25));

		//---- panHead2Slider ----
		panHead2Slider.setMaximum(127);
		panHead2Slider.setMinorTickSpacing(1);
		panHead2Slider.setPaintTicks(true);
		panHead2Slider.setMajorTickSpacing(10);
		panHead2Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				panHead2SliderPropertyChange(e);
			}
		});
		contentPane.add(panHead2Slider, CC.xy(7, 25));

		//---- panHead3Slider ----
		panHead3Slider.setMaximum(127);
		panHead3Slider.setMinorTickSpacing(1);
		panHead3Slider.setPaintTicks(true);
		panHead3Slider.setMajorTickSpacing(10);
		panHead3Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				panHead3SliderPropertyChange(e);
			}
		});
		contentPane.add(panHead3Slider, CC.xy(9, 25));

		//---- panHead4Slider ----
		panHead4Slider.setMaximum(127);
		panHead4Slider.setMinorTickSpacing(1);
		panHead4Slider.setPaintTicks(true);
		panHead4Slider.setMajorTickSpacing(10);
		panHead4Slider.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				panHead4SliderPropertyChange(e);
			}
		});
		contentPane.add(panHead4Slider, CC.xy(11, 25));

		//---- label23 ----
		label23.setText(bundle.getString("JVolanteConsole.label23.text"));
		label23.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label23, CC.xy(3, 27));

		//---- head1PlaybackButton ----
		head1PlaybackButton.setText(bundle.getString("JVolanteConsole.head1PlaybackButton.text"));
		head1PlaybackButton.setOpaque(true);
		head1PlaybackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head1PlaybackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head1PlaybackButton, CC.xy(5, 27));

		//---- head2PlaybackButton ----
		head2PlaybackButton.setText(bundle.getString("JVolanteConsole.head2PlaybackButton.text"));
		head2PlaybackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head2PlaybackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head2PlaybackButton, CC.xy(7, 27));

		//---- head3PlaybackButton ----
		head3PlaybackButton.setText(bundle.getString("JVolanteConsole.head3PlaybackButton.text"));
		head3PlaybackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head3PlaybackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head3PlaybackButton, CC.xy(9, 27));

		//---- head4PlaybackButton ----
		head4PlaybackButton.setText(bundle.getString("JVolanteConsole.head4PlaybackButton.text"));
		head4PlaybackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head4PlaybackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head4PlaybackButton, CC.xy(11, 27));

		//---- label24 ----
		label24.setText(bundle.getString("JVolanteConsole.label24.text"));
		label24.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(label24, CC.xy(3, 29));

		//---- head1FeedbackButton ----
		head1FeedbackButton.setText(bundle.getString("JVolanteConsole.head1FeedbackButton.text"));
		head1FeedbackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head1FeedbackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head1FeedbackButton, CC.xy(5, 29));

		//---- head2FeedbackButton ----
		head2FeedbackButton.setText(bundle.getString("JVolanteConsole.head2FeedbackButton.text"));
		head2FeedbackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head2FeedbackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head2FeedbackButton, CC.xy(7, 29));

		//---- head3FeedbackButton ----
		head3FeedbackButton.setText(bundle.getString("JVolanteConsole.head3FeedbackButton.text"));
		head3FeedbackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head3FeedbackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head3FeedbackButton, CC.xy(9, 29));

		//---- head4FeedbackButton ----
		head4FeedbackButton.setText(bundle.getString("JVolanteConsole.head4FeedbackButton.text"));
		head4FeedbackButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				head4FeedbackButtonItemStateChanged(e);
			}
		});
		contentPane.add(head4FeedbackButton, CC.xy(11, 29));
		setSize(915, 635);
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
	private JLabel maxTimeRangeLabel;
	private JSlider slider1;
	private JSlider slider2;
	private JSlider slider3;
	private JSlider slider4;
	private JSlider slider5;
	private JSlider slider6;
	private JSlider slider7;
	private JSlider slider8;
	private JLabel minTimeRangeLabel;
	private JLabel timeLabel;
	private JLabel label17;
	private JLabel label5;
	private JLabel label7;
	private JLabel label19;
	private JLabel label20;
	private JLabel label21;
	private JSlider levelHead1Slider;
	private JSlider levelHead2Slider;
	private JSlider levelHead3Slider;
	private JSlider levelHead4Slider;
	private JLabel label22;
	private JSlider panHead1Slider;
	private JSlider panHead2Slider;
	private JSlider panHead3Slider;
	private JSlider panHead4Slider;
	private JLabel label23;
	private JToggleButton head1PlaybackButton;
	private JToggleButton head2PlaybackButton;
	private JToggleButton head3PlaybackButton;
	private JToggleButton head4PlaybackButton;
	private JLabel label24;
	private JToggleButton head1FeedbackButton;
	private JToggleButton head2FeedbackButton;
	private JToggleButton head3FeedbackButton;
	private JToggleButton head4FeedbackButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
