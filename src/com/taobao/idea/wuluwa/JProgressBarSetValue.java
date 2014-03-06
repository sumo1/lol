package com.taobao.idea.wuluwa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: yunshu.xw
 */
public class JProgressBarSetValue extends JFrame {
    JProgressBar bar = new JProgressBar();
    JButton step = new JButton("Step");

    public JProgressBarSetValue() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        step.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int value = bar.getValue() + 7;
                if (value > bar.getMaximum()) {
                    value = bar.getMaximum();
                }
                bar.setValue(value);
            }
        });

        getContentPane().add(bar, BorderLayout.NORTH);
        getContentPane().add(step, BorderLayout.EAST);
        pack();
        setVisible(true);
    }

    public static void main(String arg[]) {
        new JProgressBarSetValue();
    }
}
