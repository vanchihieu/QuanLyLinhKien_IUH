package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutUsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutUsDialog dialog = new AboutUsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutUsDialog() {
		setBounds(100, 100, 554, 434);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon(AboutUsDialog.class.getResource("/icons/10207-man-student-light-skin-tone-icon-64.png")));
			lblNewLabel.setBounds(23, 10, 180, 186);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ SINH VIÊN");
			lblNewLabel_1.setForeground(new Color(0, 255, 64));
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblNewLabel_1.setBounds(244, 10, 201, 26);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(228, 46, 217, 13);
			contentPanel.add(separator);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Đơn Vị: IUH");
			lblNewLabel_1.setBounds(244, 56, 102, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Tác Giả: Văn Chí Hiếu");
			lblNewLabel_1.setBounds(244, 88, 142, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 206, 520, 13);
			contentPanel.add(separator);
		}
		{
			JButton btnClose = new JButton("Đóng");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnClose.setBounds(445, 216, 85, 21);
			contentPanel.add(btnClose);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
