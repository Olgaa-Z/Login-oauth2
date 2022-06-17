package com.zfuncode.login_oauth2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zfuncode.login_oauth2.adapter.ProductAdapter
import com.zfuncode.login_oauth2.data.ApiClient
import com.zfuncode.login_oauth2.data.ApiClientDua
import com.zfuncode.login_oauth2.data.response.ProductResponseItem
import com.zfuncode.login_oauth2.utils.SessionManager
import kotlinx.android.synthetic.main.activity_product_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductList : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClientDua

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        apiClient = ApiClientDua()
        sessionManager = SessionManager(this)
        val token = sessionManager.fetchAccessToken()

//        ApiClient.instance.allProduct(token = "Bearer ${sessionManager.fetchAccessToken()}")
//            .enqueue(object : retrofit2.Callback<ProductResponseItem>{
//                override fun onResponse(
//                    call: Call<ProductResponseItem>,
//                    response: Response<ProductResponseItem>
//                ) {
//                    if(response.isSuccessful){
//                        val dataProduct = response.body()
//                        val adapterProduct = ProductAdapter()
//                        val lm = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                        rvProduct.layoutManager = lm
//                        rvProduct.adapter= adapterProduct
//
//                    }else{
//                        Toast.makeText(applicationContext, "Gagal Load Data", Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<ProductResponseItem>, t: Throwable) {
//                    Toast.makeText(applicationContext, "Gagal Koneksi ke Server", Toast.LENGTH_LONG).show()
//
//                }
//
//            })

        apiClient.getApiService(this).allProduct()
            .enqueue(object :Callback<ProductResponseItem>{
                override fun onResponse(
                    call: Call<ProductResponseItem>,
                    response: Response<ProductResponseItem>
                ) {
                    if(response.isSuccessful){
                        val dataProduct = response.body()
                        tvProduct.text = dataProduct.toString()
//                        val adapterProduct = ProductAdapter()
//                        val lm = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
//                        rvProduct.layoutManager = lm
//                        rvProduct.adapter= adapterProduct

                    }else{
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<ProductResponseItem>, t: Throwable) {
                    Toast.makeText(applicationContext, "Gagal Koneksi ke Server", Toast.LENGTH_LONG).show()

                }

            })
    }
}