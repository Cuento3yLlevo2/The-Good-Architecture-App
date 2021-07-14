package com.anncode.offersandcoupons.view

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.viewmodel.CouponViewModel


class RecyclerCouponsAdapter(var couponViewModel: CouponViewModel, var resource: Int) : RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    var coupons : List<Coupon>? = null

    fun setCouponList(coupons : List<Coupon>?){
        this.coupons = coupons
    }

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): CardCouponHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(view.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, view, false)
        return CardCouponHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int {
        return resource
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(holder: CardCouponHolder, index: Int) {
        holder.setDataCard(couponViewModel, index)
    }



    class CardCouponHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(couponViewModel: CouponViewModel, position: Int ){
            binding?.setVariable(com.anncode.offersandcoupons.BR.model, couponViewModel)
            binding?.setVariable(com.anncode.offersandcoupons.BR.position, position)
            binding?.executePendingBindings()
        }


    }

}
