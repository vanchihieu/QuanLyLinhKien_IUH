package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import helpers.SharedData;

import javax.swing.JScrollBar;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;

public class MainForm extends JFrame {
	private StudentManagementPanel mStudentPanel;
	private GPAManagementPanel mGPAPanel;
	private QuanLyLoaiLinhKien mLoaiLinhKienPanel;
	private QuanLyNhanVien mQuanLyNhanVienPanel;
	private Salary mThongKeLUongPanel;
	private QuanLySanPham mQuanLySanPhamPanel;
	private MatHang mMatHangBanChay;
	private QuanLyHoaDon muQuanLyHoaDonPanel;
	private QuanLyChiTietHoaDon mQuanLyCHiTietHoaDonPanel;
	
	private JPanel contentPane;
	private JTabbedPane tplMainBoard;
	private JButton tbrAboutUs;
	private JLabel lblLoginName;
	private JLabel lblRole;
	private JMenuItem mnuManageGPA;
	private JMenuItem mnuManagerStudent;
	private JMenuItem mnuLoaiLinhKien;
	private JMenuItem mnuQuanLyNhanVien;
	private JMenuItem mnuThongKeLuong;
	private JMenuItem mnuQuanLyLinhKien;
	private JMenuItem mnuQuanLyHoaDon;
	private JMenuItem mnuQuanLyChiTietHoaDon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setBackground(new Color(255, 255, 255));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				LoginDiaLog dialog = new LoginDiaLog();
				dialog.setVisible(true);
			}
		});
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
	

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 770);
		setLocationRelativeTo(null);
		
	
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 51, 102));
		menuBar.setBackground(new Color(152, 251, 152));
		menuBar.setBounds(0, 0, 293, 22);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Hệ Thống");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnNewMenu.setForeground(new Color(255, 51, 102));
		mnNewMenu.setBackground(new Color(255, 51, 102));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Đăng Xuất");
		mntmNewMenuItem.setIcon(new ImageIcon(MainForm.class.getResource("/icons/Login-icon-16.png")));
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mnuFile_Exit = new JMenuItem("Thoát");
		mnuFile_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnuFile_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		mnuFile_Exit.setIcon(new ImageIcon(MainForm.class.getResource("/icons/Button-Close-icon-16.png")));
		mnNewMenu.add(mnuFile_Exit);

		JMenu mnNewMenu_1 = new JMenu("Quản Lý");
		menuBar.add(mnNewMenu_1);

		 mnuManagerStudent = new JMenuItem("Quản Lý Khách Hàng");
		mnuManagerStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mStudentPanel == null) {
					mStudentPanel = new StudentManagementPanel();
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/10207-man-student-light-skin-tone-icon-16.png"));
					tplMainBoard.addTab("Quản Lý Khách Hàng", icon, mStudentPanel, "Quản Lý Khách Hàng");
				}
				tplMainBoard.setSelectedComponent(mStudentPanel);
			}
		});
		mnuManagerStudent.setIcon(
				new ImageIcon("D:\\Code\\java\\demo_HSK\\App_QLLK\\src\\icons\\userClient.png"));
		mnuManagerStudent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu_1.add(mnuManagerStudent);

		JSeparator separator_1 = new JSeparator();
		mnNewMenu_1.add(separator_1);

		 mnuManageGPA = new JMenuItem("Quản Lý ĐIểm");
		mnuManageGPA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mGPAPanel == null) {
					mGPAPanel = new GPAManagementPanel();
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Quản Lý Điểm", icon, mGPAPanel, "Quản Lý Điểm");
				}
				tplMainBoard.setSelectedComponent(mGPAPanel);
			}
		});
		mnuManageGPA.setIcon(new ImageIcon(MainForm.class.getResource("/icons/gpa-icon.png")));
		mnNewMenu_1.add(mnuManageGPA);
		
		JSeparator separator_3 = new JSeparator();
		mnNewMenu_1.add(separator_3);
		
		 mnuLoaiLinhKien = new JMenuItem("Quản Lý Loại Linh Kiện");
		 mnuLoaiLinhKien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		 mnuLoaiLinhKien.setIcon(new ImageIcon("D:\\Code\\java\\demo_HSK\\App_QLLK\\src\\icons\\Laptop.16.png"));
		mnuLoaiLinhKien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mLoaiLinhKienPanel == null) {
					mLoaiLinhKienPanel = new QuanLyLoaiLinhKien();
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Quản Lý Loại Linh Kiện", icon, mLoaiLinhKienPanel, "Quản Lý Loại Linh Kiện");
				}
				tplMainBoard.setSelectedComponent(mLoaiLinhKienPanel);
			}
		});
		mnNewMenu_1.add(mnuLoaiLinhKien);
		
		JSeparator separator_4 = new JSeparator();
		mnNewMenu_1.add(separator_4);
		
		 mnuQuanLyNhanVien = new JMenuItem("Quản Lý Nhân Viên");
		 mnuQuanLyNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		 mnuQuanLyNhanVien.setIcon(new ImageIcon("D:\\Code\\java\\demo_HSK\\App_QLLK\\src\\icons\\People.16.png"));
		 mnuQuanLyNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mQuanLyNhanVienPanel == null) {
					try {
						mQuanLyNhanVienPanel = new QuanLyNhanVien();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Quản Lý Nhân Viên", icon, mQuanLyNhanVienPanel, "Quản Lý Nhân Viên");
				}
				tplMainBoard.setSelectedComponent(mQuanLyNhanVienPanel);
			}
		});
		mnNewMenu_1.add(mnuQuanLyNhanVien);
		
		JSeparator separator_5 = new JSeparator();
		mnNewMenu_1.add(separator_5);
		
		 mnuQuanLyLinhKien = new JMenuItem("Quản Lý Linh Kiện");
		mnuQuanLyLinhKien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mQuanLySanPhamPanel == null) {
					try {
						mQuanLySanPhamPanel = new QuanLySanPham();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Quản Lý Linh Kiện", icon, mQuanLySanPhamPanel, "Quản Lý Linh Kiện");
				}
				tplMainBoard.setSelectedComponent(mQuanLySanPhamPanel);
			}
		});
		mnNewMenu_1.add(mnuQuanLyLinhKien);
		
		JSeparator separator_6 = new JSeparator();
		mnNewMenu_1.add(separator_6);
		
		 mnuQuanLyHoaDon = new JMenuItem("Quản Lý Hóa Đơn");
		 mnuQuanLyHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(muQuanLyHoaDonPanel == null) {
					try {
						muQuanLyHoaDonPanel = new QuanLyHoaDon();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Quản Lý Hóa Đơn", icon, muQuanLyHoaDonPanel, "Quản Lý Hóa Đơn");
				}
				tplMainBoard.setSelectedComponent(muQuanLyHoaDonPanel);
			}
		});
		mnNewMenu_1.add(mnuQuanLyHoaDon);
		
		JSeparator separator_8 = new JSeparator();
		mnNewMenu_1.add(separator_8);
		
		 mnuQuanLyChiTietHoaDon = new JMenuItem("Quản Lý Chi Tiết Hóa Đơn");
		 mnuQuanLyChiTietHoaDon.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		if(mQuanLyCHiTietHoaDonPanel == null) {
					try {
						mQuanLyCHiTietHoaDonPanel = new QuanLyChiTietHoaDon();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Quản Lý Chi Tiết Hóa Đơn", icon, mQuanLyCHiTietHoaDonPanel, "Quản Lý Chi Tiết Hóa Đơn");
				}
				tplMainBoard.setSelectedComponent(mQuanLyCHiTietHoaDonPanel);
		 	}
		 });
		mnNewMenu_1.add(mnuQuanLyChiTietHoaDon);

		JMenu mnNewMenu_2 = new JMenu("Trợ Giúp");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Nội Dung");
		mntmNewMenuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmNewMenuItem_4.setIcon(new ImageIcon(MainForm.class.getResource("/icons/Actions-help-about-icon-16.png")));
		mnNewMenu_2.add(mntmNewMenuItem_4);

		JSeparator separator_2 = new JSeparator();
		mnNewMenu_2.add(separator_2);

		JMenuItem mnuAboutUs = new JMenuItem("Giới Thiệu");
		mnuAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						AboutUsDialog aboutUsDialog = new AboutUsDialog();
						aboutUsDialog.setVisible(true);
					}
				
			});
		mnuAboutUs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
		mnuAboutUs.setIcon(new ImageIcon(MainForm.class.getResource("/icons/Help-icon-16.png")));
		mnNewMenu_2.add(mnuAboutUs);
		
		JMenu mnNewMenu_3 = new JMenu("Thống Kê");
		menuBar.add(mnNewMenu_3);
		
		mnuThongKeLuong = new JMenuItem("Thống Kê Lương Nhân Viên");
		mnuThongKeLuong.setIcon(new ImageIcon("D:\\Code\\java\\demo_HSK\\App_QLLK\\src\\icons\\dollar.16.png"));
		mnuThongKeLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mThongKeLUongPanel == null) {
					try {
						mThongKeLUongPanel = new Salary();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Thống Kê Lương Nhân Viên", icon, mThongKeLUongPanel, "Thống Kê Lương Nhân Viên");
				}
				tplMainBoard.setSelectedComponent(mThongKeLUongPanel);
			}
		});
		mnNewMenu_3.add(mnuThongKeLuong);
		
		JSeparator separator_7 = new JSeparator();
		mnNewMenu_3.add(separator_7);
		
		JMenuItem mnuMatHangBanChay = new JMenuItem("Mặt Hàng Bán Chạy");
		mnuMatHangBanChay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mMatHangBanChay == null) {
					try {
						mMatHangBanChay = new MatHang();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ImageIcon icon = new ImageIcon(
							getClass().getResource("/icons/gpa-icon.png"));
					tplMainBoard.addTab("Thống Kê Mặt Hàng Bán Chạy", icon, mMatHangBanChay, "Thống Kê Mặt Hàng Bán Chạy");
				}
				tplMainBoard.setSelectedComponent(mMatHangBanChay);
			}
		});
		mnNewMenu_3.add(mnuMatHangBanChay);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 23, 543, 60);
		contentPane.add(toolBar);

		JButton tbrLogout = new JButton("Đăng Xuất");
		tbrLogout.setHorizontalAlignment(SwingConstants.LEFT);
		tbrLogout.setVerticalAlignment(SwingConstants.TOP);
		tbrLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginDiaLog loginDialog = new LoginDiaLog();
				
				loginDialog.setVisible(true);
				
				processLoginSuccessFul();
			}
		});
		tbrLogout.setIcon(new ImageIcon(MainForm.class.getResource("/icons/logout-icon-32.png")));
		toolBar.add(tbrLogout);

		JButton btnNewButton_1 = new JButton("Quản Lý Linh Kiện");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setIcon(
				new ImageIcon("D:\\Code\\java\\demo_HSK\\App_QLLK\\src\\icons\\devicesComputer.png"));
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("Quản Lý Nhân Viên");
		btnNewButton_1_1.setIcon(new ImageIcon("D:\\Code\\java\\demo_HSK\\App_QLLK\\src\\icons\\clients.png"));
		toolBar.add(btnNewButton_1_1);
		
				 tbrAboutUs = new JButton("Giới Thiệu");
				 toolBar.add(tbrAboutUs);
				 tbrAboutUs.addActionListener(new ActionListener() {
				 	public void actionPerformed(ActionEvent e) {
				 		AboutUsDialog aboutUsDialog = new AboutUsDialog();
				 		aboutUsDialog.setVisible(true);
				 	}
				 });
				 tbrAboutUs.setIcon(new ImageIcon(MainForm.class.getResource("/icons/Actions-help-about-icon-32.png")));

		tplMainBoard = new JTabbedPane(JTabbedPane.TOP);
		tplMainBoard.setBackground(new Color(255, 102, 102));
		tplMainBoard.setBounds(10, 80, 1129, 609);
		contentPane.add(tplMainBoard);
		
		JPanel panel = new JPanel();
		panel.setBounds(590, 10, 167, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		 lblRole = new JLabel("");
		lblRole.setHorizontalAlignment(SwingConstants.CENTER);
		lblRole.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblRole.setBounds(10, 33, 147, 13);
		panel.add(lblRole);
		
		 lblLoginName = new JLabel("");
		 lblLoginName.setBounds(640, 23, 147, 13);
		 contentPane.add(lblLoginName);
		 lblLoginName.setHorizontalAlignment(SwingConstants.CENTER);
		 lblLoginName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		
	}
	private void processLoginSuccessFul() {
		lblLoginName.setText(SharedData.nguoiDangNhap.getTenDangNhap());
		lblRole.setText(SharedData.nguoiDangNhap.getVaiTro());
		tplMainBoard.removeAll();
		if(SharedData.nguoiDangNhap.getVaiTro().equals("Giang vien")) {
			mnuManageGPA.setEnabled(true);
			mnuManagerStudent.setEnabled(false);
			
		}else if(SharedData.nguoiDangNhap.getVaiTro().equals("Dao tao")) {
			mnuManageGPA.setEnabled(false);
			mnuManagerStudent.setEnabled(true);
		}
	}
}
