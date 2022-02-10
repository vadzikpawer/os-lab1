package com.company.task1;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileSystem {
    private final File[] paths;
    private final FileSystemView fsv;

    public FileSystem(){
        fsv = FileSystemView.getFileSystemView();
        paths = File.listRoots();
    }

    public void GetAllInformation(){
        for(File path:paths)
        {
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
            System.out.println("Size: " + path.getTotalSpace()/(1024*1024*1024) + " Gb");
            System.out.println("Type: " + fsv.getSystemTypeDescription(path));
        }
    }
}
