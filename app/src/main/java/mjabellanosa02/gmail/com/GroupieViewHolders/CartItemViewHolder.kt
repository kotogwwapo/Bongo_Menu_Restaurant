package mjabellanosa02.gmail.com.GroupieViewHolders

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_cart_detail_item.view.*
import mjabellanosa02.gmail.com.Model.Cart
import mjabellanosa02.gmail.com.R

class CartItemViewHolder(val cartItem: Cart, val context: Context): Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.row_cart_detail_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var currentUser = FirebaseAuth.getInstance().currentUser!!
        var textView_cartdetailItemName = viewHolder.itemView.textView_cartdetailItemName
        var textView_cartdetailItemAdditional = viewHolder.itemView.textView_cartdetailItemAdditional
        var textView_cartDetailItemRemove = viewHolder.itemView.textView_cartDetailItemRemove
        var textView_cartdetailItemPrice = viewHolder.itemView.textView_cartdetailItemPrice
        var button_cartdetailItemNumberDecrease = viewHolder.itemView.button_cartdetailItemNumberDecrease
        var button_cartdetailItemNumberIncrease = viewHolder.itemView.button_cartdetailItemNumberIncrease
        var textView_cartdetailItemCount = viewHolder.itemView.textView_cartdetailItemCount
        var notes = ""

        textView_cartdetailItemName.text = cartItem.cart_item_name
        textView_cartdetailItemCount.text = cartItem.cart_item_count.toString()

        for (additionalInfo in cartItem.cart_item_additional_info!!){
            notes += "$additionalInfo \n"
        }

        textView_cartdetailItemAdditional.text = notes

        textView_cartdetailItemPrice.text = "$${cartItem.cart_item_price}0"

        textView_cartDetailItemRemove.setOnClickListener {
            textView_cartDetailItemRemove.isClickable = false
            textView_cartDetailItemRemove.isFocusable = false

            FirebaseFirestore.getInstance()
                .collection("Cart")
                .document(currentUser.uid)
                .collection("cart")
                .document(cartItem.cart_uid!!)
                .delete()
        }

        button_cartdetailItemNumberDecrease.setOnClickListener {
            if (textView_cartdetailItemCount.text.toString().toInt() == 1){
                //do nothing
            }else{
                var count = textView_cartdetailItemCount.text.toString().toInt()-1
                textView_cartdetailItemCount.text = count.toString()

                FirebaseFirestore.getInstance()
                    .collection("Cart")
                    .document(currentUser.uid)
                    .collection("cart")
                    .document(cartItem.cart_uid!!)
                    .update("cart_item_count", count)
            }
        }

        button_cartdetailItemNumberIncrease.setOnClickListener {
            var count = textView_cartdetailItemCount.text.toString().toInt()+1
            textView_cartdetailItemCount.text = count.toString()

            FirebaseFirestore.getInstance()
                .collection("Cart")
                .document(currentUser.uid)
                .collection("cart")
                .document(cartItem.cart_uid!!)
                .update("cart_item_count", count)
        }
    }

}