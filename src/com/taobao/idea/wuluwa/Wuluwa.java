package com.taobao.idea.wuluwa;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;

/**
 * Author: yunshu.xw
 */
public class Wuluwa extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        FileAnalysts fp = new FileAnalysts();
        String filePath = fp.getCurrentWindowFilePath(e.getProject());
        Application application = ApplicationManager.getApplication();
        Wuluboy wuluboy = application.getComponent(Wuluboy.class);
        wuluboy.lol(filePath);
    }
}
