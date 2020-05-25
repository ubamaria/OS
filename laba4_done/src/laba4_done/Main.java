package laba4_done;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Main {
	private JFrame frame;
	private JTextField textField_sizemem;
	private JTextField textField_sizefile;
	private File file;
	private JTextField textField_name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 503, 591);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new Manager();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.WHITE);
		panel.setBounds(12, 35, 465, 288);
		frame.getContentPane().add(panel);

		final DefaultListModel<String> list2 = new DefaultListModel<String>();
		JList<String> list = new JList<String>(list2);
		list.setForeground(Color.BLACK);
		list.setBorder(new LineBorder(Color.BLACK));
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBackground(Color.WHITE);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String s = (String) list.getSelectedValue();
				if (file != null) {
					int [] ps = file.getKnot().getPositions();
					for (int i = 0; i < ps.length; i++) {
						Manager.setMemoryPoint(ps[i], 2);
					}
				}
				File f = Panel.getfile(s);
				if (f != null) {
					int [] ps = f.getKnot().getPositions();
					if (ps != null) {
						for (int i = 0; i < ps.length; i++) {
							Manager.setMemoryPoint(ps[i], 3);
						}
					}
					panel.repaint();
					file = f;
				}
			}
		});
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					JOptionPane.showMessageDialog(null, file.getKnot().toStr());

				}
			}
		});
		list.setBounds(12, 350, 211, 194);
		frame.getContentPane().add(list);

		JButton btnCreate = new JButton("Размер памяти");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list.removeAll();
				list2.clear();
				Manager.setFree();
				String str = textField_sizemem.getText();
				if (Integer.parseInt(str) % 4 != 0 && str != "") {
					JOptionPane.showMessageDialog(null,
							"Размер памяти должен быть кратным 4.");
				} else {
					Manager.startup(Integer.parseInt(str));
				}
				textField_sizemem.setText("");
				panel.repaint();
			}
		});
		btnCreate.setBounds(220, 7, 148, 23);
		frame.getContentPane().add(btnCreate);

		textField_sizemem = new JTextField();
		textField_sizemem.setBounds(111, 9, 86, 20);
		frame.getContentPane().add(textField_sizemem);
		textField_sizemem.setColumns(10);

		JButton btnAdd = new JButton("Добавить файл");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int size = Integer.parseInt(textField_sizefile.getText());
				if (textField_name.getText().trim().length() > 0) {
						if (size % 4 != 0) {
							while (size % 4 != 0) {
								size++;
							}
						}
						if (size % 4 == 0) {
							String name = textField_name.getText();
							if (name != null) {
								Boolean add = Manager.addFile(name, size);
								if (add) {
									list2.addElement(name);
									list.setModel(list2);
								}
								panel.repaint();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Размер файла должен быть кратным 4.");
						}
				} else {
					JOptionPane.showMessageDialog(null,
							"Введите имя файла");
				}
				textField_name.setText("");
				textField_sizefile.setText("");
			}
		});
		btnAdd.setBounds(243, 408, 148, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnCopy = new JButton("Копировать файла");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_name.getText().trim().length() > 0) {
					String name = textField_name.getText();
					Boolean add = Manager.addFile(name + " - копия", Panel
							.getfile(name).getKnot().fileSize());
					if (add) {
						list2.addElement(name + " - копия");
						list.setModel(list2);
					}
					panel.repaint();
					textField_name.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"Выберите файл для копирования");
				}
			}
		});
		btnCopy.setBounds(241, 474, 150, 23);
		frame.getContentPane().add(btnCopy);

		JButton btnDelete = new JButton("Удалить файл");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_name.getText().trim().length() > 0) {
					String name = textField_name.getText();
					Manager.Delete(name);
					file = null;
					list2.removeElement(name);
					list.setModel(list2);
					panel.repaint();
					textField_name.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"Выберете файл для удаления");
				}
			}
		});
		btnDelete.setBounds(243, 441, 148, 23);
		frame.getContentPane().add(btnDelete);

		JLabel lblNewLabel = new JLabel("Размер файла:");
		lblNewLabel.setBounds(233, 350, 102, 16);
		frame.getContentPane().add(lblNewLabel);

		textField_sizefile = new JTextField();
		textField_sizefile.setBounds(333, 349, 89, 20);
		frame.getContentPane().add(textField_sizefile);
		textField_sizefile.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Имя файла:");
		lblNewLabel_1.setBounds(233, 375, 116, 16);
		frame.getContentPane().add(lblNewLabel_1);

		textField_name = new JTextField();
		textField_name.setBounds(333, 374, 89, 20);
		frame.getContentPane().add(textField_name);
		textField_name.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Память:");
		lblNewLabel_2.setBounds(12, 10, 100, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u0424\u0430\u0439\u043B\u044B:");
		lblNewLabel_3.setBounds(22, 324, 100, 16);
		frame.getContentPane().add(lblNewLabel_3);

	}
}
