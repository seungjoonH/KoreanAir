package view.page;

import model.user.Customer;
import model.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CustomerInfoPage extends Page implements ActionListener {
    protected JTextField idField;
    protected JTextField passwordField;
    protected JTextField nameField;
    protected JComboBox<String> sexCombo;
    protected JTextField birthField;
    protected JTextField emailField;
    protected JTextField phoneField;
    protected JTextField passportNoField;

    protected JButton submitButton;


    protected static Customer formedUser;
    protected CustomerInfoPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected void setInit() {
        idField = new JTextField(20);
        passwordField = new JPasswordField(20);
        nameField = new JTextField(20);
        sexCombo = new JComboBox<String>();
        birthField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        passportNoField = new JTextField(20);
    }

    protected abstract String getSubmitButtonText();

    @Override
    protected void build() {
        JLabel idLabel = new JLabel("아이디 :");
        JLabel passwordLabel = new JLabel("비밀번호 :");
        JLabel nameLabel = new JLabel("이름 :");
        JLabel sexLabel = new JLabel("성별 :");
        JLabel birthLabel = new JLabel("생년월일(YYYY-MM-DD) :");
        JLabel emailLabel = new JLabel("이메일 :");
        JLabel phoneLabel = new JLabel("휴대폰(- 생략) :");
        JLabel passportNoLabel = new JLabel("여권번호(영문 1자리 + 숫자 8자리) :");

        submitButton = new JButton(getSubmitButtonText());
        submitButton.addActionListener(this);

        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 0; registerPanel.add(idLabel, c);
        c.gridy = 1; registerPanel.add(passwordLabel, c);
        c.gridy = 2; registerPanel.add(nameLabel, c);
        c.gridy = 3; registerPanel.add(sexLabel, c);
        c.gridy = 4; registerPanel.add(birthLabel, c);
        c.gridy = 5; registerPanel.add(emailLabel, c);
        c.gridy = 6; registerPanel.add(phoneLabel, c);
        c.gridy = 7; registerPanel.add(passportNoLabel, c);

        String[] sexs = { "남성", "여성" };
        DefaultComboBoxModel<String> sexModel = new DefaultComboBoxModel<String>(sexs);
        sexCombo = new JComboBox<String>(sexModel);

        c.gridx = 1; c.anchor = GridBagConstraints.LINE_END;
        c.gridy = 0; registerPanel.add(idField, c);
        c.gridy = 1; registerPanel.add(passwordField, c);
        c.gridy = 2; registerPanel.add(nameField, c);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 3; registerPanel.add(sexCombo, c);
        c.anchor = GridBagConstraints.LINE_END;
        c.gridy = 4; registerPanel.add(birthField, c);
        c.gridy = 5; registerPanel.add(emailField, c);
        c.gridy = 6; registerPanel.add(phoneField, c);
        c.gridy = 7; registerPanel.add(passportNoField, c);

        c.gridx = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 8; registerPanel.add(submitButton, c);

        add(registerPanel, BorderLayout.CENTER);
    }

    public boolean validateField() {
        String msg = "";
        boolean result = true;
        String birthText = birthField.getText();

        if (idField.getText().length() < 2 || idField.getText().length() > 10) {
            msg = "2~10글자의 아이디만 사용가능 합니다."; result = false;
        }
        else if (passwordField.getText().length() < 2 || passwordField.getText().length() > 10) {
            msg = "2~10글자의 비밀번호만 사용가능 합니다."; result = false;
        }
        else if (nameField.getText().length() < 1 || nameField.getText().length() > 10) {
            msg = "1~10글자의 이름만 사용가능 합니다."; result = false;
        }
        else if (birthText.length() != 10 || !birthText.matches("\\d{4}-\\d{2}-\\d{2}")) {
            msg = "YYYY-MM-DD 형식의 생년월일을 입력하세요."; result = false;
        }
        else if (!emailField.getText().matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}")) {
            msg = "이메일을 잘못 입력했습니다"; result = false;
        }
        else if (!phoneField.getText().matches("\\d{11}")) {
            msg = "전화번호를 잘못 입력했습니다."; result = false;
        }
        else if (!passportNoField.getText().matches("\\w\\d{8}")) {
            msg = "여권번호를 잘못 입력했습니다.";  result = false;
        }

        if (!result) JOptionPane.showMessageDialog(this, msg);

        return result;
    }

    protected void setUser() {
        String[] csvList = {
            User.getNextUid(),
            idField.getText(),
            passwordField.getText(),
            nameField.getText(),
            String.valueOf(sexCombo.getSelectedIndex()),
            birthField.getText(),
            passportNoField.getText(),
            phoneField.getText(),
            emailField.getText(),
            "0"
        };
        formedUser = new Customer(csvList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals(getSubmitButtonText())) submit();
    }

    protected abstract void submit();

}
