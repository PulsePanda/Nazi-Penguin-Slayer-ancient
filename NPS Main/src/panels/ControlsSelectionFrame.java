package panels;

import files.FILES;
import io.IO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.Dialogs;
import main.Start;

public class ControlsSelectionFrame extends JFrame {
	IO io = new IO();
	int w = 425, h = 225;
	JButton confirm, cancel, up, down, left, right, jump, duck, attack;

	ControlSetFrame csf;

	public ControlsSelectionFrame() {
		setSize(w, h);
		setUndecorated(true);
		setResizable(false);
		setLocation((int) (Start.mf.getX() + (0.25 * Start.mf.getWidth())),
				(int) (Start.mf.getY() + (0.25 * Start.mf.getHeight())));
		setAlwaysOnTop(true);
		setLayout(null);
		setVisible(true);

		up = new JButton("Up");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				csf = new ControlSetFrame("up");
				dispose();
			}
		});
		up.setBounds(20, 20, 100, 50);
		add(up);

		down = new JButton("Down");
		down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				csf = new ControlSetFrame("down");
				dispose();
			}
		});
		down.setBounds(20, 70, 100, 50);
		add(down);

		left = new JButton("Left");
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				csf = new ControlSetFrame("left");
				dispose();
			}
		});
		left.setBounds(20, 120, 100, 50);
		add(left);

		right = new JButton("Right");
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				csf = new ControlSetFrame("right");
				dispose();
			}
		});
		right.setBounds(125, 20, 100, 50);
		add(right);

		attack = new JButton("Attack");
		attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				csf = new ControlSetFrame("attack");
				dispose();
			}
		});
		attack.setBounds(125, 70, 100, 50);
		add(attack);

		cancel = new JButton();
		cancel.setIcon(new ImageIcon(io.getImage(FILES.controlsCancelButton)));
		cancel.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.controlsCancelButtonRollover)));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancel.setBorderPainted(false);
		cancel.setBounds(145, 150, 248, 48);
		add(cancel);
	}

	public void addThings() {
		setUpComponents();
	}

	public void setUpComponents() {
		confirm = new JButton();
		confirm.setIcon(new ImageIcon(io.getImage(FILES.controlsConfirmButton)));
		confirm.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.controlsConfirmButtonRollover)));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		confirm.setBorderPainted(false);
		confirm.setBounds(145, 20, 248, 48);
		add(confirm);

		cancel = new JButton();
		cancel.setIcon(new ImageIcon(io.getImage(FILES.controlsCancelButton)));
		cancel.setRolloverIcon(new ImageIcon(io
				.getImage(FILES.controlsCancelButtonRollover)));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dialogs.c.dispose();
			}
		});
		cancel.setBorderPainted(false);
		cancel.setBounds(145, 85, 248, 48);
		add(cancel);
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawImage(io.getImage(FILES.controlWindowBackground), 0, 0, 425, 225,
				null);

		up.repaint();
		down.repaint();
		left.repaint();
		right.repaint();
		attack.repaint();
		cancel.repaint();
	}
}
