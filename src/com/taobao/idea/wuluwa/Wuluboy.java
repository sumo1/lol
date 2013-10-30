package com.taobao.idea.wuluwa;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.JDOMExternalizerUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Author: yunshu.xw
 */
public class Wuluboy extends JDOMExternalizerUtil implements ApplicationComponent, Configurable {
    private WuluForm wuluForm;
    private String srcPath = Constants.DEFAULT_SRC_PATH;
    private String frame;
    private String destPath = Constants.DEFAULT_DEST_PATH;

    public Wuluboy() {
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "Wuluboy";
    }

    public void lol(String filePath){
        FileAnalysts fileAnalysts = new FileAnalysts();
        String result = fileAnalysts.getDestFilePath(filePath, srcPath, destPath);
        if(result.startsWith(Constants.ERROR_SIGN)){
            showMessage(result);
            return;
        }
        if(!fileAnalysts.copy(filePath, result)){
            showMessage("文件拷贝失败。");
            return;
        }
    }

    private void showMessage(String errorMsg) {
        Messages.showMessageDialog(errorMsg, "出错啦", Messages.getInformationIcon());
    }

    @Override
    public JComponent createComponent() {
        if(wuluForm == null){
            wuluForm = new WuluForm();
        }
        return wuluForm.$$$getRootComponent$$$();
    }

    @Override
    public boolean isModified() {
        return wuluForm != null && wuluForm.isModified(this);
    }

    @Override
    public void apply() throws ConfigurationException {
        if(null == wuluForm){
            return;
        }
        wuluForm.getData(this);
    }

    @Override
    public void reset() {
        if(wuluForm != null){
            wuluForm.setData(this);
        }
    }

    @Override
    public void disposeUIResources() {
        wuluForm = null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "wuluboy";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(final String frame) {
        this.frame = frame;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }
}
