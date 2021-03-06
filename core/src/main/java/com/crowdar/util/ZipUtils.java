package com.crowdar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static String packZipWithNameOfFolder(String folder, String extension) {
        String outZipPath = folder + "." + extension;
        try {
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outZipPath))) {
                File file = new File(folder);
                doZip(file, zos);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail of packaging of folder. ", e);
        }
        return outZipPath;
    }

    private static void doZip(File dir, ZipOutputStream out) throws IOException {
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                doZip(f, out);
            } else {
                out.putNextEntry(new ZipEntry(f.getName()));
                try (FileInputStream in = new FileInputStream(f)) {
                    write(in, out);
                }
            }
        }
    }

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
    }
}