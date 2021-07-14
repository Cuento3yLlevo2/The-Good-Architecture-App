package com.anncode.offersandcoupons.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl: CouponRepository {

    private var coupons = MutableLiveData<List<Coupon>>()
    // Subject MutableListData
    // Observes List Coupons

    // API connection logic
    override fun callCouponsAPI() {

        // Controller
        var couponList: ArrayList<Coupon>? = ArrayList()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.message?.let { Log.e("ERROR: ", it) }
                t.stackTrace
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                Log.d("Coupons", offersJsonArray?.get(0).toString())
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    couponList?.add(coupon)
                }

                coupons.value = couponList




            }


        })
    }

    override fun getCoupons(): MutableLiveData<List<Coupon>> {
        return coupons
    }

}