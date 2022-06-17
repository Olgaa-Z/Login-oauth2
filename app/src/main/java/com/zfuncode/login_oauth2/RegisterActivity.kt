package com.zfuncode.login_oauth2

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.zfuncode.login_oauth2.data.ApiClientDua
import com.zfuncode.login_oauth2.data.request.SignupRequest
import com.zfuncode.login_oauth2.data.response.RegisterResponse
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private val GALLERY = 1
    private lateinit var apiClient: ApiClientDua
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        apiClient = ApiClientDua()

        btnRegister.setOnClickListener{
            val email_ = etEmailRegist.text.toString()
            val fullname_ = etFullName.text.toString()
            val pass_ = etPasswordRegist.text.toString()
            val phone_ = etPhoneNumber.text.toString().toInt()
            val address_ = etAddress.text.toString()
            val img = "https://firebasestorage.googleapis.com/v0/b/market-final-project.appspot.com/o/products%2FPR-1655397699160-avatar1.jpg?alt=media"
            doRegister(fullname_,email_,pass_,phone_,address_,img)
        }

        btn_image.setOnClickListener{
            choosePhotoFromGallary()
        }
    }

    fun doRegister(fullname : String, email: String, password: String, phone: Int, address: String, image : String){
        apiClient.getApiService(this).register(SignupRequest(fullname,email, password,phone,address,image))
            .enqueue(object : retrofit2.Callback<RegisterResponse>{
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@RegisterActivity, response.message(), Toast.LENGTH_SHORT).show()
                        resultregist.text = response.body().toString()
                    }else{
                        Toast.makeText(this@RegisterActivity, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_SHORT).show()

                }

            })
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    Toast.makeText(this@RegisterActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                    iv!!.setImageBitmap(bitmap)

//                    uploadImage(path)

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@RegisterActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/demonuts_upload"
    }

}