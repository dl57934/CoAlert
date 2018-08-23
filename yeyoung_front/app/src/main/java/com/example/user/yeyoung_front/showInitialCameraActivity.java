package com.example.user.yeyoung_front;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.googlecode.tesseract.android.TessBaseAPI;

import junit.framework.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class showInitialCameraActivity extends AppCompatActivity {
    String datapath = "";
    Bitmap image;
    TessBaseAPI mTess;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_show_initial_camera);
        Intent intent = getIntent();

        Uri uri = intent.getParcelableExtra("image");
        try {
            image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            image = Bitmap.createScaledBitmap(image, image.getWidth()/2, image.getHeight()/2, true);
            image = rotateBitmap(image);
            datapath = getFilesDir() + "/tesseract/";
            String langs[] = new String[2];
            langs[0] = "kor";
            langs[1] = "eng";
            for (int i =0; i <langs.length;i++){
                checkFile(new File(datapath+"tessdata/"), langs[i]);
            }
            mTess = new TessBaseAPI();
            mTess.init(datapath, "eng+kor");
            mTess.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "†é° 、」「!@#$%^&*()_=-[]}{;:'\"\\|~`,./<>?ﬂ");
            mTess.setImage(image);
            String test = mTess.getUTF8Text();
            Log.e("tesseractWords: ", test);
            mTess.end();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Button back=(Button)findViewById(R.id.camera_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),
                        searchPageCameraActivity.class);
                startActivity(intent);
            }
        });
    }
    Bitmap rotateBitmap(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        Matrix matrix = new Matrix();
        matrix.postRotate(90F);
        Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true);
        bmp.recycle();

        return resizedBitmap;
    }

    private void copyFiles(String lang) {
        try{
            String filepath = datapath + "/tessdata/"+lang+".traineddata";
            AssetManager assetManager = getAssets();
            InputStream instream = assetManager.open("tessdata/"+lang+".traineddata");
            OutputStream outstream = new FileOutputStream(filepath);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFile(File dir, String lang) {
        //디렉토리가 없으면 디렉토리를 만들고 그후에 파일을 카피
        if(!dir.exists()&& dir.mkdirs()) {
            copyFiles(lang);
        }
        //디렉토리가 있지만 파일이 없으면 파일카피 진행
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);
            if(!datafile.exists()) {
                copyFiles(lang);
            }
        }
    }

}
