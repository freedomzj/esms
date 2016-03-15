package com.sxt.gmms.frame.base.employeeinfo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sxt.gmms.dao.base.EmployeeInfoDao;
import com.sxt.gmms.entity.Employee;
import com.sxt.gmms.util.DateUI;
import com.sxt.gmms.util.DateUtil;
import com.sxt.gmms.util.PinyinUtil;

/**
 * 添加商品类
 * 
 * @author ming
 * 
 */
public class EditEmployeeInfoDialog extends JDialog implements ActionListener {

	JButton btnEdit;
	JButton btnCancel;
	private JTextField txtCode;
	private JTextField txtPin;
	private JTextField txtName;
	private JTextField txtSal;
	private JTextField txtPhone;
	EmployeeInfoFrame employeeInfoFrame;
	private JTextField txtAge;
	private JTextField txtDate;
	private JComboBox listSex;
	private JComboBox listStatus;
	private JTextArea txtAddress;
	String empCode;

	/**
	 * Create the dialog.
	 * 
	 * @param goodsInfoFrame
	 */
	public EditEmployeeInfoDialog(EmployeeInfoFrame employeeInfoFrame,
			String empCode) {
		this.employeeInfoFrame = employeeInfoFrame;
		this.empCode = empCode;
		setTitle("修改员工");
		setBounds(100, 100, 614, 427);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5458\u5DE5\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(0, 0, 598, 346);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("员工编号:");
		label.setBounds(34, 26, 54, 20);
		panel.add(label);

		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setColumns(10);
		txtCode.setBounds(108, 26, 153, 21);
		panel.add(txtCode);

		JLabel label_1 = new JLabel("员工拼音码:");
		label_1.setBounds(303, 26, 76, 20);
		panel.add(label_1);

		txtPin = new JTextField();
		txtPin.setColumns(10);
		txtPin.setEditable(false);
		txtPin.setBounds(389, 26, 166, 21);
		panel.add(txtPin);

		JLabel label_2 = new JLabel("员工姓名:");
		label_2.setBounds(34, 75, 54, 20);
		panel.add(label_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(108, 75, 153, 21);
		panel.add(txtName);

		JLabel label_3 = new JLabel("员工年龄:");
		label_3.setBounds(303, 78, 54, 20);
		panel.add(label_3);

		JLabel label_4 = new JLabel("员工性别:");
		label_4.setBounds(34, 176, 54, 20);
		panel.add(label_4);

		JLabel label_6 = new JLabel("基本工资:");
		label_6.setBounds(34, 125, 54, 20);
		panel.add(label_6);

		txtSal = new JTextField();
		txtSal.setColumns(10);
		txtSal.setBounds(108, 125, 153, 21);
		panel.add(txtSal);

		JLabel label_7 = new JLabel("员工电话:");
		label_7.setBounds(303, 124, 54, 20);
		panel.add(label_7);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(389, 124, 166, 21);
		panel.add(txtPhone);

		JLabel label_9 = new JLabel("员工状态:");
		label_9.setBounds(303, 175, 54, 20);
		panel.add(label_9);

		JLabel lblYuangong = new JLabel("员工备注:");
		lblYuangong.setBounds(34, 260, 54, 20);
		panel.add(lblYuangong);

		txtAddress = new JTextArea();
		txtAddress.setBounds(108, 263, 447, 61);
		panel.add(txtAddress);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(389, 75, 166, 21);
		panel.add(txtAge);

		listSex = new JComboBox();
		listSex.setModel(new DefaultComboBoxModel(new String[] { "男", "女" }));
		listSex.setBounds(108, 176, 153, 21);
		panel.add(listSex);

		listStatus = new JComboBox();
		listStatus.setModel(new DefaultComboBoxModel(
				new String[] { "在职", "离职" }));
		listStatus.setBounds(389, 175, 166, 21);
		panel.add(listStatus);

		JLabel label_5 = new JLabel("入职时间:");
		label_5.setBounds(34, 216, 54, 20);
		panel.add(label_5);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(108, 216, 350, 21);
		panel.add(txtDate);
		txtDate.setColumns(10);

		JButton btnChooseTime = new JButton("选择时间");
		btnChooseTime.setBounds(462, 215, 93, 23);
		btnChooseTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateUI.getInstence(true, txtDate,
						e.getXOnScreen() - txtDate.getWidth(), e.getYOnScreen()
								- txtDate.getHeight());
			}
		});
		panel.add(btnChooseTime);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(139, 356, 93, 23);
		getContentPane().add(btnEdit);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(371, 356, 93, 23);
		getContentPane().add(btnCancel);

		// 加载要修改的员工资料
		EmployeeInfoDao empDao = new EmployeeInfoDao();
		Employee emp = empDao.findEmployee(empCode);
		txtCode.setText(emp.getEmpCode());
		txtName.setText(emp.getEmpName());
		txtPin.setText(emp.getEmpPym());
		txtAge.setText("" + emp.getEmpAge());
		txtSal.setText("" + emp.getEmpBaseSal());
		txtPhone.setText(emp.getEmpPhone());
		listSex.setSelectedItem(emp.getEmpSex() == 1 ? "男" : "女");
		txtAddress.setText(emp.getEmpAddre());
		txtDate.setText(DateUtil.DateStringToString(emp.getEmpInDate()));
		listStatus.setSelectedItem(emp.getEmpStatus() == 1 ? "在职" : "离职");

		this.setLocationRelativeTo(this.employeeInfoFrame);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
			// 收集信息
			// 员工名称
			String empName = txtName.getText();
			Pattern p = Pattern
					.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}|[a-zA-Z]{1,20}");
			Matcher m = p.matcher(txtName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工名称输入有误请重新输入！");
				return;
			}
			// 员工拼音码
			String empPym = PinyinUtil.stringArrayToString(PinyinUtil
					.getHeadByString(txtPin.getText()));
			// 员工年龄
			p = Pattern.compile("\\d{1,3}");
			m = p.matcher(txtAge.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工年龄输入有误请重新输入！");
				return;
			}
			int empAge = Integer.parseInt(txtAge.getText());
			// 员工基本工资
			p = Pattern.compile("\\d*\\.?\\d+");
			m = p.matcher(txtSal.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工积分输入有误,请重新输入!");
				return;
			}
			float empBaseSal = Float.parseFloat(txtSal.getText());
			// 员工电话
			String empPhone = txtPhone.getText();
			p = Pattern.compile("1\\d{10}|\\d{3,4}-\\d{8}");
			m = p.matcher(txtPhone.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工电话输入有误请重新输入！");
				return;
			}
			int empSex = listSex.getSelectedItem().equals("男") ? 1 : 0;
			String empAddre = txtAddress.getText();
			Date empInDate = DateUtil.formatStringToDate(txtDate.getText());
			int empStatus = listStatus.getSelectedItem().equals("在职") ? 1 : 0;
			// 组装
			Employee emp = new Employee(0, empCode, empName, empPym, empAge,
					empBaseSal, empPhone, empSex, empAddre, empInDate,
					empStatus);
			int result = JOptionPane.showConfirmDialog(this, "确认修改 " + empName
					+ " 的信息吗？", "温馨提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用DAO层修改
				EmployeeInfoDao empDao = new EmployeeInfoDao();
				empDao.updateEmployeeInfo(emp);
				// 刷新
				employeeInfoFrame.loadEmpioyeeInfo();
				this.dispose();
			}
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
