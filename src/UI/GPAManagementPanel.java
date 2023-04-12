package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.BangDiemDao;
import dao.SinhVienDao;
import helpers.DataValidator;
import helpers.MessageDialogHelper;
import model.BangDiem;
import model.SinhVien;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class GPAManagementPanel extends JPanel {
	private MainForm parentForm;
	private JTextField txtStudentID;
	private JTextField txtName;
	private JTextField txtEnglish;
	private JTextField txtIT;
	private JTextField txtPhysicalTraining;
	private JTable tblGpa;
	private JTextField txtStudentIdSearch;
	private JLabel lblGpa;
	private DefaultTableModel tblModel;

	/**
	 * Create the panel.
	 */
	public GPAManagementPanel() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản Lý Điểm");
		lblNewLabel.setForeground(new Color(255, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 10, 150, 21);
		add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 35, 568, 2);
		add(separator);

		JPanel panel = new JPanel();
		panel.setBounds(10, 45, 568, 43);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã Sinh Viên");
		lblNewLabel_1.setBounds(18, 19, 79, 13);
		panel.add(lblNewLabel_1);

		JButton btnSearch = new JButton("Tìm Kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BangDiemDao dao = new BangDiemDao();
					BangDiem bd = dao.findByMaSinhVien(txtStudentIdSearch.getText());

					if (bd != null) {

						txtStudentID.setText(bd.getMaSinhVien());
						txtEnglish.setText(String.format("%.2f", bd.getTiengAnh()));
						txtIT.setText(String.format("%.2f", bd.getTinHoc()));
						txtPhysicalTraining.setText(String.format("%.2f", bd.getGDTC()));

						txtStudentID.addFocusListener(null);
						txtEnglish.addFocusListener(null);
					} else {
						MessageDialogHelper.showMessageDialog(parentForm, "Không tìm thấy mã sinh viên theo yêu cầu	",
								"Thông báo");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "loi");
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(GPAManagementPanel.class.getResource("/icons/search-icon-16.png")));
		btnSearch.setBounds(295, 16, 125, 21);
		panel.add(btnSearch);

		txtStudentIdSearch = new JTextField();
		txtStudentIdSearch.setBounds(112, 16, 150, 19);
		panel.add(txtStudentIdSearch);
		txtStudentIdSearch.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		panel_1.setBounds(10, 98, 568, 432);
		add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 20, 274, 160);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Mã Sinh Viên:");
		lblNewLabel_2.setBounds(10, 10, 84, 13);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Họ Tên:");
		lblNewLabel_2_1.setBounds(10, 35, 72, 15);
		panel_2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Tiếng Anh:");
		lblNewLabel_2_1_1.setBounds(10, 60, 61, 13);
		panel_2.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Tin Học:");
		lblNewLabel_2_1_1_1.setBounds(10, 85, 72, 13);
		panel_2.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("GDTC:");
		lblNewLabel_2_1_1_1_1.setBounds(10, 110, 45, 13);
		panel_2.add(lblNewLabel_2_1_1_1_1);

		txtStudentID = new JTextField();
		txtStudentID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					SinhVienDao dao = new SinhVienDao();
					SinhVien sv = dao.findById(txtStudentID.getText());
					if (sv != null) {
						txtName.setText(sv.getHoTen());
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		txtStudentID.setEditable(true);
		txtStudentID.setBounds(104, 10, 149, 19);
		panel_2.add(txtStudentID);
		txtStudentID.setColumns(10);

		txtName = new JTextField();
		txtName.setEditable(true);
		txtName.setColumns(10);
		txtName.setBounds(104, 35, 149, 19);
		panel_2.add(txtName);

		txtEnglish = new JTextField();
		txtEnglish.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtEnglish.getText().equals("") || txtIT.getText().equals("")
						|| txtPhysicalTraining.getText().equals("")) {
					return;
				}

				float ta = Float.parseFloat(txtEnglish.getText());
				float it = Float.parseFloat(txtIT.getText());
				float pt = Float.parseFloat(txtPhysicalTraining.getText());

				float avg = (ta + it + pt) / 3;

				String st = String.format("%.2f", avg);

				lblGpa.setText(st);

			}
		});
		txtEnglish.setEditable(true);
		txtEnglish.setColumns(10);
		txtEnglish.setBounds(104, 60, 149, 19);
		panel_2.add(txtEnglish);

		txtIT = new JTextField();
		txtIT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtEnglish.getText().equals("") || txtIT.getText().equals("")
						|| txtPhysicalTraining.getText().equals("")) {
					return;
				}

				float ta = Float.parseFloat(txtEnglish.getText());
				float it = Float.parseFloat(txtIT.getText());
				float pt = Float.parseFloat(txtPhysicalTraining.getText());

				float avg = (ta + it + pt) / 3;

				String st = String.format("%.2f", avg);

				lblGpa.setText(st);
			}
		});
		txtIT.setEditable(true);
		txtIT.setColumns(10);
		txtIT.setBounds(104, 85, 149, 19);
		panel_2.add(txtIT);

		txtPhysicalTraining = new JTextField();
		txtPhysicalTraining.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtEnglish.getText().equals("") || txtIT.getText().equals("")
						|| txtPhysicalTraining.getText().equals("")) {
					return;
				}

				float ta = Float.parseFloat(txtEnglish.getText());
				float it = Float.parseFloat(txtIT.getText());
				float pt = Float.parseFloat(txtPhysicalTraining.getText());

				float avg = (ta + it + pt) / 3;

				String st = String.format("%.2f", avg);

				lblGpa.setText(st);
			}
		});
		txtPhysicalTraining.setEditable(true);
		txtPhysicalTraining.setColumns(10);
		txtPhysicalTraining.setBounds(104, 110, 149, 19);
		panel_2.add(txtPhysicalTraining);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(294, 20, 120, 160);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Điểm TB");
		lblNewLabel_3.setBounds(40, 35, 58, 13);
		panel_3.add(lblNewLabel_3);

		lblGpa = new JLabel("9.5");
		lblGpa.setHorizontalAlignment(SwingConstants.CENTER);
		lblGpa.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblGpa.setBounds(40, 68, 45, 13);
		panel_3.add(lblGpa);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(109, 10, 11, 140);
		panel_3.add(separator_3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 190, 548, 4);
		panel_1.add(separator_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 242, 548, 2);
		panel_1.add(separator_1_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 254, 548, 168);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		tblGpa = new JTable();
		tblGpa.setBounds(10, 10, 528, 131);
		panel_4.add(tblGpa);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBounds(424, 20, 134, 160);
		panel_1.add(panel_3_1);

		JButton btnNew = new JButton("Nhập Mới");
		btnNew.setBounds(10, 10, 114, 21);
		panel_3_1.add(btnNew);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEnglish.setText("");
				txtIT.setText("");
				txtName.setText("");
				txtPhysicalTraining.setText("");
				txtStudentID.setText("");
			}
		});
		btnNew.setIcon(new ImageIcon(GPAManagementPanel.class.getResource("/icons/new-icon-16.png")));

		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				DataValidator.validateEmpty(txtStudentID, sb, "Mã sinh viên cần phải nhập!");
				DataValidator.validateEmpty(txtEnglish, sb, "Tiếng anh cần phải nhập!");
				if (sb.length() > 0) {
					MessageDialogHelper.showErrorDialog(parentForm, sb.toString(), "Lỗi!");
					return;
				}

				try {
					BangDiem bd = new BangDiem();
					bd.setMaSinhVien(txtStudentID.getText());
					bd.setTiengAnh(Float.parseFloat(txtEnglish.getText()));
					bd.setTinHoc(Float.parseFloat(txtIT.getText()));
					bd.setGDTC(Float.parseFloat(txtPhysicalTraining.getText()));

					BangDiemDao dao = new BangDiemDao();
					if (dao.findByMaSinhVien(txtStudentID.getText()) != null) {
						if (MessageDialogHelper.showConfirmDialog(parentForm, "Bạn có muốn cập nhật điểm không?",
								"Hỏi") == JOptionPane.NO_OPTION) {
							return;
						}
						if (dao.update(bd)) {
							MessageDialogHelper.showMessageDialog(parentForm, "Bảng điểm đã được cập nhật",
									"Thông Báo");
						} else {
							MessageDialogHelper.showMessageDialog(parentForm, "Không thể cập nhật bảng điểm",
									"Thông Báo");
						}
					} else {
						if (dao.insert(bd)) {
							MessageDialogHelper.showMessageDialog(parentForm, "Bảng điểm đã được lưu", "Thông Báo");
						} else {
							MessageDialogHelper.showMessageDialog(parentForm, "Không thể lưu bảng điểm", "Thông Báo");
						}
					}
					loadBangDiem();

				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "Lỗi!");
				}
			}
		});
		btnSave.setBounds(10, 41, 114, 21);
		panel_3_1.add(btnSave);
		btnSave.setIcon(new ImageIcon(GPAManagementPanel.class.getResource("/icons/Save-icon.png")));

		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtStudentID.getText().equals("")) {
						MessageDialogHelper.showErrorDialog(parentForm, "Mã sinh viên cần phải nhập", "Lỗi");
						return;
					}
					BangDiemDao dao = new BangDiemDao();
					BangDiem bd = dao.findByMaSinhVien(txtStudentID.getText());
					if (bd == null) {
						MessageDialogHelper.showErrorDialog(parentForm, "Mã sinh viên không tồn tại trong bảng điểm",
								"Lỗi");
						return;
					}
					if (MessageDialogHelper.showConfirmDialog(parentForm, "Bạn có muốn xóa điểm sinh viên không?",
							"Hỏi") == JOptionPane.NO_OPTION) {
						return;
					}
					if (dao.deleteByMaSinhVien(txtStudentID.getText())) {
						MessageDialogHelper.showMessageDialog(parentForm, "Điểm của sinh viên đã được xóa",
								"Thông Báo");
						txtEnglish.setText("");
						txtIT.setText("");
						txtName.setText("");
						txtPhysicalTraining.setText("");
						txtStudentID.setText("");
					} else {
						MessageDialogHelper.showErrorDialog(parentForm, "Mã sinh viên không thể xóa được", "Lỗi");
						return;
					}
					loadBangDiem();

				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "Lỗi!");
				}
			}
		});
		btnDelete.setBounds(10, 72, 114, 21);
		panel_3_1.add(btnDelete);
		btnDelete
				.setIcon(new ImageIcon(GPAManagementPanel.class.getResource("/icons/Actions-edit-delete-icon-16.png")));

		JButton btnUpdate = new JButton("Cập Nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				DataValidator.validateEmpty(txtStudentID, sb, "Mã sinh viên cần phải nhập!");
				DataValidator.validateEmpty(txtEnglish, sb, "Tiếng anh cần phải nhập!");
				if (sb.length() > 0) {
					MessageDialogHelper.showErrorDialog(parentForm, sb.toString(), "Lỗi!");
					return;
				}

				try {
					BangDiem bd = new BangDiem();
					bd.setMaSinhVien(txtStudentID.getText());
					bd.setTiengAnh(Float.parseFloat(txtEnglish.getText()));
					bd.setTinHoc(Float.parseFloat(txtIT.getText()));
					bd.setGDTC(Float.parseFloat(txtPhysicalTraining.getText()));

					BangDiemDao dao = new BangDiemDao();
					if (dao.findByMaSinhVien(txtStudentID.getText()) != null) {
						if (MessageDialogHelper.showConfirmDialog(parentForm, "Bạn có muốn cập nhật điểm không?",
								"Hỏi") == JOptionPane.NO_OPTION) {
							return;
						}
						if (dao.update(bd)) {
							MessageDialogHelper.showMessageDialog(parentForm, "Bảng điểm đã được cập nhật",
									"Thông Báo");
						} else {
							MessageDialogHelper.showMessageDialog(parentForm, "Không thể cập nhật bảng điểm",
									"Thông Báo");
						}
					} else {
						if (dao.insert(bd)) {
							MessageDialogHelper.showMessageDialog(parentForm, "Bảng điểm đã được lưu", "Thông Báo");
						} else {
							MessageDialogHelper.showMessageDialog(parentForm, "Không thể lưu bảng điểm", "Thông Báo");
						}
					}
					loadBangDiem();

				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(parentForm, e2.getMessage(), "Lỗi!");
				}
			}
		});
		btnUpdate.setBounds(10, 103, 114, 21);
		panel_3_1.add(btnUpdate);
		btnUpdate.setIcon(
				new ImageIcon(GPAManagementPanel.class.getResource("/icons/Actions-document-edit-icon-16.png")));

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-11, 10, 11, 140);
		panel_3_1.add(separator_2);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		
		initTable();
		loadBangDiem();
	}
	
	private void initTable() {
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(new String[] {
				"Mã sinh viên", "Tiếng Anh", "Tin học", "GDTC", "ĐIểm TB"
		});
		tblGpa.setModel(tblModel);
	}
	
	private void loadBangDiem() {
		try {
			BangDiemDao dao = new BangDiemDao();
			List<BangDiem> list = dao.findTop(3);
			tblModel.setRowCount(0);
			for (BangDiem bd : list) {
				tblModel.addRow(new Object[] {
						bd.getMaSinhVien(), bd.getTiengAnh(), bd.getTinHoc(), bd.getGDTC(), 
						(bd.getTiengAnh() + bd.getTinHoc() +bd.getGDTC()) /3
				});
			}
			tblModel.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialogHelper.showMessageDialog(parentForm,e.getMessage(), "Loi");
		}
		
	}
}
