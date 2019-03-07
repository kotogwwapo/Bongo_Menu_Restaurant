package mjabellanosa02.gmail.com.GroupieViewHolders

import android.content.Context
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_menu_category.view.*
import mjabellanosa02.gmail.com.R

class MenuCategoriesViewHolder(var categoryUrl: String, var categoryName: String, val context: Context): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.row_menu_category
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var categoryImageView = viewHolder.itemView.imageView_menuCategoryRowImage
        var categoryNameTextView = viewHolder.itemView.textView_menuCategoryRowCategoryName

        if (categoryUrl.isNullOrEmpty()){
            Picasso.get().load(R.drawable.ic_error_green_24dp).into(categoryImageView)
        }else {
            Picasso.get().load(categoryUrl).into(categoryImageView)
        }

        categoryNameTextView.text = categoryName
    }
}