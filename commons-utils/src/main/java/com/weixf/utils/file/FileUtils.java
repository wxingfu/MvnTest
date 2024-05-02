package com.weixf.utils.file;

import java.io.File;

public class FileUtils {


    private FileUtils() {
    }

    public static void searchFile(String dir, String fileName) {
        searchFile(new File(dir), fileName);
    }


    public static void searchFile(File dir, String fileName) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().equals(fileName)) {
                    System.out.println(file.getAbsolutePath());
                }
            } else {
                searchFile(file, fileName);
            }
        }

    }

}
