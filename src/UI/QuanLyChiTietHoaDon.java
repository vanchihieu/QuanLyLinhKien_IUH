package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDon_Dao;
import helpers.ConnectDB;
import model.ChiTietHoaDon;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyChiTietHoaDon extends JPanel {
	private JTextField txtMaCTHD;
	private JTextField txtMaLinhKien;
	private JTextField txtSoLuong;
	private JTextField txtMaHD;
	private JTextField txtDonGia;
	private JTextField txtThanhTien;
	private DecimalFormat df;

	private JTable tableCTHD;
	private DefaultTableModel modalTable;
	private ChiTietHoaDon_Dao cthd_dao;

	/**
	 * Create the panel.
	 */
	public QuanLyChiTietHoaDon() throws SQLException{
		df = new DecimalFormat("#");
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		cthd_dao = new ChiTietHoaDon_Dao();
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Chi Tiết Hóa Đơn");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(244, 10, 176, 37);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã CTHD:");
		lblNewLabel_1.setBounds(36, 49, 96, 13);
		add(lblNewLabel_1);

		txtMaCTHD = new JTextField();
		txtMaCTHD.setBounds(139, 46, 156, 19);
		add(txtMaCTHD);
		txtMaCTHD.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Mã Linh Kiện:");
		lblNewLabel_1_1.setBounds(36, 82, 96, 13);
		add(lblNewLabel_1_1);

		txtMaLinhKien = new JTextField();
		txtMaLinhKien.setColumns(10);
		txtMaLinhKien.setBounds(139, 79, 156, 19);
		add(txtMaLinhKien);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số Lượng:");
		lblNewLabel_1_1_1.setBounds(36, 120, 96, 13);
		add(lblNewLabel_1_1_1);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(139, 117, 156, 19);
		add(txtSoLuong);

		JLabel lblNewLabel_1_2 = new JLabel("Mã Hóa Đơn:");
		lblNewLabel_1_2.setBounds(351, 49, 96, 13);
		add(lblNewLabel_1_2);

		txtMaHD = new JTextField();
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(446, 46, 156, 19);
		add(txtMaHD);

		JLabel lblNewLabel_1_1_2 = new JLabel("Đơn Giá:");
		lblNewLabel_1_1_2.setBounds(351, 82, 96, 13);
		add(lblNewLabel_1_1_2);

		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(446, 79, 156, 19);
		add(txtDonGia);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Thành Tiền:");
		lblNewLabel_1_1_2_1.setBounds(351, 120, 96, 13);
		add(lblNewLabel_1_1_2_1);

		txtThanhTien = new JTextField();
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(446, 117, 156, 19);
		add(txtThanhTien);

		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maCTHD = txtMaCTHD.getText().trim();
				String maHD = txtMaHD.getText().trim();
				String maLK = txtMaLinhKien.getText().trim();
				double donGia = 0, thanhTien = 0;
				int soLuong = 0;
				try {
					donGia = Double.parseDouble(txtDonGia.getText());
					thanhTien = Double.parseDouble(txtThanhTien.getText());
					soLuong = Integer.parseInt(txtSoLuong.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maCTHD, maLK, soLuong, donGia, thanhTien);
				boolean isAdded = cthd_dao.modifieldCTHD(cthd);
				if (isAdded) {
					JOptionPane.showMessageDialog(null, "Sửa thành công!");
					loadDataToTable();
				} else {
					JOptionPane.showMessageDialog(null, "Sửa thất bại");
				}
				int rowSeleted = tableCTHD.getSelectedRow();
				if (rowSeleted != -1) {
					sendDataToTxt(rowSeleted);
				}
			}
		});
		btnThem.setBounds(36, 158, 85, 21);
		add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCountSelect = tableCTHD.getSelectedRow();
				if (rowCountSelect != -1) {
					int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa");
					if (isYes == 0) {
						// do remove record
						String maCTHD = tableCTHD.getValueAt(rowCountSelect, 0).toString();
						String maHD = tableCTHD.getValueAt(rowCountSelect, 1).toString();
						boolean isDel = cthd_dao.deleteCTHD(maCTHD, maHD);
						if (isDel) {
							JOptionPane.showMessageDialog(null, "Xóa thành công");
							loadDataToTable();
						} else {
							JOptionPane.showMessageDialog(null, "Xoá thất bại");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Phải chọn vào dòng cấn xóa", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setBounds(165, 158, 85, 21);
		add(btnXoa);

		JButton btnXaTrng = new JButton("Xóa Trắng");
		btnXaTrng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaCTHD.setText("");
				txtMaHD.setText("");
				txtMaLinhKien.setText("");
				txtSoLuong.setText("");
				txtDonGia.setText("");
				txtThanhTien.setText("");
			}
		});
		btnXaTrng.setBounds(295, 158, 105, 21);
		add(btnXaTrng);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				String maHD = JOptionPane.showInputDialog("Nhập vào Mã Hóa Đơn");
				String maCTHD = JOptionPane.showInputDialog("Nhập vào mã Chi Tiết Hóa Đơn");
				if (maCTHD == null || maHD == null) {
					return;
				}
				for (int i = 0; i < tableCTHD.getRowCount(); i++) {
					if (tableCTHD.getValueAt(i, 0).toString().equalsIgnoreCase(maCTHD)
							&& tableCTHD.getValueAt(i, 1).toString().equalsIgnoreCase(maHD)) {
						tableCTHD.setRowSelectionInterval(i, i);
						sendDataToTxt(i);
						flag = 1;
						break;
					}
				}
				if (flag == 0) {
					JOptionPane.showMessageDialog(null, "Không có Mã Hóa đơn này trong danh sách");
				}
			}
		});
		btnTimKiem.setBounds(446, 158, 105, 21);
		add(btnTimKiem);

		String fieldName[] = { "Mã CTHD", "Mã Hóa Đơn", "Mã Linh Kiện", "Đơn Gía", "Số Lượng", "Thành Tiền" };
		modalTable = new DefaultTableModel(fieldName, 0);
		tableCTHD = new JTable(modalTable);
		tableCTHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if (o.equals(tableCTHD)) {
					int row = tableCTHD.getSelectedRow();
					if (row != -1) {
						sendDataToTxt(row);
					}
				}
			}
		});
		JScrollPane scrollTable = new JScrollPane(tableCTHD);
	
		tableCTHD.getRowHeight(20);
		add(scrollTable);
		scrollTable.setBounds(36, 209, 566, 152);
		
		loadDataToTable();
	}

	public void sendDataToTxt(int row) {
		txtMaCTHD.setText(tableCTHD.getValueAt(row, 0).toString());
		txtMaHD.setText(tableCTHD.getValueAt(row, 1).toString());
		txtMaLinhKien.setText(tableCTHD.getValueAt(row, 2).toString());
		txtDonGia.setText(tableCTHD.getValueAt(row, 3).toString());
		txtSoLuong.setText(tableCTHD.getValueAt(row, 4).toString());
		txtThanhTien.setText(tableCTHD.getValueAt(row, 5).toString());

	}

	protected void loadDataToTable() {
		while (tableCTHD.getRowCount() != 0) {
			modalTable.removeRow(0);
		}

		ArrayList<ChiTietHoaDon> dsCTHD = cthd_dao.getAllChiTietHoaDon();
		for (ChiTietHoaDon cthd : dsCTHD) {
			String data[] = { cthd.getMaChiTietHoaDon(), cthd.getMaHoaDon(), cthd.getMaLinhKien(),
					df.format(cthd.getDonGia()), cthd.getSoLuongLinhKien() + "", df.format(cthd.getThanhTien()) };
			modalTable.addRow(data);

		}
		if (tableCTHD.getRowCount() != 0) {
			tableCTHD.setRowSelectionInterval(0, 0);
			sendDataToTxt(0);
		}
	}
}
