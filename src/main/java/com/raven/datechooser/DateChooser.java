package com.raven.datechooser;

import java.awt.Color;
import java.text.DateFormatSymbols;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.layout.DateChooserLayout;

public class DateChooser extends JPanel {
  private static final String FLATLAF_STYLE_VALUE = "background: if ($DateChooser.background, $DateChooser.background, $Panel.background);\nborder: 5, 5, 5, 5";

  private JPanel pnlHeader;
  private JPanel pnlTitle;
  private JPanel pnlDate;

  private JTextField textField;
  private JButton btnCurrentDate;

  private JSpinner spnrMonth;
  private JSpinner spnrYear;

  private Color theme;

  private LocalDate selectedDate;

  private String[] months;
  private String[] shortWeekdays;

  public DateChooser(JTextField textField) {
    this.textField = textField;

    DateFormatSymbols dfs = DateFormatSymbols.getInstance();
    this.months = dfs.getMonths();
    this.shortWeekdays = dfs.getShortWeekdays();

    Color focusColor = UIManager.getColor("Component.focusColor");
    this.theme = focusColor != null ? focusColor : new Color(67, 127, 251);

    this.initializeComponents();
  }

  public void setSelectedDate(LocalDate selectedDate) {
    this.selectedDate = selectedDate;
  }

  private void initializeComponents() {
    this.putClientProperty(FlatClientProperties.STYLE, DateChooser.FLATLAF_STYLE_VALUE);
    this.setLayout(new DateChooserLayout(pnlHeader, pnlTitle, pnlDate, btnCurrentDate));

    this.createHeader();
    this.createDate();

    this.createBtnCurrentDate();

    LocalDate now = LocalDate.now();
    spnrMonth.setValue(this.months[now.getMonthValue() - 1]);
    this.initializeEvents();
    spnrYear.setValue(now.getYear());
    this.setSelectedDate(now);
  }

  private void initializeEvents() {
    spnrMonth.addChangeListener(e -> showDate());
    spnrYear.addChangeListener(e -> showDate());
  }
}
