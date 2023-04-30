package UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import dao.LinhKien_DAO;
import dao.LoailinhKien_DAO;
import dao.NhaCungCap_DAO;
import helpers.ConnectDB;
import helpers.ImageHelper;
import helpers.MessageDialogHelper;
import model.LinhKien;
import model.LoaiLinhKien;
import model.NhaCungCap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class QuanLySanPham extends JPanel {
	private MainForm parentForm;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtMaNhaCCC;
	private JTextField txtmaLoai;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;
	private JTextField txtDiaChi;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnThem;
	private JButton btnDelete;
	private JButton btnSave;
	
	private LinhKien linhKien;
	private LinhKien_DAO linhKien_DAO;
	private LoailinhKien_DAO loaiLinhKien;
	private JLabel lbAnh;
	private JButton btnBrowse;
	
	private byte[] personalImage;
	/**
	 * Create the panel.
	 */
	public QuanLySanPham() throws SQLException {
		ConnectDB.getInstance().connect();
		
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Sản Phẩm");
		lblNewLabel.setBounds(416, 10, 85, 13);
		add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(21, 45, 204, 248);
		add(panel);
		panel.setLayout(null);
		
		lbAnh = new JLabel("");
		lbAnh.setBounds(10, 10, 184, 228);
		panel.add(lbAnh);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(235, 45, 430, 248);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã Linh Kiện:");
		lblNewLabel_1.setBounds(10, 10, 83, 26);
		panel_1.add(lblNewLabel_1);

		txtMa = new JTextField();
		txtMa.setBounds(130, 14, 294, 19);
		panel_1.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Tên Linh Kiện:");
		lblNewLabel_1_1.setBounds(10, 46, 83, 26);
		panel_1.add(lblNewLabel_1_1);

		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(130, 50, 294, 19);
		panel_1.add(txtTen);

		JLabel lblNewLabel_1_1_1 = new JLabel("Mã Nhà Cung Cấp:");
		lblNewLabel_1_1_1.setBounds(10, 79, 113, 26);
		panel_1.add(lblNewLabel_1_1_1);

		txtMaNhaCCC = new JTextField();
		txtMaNhaCCC.setColumns(10);
		txtMaNhaCCC.setBounds(130, 79, 294, 19);
		panel_1.add(txtMaNhaCCC);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Mã Loại:");
		lblNewLabel_1_1_1_1.setBounds(10, 111, 113, 26);
		panel_1.add(lblNewLabel_1_1_1_1);

		txtmaLoai = new JTextField();
		txtmaLoai.setColumns(10);
		txtmaLoai.setBounds(130, 115, 294, 19);
		panel_1.add(txtmaLoai);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Số Lượng:");
		lblNewLabel_1_1_1_1_1.setBounds(10, 144, 113, 26);
		panel_1.add(lblNewLabel_1_1_1_1_1);

		txtSoLuong = new JTextField();
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(130, 148, 294, 19);
		panel_1.add(txtSoLuong);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Đơn Giá:");
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 175, 113, 26);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);

		txtDonGia = new JTextField();
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(130, 180, 294, 19);
		panel_1.add(txtDonGia);

		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Địa Chỉ Hình Ảnh:");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(10, 211, 113, 26);
		panel_1.add(lblNewLabel_1_1_1_1_1_1_1);

		txtDiaChi = new JTextField();
		txtDiaChi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser filechoose = new JFileChooser();
				int rp = filechoose.showSaveDialog(null);
				if (rp == JFileChooser.APPROVE_OPTION) {
					File file = filechoose.getSelectedFile();
					txtDiaChi.setText(file.getName());
				}
			}
		});
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(130, 215, 294, 19);
		panel_1.add(txtDiaChi);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(679, 45, 209, 248);
		add(panel_2);
		panel_2.setLayout(null);

		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ktInput();
			}
		});
		btnThem.setBounds(57, 32, 101, 21);
		panel_2.add(btnThem);

		btnDelete = new JButton("Xóa");
		btnDelete.setBounds(57, 77, 101, 21);
		panel_2.add(btnDelete);

		btnSave = new JButton("Cập Nhật");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ktUpdate();
			}
		});
		btnSave.setBounds(57, 168, 101, 21);
		panel_2.add(btnSave);
		
		btnBrowse = new JButton("Mở Hình");
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
					lbAnh.setIcon(resizedIcon);
					personalImage = ImageHelper.toByteArray(img, "jpg");
					
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showMessageDialog(parentForm, e2.getMessage(), "Loi!");
				}
			}
		});
		btnBrowse.setBounds(57, 205, 101, 21);
		panel_2.add(btnBrowse);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnTim.getText().equalsIgnoreCase("Tìm")) {
					btnTim.setText("Hủy");
					timTheoMaLoai();
				} else {
					btnTim.setText("Tìm");
					loadData();
				}
			}
		});
		btnTim.setBounds(57, 122, 101, 21);
		panel_2.add(btnTim);

		String[] s = { "Mã linh kiện", "Tên", "mã nhà cung cấp", "Mã loại", "Số lượng", "Đơn giá nhập",
				" Địa chỉ hình ảnh" };
		model = new DefaultTableModel(s, 0);
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txtMa.setText(model.getValueAt(row, 0).toString());
				txtTen.setText(model.getValueAt(row, 1).toString());
				txtMaNhaCCC.setText(model.getValueAt(row, 2).toString());
				txtmaLoai.setText(model.getValueAt(row, 3).toString());
				txtSoLuong.setText(model.getValueAt(row, 4).toString());
				txtDonGia.setText(model.getValueAt(row, 5).toString());
				txtDiaChi.setText(model.getValueAt(row, 6).toString());
				String diaChi = model.getValueAt(row, 6).toString();
				ImageIcon icon = new ImageIcon(this.getClass().getResource(diaChi));
				Image img = icon.getImage();
				Image scale = img.getScaledInstance(250, 270, Image.SCALE_SMOOTH);
				ImageIcon scaleIcon = new ImageIcon(scale);
				lbAnh.setIcon(scaleIcon);
				

			}
		});
	

		JScrollPane scrolTable = new JScrollPane(table);

		add(scrolTable);
		scrolTable.setBounds(33, 313, 918, 186);

		loadData();
	}

	public boolean ktInput() {
		String maLinhKien = this.txtMa.getText().trim();
		String tenLinhKien = this.txtTen.getText().trim();
		String nhaCungCap = this.txtMaNhaCCC.getText().trim();
		String maLoai = this.txtmaLoai.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String donGia = txtDonGia.getText().trim();
		String diaChiHinhAnh = txtDiaChi.getText().trim();
		double donGiaDou = 0;
		int soLuongInt = 0;
//		if (maLinhKien.equals("")) {
//			JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống");
//			this.txtMa.setRequestFocusEnabled(true);
//			return false;
//		}
//		if (tenLinhKien.equals("")) {
//			JOptionPane.showMessageDialog(this, "Tên linh kiện không  được đê trống");
//			this.txtTen.setRequestFocusEnabled(true);
//			return false;
//		}
//		if (nhaCungCap.equals("")) {
//			JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không đc để trống");
//			this.txtMaNhaCCC.setRequestFocusEnabled(true);
//			return false;
//		}
//		
//		if (!maLinhKien.matches("^LK\\d{3}$")) {
//			JOptionPane.showMessageDialog(null, "Mã Linh kiện theo định dạng: LKxxx với x là các số bất kỳ");
//			return false;
//		}
//		if (!tenLinhKien.matches("^([A-Z][a-z]+)(\\s[a-zA-Z0-9]+)*$")) {
//			JOptionPane.showMessageDialog(null, "Tên linh kiện bắt đầu bằng in hoa, từ đầu tiên không có số. các từ không có kí tự đặc biệt");
//			return false;
//		}
//		if (!nhaCungCap.matches("^NCC\\d{3}$")) {
//			JOptionPane.showMessageDialog(null, "Mã Nhà cung cấp theo định dạng: NCCxxx với x là các số bất kỳ");
//			return false;
//		}
//		if (!maLoai.matches("^LLK\\d{3}$")) {
//			JOptionPane.showMessageDialog(null, "Mã Loại linh kiện theo định dạng: LLKxxx với x là các số bất kỳ");
//			return false;
//		}
//		try {
//			soLuongInt = Integer.parseInt(soLuong);
//			if (soLuongInt <= 0) {
//				JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0");
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			JOptionPane.showMessageDialog(null, "Đầu vào của field Số lượng không hợp lệ!");
//			return false;
//		}
//		try {
//			donGiaDou = Double.parseDouble(donGia);
//			if (donGiaDou < 0) {
//				JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn bằng 0");
//				return false;
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			JOptionPane.showMessageDialog(null, "Đầu vào của field Đơn giá không hợp lệ!");
//			return false;
//		}
//	
//		if (!diaChiHinhAnh.matches(".+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$")) {
//			JOptionPane.showMessageDialog(null, "Đuôi định dạng phải là .png hoặc .jpg");
//			return false;
//		}
		
		NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
		List<NhaCungCap> dsNCC = nccDao.getAllNhaCungCap();
		boolean kt = false;
		for (NhaCungCap nhaCungCap2 : dsNCC) {
			if (nhaCungCap.equals(nhaCungCap2.getMaNhaCungCap())) {
				kt = true;
			}
		}
		if (!kt) {
			JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại trong Database");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		if (maLoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Loại sản phẩm không đc để trống");
			this.txtmaLoai.setRequestFocusEnabled(true);
			return false;
		}

		LoailinhKien_DAO loaiLK_deo = new LoailinhKien_DAO();
		List<LoaiLinhKien> dsLk = loaiLK_deo.getAllLoaiLinhKien();
		boolean kt2 = false;
		for (LoaiLinhKien loailk : dsLk) {
			if (maLoai.equals(loailk.getMaloai())) {
				kt2 = true;
			}
		}
		if (!kt2) {
			JOptionPane.showMessageDialog(this, "Mã loại không tồn tại");
			return false;
		}

		try {
			soLuongInt = Integer.parseInt(this.txtSoLuong.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Số lượng linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		try {
			donGiaDou = Double.parseDouble(this.txtDonGia.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Đơn giá  linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		String diaChi = this.txtDiaChi.getText().trim();
		if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn ảnh để lưu");
			return false;
		}
		String diaChiNew = "/images_LinhKien/" ;
	
		
		
		LinhKien_DAO lk_Dao = new LinhKien_DAO();
		LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuongInt, diaChiNew, maLoai, nhaCungCap, donGiaDou);
		if (!lk_Dao.themLinhKien(lk)) {
			JOptionPane.showMessageDialog(this, "Thêm không thành công");
			return false;
		}
		JOptionPane.showMessageDialog(this, "Thêm thành công");
		loadData();
		return true;
	}
	public boolean ktUpdate() {
		String maLinhKien = this.txtMa.getText().trim();
		String tenLinhKien = this.txtTen.getText().trim();
		String nhaCungCap = this.txtMaNhaCCC.getText().trim();
		String maLoai = this.txtmaLoai.getText().trim();
		double donGia = 0;
		int soLuong = 0;
		if (maLinhKien.equals("")) {
			JOptionPane.showMessageDialog(this, "Ma san pham ko dc de trong");
			this.txtMa.setRequestFocusEnabled(true);
			return false;
		}
		if (tenLinhKien.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên linh kiện không  được đê trống");
			this.txtTen.setRequestFocusEnabled(true);
			return false;
		}
		if (nhaCungCap.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không đc để trống");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		NhaCungCap_DAO nccDao = new NhaCungCap_DAO();
		List<NhaCungCap> dsNCC = nccDao.getAllNhaCungCap();
		boolean kt = false;
		for (NhaCungCap nhaCungCap2 : dsNCC) {
			if (nhaCungCap.equals(nhaCungCap2.getMaNhaCungCap())) {
				kt = true;
			}
		}
		if (!kt) {
			JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại trong Database");
			this.txtMaNhaCCC.setRequestFocusEnabled(true);
			return false;
		}
		if (maLoai.equals("")) {
			JOptionPane.showMessageDialog(this, "Loại sản phẩm không đc để trống");
			this.txtmaLoai.setRequestFocusEnabled(true);
			return false;
		}

		LoailinhKien_DAO loaiLK_deo = new LoailinhKien_DAO();
		List<LoaiLinhKien> dsLk = loaiLK_deo.getAllLoaiLinhKien();
		boolean kt2 = false;
		for (LoaiLinhKien loailk : dsLk) {
			if (maLoai.equals(loailk.getMaloai())) {
				kt2 = true;
			}
		}
		if (!kt2) {
			JOptionPane.showMessageDialog(this, "Mã loại không tồn tại");
			return false;
		}

		try {
			soLuong = Integer.parseInt(this.txtSoLuong.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Số lượng linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		try {
			donGia = Double.parseDouble(this.txtDonGia.getText().trim());
		} catch (Exception e2) {
			JOptionPane.showConfirmDialog(this, "Đơn giá  linh kiện bạn nhập ko hợp lệ", "Lỗi", JOptionPane.YES_OPTION);
			return false;
		}
		String diaChi = this.txtDiaChi.getText().trim();
		if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn ảnh để lưu");
			return false;
		}
		LinhKien_DAO lk_Dao = new LinhKien_DAO();
		LinhKien lk = new LinhKien(maLinhKien, tenLinhKien, soLuong, diaChi, maLoai, nhaCungCap, donGia);
		try {
			if (!lk_Dao.capNhatLinhKien(lk)) {
				JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
				return false;
			}
		} catch (HeadlessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadData();
		JOptionPane.showMessageDialog(this, "Cập nhật  thành công");
		return true;
	}
	public void loadData() {
		while (this.model.getRowCount() > 0) {
			this.model.removeRow(0);
		}
		this.linhKien_DAO = new LinhKien_DAO();
		int num = 0;
		List<LinhKien> dslk = this.linhKien_DAO.getAllLinhKien();
		for (LinhKien lk : dslk) {
			model.addRow(new Object[] { lk.getMaLinhKien(), lk.getTenLinhKien(), lk.getMaNhaCungCap(), lk.getMaLoai(),
					lk.getSoLuong(), lk.getDonGia(), lk.getDiaChiHinhAnh() });
			if (num == 0) {
				txtMa.setText(dslk.get(0).getMaLinhKien());
				txtmaLoai.setText(dslk.get(0).getMaLoai());
				txtDiaChi.setText(dslk.get(0).getDiaChiHinhAnh());
				txtDonGia.setText(dslk.get(0).getDonGia() + "");
				txtMaNhaCCC.setText(dslk.get(0).getMaNhaCungCap());
				txtSoLuong.setText(dslk.get(0).getSoLuong() + "");
				txtTen.setText(dslk.get(0).getTenLinhKien());
				table.setRowSelectionInterval(0, 0);
			}
			num++;
		}

	}

	public void timTheoMaLoai() {

		String maMaLoai = JOptionPane.showInputDialog("Nhập vào mã cần tìm");
		ArrayList<LinhKien> dslk = (ArrayList<LinhKien>) linhKien_DAO.getLinhKienTheoMa(maMaLoai);
		if (dslk.size() == 0) {
			JOptionPane.showMessageDialog(null, "Không có mã linh kiện này trong bảng");

		} else {
			while (model.getRowCount() > 0) {
				model.removeRow(0);
			}

			for (LinhKien lk : dslk) {
				model.addRow(new Object[] { lk.getMaLinhKien(), lk.getTenLinhKien(), lk.getMaNhaCungCap(),
						lk.getMaLoai(), lk.getSoLuong(), lk.getDonGia(), lk.getDiaChiHinhAnh() });
			}
			txtMa.setText(dslk.get(0).getMaLinhKien());
			txtmaLoai.setText(dslk.get(0).getMaLoai());
			txtDiaChi.setText(dslk.get(0).getDiaChiHinhAnh());
			txtDonGia.setText(dslk.get(0).getDonGia() + "");
			txtMaNhaCCC.setText(dslk.get(0).getMaNhaCungCap());
			txtSoLuong.setText(dslk.get(0).getSoLuong() + "");
			txtTen.setText(dslk.get(0).getTenLinhKien());
			table.setRowSelectionInterval(0, 0);
		}
	}
}
