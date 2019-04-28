package awt;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class LayoutTest {

	public static void main(String[] args) {

		Font boldFont = new Font("Tahoma", Font.BOLD, 14);
		Font plainFont = new Font("Tahoma", Font.PLAIN, 14);

		// Header
		JLabel label_header = new JLabel();
		label_header.setFont(boldFont);
		label_header.setText("<html>Section Header</html>");

		JLabel label_header_description = new JLabel();
		label_header_description.setFont(plainFont);
		label_header_description.setText(
				"<html><br>This is the heading description which is deliberately<br>spread across multiple lines.There was no real need<br>but meh...</html>");

		JPanel panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		panel1.add(label_header);
		panel1.add(label_header_description);

		// Question a
		JLabel label_question_a = new JLabel();
		label_question_a.setFont(boldFont);
		label_question_a.setText("<html>Choose from the three options given below,<br> go ahead...</html>");

		ButtonGroup bg_question_a = new ButtonGroup();
		JRadioButton opt_1_question_a = new JRadioButton("Option not too long");
		JRadioButton opt_2_question_a = new JRadioButton("Option small");
		JRadioButton opt_3_question_a = new JRadioButton("Option way too much longer than expected");
		opt_1_question_a.setFont(plainFont);
		opt_2_question_a.setFont(plainFont);
		opt_3_question_a.setFont(plainFont);
		bg_question_a.add(opt_1_question_a);
		bg_question_a.add(opt_2_question_a);
		bg_question_a.add(opt_3_question_a);

		JPanel panel2 = new JPanel();
		panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		panel2.add(label_question_a);
		panel2.add(opt_1_question_a);
		panel2.add(opt_2_question_a);
		panel2.add(opt_3_question_a);

		// Question b
		JLabel label_question_b = new JLabel();
		label_question_b.setFont(boldFont);
		label_question_b.setText("<html>Another chance to choose,<br> Ready?</html>");

		ButtonGroup bg_question_b = new ButtonGroup();
		JRadioButton opt_1_question_b = new JRadioButton("Yeah");
		JRadioButton opt_2_question_b = new JRadioButton("Naah");
		JRadioButton opt_3_question_b = new JRadioButton("Whatever");
		opt_1_question_b.setFont(plainFont);
		opt_2_question_b.setFont(plainFont);
		opt_3_question_b.setFont(plainFont);
		bg_question_b.add(opt_1_question_b);
		bg_question_b.add(opt_2_question_b);
		bg_question_b.add(opt_3_question_b);

		JPanel panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

		panel3.add(label_question_b);
		panel3.add(opt_1_question_b);
		panel3.add(opt_2_question_b);
		panel3.add(opt_3_question_b);

		// Question c
		JLabel label_question_c = new JLabel();
		label_question_c.setFont(boldFont);
		label_question_c.setText("<html>How was the tea?</html>");

		JComboBox<String> options_question_c = new JComboBox<>(
				new String[] { "good", "bad", "Fantastic!!", "Just to set the width" });
		options_question_c.setFont(plainFont);
		options_question_c.setMaximumSize(options_question_c.getPreferredSize());
		options_question_c.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel panel4 = new JPanel();
		panel4.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

		panel4.add(label_question_c);
		panel4.add(options_question_c);

		// Question d
		JLabel label_question_d = new JLabel();
		label_question_d.setFont(boldFont);
		label_question_d.setText("<html>Want one more chance?</html>");

		ButtonGroup bg_question_d = new ButtonGroup();
		JRadioButton opt_1_question_d = new JRadioButton("Ok");
		JRadioButton opt_2_question_d = new JRadioButton("Bye");
		JRadioButton opt_3_question_d = new JRadioButton("Why?");
		opt_1_question_d.setFont(plainFont);
		opt_2_question_d.setFont(plainFont);
		opt_3_question_d.setFont(plainFont);
		bg_question_d.add(opt_1_question_d);
		bg_question_d.add(opt_2_question_d);
		bg_question_d.add(opt_3_question_d);

		JPanel panel5 = new JPanel();
		panel5.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

		panel5.add(label_question_d);
		panel5.add(opt_1_question_d);
		panel5.add(opt_2_question_d);
		panel5.add(opt_3_question_d);

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Layout Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
		frame.add(panel5);

		frame.pack();
		frame.setVisible(true);
	}
}
