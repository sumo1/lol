package com.taobao.idea.wuluwa;

import com.google.common.collect.Lists;
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
import java.util.List;

/**
 * Author: yunshu.xw
 */
public class FileAnalysts {

    public String getCurrentWindowFilePath(Project project){
        FileEditorManager fem = FileEditorManager.getInstance(project);
        String result = ((PsiAwareFileEditorManagerImpl) fem).getCurrentFile().getPresentableUrl();
        return result;
    }

    public List<String> getAllOpenWebFilePath(Project project){
        List<String> webFilePath = Lists.newArrayList();
        FileEditorManager fem = FileEditorManager.getInstance(project);
        VirtualFile[] vfs = fem.getOpenFiles();
        for (VirtualFile virtualFile : vfs) {
            String filePath = virtualFile.getPresentableUrl();
            for (String suffix : Constants.WEB_FILE_SUFFIX) {
                if(filePath.endsWith(suffix) || filePath.endsWith(suffix.toUpperCase())){
                    webFilePath.add(filePath);
                }
            }
        }
        return webFilePath;
    }

    public String getDestFilePath(String filePath, String srcPath, String destPath){
        String checkResult = validatePath(filePath, srcPath, destPath);
        if(null != checkResult){
            return checkResult;
        }
        String prefix = filePath.substring(0, filePath.indexOf(srcPath));
        String suffix = filePath.substring(filePath.indexOf(srcPath) + srcPath.length(), filePath.length());
        if(!destPath.contains(Constants.DEFAULT_WILD_SIGN)){
            String destFilePath = prefix + destPath;
            File destfile = new File(destFilePath);
            if(null == destfile.listFiles()){
                return Constants.ERROR_SIGN + "不存在文件目录：" + destFilePath;
            }
            return destFilePath + suffix;
        }
        String destPrefix = destPath.substring(0, destPath.indexOf(Constants.DEFAULT_WILD_SIGN));
        String destSuffix = destPath.substring(destPath.indexOf(Constants.DEFAULT_WILD_SIGN) + 1, destPath.length());
        String fileName = prefix + destPrefix;
        File file = new File(fileName);
        if(null == file.listFiles()){
            return Constants.ERROR_SIGN + "不存在文件目录：" + fileName;
        }
        String prefix3 = "";
        for (File contenfile : file.listFiles()) {
            if(contenfile.isDirectory() && contenfile.getName().endsWith(destSuffix)){
                prefix3 = contenfile.getName();
                break;
            }
        }
        if("".equals(prefix3)){
            return Constants.ERROR_SIGN + "不存在通配目标文件夹：" + destPath.substring(destPath.indexOf(Constants.DEFAULT_WILD_SIGN), destPath.length());
        }
        return prefix + destPrefix + prefix3 + suffix;
    }

    private String validatePath(String filePath, String srcPath, String destPath) {
        if(StringUtils.isBlank(filePath)){
            return Constants.ERROR_SIGN + "当前打开的文件为空。";
        }
        if(!filePath.contains(srcPath)){
            return Constants.ERROR_SIGN + "当前文件路径:" + filePath +"\n不符合路径格式。不包含路径：" + srcPath;
        }
        if(null == destPath){
            return Constants.ERROR_SIGN + "目标文件路径不能为空。";
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
