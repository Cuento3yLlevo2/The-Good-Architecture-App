package com.anncode.offersandcoupons.model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.presenter.CouponPresenter
import com.anncode.offersandcoupons.view.RecyclerCouponsAdapter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl(var couponPresenter: CouponPresenter): CouponRepository {

    // API connection logic
    override fun getCouponsAPI() {

        // Controller
        var coupons: ArrayList<Coupon>? = ArrayList()
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
                    coupons?.add(coupon)
                }

                couponPresenter.showCoupons(coupons)


            }


        })
    }

}