package com.raven.datechooser.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import com.formdev.flatlaf.util.UIScale;

public class HeaderLayout implements LayoutManager {
  private final static int GAP = 5;
  private final static int MONTH_WIDTH = 110;
  private final static int YEAR_WIDTH = 80;
  private final static int BUTTON_WIDTH = 30;

  @Override
  public void addLayoutComponent(String name, Component comp) {
    throw new UnsupportedOperationException("Unimplemented method 'addLayoutComponent'");
  }

  @Override
  public void removeLayoutComponent(Component comp) {
    throw new UnsupportedOperationException("Unimplemented method 'removeLayoutComponent'");
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    synchronized (parent.getTreeLock()) {
      Insets insets = parent.getInsets();

      int width = insets.left + insets.right + this.getTotalWidth(parent);
      int height = insets.top + insets.bottom + this.getmaxHeight(parent);

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
      int y = insets.top;
      int localGap = UIScale.scale(HeaderLayout.GAP);
      int height = parent.getHeight() - (insets.top + insets.bottom);

      Component[] components = parent.getComponents();
      for (int i = 0; i < components.length; i++) {
        Component component = components[i];

        int width = getWidth(i);
        component.setBounds(x, y, width, height);

        x += width + localGap;
      }
    }
  }

  private int getTotalWidth(Container parent) {
    return (2 * UIScale.scale(HeaderLayout.BUTTON_WIDTH)) +
        UIScale.scale(HeaderLayout.MONTH_WIDTH) + UIScale.scale(HeaderLayout.YEAR_WIDTH) +
        (3 * UIScale.scale(HeaderLayout.GAP));
  }
}
