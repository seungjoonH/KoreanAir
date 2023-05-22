package view.page;

import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.*;

import model.dao.CustomerDAOFactory;
import model.user.Customer;
import model.user.User;
import view.page.CustomerInfoPage;

public class MyInfoPage extends CustomerInfoPage implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected String getTitle() { return "내 정보"; }

    public MyInfoPage() {
        super(null, null, true);
    }
    public MyInfoPage(JComponent left) {
        super(left, null, true);
    }
    public MyInfoPage(JComponent left, JComponent right) {
        super(left, right, true);
    }
    public MyInfoPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected String getSubmitButtonText() { return "저장"; }

    @Override
    public void setInit() {
        super.setInit();
        CustomerDAOFactory.getFactory().loadList();
        idField.setText(User.getId());
        passwordField.setText(User.getPassword());
        nameField.setText(User.getName());
        sexCombo.setSelectedIndex(User.getSex().ordinal());
        birthField.setText(User.getBirth().toString());
        emailField.setText(User.getEmail());
        phoneField.setText(User.getPhone());
        passportNoField.setText(User.getPassportNo());
    }

    protected void setUser() {
        String[] csvList = getCSV();
        csvList[0] = User.getUid();
        csvList[csvList.length - 1] = String.valueOf(User.getMileagePoint());
        formedUser = new Customer(csvList);
    }

    public void submit() {
        if (!validateField()) return;
        setUser();
        User.setUser(formedUser);
        JOptionPane.showMessageDialog(this, "성공적으로 저장 되었습니다");
    }
}