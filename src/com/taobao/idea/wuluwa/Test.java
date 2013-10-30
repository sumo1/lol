package com.taobao.idea.wuluwa;

/**
 * Author: yunshu.xw
 */
public class Test {
    public static void main(String[] args) {
        String filePath = "D:\\workplace\\BR_zs_crm_42_daily_yunshu_20131023_merge\\zuanshi-crm-web\\src\\main\\webapp\\WEB-INF\\jsp\\adboard\\adboardAudit.jsp";
        FileAnalysts fileAnalysts = new FileAnalysts();
        System.out.println(fileAnalysts.getDestFilePath(filePath, Constants.DEFAULT_SRC_PATH, Constants.DEFAULT_DEST_PATH));
        String srcPath = "D:\\1.txt";
        String destPath = "D:\\output\\1.txt";
        if(fileAnalysts.copy(srcPath, destPath)){
            System.out.println(true);
        }
    }
}
