package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.SoTienPhaiTra_DAO;
import helpers.ConnectDB;
import model.SoTienPTCMHD;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;

public class ThongKeHoaDon extends JPanel {
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private SoTienPhaiTra_DAO soTien_Dao = new SoTienPhaiTra_DAO();
	/**
	 * Create the panel.
	 */
	public ThongKeHoaDon() throws SQLException{
		ConnectDB.getInstance().connect();
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản Lý Hóa Đơn");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(152, 10, 162, 31);
		add(lblNewLabel);
		
		String[] colHeader = { "Mã Hóa Đơn" , "Ngày Lập" , "Tổng Tiền"};
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		
		JScrollPane scrollPane = new JScrollPane(tableNhanVien);
		add(scrollPane);
		scrollPane.setBounds(10, 48, 430, 192);
		
		while (modelNhanVien.getRowCount() != 0) {
			modelNhanVien.removeRow(0);
		}
		ArrayList<SoTienPTCMHD> listMH = soTien_Dao.getKhachHang();
		for (SoTienPTCMHD matHang : listMH) {
			String data[] = { matHang.getMaHD(), matHang.getNgayDatHang() + "", matHang.getThanhTien() + "" };
			modelNhanVien.addRow(data);
		}
	}

}
