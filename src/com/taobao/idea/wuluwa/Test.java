package com.taobao.idea.wuluwa;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: yunshu.xw
 */
public class Test {
    public static void main(String[] args) {
        String filePath = "\\a\\*.jar\\b";
        String pattern = "^(.+[^\\*])\\*(.+[^\\\\])(.*)";
        Pattern patt = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher mather = patt.matcher(filePath);
        if(mather.matches()){
            System.out.println(mather.group(2));
        }
        FileAnalysts fileAnalysts = new FileAnalysts();
        filePath = "D:\\workplace\\BR_zs_crm_42_daily_yunshu_20131023_merge\\zuanshi-crm-web\\src\\main\\webapp\\audit\\list.html";
        System.out.println(fileAnalysts.getDestFilePath(filePath, Constants.DEFAULT_SRC_PATH, Constants.DEFAULT_DEST_PATH));
        String srcPath = "D:\\1.txt";
        String destPath = "D:\\output\\11.txt";
        if(fileAnalysts.copy(srcPath, destPath)){
            System.out.println(true);
        }
        //new JProgressBarSetValue();

    }


}
