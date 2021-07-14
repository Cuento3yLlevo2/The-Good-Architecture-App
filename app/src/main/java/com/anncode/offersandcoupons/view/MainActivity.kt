package com.anncode.offersandcoupons.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anncode.offersandcoupons.R
import com.anncode.offersandcoupons.databinding.ActivityMainBinding
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity() {

    private var couponViewModel : CouponViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setupBindings(savedInstanceState)



    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        couponViewModel = ViewModelProvider.NewInstanceFactory().create(CouponViewModel::class.java)
        activityMainBinding.model = couponViewModel
        setupListUpdate()
    }

    fun setupListUpdate() {
        // Call Coupons
        couponViewModel?.callCoupons()

        couponViewModel?.getCoupons()?.observe(this, Observer { coupons: List<Coupon> ->
            coupons[0].title?.let { Log.w("Coupon", it) }
            couponViewModel?.setCouponsInRecyclerAdapter(coupons)
        })
    }



}
