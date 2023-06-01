package view.page;

import model.user.Customer;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class CustomerInfoPage extends Page {
    protected JTextField idField;
    protected JTextField passwordField;
    protected JTextField nameField;
    protected JComboBox<Customer.Sex> sexCombo;
    protected JTextField birthField;
    protected JTextField emailField;
    protected JTextField phoneField;
    protected JTextField passportNoField;

    protected JButton submitButton;

    protected static Customer formedUser;


    protected CustomerInfoPage() {
        super(null, null, true);
    }
    protected CustomerInfoPage(JComponent left) {
        super(left, null, true);
    }
    protected CustomerInfoPage(JComponent left, JComponent right) {
        super(left, right, true);
    }
    protected CustomerInfoPage(JComponent left, JComponent right, boolean displayTitle) {
        super(left, right, displayTitle);
    }

    @Override
    protected void setInit() {
        super.setInit();
        idField = new JTextField(20);
        passwordField = new JPasswordField(20);
        nameField = new JTextField(20);
        sexCombo = new JComboBox<>(Customer.Sex.values());
        birthField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        passportNoField = new JTextField(20);
    }

    protected abstract String getSubmitButtonText();

    protected JLabel buildMileageTextLabel() { return null; }
    protected JTextField buildMileageField() { return null; }

    @Override
    protected final void buildContent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ThemeMode.getBackgroundColor());
        Color fontColor = ThemeMode.getFontColor();

        JLabel idLabel = new CustomTextLabel("아이디 :", fontColor);
        JLabel passwordLabel = new CustomTextLabel("비밀번호 :", fontColor);
        JLabel nameLabel = new CustomTextLabel("이름 :", fontColor);
        JLabel sexLabel = new CustomTextLabel("성별 :", fontColor);
        JLabel birthLabel = new CustomTextLabel("생년월일(YYYY-MM-DD) :", fontColor);
        JLabel emailLabel = new CustomTextLabel("이메일 :", fontColor);
        JLabel phoneLabel = new CustomTextLabel("휴대폰(- 생략) :", fontColor);
        JLabel passportNoLabel = new CustomTextLabel("여권번호(영문 1자리 + 숫자 8자리) :", fontColor);

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
        if (buildMileageTextLabel() != null) {
            c.gridy = 8; registerPanel.add(buildMileageTextLabel(), c);
        }


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
        if (buildMileageField() != null) {
            c.gridy = 8; registerPanel.add(buildMileageField(), c);
        }

        c.gridx = 0; c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy++; registerPanel.add(submitButton, c);

        registerPanel.setOpaque(false);
        panel.add(registerPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
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

    protected String[] getCSV() {
        return new String[] {
            "",
            idField.getText(),
            passwordField.getText(),
            nameField.getText(),
            String.valueOf(sexCombo.getSelectedIndex()),
            birthField.getText(),
            passportNoField.getText(),
            phoneField.getText(),
            emailField.getText(),
            ""
        };
    }

    protected abstract void setUser();

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals(getSubmitButtonText())) submit();
    }

    protected abstract void submit();

}
