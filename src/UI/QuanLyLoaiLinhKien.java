package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.BangDiemDao;
import dao.ChiTietHoaDon_Dao;
import dao.LinhKien_DAO;
import dao.LoailinhKien_DAO;
import dao.SinhVienDao;
import helpers.ConnectDB;
import helpers.DataValidator;
import helpers.ImageHelper;
import helpers.MessageDialogHelper;
import model.BangDiem;
import model.LinhKien;
import model.LoaiLinhKien;
import model.SinhVien;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyLoaiLinhKien extends JPanel {
	private JTextField txtMa;
	private JTextField txtTen;
	private JTable table;
	private DefaultTableModel talbleModel;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnThoat;
	private JButton btnLuu;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private MainForm parentForm;

	private LoailinhKien_DAO llk_dao;
	private DefaultTableModel tableModel;


	/**
	 * Create the panel.
	 */
	public QuanLyLoaiLinhKien() {
		ConnectDB.getInstance().connect();

		setLayout(null);

		JLabel lblQunLLoi = new JLabel("Quản Lý Loại Linh Kiện");
		lblQunLLoi.setBounds(22, 10, 234, 21);
		lblQunLLoi.setForeground(new Color(255, 0, 128));
		lblQunLLoi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lblQunLLoi);

		JLabel lblNewLabel = new JLabel("Mã loại linh kiện");
		lblNewLabel.setBounds(10, 49, 96, 13);
		add(lblNewLabel);

		txtMa = new JTextField();
		txtMa.setBounds(103, 46, 211, 19);
		add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTnLoiLinh = new JLabel("Tên loại linh kiện");
		lblTnLoiLinh.setBounds(356, 49, 124, 13);
		add(lblTnLoiLinh);

		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(461, 46, 220, 19);
		add(txtTen);

		btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				String ma = JOptionPane.showInputDialog(null, "Nhập vào mã loại linh kiện cần tìm");
				if (ma == null) {
					return;
				}
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.getValueAt(i, 0).toString().equalsIgnoreCase(ma)) {
						table.setRowSelectionInterval(i, i);
						sendDataToTxt(i);
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					JOptionPane.showMessageDialog(null, "Không có mã loại linh kiện này trong danh sách");
				}
			}
		});
		btnTim.setBounds(10, 82, 85, 21);
		add(btnTim);

		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnThem.getText().equalsIgnoreCase("Thêm")) {
					btnThem.setText("Hủy");
					btnSua.setEnabled(false);
					btnLuu.setEnabled(true);
					btnXoa.setEnabled(false);
					btnTim.setEnabled(false);
					setWhenAddData(true);
				} else if (btnThem.getText().equalsIgnoreCase("Hủy")) {
					btnThem.setText("Thêm");
					btnSua.setEnabled(true);
					btnLuu.setEnabled(false);
					btnXoa.setEnabled(true);
					btnTim.setEnabled(true);
					setWhenAddData(false);
				}
			}
		});
		btnThem.setBounds(113, 82, 85, 21);
		add(btnThem);

		btnThoat = new JButton("Thoát");
		btnThoat.setBounds(640, 82, 85, 21);
		add(btnThoat);

		btnLuu = new JButton("Lưu ");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnThem.getText().equalsIgnoreCase("Hủy")) {
					if (!validator()) {
						return;
					}
					String ma = txtMa.getText();
					String ten = txtTen.getText();
					LoaiLinhKien llk = new LoaiLinhKien(ma, ten);
					boolean isAdded = llk_dao.addLoaiLinhKien(llk);
					if (!isAdded) {
						JOptionPane.showMessageDialog(null, "Thêm vào thất bại", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Thêm vào thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						loadBangDiem();
					}
					btnThem.setText("Thêm");
					btnXoa.setEnabled(true);
					btnSua.setEnabled(true);
					btnTim.setEnabled(true);
				} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
					String maLLK = txtMa.getText();
					String tenLLK = txtTen.getText();
					LoaiLinhKien loaiLK = new LoaiLinhKien(maLLK, tenLLK);
					boolean isModifield = llk_dao.updateLoaiLK(loaiLK);
					if (isModifield) {
						JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						loadBangDiem();
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thất bại", "Thống báo", JOptionPane.ERROR_MESSAGE);
					}
					btnSua.setText("Sửa");
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
					btnTim.setEnabled(true);
					setWhenEditField(false);
				}
			}
		});
		btnLuu.setBounds(544, 82, 85, 21);
		add(btnLuu);

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMa.setText("");
				txtTen.setText("");
			}
		});
		btnXoaTrang.setBounds(428, 82, 106, 21);
		add(btnXoaTrang);

		String[] header = { "Mã Loại Linh Kiện ", "Tên Loại Linh Kiện" };
		talbleModel = new DefaultTableModel(header, 0);
		table = new JTable(talbleModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					sendDataToTxt(row);
				}

			}
		});
		JScrollPane sp = new JScrollPane(table);
