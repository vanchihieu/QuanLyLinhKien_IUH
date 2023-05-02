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

import com.toedter.calendar.JDateChooser;

import dao.HoaDon_DAO;
import helpers.ConnectDB;
import model.HoaDon;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyHoaDon extends JPanel {
	private JTextField txtMaHD;
	private JTextField txtMaNV;
	private JDateChooser dateNgayGiaoHang;
	private JTextField txtNoiNhan;
	private JTextField txtMaKH;
	private JDateChooser dateNgayDatHang;
	private JDateChooser dateNgayChuyen;
	private JTable tableHoaDon;
	private DefaultTableModel modalTableHoaDon;
	
	private HoaDon_DAO  hoaDon_dao;
	private JDateChooser txtDate;
	/**
	 * Create the panel.
	 */
	public QuanLyHoaDon() throws SQLException{
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		hoaDon_dao = new HoaDon_DAO();
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản Lý Hóa Đơn");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(213, 10, 190, 37);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Hóa Đơn:");
		lblNewLabel_1.setBounds(43, 52, 109, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã Nhân Viên:");
		lblNewLabel_1_1.setBounds(43, 85, 109, 13);
		add(lblNewLabel_1_1);
		
		txtMaHD = new JTextField();
		txtMaHD.setBounds(159, 49, 293, 19);
		add(txtMaHD);
		txtMaHD.setColumns(10);
		
		txtMaNV = new JTextField();
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(159, 82, 293, 19);
		add(txtMaNV);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Ngày Giao Hàng:");
		lblNewLabel_1_1_1.setBounds(43, 122, 109, 13);
		add(lblNewLabel_1_1_1);
		
		dateNgayGiaoHang = new JDateChooser();
		dateNgayGiaoHang.setDateFormatString("dd-MM-yyyy");
		dateNgayGiaoHang.setBounds(159, 116, 293, 19);
		add(dateNgayGiaoHang);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nơi Nhận:");
		lblNewLabel_1_1_1_1.setBounds(43, 158, 77, 13);
		add(lblNewLabel_1_1_1_1);
		
		txtNoiNhan = new JTextField();
		txtNoiNhan.setColumns(10);
		txtNoiNhan.setBounds(159, 155, 732, 19);
		add(txtNoiNhan);
		
		JLabel lblNewLabel_1_2 = new JLabel("Mã Khách Hàng:");
		lblNewLabel_1_2.setBounds(529, 52, 117, 13);
		add(lblNewLabel_1_2);
		
		txtMaKH = new JTextField();
		txtMaKH.setColumns(10);
		txtMaKH.setBounds(631, 49, 269, 19);
		add(txtMaKH);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Ngày Đặt Hàng");
		lblNewLabel_1_2_1.setBounds(529, 85, 83, 13);
		add(lblNewLabel_1_2_1);
		dateNgayDatHang = new JDateChooser();
		dateNgayDatHang.setDateFormatString("dd-MM-yyyy");
		dateNgayDatHang.setBounds(631, 82, 269, 19);
		add(dateNgayDatHang);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Ngày Chuyển");
		lblNewLabel_1_2_1_1.setBounds(530, 117, 85, 13);
		add(lblNewLabel_1_2_1_1);
		
		dateNgayChuyen= new JDateChooser();
		dateNgayChuyen.setDateFormatString("dd-MM-yyyy");
		dateNgayChuyen.setBounds(631, 111, 269, 19);
		add(dateNgayChuyen);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maHoaDon = txtMaHD.getText().trim();
				String maKhachHang = txtMaKH.getText().trim();
				String maNhanVien = txtMaNV.getText().trim();
				java.util.Date ngayDatHang = dateNgayDatHang.getDate();
				java.util.Date ngayGiaoHang = dateNgayGiaoHang.getDate();
				java.util.Date ngayChuyenHang = dateNgayChuyen.getDate();
				String noiNhan = txtNoiNhan.getText();
				HoaDon hoaDon = new HoaDon(maHoaDon, maNhanVien, maKhachHang, ngayDatHang, ngayGiaoHang, ngayChuyenHang,
						noiNhan);
				
				boolean isAdded = hoaDon_dao.addHoaDon(hoaDon);
				if (!isAdded) {
					JOptionPane.showMessageDialog(null, "Thêm vào thất bại", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
							int rowSeleted = tableHoaDon.getSelectedRow();
							if (rowSeleted!= -1) {
								sendDataToTxt(rowSeleted);
							}
				} else {
					JOptionPane.showMessageDialog(null, "Thêm vào thành công", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					loadHoaDonToTable();
				}
			}
		});
		btnThem.setBounds(56, 191, 85, 21);
		add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSeleted = tableHoaDon.getSelectedRow();
				if (rowSeleted != -1) {
					int isYes = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hay không");
					if (isYes == 0) {
						String maHD = tableHoaDon.getValueAt(rowSeleted, 0).toString();
						boolean isDel = hoaDon_dao.deleteHoaDon(maHD);
						if (isDel) {
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
							loadHoaDonToTable();
						} else {
							JOptionPane.showMessageDialog(null, "Xóa thất bại");
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Bạn phải chọn vào dòng cần xóa", "Thông Báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnXoa.setBounds(159, 191, 85, 21);
		add(btnXoa);
		
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				String maHD = JOptionPane.showInputDialog(null, "Nhập vào mã hóa đơn cần tìm");
				if (maHD == null) {
					return;
				}
				for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
					if (tableHoaDon.getValueAt(i, 0).toString().equalsIgnoreCase(maHD)) {
						tableHoaDon.setRowSelectionInterval(i, i);
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
		btnTimKiem.setBounds(266, 191, 99, 21);
		add(btnTimKiem);
		
		JButton btnSua = new JButton("Xóa Trắng");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMaHD.setText("");
				txtMaKH.setText("");
				txtMaNV.setText("");
				txtNoiNhan.setText("");
				dateNgayChuyen.setDate(null);
				dateNgayDatHang.setDate(null);
				dateNgayGiaoHang.setDate(null);
			}
		});
		btnSua.setBounds(375, 191, 109, 21);
		add(btnSua);
		
		
		String fieldName[] = { "Mã Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Đặt Hàng", "Ngày Giao Hàng",
				"Ngày Chuyển", "Nơi Nhận" };
		modalTableHoaDon = new DefaultTableModel(fieldName, 0);
		tableHoaDon = new JTable(modalTableHoaDon);
		tableHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = e.getSource();
				if (o.equals(tableHoaDon)) {
					int row = tableHoaDon.getSelectedRow();
					if (row != -1) {
						sendDataToTxt(row);
					}
				}
			}
		});
		JScrollPane scrollTable = new JScrollPane(tableHoaDon);
		tableHoaDon.getRowHeight(20);
		add(scrollTable);
		scrollTable.setBounds(23, 222, 904, 220);
		
		loadHoaDonToTable();
	}
	protected void loadHoaDonToTable() {
		while (tableHoaDon.getRowCount() != 0) {
			modalTableHoaDon.removeRow(0);
		}
		ArrayList<HoaDon> dsHoaDon = hoaDon_dao.getAllHoaDon();
		for (HoaDon hd : dsHoaDon) {
			String data[] = { hd.getMaHoaDon(), hd.getMaKhachHang(), hd.getMaNhanVien(),
					StringToDate(hd.getNgayDatHang()), StringToDate(hd.getNgayGiaoHang()),
					StringToDate(hd.getNgayGiaoHang()), hd.getNoiNhanHang() };
			modalTableHoaDon.addRow(data);
		}
		if (tableHoaDon.getRowCount() != 0) {
			tableHoaDon.setRowSelectionInterval(0, 0);
			sendDataToTxt(0);
		}
	}
	public String StringToDate(Date date) {
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}
	protected void sendDataToTxt(int row) {
		txtMaHD.setText(tableHoaDon.getValueAt(row, 0).toString());
		txtMaKH.setText(tableHoaDon.getValueAt(row, 1).toString());
		txtMaNV.setText(tableHoaDon.getValueAt(row, 2).toString());
		try {
			String date = tableHoaDon.getValueAt(row, 3).toString();
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			dateNgayDatHang.setDate(date2);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			String date = tableHoaDon.getValueAt(row, 4).toString();
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			dateNgayGiaoHang.setDate(date2);
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		try {
			String date = tableHoaDon.getValueAt(row, 5).toString();
			Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			dateNgayChuyen.setDate(date2);
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		txtNoiNhan.setText(tableHoaDon.getValueAt(row, 6).toString());
	}
}
