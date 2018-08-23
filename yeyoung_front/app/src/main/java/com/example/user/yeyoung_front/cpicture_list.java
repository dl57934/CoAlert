package com.example.user.yeyoung_front;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class cpicture_list extends AppCompatActivity {
    String datapath = "";
    Bitmap image;
    TessBaseAPI mTess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpicture_list);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item> items=new ArrayList<>();
        Recycler_item[] item=new Recycler_item[5];
        item[0]=new Recycler_item(R.drawable.sun1,"듬뿍듬뿍 썬크림", "80%");
        item[1]=new Recycler_item(R.drawable.suncream,"쫀득쫀득 썬크림","90%");
        item[2]=new Recycler_item(R.drawable.suncream2,"말랑말랑 썬크림","50%");
        item[3]=new Recycler_item(R.drawable.suncream3,"듬뿍듬뿍 썬크림","80%");
        item[4]=new Recycler_item(R.drawable.suncream4,"듬뿍듬뿍 썬크림","80%");

        for(int i=0;i<5;i++) items.add(item[i]);

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),items,R.layout.activity_cpicture_list));

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
