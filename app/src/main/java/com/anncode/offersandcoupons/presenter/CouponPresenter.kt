package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon

interface CouponPresenter {
    // view
    fun showCoupons(coupons: ArrayList<Coupon>?)

    // interactor
    fun getCoupons()
}