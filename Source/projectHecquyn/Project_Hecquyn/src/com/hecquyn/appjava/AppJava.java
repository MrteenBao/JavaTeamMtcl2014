package com.hecquyn.appjava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JTextField;
import java.awt.List;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Canvas;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import java.awt.SystemColor;

/**
*
* @author Lam Viet Tri
*/

public class AppJava {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppJava window = new AppJava();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppJava() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Hercules Admin");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnGeneral = new JMenu("General");
		mnGeneral.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\Actions-home-icon (1).png"));
		menuBar.add(mnGeneral);
		
		JMenuItem mntmRefesh = new JMenuItem("Refresh");
		mntmRefesh.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\Reload-icon (1).png"));
		mnGeneral.add(mntmRefesh);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sign out");
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\SignOut-icon (1).png"));
		mnGeneral.add(mntmNewMenuItem);
		
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\delete-icon.png"));
		mnGeneral.add(mntmExit);
		
		
		
		JMenu mnOption = new JMenu("Option");
		mnOption.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\shopping-cart-icon.png"));
		menuBar.add(mnOption);
		
		JMenu mnNewMenu_1 = new JMenu("Category");
		mnNewMenu_1.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\category-item-select-icon.png"));
		mnOption.add(mnNewMenu_1);
		
		JMenuItem mntmW = new JMenuItem("World");
		mntmW.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\Whack-Google-Earth-icon.png"));
		mnNewMenu_1.add(mntmW);
		
		JMenuItem mntmFinace = new JMenuItem("Finace");
		mntmFinace.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\dollar-icon.png"));
		mnNewMenu_1.add(mntmFinace);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sport");
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\Sports-Football-2-icon.png"));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmEntertaiment = new JMenuItem("Entertaiment");
		mntmEntertaiment.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\Alarm-Play-icon.png"));
		mnNewMenu_1.add(mntmEntertaiment);
		
		JMenuItem mntmTech = new JMenuItem("Tech");
		mntmTech.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\technology-icon.png"));
		mnNewMenu_1.add(mntmTech);
		
		JMenu mnNewMenu = new JMenu("Help");
		mnNewMenu.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\help-icon (1).png"));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmTututorial = new JMenuItem("Tutorial");
		mntmTututorial.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\Button-Help-icon.png"));
		mnNewMenu.add(mntmTututorial);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\back-icon.png"));
		panel.add(lblNewLabel_2);
		
		Button button_2 = new Button("Back");
		panel.add(button_2);
		
		JLabel lblNewLabel_3 = new JLabel("    ");
		panel.add(lblNewLabel_3);
		
		JLabel lblNext = new JLabel("");
		lblNext.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\next-icon.png"));
		panel.add(lblNext);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		Button button_1 = new Button("Next");
		panel.add(button_1);
		
		JLabel lblNewLabel_1 = new JLabel("                          ");
		panel.add(lblNewLabel_1);
		
		JLabel lblChoose = new JLabel("");
		lblChoose.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChoose.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\check-icon.png"));
		panel.add(lblChoose);
		
		Button button = new Button("Choose");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\lamvi\\workspace\\Hecquyn\\src\\com\\hecquyn\\appjava\\Picture\\Tomahawk.jpg"));
		lblNewLabel_4.setBounds(10, 11, 281, 179);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setForeground(Color.PINK);
		lblCategory.setBounds(301, 11, 56, 14);
		panel_1.add(lblCategory);
		
		JLabel lblWorld = new JLabel("World");
		lblWorld.setForeground(Color.BLUE);
		lblWorld.setBounds(358, 11, 46, 14);
		panel_1.add(lblWorld);
		
		JLabel lblNewLabel_5 = new JLabel("Mỹ bắn hàng chục tên lửa Tomahawk vào Syria sau vụ tấn công hóa học");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(301, 36, 123, 26);
		panel_1.add(lblNewLabel_5);
		
		JEditorPane dtrpnMBn = new JEditorPane();
		dtrpnMBn.setForeground(Color.BLACK);
		dtrpnMBn.setBackground(new Color(240, 240, 240));
		dtrpnMBn.setEditable(false);
		dtrpnMBn.setEnabled(false);
		dtrpnMBn.setText("Mỹ đã bắn khoảng 60 tên lửa Tomahawk vào một căn cứ không quân của Syria để trả đũa vụ tấn công hoá học diễn ra cách đây vài ngày.");
		dtrpnMBn.setBounds(301, 73, 123, 112);
		panel_1.add(dtrpnMBn);
		
		

}
	}
