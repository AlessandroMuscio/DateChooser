package com.raven.datechooser.layout;

import com.formdev.flatlaf.util.UIScale;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DateChooserLayout implements LayoutManager {
  private final JPanel pnlHeader;
  private final JPanel pnlTitle;
  private final JPanel pnlDate;
  private final JButton lblCurrentDate;

  public DateChooserLayout(JPanel pnlHeader, JPanel pnlTitle, JPanel pnlDate, JButton lblCurrentDate) {
    this.pnlHeader = pnlHeader;
    this.pnlTitle = pnlTitle;
    this.pnlDate = pnlDate;
    this.lblCurrentDate = lblCurrentDate;
  }

  @Override
  public void addLayoutComponent(String name, Component comp) {
  }

  @Override
  public void removeLayoutComponent(Component comp) {
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    synchronized (parent.getTreeLock()) {
      Insets insets = parent.getInsets();

      int width = insets.left + insets.right;
      width += pnlHeader.getPreferredSize().width;

      int height = insets.top + insets.bottom;
      height += pnlHeader.getPreferredSize().height + UIScale.scale(2) +
          pnlTitle.getPreferredSize().height + pnlDate.getPreferredSize().height;
      height += lblCurrentDate.isVisible() ? lblCurrentDate.getPreferredSize().height : 0;

      return new Dimension(width, height);
    }
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    synchronized (parent.getTreeLock()) {
      return new Dimension(0, 0);
    }
  }

  @Override
  public void layoutContainer(Container parent) {
    synchronized (parent.getTreeLock()) {
      Insets insets = parent.getInsets();
      int x = insets.left;
      int y = insets.right;
      int width = parent.getWidth() - (x + y);
      int pnlHeaderPreferredHeight = pnlHeader.getPreferredSize().height;

      pnlHeader.setBounds(x, y, width, pnlHeaderPreferredHeight);

      y += pnlHeaderPreferredHeight + UIScale.scale(1);
      pnlTitle.setBounds(x, y, width, pnlHeaderPreferredHeight);

      y += pnlHeaderPreferredHeight + UIScale.scale(1);
      pnlDate.setBounds(x, y, width, pnlHeaderPreferredHeight);

      if (lblCurrentDate.isVisible()) {
        int lblCurrentDateWidth = lblCurrentDate.getPreferredSize().width;
        int lx = (width - lblCurrentDateWidth) / 2;

        y += pnlDate.getPreferredSize().height + UIScale.scale(3);

        lblCurrentDate.setBounds(lx, y, lblCurrentDateWidth, lblCurrentDate.getPreferredSize().height);
      }
    }
  }

}
