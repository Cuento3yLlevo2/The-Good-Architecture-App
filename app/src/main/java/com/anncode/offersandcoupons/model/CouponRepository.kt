package com.anncode.offersandcoupons.model

import androidx.lifecycle.MutableLiveData

interface CouponRepository {
    fun callCouponsAPI()
    fun getCoupons(): MutableLiveData<List<Coupon>>
}