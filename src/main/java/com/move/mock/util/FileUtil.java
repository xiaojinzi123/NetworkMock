package com.move.mock.util;

import java.io.*;

public class FileUtil {

    public static synchronized void saveToFile(File file, String data) throws IOException {

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

    public static synchronized String getFromFile(File file) throws IOException {

        FileInputStream inputStream = null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream = new FileInputStream(file);

            int len = -1;
            byte[] bf = new byte[1024];

            while ((len = inputStream.read(bf)) != -1) {
                byteArrayOutputStream.write(bf,0,len);
            }
            inputStream.close();

            return new String(byteArrayOutputStream.toByteArray(), Constant.CHARSET);

        }catch (IOException e) {
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignore) {
                }
                inputStream = null;
            }
        }

    }



}
