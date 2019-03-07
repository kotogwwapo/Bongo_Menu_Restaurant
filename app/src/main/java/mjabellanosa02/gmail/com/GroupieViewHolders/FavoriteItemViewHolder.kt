package mjabellanosa02.gmail.com.GroupieViewHolders

import android.app.AlertDialog
import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_favorite_item.view.*
import mjabellanosa02.gmail.com.R
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs

class FavoriteItemViewHolder(var itemUid:String, var itemUrl: String, var itemName: String, var itemPrice: Double, val context: Context): Item<ViewHolder>() {
    override fun getLayout(): Int {
      return R.layout.row_favorite_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        var itemFoodImage = viewHolder.itemView.imageView_favoriteItemFoodImage
        var itemFoodName = viewHolder.itemView.textView_favoriteItemRowCategoryName
        var itemFoodPrice = viewHolder.itemView.textView_favoriteItemRowPrice
        var itemDeleteButton = viewHolder.itemView.imageButton_favoriteItemRowDelete

        var currentUser = FirebaseAuth.getInstance().currentUser!!

        if(itemUrl == "") {
            Picasso.get().load(R.drawable.food).into(itemFoodImage)
        }else{
            Picasso.get().load(itemUrl).into(itemFoodImage)
        }
        itemFoodName.text = itemName
        itemFoodPrice.text = itemPrice.toString()

        itemDeleteButton.setOnClickListener {
            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setIcon(R.drawable.ic_check_circle_green_24dp)
            alertDialog.setMessage("Are you sure you want to delete this?")
            alertDialog.setTitle("Confirm Delete")
            alertDialog.setCancelable(false)

            alertDialog.setPositiveButton("OK") { dialog, which ->
                var dialog1 = Custom_Progress_Dialog(context)
                dialog1.showDialog("Loading", "Deleting Stuff")

                FirebaseFirestore.getInstance()
                    .collection("Favorite_Items")
                    .document(currentUser.uid)
                    .collection("favorite_Items")
                    .document(itemUid)
                    .delete()
                    .addOnCompleteListener {
                        task: Task<Void> ->
                        if (task.isSuccessful){
                            dialog1.dissmissDialog()
                            dialog.dismiss()
                        }else{
                            dialog1.dissmissDialog()
                            dialog.dismiss()
                            PopUpDialogs(context).errorDialog("Error", "Something went wrong")
                        }
                    }
            }

            alertDialog.setNegativeButton("Cancel"){dialog, which ->
                dialog.dismiss()
            }

            alertDialog.show()

        }
    }
}