package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import helpers.DataValidator;
import helpers.ImageHelper;
import helpers.MessageDialogHelper;
import model.SinhVien;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;

import dao.SinhVienDao;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class StudentManagementPanel extends JPanel {
	private MainForm parentForm;
	private DefaultTableModel tblModel;

	private JTextField txtStudentId;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTable tblStudents;
	
	private byte[] personalImage;
	private JLabel lblImage;

	/**
	 * Create the panel.
	 */
	public StudentManagementPanel() {

		JLabel lblNewLabel = new JLabel("Quản Lý Khách Hàng");
		lblNewLabel.setForeground(new Color(255, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

		JSeparator separator = new JSeparator();

		JLabel lblNewLabel_1 = new JLabel("Mã Khách Hàng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblNewLabel_1_1 = new JLabel("Họ Tên");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblNewLabel_1_1_1 = new JLabel("Email");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Số Điện Thoại");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Địa Chỉ");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Giới Tính");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtStudentId = new JTextField();
		txtStudentId.setColumns(10);

		txtName = new JTextField();
		txtName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JRadioButton rdbMale = new JRadioButton("Nam");
		rdbMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JRadioButton rdbFemale = new JRadioButton("Nữ");
		rdbFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonGroup.add(rdbFemale);
		buttonGroup.add(rdbMale);
		JTextArea txaAddress = new JTextArea();

		JButton btnNew = new JButton("Tạo Mới");
		btnNew.setBackground(Color.ORANGE);
		btnNew.setIcon(new ImageIcon(StudentManagementPanel.class.getResource("/icons/new-icon-16.png")));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setText("");
				txtName.setText("");
				txtPhone.setText("");
				txtStudentId.setText("");
				txaAddress.setText("");
				
				personalImage = null;
				ImageIcon icon = new ImageIcon(getClass().getResource("/icons/10207-man-student-light-skin-tone-icon-64.png"));
				lblImage.setIcon(icon);
				
			}
		});

		JButton btnDelete = new JButton("Xóa");
		btnDelete.setBackground(Color.ORANGE);
		btnDelete.setIcon(
				new ImageIcon(StudentManagementPanel.class.getResource("/icons/Actions-edit-delete-icon-16.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				DataValidator.validateEmpty(txtStudentId, sb, "Mã sinh viên không được để trống!");
				if (sb.length() > 0) {
					MessageDialogHelper.showErrorDialog(parentForm, sb.toString(), "Lỗi");
					return;
				}
				if (MessageDialogHelper.showConfirmDialog(parentForm, "Bạn có muốn xóa sinh viên không?",
						"Hỏi") == JOptionPane.NO_OPTION) {
					return;
				}
				try {
					SinhVienDao dao = new SinhVienDao();
					if (dao.delete(txtStudentId.getText())) {
						MessageDialogHelper.showMessageDialog(parentForm, "Sinh viên đã được xóa", "Thông báo");
						btnNew.addActionListener(this);
						loadDataToTable();
					} else {
						MessageDialogHelper.showConfirmDialog(parentForm, "Sinh viên không được xóa do lỗi",
								"Cảnh báo");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "Lỗi");
				}
			}
		});

		JButton btnSave = new JButton("Lưu");
		btnSave.setBackground(Color.ORANGE);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				DataValidator.validateEmpty(txtStudentId, sb, "Mã sinh viên không được để trống!");
				DataValidator.validateEmpty(txtName, sb, "Tên sinh viên không được để trống!");
				if (sb.length() > 0) {
					MessageDialogHelper.showErrorDialog(parentForm, sb.toString(), "Lỗi");
					return;
				}
				try {
					SinhVien sv = new SinhVien();
					sv.setMaSinhVien(txtStudentId.getText());
					sv.setHoTen(txtName.getText());
					sv.setEmail(txtEmail.getText());
					sv.setSoDT(txtPhone.getText());
					sv.setDiaChi(txaAddress.getText());
					sv.setGioiTinh(rdbMale.isSelected() ? 1 : 0);

					sv.setHinh(personalImage);
					
					SinhVienDao dao = new SinhVienDao();
					if (dao.insert(sv)) {
						MessageDialogHelper.showMessageDialog(parentForm, "Sinh viên đã được lưu", "Thông báo");
						loadDataToTable();
					} else {
						MessageDialogHelper.showConfirmDialog(parentForm, "Sinh viên không được lưu do lỗi",
								"Cảnh báo");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "Lỗi");
				}
			}
		});
		btnSave.setIcon(new ImageIcon(StudentManagementPanel.class.getResource("/icons/Save-icon.png")));

		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				DataValidator.validateEmpty(txtStudentId, sb, "Mã sinh viên không được để trống!");
				DataValidator.validateEmpty(txtName, sb, "Tên sinh viên không được để trống!");
				if (sb.length() > 0) {
					MessageDialogHelper.showErrorDialog(parentForm, sb.toString(), "Lỗi");
					return;
				}
				if (MessageDialogHelper.showConfirmDialog(parentForm, "Bạn có muốn cập nhật sinh viên không?",
						"Hỏi") == JOptionPane.NO_OPTION) {
					return;
				}
				try {
					SinhVien sv = new SinhVien();
					sv.setMaSinhVien(txtStudentId.getText());
					sv.setHoTen(txtName.getText());
					sv.setEmail(txtEmail.getText());
					sv.setSoDT(txtPhone.getText());
					sv.setDiaChi(txaAddress.getText());
					sv.setGioiTinh(rdbMale.isSelected() ? 1 : 0);
					
					sv.setHinh(personalImage);
					
					SinhVienDao dao = new SinhVienDao();
					if (dao.update(sv)) {
						MessageDialogHelper.showMessageDialog(parentForm, "Sinh viên đã được cập nhật", "Thông báo");
						loadDataToTable();
					} else {
						MessageDialogHelper.showConfirmDialog(parentForm, "Sinh viên không được cập nhật do lỗi",
								"Cảnh báo");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "Lỗi");
				}
			}
		});
		btnUpdate.setIcon(
				new ImageIcon(StudentManagementPanel.class.getResource("/icons/Actions-document-edit-icon-16.png")));

		JSeparator separator_1 = new JSeparator();

		tblStudents = new JTable();
		tblStudents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = tblStudents.getSelectedRow();

					if (row >= 0) {
						String id = (String) tblStudents.getValueAt(row, 0);
						SinhVienDao dao = new SinhVienDao();
						SinhVien sv = dao.findById(id);

						if (sv != null) {
							txtStudentId.setText(sv.getMaSinhVien());
							txtName.setText(sv.getHoTen());
							txtEmail.setText(sv.getEmail());
							txtPhone.setText(sv.getSoDT());
							txaAddress.setText(sv.getDiaChi());
							if (sv.getGioiTinh() == 1) {
								rdbMale.setSelected(true);
								rdbFemale.setSelected(false);
							} else {
								rdbFemale.setSelected(true);
								rdbMale.setSelected(false);
							}
							
							if(sv.getHinh() != null) {
								Image img = ImageHelper.createImageFromByteArray(sv.getHinh(), "jpg");
								lblImage.setIcon(new ImageIcon(img));
								personalImage = sv.getHinh();
							}else {
								personalImage = sv.getHinh();
								ImageIcon icon = new ImageIcon(getClass().getResource("/icons/10207-man-student-light-skin-tone-icon-64.png"));
								lblImage.setIcon(icon);
							}
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "Lỗi");
				}
			}
		});

		JSeparator separator_2 = new JSeparator();

		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(30)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
											.addGap(29)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(rdbMale, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
													.addGap(9)
													.addComponent(rdbFemale, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
												.addComponent(txtName)
												.addComponent(txtStudentId, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
												.addComponent(txtEmail)
												.addComponent(txtPhone)
												.addComponent(txaAddress, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(59)
											.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
											.addGap(48)
											.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
											.addGap(26)
											.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
									.addGap(18)
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)
								.addComponent(tblStudents, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(249, Short.MAX_VALUE)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE)))
					.addGap(227))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(261)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(545, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_1)
									.addGap(12)
									.addComponent(lblNewLabel_1_1)
									.addGap(12)
									.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(lblNewLabel_1_1_1_1)
									.addGap(17)
									.addComponent(lblNewLabel_1_1_1_1_1_1)
									.addGap(32)
									.addComponent(lblNewLabel_1_1_1_1_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtStudentId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(rdbMale)
										.addComponent(rdbFemale))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txaAddress, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(13)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
							.addGap(3)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tblStudents, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		panel_1.setLayout(null);

		 lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon("D:\\Code\\java\\demo_HSK\\App_QLLK\\src\\icons\\dogeIcon.png"));
		lblImage.setBounds(10, 10, 147, 146);
		panel_1.add(lblImage);

		JButton btnBrowse = new JButton("Mở hình");
		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowse.setBackground(Color.ORANGE);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "Image File (*.jpg)";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".jpg");
						}
					}
				});
				if (chooser.showOpenDialog(parentForm) == JFileChooser.CANCEL_OPTION) {
					return;
				}
				File file = chooser.getSelectedFile();
				try {
					ImageIcon icon = new ImageIcon(file.getPath());
					Image img = ImageHelper.resize(icon.getImage(), 140, 160);
					ImageIcon resizedIcon = new ImageIcon(img);
					lblImage.setIcon(resizedIcon);
					personalImage = ImageHelper.toByteArray(img, "jpg");
					
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showMessageDialog(parentForm, e2.getMessage(), "Loi!");
				}
			}
		});
		btnBrowse.setIcon(new ImageIcon(StudentManagementPanel.class.getResource("/icons/open-file-icon-16.png")));
		btnBrowse.setBounds(30, 154, 112, 33);
		panel_1.add(btnBrowse);
		setLayout(groupLayout);

		initTable();

		loadDataToTable();
	}

	private void initTable() {

		
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(
				new String[] { "Mã Sinh Viên", "Họ Tên", "Email", "Số ĐT", "Giới Tính", "Địa Chỉ" });
//		tblStudents = new JTable(tblModel);
		tblStudents.setModel(tblModel);
		
		
		
//		JScrollPane scrollPane = new JScrollPane(tblStudents);
	}

	private void loadDataToTable() {
		try {
			SinhVienDao dao = new SinhVienDao();
			List<SinhVien> list = dao.findAll();
			tblModel.setRowCount(0);
			for (SinhVien sv : list) {
				tblModel.addRow(new Object[] { sv.getMaSinhVien(), sv.getHoTen(), sv.getEmail(), sv.getSoDT(),
						sv.getGioiTinh() == 1 ? "Nam" : "Nữ", sv.getDiaChi() });
//				String row[] = {sv.getMaSinhVien(), sv.getHoTen(), sv.getEmail(), sv.getSoDT(), 
//					sv.getGioiTinh() == 1 ? "Nam" : "Nữ", sv.getDiaChi()};
//				tblModel.addRow(row);
			}
			tblModel.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialogHelper.showErrorDialog(parentForm, e.getMessage(), "Lỗi");
		}
	}
}
