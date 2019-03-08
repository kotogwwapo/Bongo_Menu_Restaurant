package mjabellanosa02.gmail.com.GroupieViewHolders

import android.content.Context
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_cart_details_summary.view.*
import mjabellanosa02.gmail.com.Model.CartDetail
import mjabellanosa02.gmail.com.R

class CartSummaryViewHolder(var cartDetail: CartDetail, val context: Context):Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.row_cart_details_summary
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var textView_yourCartNumberofItems = viewHolder.itemView.textView_cartDetailsSummaryRowCount
        var textView_yourCartSubtotalPrice = viewHolder.itemView.textView_cartDetailsSummaryRowTotal

        textView_yourCartNumberofItems.text = cartDetail.cart_detail_item_count.toString()
        textView_yourCartSubtotalPrice.text = "$"+cartDetail.cart_detail_cost+"0"
    }
}