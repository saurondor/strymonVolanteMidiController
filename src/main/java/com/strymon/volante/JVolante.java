/*
 * Created by JFormDesigner on Mon Nov 30 20:20:15 CST 2020
 */

package com.strymon.volante;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Gerardo Esteban Tasistro Giubetic
 */
public class JVolante extends JFrame {
	public JVolante() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		comboBox1 = new JComboBox();
		spinner1 = new JSpinner();
		slider1 = new JSlider();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default, 2*($lcgap, 67dlu), $lcgap, 43dlu",
			"3*(default, $lgap), default"));
		contentPane.add(comboBox1, CC.xy(3, 5));
		contentPane.add(spinner1, CC.xy(7, 5));
		contentPane.add(slider1, CC.xywh(5, 7, 3, 1));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JComboBox comboBox1;
	private JSpinner spinner1;
	private JSlider slider1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
