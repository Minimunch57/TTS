package me.mw.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import me.mw.main.Main;

/**
 * 
 * @author Matthew Whitney
 *
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = -3750577114021586721L;
	private JPanel contentPane;
	private JTextArea txtArea = null;
	private JButton btnSpeak = null;

	/**
	 * <ul>
	 * <p>	<b><i>main</i></b>
	 * <p>	<code>public static void main(String[] args)</code>
	 * <p>	The Main method for the GUI.
	 * @param args - the arguments.
	 * </ul>
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * <ul>
	 * <p>	<b><i>GUI</i></b>
	 * <p>	<code>public GUI()</code>
	 * <p>	Creates a new TTS GUI.
	 * </ul>
	 */
	public GUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/me/mw/resources/Speaker Icon.png")));
		setTitle("TTS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Text to Speech");
		lblTitle.setFocusable(false);
		lblTitle.setToolTipText("Text to Speech by Matthew Whitney");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setBounds(5, 5, 564, 62);
		lblTitle.setFont(new Font("DialogInput", Font.PLAIN, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);
		
		btnSpeak = new JButton("Speak!");
		btnSpeak.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSpeak.setEnabled(false);
				btnSpeak.setText("Speaking...");
				Main.speak(txtArea.getText());
				btnSpeak.setText("Speak!");
				btnSpeak.setEnabled(true);
			}
		});
		
		txtArea = new JTextArea();
		txtArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtArea.setToolTipText("Type what you would like to be spoken here.");
		txtArea.setBounds(5, 69, 584, 265);
		txtArea.setBackground(SystemColor.menu);
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.setFont(new Font("DialogInput", Font.PLAIN, 20));
		contentPane.add(txtArea);
		
		JScrollPane scrollPane = new JScrollPane(txtArea);
		scrollPane.setBorder(null);
		scrollPane.setFocusable(false);
		scrollPane.setBounds(5, 69, 584, 265);
		contentPane.add(scrollPane);
		
		btnSpeak.setBorder(null);
		btnSpeak.setFocusable(false);
		btnSpeak.setFocusPainted(false);
		btnSpeak.setToolTipText("Speak what is in the text box.");
		btnSpeak.setForeground(Color.WHITE);
		btnSpeak.setFont(new Font("Arial", Font.BOLD, 35));
		btnSpeak.setBackground(new Color(30, 144, 255));
		btnSpeak.setBounds(5, 338, 584, 56);
		contentPane.add(btnSpeak);
	}
}
