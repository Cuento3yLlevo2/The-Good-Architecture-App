package com.anncode.offersandcoupons.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.model.CouponView
import com.anncode.offersandcoupons.presenter.CouponPresenter
import com.anncode.offersandcoupons.presenter.CouponPresenterImpl

class MainActivity : AppCompatActivity(), CouponView {

    private var couponPresenter: CouponPresenter? = null
    private var rvCoupons: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        couponPresenter = CouponPresenterImpl(this)

        // R. View
        rvCoupons = findViewById(R.id.rvCoupons)
        rvCoupons?.layoutManager = LinearLayoutManager(this)

        getCoupons()
    }

    override fun getCoupons() {
        couponPresenter?.getCoupons()
    }

    override fun showCoupons(coupons: ArrayList<Coupon>?) {
        try {
            rvCoupons!!.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
        } catch (e: Exception){
            e.printStackTrace()
        }

    }
}
