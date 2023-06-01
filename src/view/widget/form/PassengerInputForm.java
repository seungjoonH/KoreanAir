package view.widget.form;

import model.user.Customer;
import view.page.theme.ThemeMode;
import view.widget.CustomTextLabel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PassengerInputForm {
    private final int index;

    private final JTextField nameField = new JTextField(10);
    private final JComboBox<Customer.Sex> sexComboBox = new JComboBox<>(Customer.Sex.values());
    private final JTextField birthField = new JTextField(10);
    private final JTextField ppNoField = new JTextField(10);

    public JTextField getNameField() { return nameField; }
    public JComboBox<Customer.Sex> getSexComboBox() { return sexComboBox; }
    public JTextField getBirthField() { return birthField; }
    public JTextField getPpNoField() { return ppNoField; }

    public PassengerInputForm() { this.index = -1; }
    public PassengerInputForm(int index) { this.index = index; }

    public void setTexts(String name, Customer.Sex sex, LocalDate birth, String ppNo) {
        nameField.setText(name);
        sexComboBox.setSelectedItem(sex);
        birthField.setText(birth.toString());
        ppNoField.setText(ppNo);
    }

    public List<String> getTexts() {
        List<String> list = new ArrayList<>();

        list.add(nameField.getText());
        list.add(String.valueOf(sexComboBox.getSelectedIndex()));
        list.add(birthField.getText());
        list.add(ppNoField.getText());

        return list;
    }

    private JPanel buildUserInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        Color fontColor = ThemeMode.getFontColor();

        String title = "승객 정보" + (index < 0 ? " #" + (index + 1) : "");
        JLabel userLabel = new CustomTextLabel(title, 20, fontColor, Font.BOLD);
        JLabel nameLabel = new CustomTextLabel("이름 :", fontColor);
        JLabel sexLabel = new CustomTextLabel("성별 :", fontColor);
        JLabel birthLabel = new CustomTextLabel("생년월일(YYYY-MM-DD) :", fontColor);
        JLabel ppLabel = new CustomTextLabel("여권번호 :", fontColor);

        c.gridx = 0; c.gridy = 12;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(userLabel, c); c.gridy++;
        panel.add(nameLabel, c); c.gridy++;
        panel.add(sexLabel, c); c.gridy++;
        panel.add(birthLabel, c); c.gridy++;
        panel.add(ppLabel, c);

        c.gridx = 1; c.gridy = 13;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(nameField, c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_START;

        panel.add(sexComboBox, c); c.gridy++;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(birthField, c);
        c.gridy++; panel.add(ppNoField, c);

        return panel;
    }
}
