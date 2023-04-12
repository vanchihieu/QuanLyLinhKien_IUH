package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.NguoiDungDao;
import helpers.DataValidator;
import helpers.MessageDialogHelper;
import helpers.SharedData;
import model.NguoiDung;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;

public class LoginDiaLog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDiaLog dialog = new LoginDiaLog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDiaLog() {
		setBounds(100, 100, 501, 237);
		setLocationRelativeTo(getParent());

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			panel_1 = new JPanel();
			sl_contentPanel.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, panel_1, 213, SpringLayout.WEST, contentPanel);
			contentPanel.add(panel_1);
		}

		JPanel panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 6, SpringLayout.SOUTH, panel_1);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 192, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, 195, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, 472, SpringLayout.WEST, contentPanel);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tên đăng nhập:");
		lblNewLabel.setBounds(10, 10, 97, 13);
		panel.add(lblNewLabel);
		{
			JLabel lblMtKhu = new JLabel("Mật khẩu:");
			lblMtKhu.setBounds(10, 59, 73, 13);
			panel.add(lblMtKhu);
		}

		txtUsername = new JTextField();
		txtUsername.setText("anhnn");
		txtUsername.setBounds(10, 33, 231, 19);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setText("abc");
		txtPassword.setBounds(10, 82, 231, 19);
		panel.add(txtPassword);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 117, 1, 2);
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 111, 204, 2);
		panel.add(separator_1);

		JButton btnClose = new JButton("Thoát");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setIcon(new ImageIcon(LoginDiaLog.class.getResource("/icons/Actions-edit-delete-icon-16.png")));
		btnClose.setBounds(145, 120, 96, 22);
		panel.add(btnClose);

		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogin.setIcon(new ImageIcon(LoginDiaLog.class.getResource("/icons/Login-icon-16.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				DataValidator.validateEmpty(txtUsername, sb, "Tên đăng nhập không được để trống!");
				DataValidator.validateEmpty(txtPassword, sb, "Mật khẩu không được để trống!");
				if (sb.length() > 0) {
					MessageDialogHelper.showErrorDialog(null, sb.toString(), "Lỗi");
					return;
				}

				NguoiDungDao dao = new NguoiDungDao();
				try {
					NguoiDung nd = dao.checkLogin(txtUsername.getText(), new String(txtPassword.getPassword()));
					if (nd == null) {
						MessageDialogHelper.showErrorDialog(null, "Tên đăng nhập sai hay mật khẩu sai", "Lỗi");
					} else {
						SharedData.nguoiDangNhap = nd;
						dispose();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
					MessageDialogHelper.showErrorDialog(null, e2.getMessage(), "Lỗi");
				}

			}
		});
		btnLogin.setBounds(10, 120, 115, 22);
		panel.add(btnLogin);

		JLabel lblLogo = new JLabel("");
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblLogo, 171, SpringLayout.WEST, contentPanel);
		lblLogo.setHorizontalTextPosition(SwingConstants.LEADING);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblLogo, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblLogo, 10, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblLogo, 164, SpringLayout.NORTH, contentPanel);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(
				new ImageIcon(LoginDiaLog.class.getResource("/icons/10207-man-student-light-skin-tone-icon-64.png")));
		contentPanel.add(lblLogo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