//		
		table.getRowHeight(30);
		add(sp);
		sp.setBounds(10, 161, 715, 185);

		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnSua.getText().equalsIgnoreCase("Sửa")) {
					setWhenEditField(true);
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					btnLuu.setEnabled(true);
					btnXoa.setEnabled(false);
					btnTim.setEnabled(false);
				} else if (btnSua.getText().equalsIgnoreCase("Hủy")) {
					btnSua.setText("Sửa");
					setWhenEditField(false);
					btnThem.setEnabled(true);
					btnLuu.setEnabled(false);
					btnXoa.setEnabled(true);
					btnTim.setEnabled(true);

				}
				loadBangDiem();
			}
		});
		btnSua.setBounds(214, 82, 85, 21);
		add(btnSua);

		btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinhKien_DAO lkDao = new LinhKien_DAO();
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Bạn cần chọn dòng muốn xoá");
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "Bạn xác nhận xoá dòng đã chọn, và có thể làm mất mất dữ liệu",
						"Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maLLK = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
					LinhKien_DAO lllk_dao = new LinhKien_DAO();
					ChiTietHoaDon_Dao cthd = new ChiTietHoaDon_Dao();
					List<LinhKien> lkTheoMaLLK = lkDao.getAllLinhKienMALLK(maLLK);
					for (LinhKien linhKien : lkTheoMaLLK) {
						cthd.xoaLk(linhKien.getMaLinhKien());
					}
					lkDao.xoaLinhKienTheoMaLoaiLinhKien(maLLK);
					if (llk_dao.deleteLLK(maLLK)) {
						loadBangDiem();
						JOptionPane.showMessageDialog(null, "Xoá thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Xoá không thành công");
					}
				}
			}
		});
		btnXoa.setBounds(319, 82, 85, 21);
		add(btnXoa);

		llk_dao = new LoailinhKien_DAO();
//		loadLLKToTable();

		initTable();
		loadBangDiem();
	}

	private void initTable() {
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[] { "Mã Loại Linh Kiện", "Tên Loại Linh Kiện" });
		table.setModel(tableModel);
	}

	private void loadBangDiem() {
		try {
			LoailinhKien_DAO dao = new LoailinhKien_DAO();
			List<LoaiLinhKien> list = dao.getAllLoaiLinhKien();
			tableModel.setRowCount(0);
			for (LoaiLinhKien bd : list) {
				tableModel.addRow(new Object[] { bd.getMaloai(), bd.getTenLinhKien()

				});
			}
			tableModel.fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialogHelper.showMessageDialog(parentForm, e.getMessage(), "Loi");
		}

	}

	public void setWhenAddData(Boolean b) {
		txtMa.setEditable(b);
		txtTen.setEditable(b);
	}

	public void setWhenEditField(Boolean b) {
		txtMa.setEditable(true);
		txtTen.setEditable(true);
	}

	public boolean validator() {
		String maLLK = txtMa.getText().trim();
		String tenLLK = txtTen.getText().trim();
		if (maLLK.isEmpty() || tenLLK.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không field nào được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!maLLK.matches("^LLK\\d{3}$")) {
			JOptionPane.showMessageDialog(null, "Mã loại linh kiện bắt đầu bằng LLK và theo sau là 3 chữ số!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void sendDataToTxt(int row) {
		txtMa.setText(table.getValueAt(row, 0).toString());
		txtTen.setText(table.getValueAt(row, 1).toString());
	}

}
