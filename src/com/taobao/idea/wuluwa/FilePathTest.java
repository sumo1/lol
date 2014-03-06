package com.taobao.idea.wuluwa;

import java.io.File;

/**
 * Author: yunshu.xw
 */
public class FilePathTest {
    public static void main(String[] args) {
        testGetDestPath();
        testAllOpenFilePath();
        File file = new File("D:\\workplace\\BR_zs_crm_42_daily_yunshu_20131023_merge\\zuanshi-crm-web\\src\\main\\webapp\\adboard\\t\\aa");
        System.out.println("".contains("a"));
    }

    private static void testGetDestPath(){
        String srcPath = "\\src\\main\\webapp";
        String destPath = "\\target\\cater";
        FileAnalysts fileAnalysts = new FileAnalysts();
        String filePath = "D:\\workplace\\BR_zs_crm_42_daily_yunshu_20131023_merge\\zuanshi-crm-web\\src\\main\\webapp\\templates\\screen\\Project.vm";
        System.out.println(fileAnalysts.getDestFilePath(filePath, srcPath, destPath));
        filePath = "D:\\workplace\\BR_zs_crm_42_daily_yunshu_20131023_merge\\zuanshi-crm-web\\src\\main\\webapp\\adboard";
        destPath = "\\target\\cater";
        System.out.println(fileAnalysts.getDestFilePath(filePath, srcPath, destPath));
    }

    private static void testAllOpenFilePath(){
        String filePath = "D:\\workplace\\BR_zs_crm_42_daily_yunshu_20131023_merge\\zuanshi-crm-web\\src\\main\\webapp\\adboard";
        getOpenFilePath(filePath);
        filePath = "D:\\workplace\\BR_zs_crm_42_daily_yunshu_20131023_merge\\zuanshi-crm-web\\src\\main\\webapp\\adboard\\adbaordReview.vm";
        getOpenFilePath(filePath);
    }

    private static void getOpenFilePath(String path){
        for (String suffix : Constants.WEB_FILE_SUFFIX) {
            if(path.endsWith(suffix) || path.endsWith(suffix.toUpperCase())){
                System.out.println(path);
            }
        }
    }
}
