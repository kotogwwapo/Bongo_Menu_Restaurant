package mjabellanosa02.gmail.com.GroupieViewHolders

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_vat.view.*
import mjabellanosa02.gmail.com.R

class VATViewHolder(var vatValue: Double):Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.row_vat
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var textView_rowVatNumber = viewHolder.itemView.textView_rowVatNumber
        textView_rowVatNumber.text = "$$vatValue"
    }
}