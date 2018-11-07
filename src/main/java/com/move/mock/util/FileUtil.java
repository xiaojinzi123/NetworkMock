package com.move.mock.util;

import java.io.*;

public class FileUtil {

    public static void saveToFile(File file, String data) throws IOException {

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data.getBytes(Constant.CHARSET));
            outputStream.flush();
            outputStream.close();
        }catch (IOException e) {
            throw e;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignore) {
                }
                outputStream = null;
            }
        }

    }

}
