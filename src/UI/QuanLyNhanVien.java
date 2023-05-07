package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.LoailinhKien_DAO;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import helpers.ConnectDB;
import model.NhanVien;
import model.PhongBan;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyNhanVien extends JPanel {
	private JTextField txtMaNhanVien;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtSdt;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JTextField txtSoCCCD;
	private JDateChooser txtDate;

	private JTable table;
	private DefaultTableModel tableModel;

	private LoailinhKien_DAO loaiLinhKien;
	NhanVien_DAO nhanVienDao;
	private JComboBox cbPhongBan;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private JPasswordField txtMatKhau;
	private JPasswordField txtNhapLai;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public QuanLyNhanVien() throws SQLException {
		ConnectDB.getInstance().connect();
		nhanVienDao = new NhanVien_DAO();
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản Lý Nhân Viên");
		lblNewLabel.setForeground(new Color(255, 0, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(363, 20, 243, 25);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mã Nhân Viên:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(32, 55, 92, 13);
		add(lblNewLabel_1);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMaNhanVien.setBounds(134, 55, 680, 19);
		add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Họ:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(32, 88, 92, 13);
		add(lblNewLabel_1_1);

		txtHo = new JTextField();
		txtHo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtHo.setColumns(10);
		txtHo.setBounds(134, 85, 263, 19);
		add(txtHo);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tên");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(425, 85, 92, 13);
		add(lblNewLabel_1_1_1);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTen.setColumns(10);
		txtTen.setBounds(566, 85, 268, 19);
		add(txtTen);

		JLabel lblNewLabel_1_1_2 = new JLabel("Số Điện Thoại");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2.setBounds(32, 120, 92, 13);
		add(lblNewLabel_1_1_2);

		txtSdt = new JTextField();
		txtSdt.setColumns(10);
		txtSdt.setBounds(134, 117, 263, 19);
		add(txtSdt);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Email:");
		lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_1.setBounds(32, 152, 92, 13);
		add(lblNewLabel_1_1_2_1);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(134, 149, 263, 16);
		add(txtEmail);

		JLabel lblNewLabel_1_1_2_2 = new JLabel("Địa chỉ:");
		lblNewLabel_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_2.setBounds(425, 117, 92, 13);
		add(lblNewLabel_1_1_2_2);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(566, 118, 268, 19);
		add(txtDiaChi);

		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("Giới tính:");
		lblNewLabel_1_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_2_1.setBounds(425, 149, 60, 13);
		add(lblNewLabel_1_1_2_2_1);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		rdNam = new JRadioButton("Nam");
		rdNam.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdNam.setBounds(576, 148, 60, 21);
		add(rdNam);

		rdNu = new JRadioButton("Nữ");
		rdNu.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdNu.setBounds(662, 148, 103, 21);
		add(rdNu);
		buttonGroup.add(rdNam);
		buttonGroup.add(rdNu);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Ngày vào làm:");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_1_1.setBounds(32, 186, 92, 13);
		add(lblNewLabel_1_1_2_1_1);

		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Số CCCD:");
		lblNewLabel_1_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_1_1_1.setBounds(425, 183, 92, 13);
		add(lblNewLabel_1_1_2_1_1_1);

		txtSoCCCD = new JTextField();
		txtSoCCCD.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtSoCCCD.setColumns(10);
		txtSoCCCD.setBounds(566, 186, 268, 19);
		add(txtSoCCCD);

		JLabel lblNewLabel_1_1_2_1_1_2 = new JLabel("Mật Khẩu:");
		lblNewLabel_1_1_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_1_1_2.setBounds(32, 224, 92, 13);
		add(lblNewLabel_1_1_2_1_1_2);

		JLabel lblNewLabel_1_1_2_1_1_2_1 = new JLabel("Nhập Lại Mật Khẩu:");
		lblNewLabel_1_1_2_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_1_1_2_1.setBounds(425, 221, 127, 13);
		add(lblNewLabel_1_1_2_1_1_2_1);

		JLabel lblNewLabel_1_1_2_1_1_2_2 = new JLabel("Tên phòng ban:");
		lblNewLabel_1_1_2_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2_1_1_2_2.setBounds(32, 262, 92, 13);
		add(lblNewLabel_1_1_2_1_1_2_2);

		txtDate = new JDateChooser();
		txtDate.setDateFormatString("dd-MM-yyyy");
		txtDate.setBounds(134, 186, 263, 19);
		add(txtDate);

		cbPhongBan = new JComboBox();
//		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Phòng Nhân Sự"}));
		PhongBan_DAO pbDao = new PhongBan_DAO();
		for (PhongBan pb : pbDao.getAllPhongBan()) {
			cbPhongBan.addItem(pb.getTenPhongBan());
		}
		cbPhongBan.setBounds(134, 258, 263, 21);
		add(cbPhongBan);

		JButton btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThem.setBackground(Color.ORANGE);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					them();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThem.setBounds(72, 306, 93, 32);
		add(btnThem);

		JSeparator separator = new JSeparator();
		separator.setBounds(32, 292, 457, 4);
		add(separator);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoa.setBackground(Color.ORANGE);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoa();
			}
		});
		btnXoa.setBounds(192, 306, 97, 32);
		add(btnXoa);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLuu.setBackground(Color.ORANGE);
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					luu();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnLuu.setBounds(312, 306, 85, 32);
		add(btnLuu);

		JButton btnTim = new JButton("Tìm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTim.setBackground(Color.ORANGE);
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String maNhanVien = JOptionPane.showInputDialog(null,"Mời bạn nhập mã nhân viên");
				NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
				NhanVien nv = nhanVien_DAO.getNhanVien(maNhanVien);
				if(nv == null) {
					JOptionPane.showMessageDialog(null, "Không tồn tại mã nhân viên cần tìm");
				}else {
					String ma = nv.getMaNhanVien();
					String ho = nv.getHo();
					String ten = nv.getTen();
					String sdt = nv.getSdt();
					String diaChi = nv.getDiaChi();
					String email = nv.getEmail();
					String gioiTinh = nv.isGioiTinh() ? "Nam" : "Nữ";
					String ngayVaoLam = nv.getNgayVaoLam().toString();
					String sCCCD = nv.getSoCCCD();
					String matKhau = nv.getMatKhau();
					String maphongBan = nv.getMaPhongBan();
					String tenPhongBan = "";
					PhongBan_DAO pb = new PhongBan_DAO();
					// tenPhongBan la comboBox nen phai lam vay
					try {
						List<PhongBan> phongBanTheoMa = pb.getAllPhongBan();
						for (PhongBan phongBan : phongBanTheoMa) {
							if(phongBan.getMaPhongBan().equals(maphongBan)) {
								tenPhongBan = phongBan.getTenPhongBan();
							}
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
					while(tableModel.getRowCount() > 0) {
						tableModel.removeRow(0);
					}
					tableModel.addRow(new Object[] {ma, ho, ten, sdt, diaChi, email, gioiTinh, ngayVaoLam, sCCCD, matKhau,
							tenPhongBan });
				}
						
			}
		});
		btnTim.setBounds(419, 306, 89, 32);
		add(btnTim);

		String[] s = { "Mã NV", "Họ", "Tên", "Số DT", "Địa chỉ", "Email", "Giới tính", "Ngày vào làm", "SCCCD",
				"Mật khẩu", "Phòng ban" };
		tableModel = new DefaultTableModel(s, 0);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMaNhanVien.setText(tableModel.getValueAt(row, 0).toString());
				txtHo.setText(tableModel.getValueAt(row, 1).toString());
				txtTen.setText(tableModel.getValueAt(row, 2).toString());
				txtSdt.setText(tableModel.getValueAt(row, 3).toString());
				txtDiaChi.setText(tableModel.getValueAt(row, 4).toString());
				txtEmail.setText(tableModel.getValueAt(row, 5).toString());
				if(tableModel.getValueAt(row, 6).toString().equalsIgnoreCase("Nam")) {
					rdNam.setSelected(true);
				}else {
					rdNu.setSelected(false);
				}
				String d = tableModel.getValueAt(row, 7).toString();
				try {
					java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(d);
					txtDate.setDate(date);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				txtSoCCCD.setText(tableModel.getValueAt(row, 8).toString());
				txtMatKhau.setText(table.getValueAt(row, 9).toString());
				txtNhapLai.setText(tableModel.getValueAt(row, 9).toString());
				cbPhongBan.setSelectedItem(tableModel.getValueAt(row, 10));
				
			}
		});

		JScrollPane sp = new JScrollPane(table);

		table.getRowHeight(30);
		add(sp);
		sp.setBounds(22, 362, 988, 188);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(134, 221, 263, 19);
		add(txtMatKhau);

		txtNhapLai = new JPasswordField();
		txtNhapLai.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtNhapLai.setBounds(566, 222, 268, 19);
		add(txtNhapLai);
		
		JButton btnHuy = new JButton("Quay Lại");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHuy.setBackground(Color.ORANGE);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
		btnHuy.setBounds(532, 306, 111, 32);
		add(btnHuy);

		loadData();
	}

	public boolean xoa() {
		if(table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Bạn phải chọn dòng cần xóa!");
			return false;
		}
		int row = table.getSelectedRow();
		String maNhanVien = tableModel.getValueAt(row, 0).toString();
		if(JOptionPane.showConfirmDialog(null, "Bạn xóa nhận xóa nhân viên ?", "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			NhanVien_DAO nhanVien = new NhanVien_DAO();
			try {
				if(nhanVien.xoaNhanVien(maNhanVien)) {
					loadData();
					JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
					huy();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void huy() {
		this.txtMaNhanVien.setText("");
		this.txtMaNhanVien.setEditable(true);
		this.txtHo.setText("");
		this.txtHo.setEditable(true);
		this.txtDate.setDate(null);
		this.txtDate.setEnabled(true);
		this.txtTen.setText("");
		this.txtTen.setEditable(true);
		this.txtSdt.setText("");
		this.txtSdt.setEditable(true);
		this.txtEmail.setText("");
		this.txtEmail.setEditable(true);
		this.rdNam.setSelected(true);
		this.rdNam.setEnabled(true);
		this.rdNu.setEnabled(true);
		this.txtSoCCCD.setText("");
		this.txtSoCCCD.setEditable(true);
		this.cbPhongBan.setSelectedIndex(0);
		this.cbPhongBan.setEditable(true);
		this.txtMatKhau.setText("");
		this.txtMatKhau.setEditable(true);
		this.txtNhapLai.setText("");
		this.txtNhapLai.setEditable(true);
		this.txtDiaChi.setText("");
		this.txtDiaChi.setEditable(true);
		
		try {
			loadData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean luu() throws SQLException {
		String maNhanVien = txtMaNhanVien.getText().trim();
		String ho = txtHo.getText().trim();
		String ten = txtTen.getText().trim();
		String sdt = txtSdt.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String email = txtEmail.getText().trim();
		boolean gioiTinh = true;
		if (rdNu.isSelected()) {
			gioiTinh = false;
		}
		java.util.Date ngayVaoLam = txtDate.getDate();
		String soCCCD = txtSoCCCD.getText();
		String matKhau = new String(txtMatKhau.getPassword());
		String phongBan = cbPhongBan.getSelectedItem().toString();
//		if (!(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$"))) {
//			JOptionPane.showMessageDialog(null, "Mã nhân viên phải theo mẫu NV|AD+[0-9]{6}");
//			System.out.println(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$"));
//			return false;
//		}
//		if (!(ho.matches("^[A-Z][a-z]+$"))) {
//			System.out.println(ho);
//			JOptionPane.showMessageDialog(null, "Họ nhân viên phải được viết hoa chữ cái đầu");
//			return false;
//		}
//		if (!(ten.matches("^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$"))) {
//			JOptionPane.showMessageDialog(this,
//					"Tên nhân viên phải được viết hoa chữ cái đầu mỗi tưc phải có 1 khoảng trắng");
//			return false;
//		}
//		if (!(sdt.matches("^[0-9]{9,11}$"))) {
//			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
//			return false;
//		}
//		if (!(email.matches("^[a-zA-Z0-9]\\w+@(gmail|email).com$"))) {
//			JOptionPane.showMessageDialog(this, "Email phải theo mẫu nguyenvan@gmail.com|@email.com");
//			return false;
//		}
//		if (ngayVaoLam.after(new java.util.Date())) {
//			JOptionPane.showMessageDialog(this, "Ngày vào làm không hợp lệ");
//			return false;
//		}
//		if (!soCCCD.matches("^[0-9]{12}$")) {
//			JOptionPane.showMessageDialog(this, "Số CCCD không hơp lệ, phai 0->9 {12}");
//			return false;
//		}
//		if (!(matKhau.equals(String.valueOf(this.txtNhapLai.getPassword())))) {
//
//			JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp");
//			return false;
//		}
		String maPhongBan = "";
		PhongBan_DAO phongban = new PhongBan_DAO();
		List<PhongBan> dspb = phongban.getAllPhongBan();
		for (PhongBan phongBan2 : dspb) {
			if(phongBan2.getTenPhongBan().equals(phongban)) {
				maPhongBan = phongBan2.getMaPhongBan();
			}
		}
		
		NhanVien nhanVien = new NhanVien(maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, ngayVaoLam, soCCCD, matKhau, maPhongBan);
		nhanVienDao.capNhatNhanVien(nhanVien);
		JOptionPane.showMessageDialog(null, "Cập nhật thành công");
		loadData();
		return true;
	}

	public boolean them() throws SQLException {
		String maNhanVien = this.txtMaNhanVien.getText().trim();
		String ho = this.txtHo.getText().trim();
		String ten = this.txtTen.getText().trim();
		String sdt = this.txtSdt.getText().trim();
		String diaChi = this.txtDiaChi.getText().trim();
		String email = this.txtEmail.getText().trim();
		boolean gioiTinh = true;
		if (this.rdNu.isSelected()) {
			gioiTinh = false;
		}
		java.util.Date ngayVaoLam = this.txtDate.getDate();
		String soCCCD = this.txtSoCCCD.getText();
		String matKhau = new String(this.txtMatKhau.getPassword());
		String phongBan = this.cbPhongBan.getSelectedItem().toString();
//		if (!(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$"))) {
//			JOptionPane.showMessageDialog(this, "Mã nhân viên phải theo mẫu NV|AD+[0-9]{6}");
//			System.out.println(maNhanVien.matches("^((AD)|(NV))[0-9]{6}$") + "");
//			return false;
//		}
//		if (!(ho.matches("^[A-Z][a-z]+$"))) {
//			System.out.println(ho);
//			JOptionPane.showMessageDialog(this, "Họ nhân viên phải được viết hoa chữ cái đầu");
//			return false;
//		}
//		if (!(ten.matches("^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$"))) {
//			JOptionPane.showMessageDialog(this,
//					"Tên nhân viên phải được viết hoa chữ cái đầu mỗi tưc phải có 1 khoảng trắng");
//			return false;
//		}
//		if (!(sdt.matches("^[0-9]{9,11}$"))) {
//			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
//		}
//		if (!(email.matches("^[a-z]\\w+@(gmail|email).com$"))) {
//			JOptionPane.showMessageDialog(this, "Email phải theo mẫu nguyenvan@gmail.com|@email.com");
//			return false;
//		}
//		if (ngayVaoLam.after(new java.util.Date())) {
//			JOptionPane.showMessageDialog(this, "Ngày vào làm không hợp lệ");
//			return false;
//		}
//		if (!soCCCD.matches("^[0-9]{12}$")) {
//			JOptionPane.showMessageDialog(this, "Số CCCD không hơp lệ, phai 0->9 {12}");
//			return false;
//		}
//		this.nhanVienDao = new NhanVien_DAO();
//		List<NhanVien> nhanVien = this.nhanVienDao.getAllNhanVien();
//		for (NhanVien nhanVien2 : nhanVien) {
//			if (nhanVien2.getMaNhanVien().equalsIgnoreCase(maNhanVien)) {
//				JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại");
//				return false;
//			}
//		}
//		if (!(matKhau.equals(this.txtNhapLai.getText()))) {
//			System.out.println(matKhau);
//			System.out.println(this);
//			JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp");
//			return false;
//		}
		String maPhongBan = "";
		PhongBan_DAO phongban = new PhongBan_DAO();
		List<PhongBan> dsPb = phongban.getAllPhongBan();
		for (PhongBan phongBan2 : dsPb) {
			if (phongBan2.getTenPhongBan().equals(phongBan)) {
				maPhongBan = phongBan2.getMaPhongBan();
			}
		}
		NhanVien nhanVien2 = new NhanVien(maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, ngayVaoLam, soCCCD,
				matKhau, maPhongBan);
		this.nhanVienDao.themNhanVien(nhanVien2);
		loadData();
		JOptionPane.showMessageDialog(this, "Thêm thành công");
		return true;
	}

	public void loadData() throws SQLException {
		while (this.tableModel.getRowCount() > 0) {
			this.tableModel.removeRow(0);
		}
		this.nhanVienDao = new NhanVien_DAO();
		List<NhanVien> dsNhanVien = this.nhanVienDao.getAllNhanVien();
		for (NhanVien nhanvien : dsNhanVien) {
			String maNhanVien = nhanvien.getMaNhanVien();
			String ho = nhanvien.getHo();
			String ten = nhanvien.getTen();
			String sdt = nhanvien.getSdt();
			String diaChi = nhanvien.getDiaChi();
			String email = nhanvien.getEmail();
			boolean gender = nhanvien.isGioiTinh();
			String gioiTinh = "Nam";
			if (!gender) {
				gioiTinh = "Nữ";
			}
			String ngayVaoLam = nhanvien.getNgayVaoLam().toString();
			String soCCCD = nhanvien.getSoCCCD();
			String matKhau = nhanvien.getMatKhau();
			String pb = nhanvien.getMaPhongBan();
			String phongBan = "";
			PhongBan_DAO phongbanDao = new PhongBan_DAO();
			List<PhongBan> dsPhongBan = phongbanDao.getAllPhongBan();
			for (PhongBan pb1 : dsPhongBan) {
				if (pb1.getMaPhongBan().equalsIgnoreCase(pb)) {
					phongBan = pb1.getTenPhongBan();
					break;
				}
			}
			tableModel.addRow(new Object[] { maNhanVien, ho, ten, sdt, diaChi, email, gioiTinh, ngayVaoLam, soCCCD,
					matKhau, phongBan });
		}

	}
}
