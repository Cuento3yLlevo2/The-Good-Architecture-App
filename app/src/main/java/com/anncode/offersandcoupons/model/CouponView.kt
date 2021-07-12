package com.anncode.offersandcoupons.model

interface CouponView {
    fun getCoupons()
    fun showCoupons(coupons: ArrayList<Coupon>?)
}