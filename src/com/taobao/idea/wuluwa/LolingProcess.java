package com.taobao.idea.wuluwa;

import javax.swing.*;

/**
 * Author: yunshu.xw
 */
public class LolingProcess extends JPanel {

    JProgressBar bar = new JProgressBar();

    public LolingProcess(){
    }

    public void prosecss(int value){
        if(value > bar.getMaximum()){
            value = bar.getMaximum();
        }
        bar.setValue(value);
    }

    public void showProcess(){
        setVisible(true);
    }

    public void end(){
        bar.setValue(bar.getMaximum());
    }

    public void hideProcess(){
        setVisible(false);
    }
}
