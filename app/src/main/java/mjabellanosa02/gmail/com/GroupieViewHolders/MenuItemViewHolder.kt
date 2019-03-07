package mjabellanosa02.gmail.com.GroupieViewHolders

import android.content.Context
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_menu_items.view.*
import mjabellanosa02.gmail.com.R

class MenuItemViewHolder( var itemUid:String , var itemUrl: String, var itemName: String,var itemPrice: Double , val context: Context): Item<ViewHolder>() {
    override fun getLayout(): Int {
      return R.layout.row_menu_items
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
      var itemFoodImage = viewHolder.itemView.imageView_menuItemFoodImage
      var itemFoodName = viewHolder.itemView.textView_menuItemsFoodName
      var itemFoodPrice = viewHolder.itemView.textView_menuItemsFoodPrice

        if(itemUrl == "") {
            Picasso.get().load(R.drawable.food).into(itemFoodImage)
        }else{
            Picasso.get().load(itemUrl).into(itemFoodImage)
        }
        itemFoodName.text = itemName
        itemFoodPrice.text = itemPrice.toString()
    }
}