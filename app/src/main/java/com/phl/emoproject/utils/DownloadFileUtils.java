package com.phl.emoproject.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;

import com.phl.emoproject.core.Constans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadFileUtils {

    public static String storeFile(File response, String fileName) {
        try {
            String path;
            InputStream in= new FileInputStream(response);

            File dir = new File(Constans.FILE_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }

            path = dir + "/" + fileName;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }

            OutputStream out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int read;
            while((read = in.read(buffer)) != -1){
                out.write(buffer, 0, read);
            }

            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void openFile(Context context, String name) {
        String path = Constans.FILE_PATH + "/" + name;
        File file = new File(path);
        Uri uri_path = Uri.fromFile(file);
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension
                (MimeTypeMap.getFileExtensionFromUrl(path));

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setType(mimeType);
        intent.setDataAndType(uri_path, mimeType);
        context.startActivity(intent);
    }
}
