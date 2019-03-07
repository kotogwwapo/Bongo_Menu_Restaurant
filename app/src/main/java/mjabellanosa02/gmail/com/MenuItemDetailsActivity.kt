package mjabellanosa02.gmail.com

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_menu_item_details.*
import kotlinx.android.synthetic.main.modal_add_menu_item_option.view.*
import mjabellanosa02.gmail.com.Model.FavoriteItem
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages
import java.lang.Exception

class MenuItemDetailsActivity : AppCompatActivity() {

    var imageUrl: String ?= null
    var itemName: String ?= null
    var price: Double ?= null
    var itemDetails: String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_item_details)

        var itemUid = intent.getStringExtra("itemUid")
        var categoryUid = intent.getStringExtra("categoryUid")
        val currentUser = FirebaseAuth.getInstance().currentUser!!
        var additionalInfo = ArrayList<String>()

        var dialog = Custom_Progress_Dialog(this)
        dialog.showDialog("Loading", RandomMessages().getRandomMessage())

        FirebaseFirestore.getInstance()
            .collection("Menu_Items")
            .document(categoryUid)
            .collection("menu_items")
            .document(itemUid)
            .get()
            .addOnCompleteListener {
                task: Task<DocumentSnapshot> ->
                if  (task.isSuccessful){
                    val doc = task.result
                    if (doc != null){
                        imageUrl = doc.getString("menu_item_image_url")
                        itemName = doc.getString("menu_item_name")!!
                        price = doc.getDouble("menu_item_price")!!
                        itemDetails = doc.getString("menu_item_details")

                        title = itemName

                        textView_menuItemDetailsPriceofItem.text = "$ "+price+"0"
                        textView_menuItemDetailsandNutrition.text = itemDetails
//                        dialog.dissmissDialog()

                        FirebaseFirestore.getInstance()
                            .collection("Favorite_Food")
                            .document(currentUser.uid)
                            .collection("favorite_food")
                            .document(itemUid)
                            .get()
                            .addOnCompleteListener {
                                task1: Task<DocumentSnapshot> ->
                                if (task1.isSuccessful){
                                    val doc1 = task1.result
                                    var path = doc1!!.reference.path

                                    if(doc1.getString("favorite_item_name") != null){
                                        imageView_menuItemDetailsFavorite.setImageResource(R.drawable.ic_favorite_black_24dp)
                                        imageView_menuItemDetailsFavorite.visibility = View.VISIBLE

                                        if (imageUrl == ""){
                                            Picasso.get().load(R.drawable.food).into(imageView_menuItemDetailsImage)
                                        }else {
                                            Picasso.get().load(imageUrl).into(imageView_menuItemDetailsImage)
                                        }

                                        dialog.dissmissDialog()
                                    }else{
                                        imageView_menuItemDetailsFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                                        imageView_menuItemDetailsFavorite.visibility = View.VISIBLE


                                        if (imageUrl == ""){
                                            Picasso.get().load(R.drawable.food).into(imageView_menuItemDetailsImage)
                                        }else {
                                            Picasso.get().load(imageUrl).into(imageView_menuItemDetailsImage)
                                        }

                                        dialog.dissmissDialog()
                                    }
                                }else{
                                    imageView_menuItemDetailsFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                                    imageView_menuItemDetailsFavorite.visibility = View.VISIBLE

                                    if (imageUrl == ""){
                                        Picasso.get().load(R.drawable.food).into(imageView_menuItemDetailsImage)
                                    }else {
                                        Picasso.get().load(imageUrl).into(imageView_menuItemDetailsImage)
                                    }

                                    dialog.dissmissDialog()
                                }
                            }
                    }
                }else{
                    //oops
                }
            }


        imageView_menuItemDetailsFavorite.setOnClickListener {
            if (imageView_menuItemDetailsFavorite.tag == R.drawable.ic_favorite_black_24dp)
            {
                imageView_menuItemDetailsFavorite.isClickable = false
                imageView_menuItemDetailsFavorite.isFocusable = false

                FirebaseFirestore.getInstance()
                    .collection("Favorite_Food")
                    .document(currentUser.uid)
                    .collection("favorite_food")
                    .document(itemUid)
                    .delete()
                    .addOnCompleteListener {
                        task: Task<Void> ->
                        if (task.isSuccessful){
                            imageView_menuItemDetailsFavorite.isClickable = true
                            imageView_menuItemDetailsFavorite.isFocusable = true
                            imageView_menuItemDetailsFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                            imageView_menuItemDetailsFavorite.visibility = View.VISIBLE
                            imageView_menuItemDetailsFavorite.tag = R.drawable.ic_favorite_border_black_24dp
                        }
                    }
            }else{
                imageView_menuItemDetailsFavorite.isClickable = false
                imageView_menuItemDetailsFavorite.isFocusable = false

                val favoriteItem = FavoriteItem(itemName, price, itemUid, imageUrl, categoryUid)

                FirebaseFirestore.getInstance()
                    .collection("Favorite_Food")
                    .document(currentUser.uid)
                    .collection("favorite_food")
                    .document(itemUid)
                    .set(favoriteItem)
                    .addOnCompleteListener {
                        task: Task<Void> ->
                        if (task.isSuccessful){
                            imageView_menuItemDetailsFavorite.isClickable = true
                            imageView_menuItemDetailsFavorite.isFocusable = true
                            imageView_menuItemDetailsFavorite.setImageResource(R.drawable.ic_favorite_black_24dp)
                            imageView_menuItemDetailsFavorite.visibility = View.VISIBLE
                            imageView_menuItemDetailsFavorite.tag = R.drawable.ic_favorite_black_24dp
                        }
                    }
            }
        }

        button_menuItemDetailsCustomizeOrder.setOnClickListener {
            var dialog: Dialog?
            var view = LayoutInflater.from(this).inflate(R.layout.modal_add_menu_item_option, null)//inflate the modal

            //assign buttons from the modal
            var checkboxExtraSauce = view.checkBox_addMenuItemOptionaddExtraSauce
            var checkboxExtraChicken = view.checkBox_addMenuItemOptionaddExtraChicken
            var checkboxAddBeef = view.checkBox_addMenuItemOptionModalAddBeedf
            var editTextNotes = view.editText_addMenuItemOptionModalNotes
            var buttonOK = view.textView_addMenuItemOptionOk
            var buttonCancel = view.textView_addMenuItemOptionCancel


            //instantiate the dialog
            dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()

            //if already contains
            if (additionalInfo!!.contains(checkboxExtraSauce.text.toString().trim())){
                checkboxExtraSauce.isChecked = true
            }

            if (additionalInfo!!.contains(checkboxExtraChicken.text.toString().trim())){
                checkboxExtraChicken.isChecked = true
            }

            if (additionalInfo!!.contains(checkboxAddBeef.text.toString().trim())){
                checkboxAddBeef.isChecked = true
            }

            if (additionalInfo!!.contains(editTextNotes.text.toString().trim())) {
                editTextNotes.setText(additionalInfo!!.get(additionalInfo!!.indexOf(editTextNotes.text.toString().trim())))
            }

            checkboxExtraSauce.setOnClickListener {

            }

            //add functionality to buttons
            if(checkboxExtraSauce.isChecked){
                additionalInfo!!.add(checkboxExtraSauce.text.toString().trim())
            }else{
                try {
                    additionalInfo!!.remove(checkboxExtraChicken.text.toString().trim())
                }catch (e:Exception){

                }
            }

            if(checkboxExtraChicken.isChecked){
                additionalInfo!!.add(checkboxExtraChicken.text.toString().trim())
            }else{
                try {
                    additionalInfo!!.remove(checkboxExtraChicken.text.toString().trim())
                }catch (e:Exception){

                }
            }

            if(checkboxAddBeef.isChecked){
                additionalInfo!!.add(checkboxAddBeef.text.toString().trim())
            }else{
                try {
                    additionalInfo!!.remove(checkboxAddBeef.text.toString().trim())
                }catch (e:Exception){

                }
            }

            buttonOK.setOnClickListener {
                try {
                    additionalInfo!!.remove(editTextNotes.text.toString().trim())
                    additionalInfo!!.add(editTextNotes.text.toString().trim())
                }catch (e:Exception){
                    additionalInfo!!.add(editTextNotes.text.toString().trim())
                }

                dialog.dismiss()
            }

            buttonCancel.setOnClickListener{
                additionalInfo!!.clear()
                dialog.dismiss()
            }

        }
    }
}
