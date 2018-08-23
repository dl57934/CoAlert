package com.example.user.yeyoung_front

import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

import com.googlecode.tesseract.android.TessBaseAPI

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.ArrayList

class cpicture_list : AppCompatActivity() {
    internal var datapath = ""
    internal lateinit var image: Bitmap
    internal lateinit var mTess: TessBaseAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cpicture_list)

        val recyclerView = findViewById<View>(R.id.recyclerview) as RecyclerView
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        val items:ArrayList<Recycler_item?> = ArrayList<Recycler_item?>()
        val item:Array<Recycler_item?> = arrayOfNulls<Recycler_item>(5)
        item[0] = Recycler_item(R.drawable.sun1, "듬뿍듬뿍 썬크림", "80%")
        item[1] = Recycler_item(R.drawable.suncream, "쫀득쫀득 썬크림", "90%")
        item[2] = Recycler_item(R.drawable.suncream2, "말랑말랑 썬크림", "50%")
        item[3] = Recycler_item(R.drawable.suncream3, "듬뿍듬뿍 썬크림", "80%")
        item[4] = Recycler_item(R.drawable.suncream4, "듬뿍듬뿍 썬크림", "80%")

        for (i in 0..4) {
            items.add(item[i])
        }
        recyclerView.adapter = RecyclerAdapter(applicationContext, items, R.layout.activity_cpicture_list)
        val intent = intent

        val uri = intent.getParcelableExtra<Uri>("image")
        try {
            image = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            image = Bitmap.createScaledBitmap(image, image.width / 2, image.height / 2, true)
            image = rotateBitmap(image)
            datapath = filesDir.toString() + "/tesseract/"
            var langs = arrayOf("eng", "kor")
            for (lang in langs) {
                Log.e("Lang: ", lang)
                checkFile(File(datapath + "tessdata/"), lang)
            }

            mTess = TessBaseAPI()

            mTess.init(datapath, "eng+kor")
            mTess.setVariable(TessBaseAPI.VAR_CHAR_BLACKLIST, "†é° 、」「!@#$%^&*()_=-[]}{;:'\"\\|~`,./<>?ﬂ")
            mTess.setImage(image)
            val test = mTess.utF8Text
            Log.e("tesseractWords: ", test)
            mTess.end()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun rotateBitmap(bmp:Bitmap): Bitmap {
        var width = bmp.width
        var height = bmp.height

        var matrix = Matrix()
        matrix.postRotate(90F)
        var resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true)
        bmp.recycle()

        return resizedBitmap
    }

    fun copyFiles(lang:String) {
        try {
            //location we want the file to be at
            var filepath = "$datapath/tessdata/$lang.traineddata"

            //get access to AssetManager
            var assetManager = assets

            //open byte streams for reading/writing
            var instream = assetManager.open("tessdata/$lang.traineddata")
            var  outstream = FileOutputStream(filepath)

            //copy the file to the location specified by filepath
            var buffer = ByteArray(1024)
            var read:Int
            while (true) {
                read = instream.read(buffer)
                if (read != -1) {
                    outstream.write(buffer, 0, read);
                    Log.e(lang, ": //copy the file to the location specified by filepath")
                }else{
                    break
                }
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (e:FileNotFoundException) {
            e.printStackTrace();
        } catch (e:IOException) {
            e.printStackTrace();
        }
    }

    fun checkFile(dir: File, lang:String) {
        //directory does not exist, but we can successfully create it
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles(lang)
        }
        //The directory exists, but there is no data file in it
        if(dir.exists()) {
            var datafilepath = "$datapath/tessdata/$lang.traineddata";
            var datafile =  File(datafilepath);
            if (!datafile.exists()) {
                copyFiles(lang);
            }
        }
    }
}
