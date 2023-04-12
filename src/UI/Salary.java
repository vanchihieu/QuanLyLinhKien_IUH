package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Salary_DAO;
import helpers.ConnectDB;
import model.SalaryEntity;

public class Salary extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private Salary_DAO nv_DAO = new Salary_DAO();

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public Salary() throws SQLException {
		setLayout(null);
		ConnectDB.getInstance().connect();
		JLabel lblNewLabel = new JLabel("Thống Kê Lương Nhân Viên");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBackground(new Color(128, 0, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(36, 10, 198, 23);
		add(lblNewLabel);

		String[] header = { "Mã NV ", "Họ NV", "Tên NV", "Phái", "Phòng Ban", "Lương" };
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 62, 366, 136);
		add(sp);

		JPanel panel = new JPanel();
		panel.setBounds(392, 62, 102, 136);
		add(panel);
		
		docDuLieu();
	}

	private void docDuLieu() throws SQLException {
		while (tableModel.getRowCount() != 0) {
			tableModel.removeRow(0);
		}
		String gioiTinh = "";
		ArrayList<SalaryEntity> listNV = nv_DAO.getSalary();
		for (SalaryEntity salaryEntity : listNV) {
			if (salaryEntity.isGioiTinh() == true) {
				gioiTinh = "Nam";
			} else {
				gioiTinh = "Nu";
			}
			String data[] = { salaryEntity.getMaNV(), salaryEntity.getHoHV(), salaryEntity.getTenNV(), gioiTinh + "",
					salaryEntity.getPhongBan().getMaPB(), salaryEntity.getLuong() + "" };
			tableModel.addRow(data);
		}
	}
}
