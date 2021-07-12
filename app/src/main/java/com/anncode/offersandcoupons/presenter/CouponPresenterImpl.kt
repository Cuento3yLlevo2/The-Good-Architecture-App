package com.anncode.offersandcoupons.presenter

import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponView
import com.anncode.offersandcoupons.model.CouponsInteractor
import com.anncode.offersandcoupons.model.CouponsInteractorImpl

class CouponPresenterImpl(var couponView: CouponView) : CouponPresenter {

    private var couponInteractor: CouponsInteractor = CouponsInteractorImpl(this)

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        couponView.showCoupons(coupons)
    }

    override fun getCoupons() {
        couponInteractor.getCouponsAPI()
    }
}