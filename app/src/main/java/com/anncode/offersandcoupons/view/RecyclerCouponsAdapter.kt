package com.anncode.offersandcoupons.view

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anncode.offersandcoupons.model.Coupon
import com.anncode.offersandcoupons.R
import com.squareup.picasso.Picasso

class RecyclerCouponsAdapter(var coupons : ArrayList<Coupon>?, var resource: Int) : RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): CardCouponHolder {
        val cardCouponview: View = LayoutInflater.from(view.context).inflate(resource, view, false)
        return CardCouponHolder(cardCouponview)
    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(holder: CardCouponHolder, index: Int) {
        val coupon = coupons?.get(index)
        holder.setDataCard(coupon)
    }

    class CardCouponHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var currentCoupon: Coupon? = null
        private var imgCoupon: ImageView = v.findViewById(R.id.imgCoupon)
        private var tvTitle: TextView = v.findViewById(R.id.tvTitle)
        private var tvDescriptionShort: TextView = v.findViewById(R.id.tvDescriptionShort)
        private var tvCategory: TextView = v.findViewById(R.id.tvCategory)
        private var tvDate: TextView = v.findViewById(R.id.tvDate)

        init {
            v.setOnClickListener(this)
        }

        fun setDataCard(coupon: Coupon?){
            this.currentCoupon = coupon
            if (currentCoupon != null){
                if (currentCoupon?.image_url != "") {
                    Picasso.get().load(currentCoupon?.image_url).resize(520, 520).centerCrop().into(imgCoupon)
                }
                tvTitle.text = currentCoupon?.title
                tvDescriptionShort.text = currentCoupon?.descriptionShort
                tvCategory.text = currentCoupon?.category
                tvDate.text = currentCoupon?.endDate
            }
        }

        override fun onClick(v: View) {
            currentCoupon?.title?.let { Log.i("CLICK Coupon: ", it) }
            val context = v.context
            val showPhotoIntent = Intent(context, CouponDetailActivity::class.java)
            showPhotoIntent.putExtra("COUPON", currentCoupon)
            context.startActivity(showPhotoIntent)

        }

    }

}
