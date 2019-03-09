package mjabellanosa02.gmail.com.GroupieViewHolders

import android.content.Context
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_view_pastorder.view.*
import mjabellanosa02.gmail.com.Model.Cart
import mjabellanosa02.gmail.com.R

class OrderItemViewHolder(val cart: Cart, val context: Context) :Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.row_view_pastorder
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var textView_itemPriceSummaryRowItemCategory = viewHolder.itemView.textView_itemPriceSummaryRowItemCategory
        var textView_itemCountSummary = viewHolder.itemView.textView_itemCountSummary
        var textView_itemtotalPriceSummaryRow = viewHolder.itemView.textView_itemtotalPriceSummaryRow

        textView_itemPriceSummaryRowItemCategory.text = cart.cart_item_name
        textView_itemCountSummary.text = "x${cart.cart_item_count}"
        textView_itemtotalPriceSummaryRow.text = "$${cart.cart_item_price}0"
    }
}