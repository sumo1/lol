package com.taobao.idea.wuluwa;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl;
import com.intellij.openapi.fileEditor.impl.PsiAwareFileEditorManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.docking.impl.DockManagerImpl;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Author: yunshu.xw
 */
public class FileAnalysts {

    public String getWindowFilePath(Project project){
        FileEditorManager fem = FileEditorManager.getInstance(project);
        String result = ((PsiAwareFileEditorManagerImpl) fem).getCurrentFile().getPresentableUrl();
        return result;
    }

    public String getDestFilePath(String filePath, String srcPath, String destPath){
        String checkResult = validatePath(filePath, srcPath, destPath);
        if(null != checkResult){
            return checkResult;
        }
        String prefix = filePath.substring(0, filePath.indexOf(srcPath));
        String suffix = filePath.substring(filePath.indexOf(srcPath) + srcPath.length(), filePath.length());
        String prefix2 = destPath.substring(0, destPath.indexOf(Constants.DEFAULT_WAR_PATH));
        String fileName = prefix + prefix2;
        File file = new File(fileName);
        if(null == file.listFiles()){
            return Constants.ERROR_SIGN + "不存在文件：" + fileName;
        }
        String prefix3 = "";
        for (File contenfile : file.listFiles()) {
            if(contenfile.isDirectory() && contenfile.getName().endsWith(Constants.WAR_SUFFIX)){
                prefix3 = contenfile.getName();
                break;
            }
        }
        return prefix + prefix2 + prefix3 + suffix;
    }

    private String validatePath(String filePath, String srcPath, String destPath) {
        if(StringUtils.isBlank(filePath)){
            return Constants.ERROR_SIGN + "当前打开的文件为空。";
        }
        if(!filePath.contains(srcPath)){
            return Constants.ERROR_SIGN + "当前文件不符合路径格式。不包含路径：" + srcPath;
        }
        return null;
    }

    public boolean copy(String fileFrom, String fileTo) {
        try {
            FileInputStream in = new FileInputStream(fileFrom);
            FileOutputStream out = new FileOutputStream(fileTo);
            byte[] bt = new byte[1024];
            int count;
            while ((count = in.read(bt)) > 0) {
                out.write(bt, 0, count);
            }
            in.close();
            out.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
