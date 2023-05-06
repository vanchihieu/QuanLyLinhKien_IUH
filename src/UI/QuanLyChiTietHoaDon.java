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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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
		
		JLabel backgr = new JLabel(new ImageIcon("D:\\ram.jpg"));
		backgr.setBounds(0,  0, 1457, 743);
		add(backgr);

		JLabel lblNewLabel = new JLabel("Chi Tiết Hóa Đơn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Border bd = BorderFactory.createLineBorder(Color.DARK_GRAY);
		lblNewLabel.setBorder(null);
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(25, 10, 1432, 50);
		backgr.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã CTHD:");
		lblNewLabel_1.setBackground(new Color(204, 204, 204));
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setBounds(110, 70, 120, 20);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backgr.add(lblNewLabel_1);

		txtMaCTHD = new JTextField();
		txtMaCTHD.setBounds(220, 70, 250, 25);
		backgr.add(txtMaCTHD);
		txtMaCTHD.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Mã Linh Kiện:");
		lblNewLabel_1_1.setForeground(Color.ORANGE);
		lblNewLabel_1_1.setBounds(110, 110, 120, 20);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backgr.add(lblNewLabel_1_1);

		txtMaLinhKien = new JTextField();
		txtMaLinhKien.setColumns(10);
		txtMaLinhKien.setBounds(220, 110, 250, 25);
		backgr.add(txtMaLinhKien);

		JLabel lblNewLabel_1_1_1 = new JLabel("Số Lượng:");
		lblNewLabel_1_1_1.setForeground(Color.ORANGE);
		lblNewLabel_1_1_1.setBounds(110, 150, 120, 20);
lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backgr.add(lblNewLabel_1_1_1);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(220, 150, 250, 25);
		backgr.add(txtSoLuong);

		JLabel lblNewLabel_1_2 = new JLabel("Mã Hóa Đơn:");
		lblNewLabel_1_2.setForeground(Color.ORANGE);
		lblNewLabel_1_2.setBounds(800, 70, 120, 20);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backgr.add(lblNewLabel_1_2);

		txtMaHD = new JTextField();
		txtMaHD.setColumns(10);
		txtMaHD.setBounds(900, 70, 250, 25);
		backgr.add(txtMaHD);

		JLabel lblNewLabel_1_1_2 = new JLabel("Đơn Giá:");
		lblNewLabel_1_1_2.setForeground(Color.ORANGE);
		lblNewLabel_1_1_2.setBounds(800, 110, 120, 20);
lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
backgr.add(lblNewLabel_1_1_2);

		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(900, 110, 250, 25);
		backgr.add(txtDonGia);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Thành Tiền:");
		lblNewLabel_1_1_2_1.setForeground(Color.ORANGE);
		lblNewLabel_1_1_2_1.setBounds(800, 150, 120, 20);
		lblNewLabel_1_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		backgr.add(lblNewLabel_1_1_2_1);

		txtThanhTien = new JTextField();
		txtThanhTien.setColumns(10);
		txtThanhTien.setBounds(900, 150, 250, 25);
		backgr.add(txtThanhTien);

		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBackground(Color.ORANGE);
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
		btnThem.setBounds(380, 200, 120,50);
		backgr.add(btnThem);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(Color.ORANGE);
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
		btnXoa.setBounds(580, 200,120,50);
		backgr.add(btnXoa);

		JButton btnXaTrng = new JButton("Xóa Trắng");
		btnXaTrng.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXaTrng.setBackground(Color.ORANGE);
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
		btnXaTrng.setBounds(800, 200,120,50);
		backgr.add(btnXaTrng);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTimKiem.setBackground(Color.ORANGE);
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
		btnTimKiem.setBounds(1000, 200, 120,50);
		backgr.add(btnTimKiem);

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
		backgr.add(scrollTable);
		scrollTable.setBounds(100, 270, 1300, 350);
		
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