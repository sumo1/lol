package com.taobao.idea.wuluwa;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.refactoring.ui.InfoDialog;

import java.util.List;

/**
 * Author: yunshu.xw
 */
public class WuluBrother extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        FileAnalysts fp = new FileAnalysts();
        List<String> filePaths = fp.getAllOpenWebFilePath(e.getProject());
        if(filePaths.isEmpty()){
            Messages.showErrorDialog("互撸失败，无打开的Web文件", "出错啦");
            return;
        }
        Application application = ApplicationManager.getApplication();
        Wuluboy wuluboy = application.getComponent(Wuluboy.class);
        boolean lolResult = true;
        for (String filePath : filePaths) {
            if(!wuluboy.lol(filePath)){
                lolResult = false;
                break;
            }
        }
//        if(lolResult){
//            Messages.showInfoMessage("批量互撸完成", "互撸娃告诉你");
//        }
    }
}
