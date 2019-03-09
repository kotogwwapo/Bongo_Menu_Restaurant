package mjabellanosa02.gmail.com.GroupieViewHolders

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_total.view.*
import mjabellanosa02.gmail.com.R

class TotalViewHolder(val total: Double):Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.row_total
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var textView_rowTotalNumber = viewHolder.itemView.textView_rowTotalNumber

        textView_rowTotalNumber.text = "$$total"
    }
}