package com.example.user.yeyoung_front

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import java.io.File

class initialCameraActivity : AppCompatActivity() {
    var cameraRequest = 1
    lateinit var allUri: Uri
    override fun onCreate(saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContentView(R.layout.activity_initial_camera)
    }
    fun cameraView(v: View){
        var uri = FileProvider.getUriForFile(this, "com.bignerdranch.android.test.fileprovider", File(Environment.getExternalStorageDirectory(), "tmp_contact_" + System.currentTimeMillis().toString() + ".jpg"))
        allUri = uri
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, cameraRequest)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequest && resultCode == Activity.RESULT_OK){
            var intent = Intent(this, showInitialCameraActivity::class.java)
            intent.putExtra("image", allUri)
            startActivity(intent)
        }
    }

}
