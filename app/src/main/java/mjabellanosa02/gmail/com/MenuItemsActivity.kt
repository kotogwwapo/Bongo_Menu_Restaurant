package mjabellanosa02.gmail.com

import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_menu_items.*
import mjabellanosa02.gmail.com.GroupieViewHolders.MenuItemViewHolder
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages

class MenuItemsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_items)

        var categoryUid = intent.getStringExtra("menuCategoryUid")

        title = if (categoryUid.contains("_")) categoryUid.replace("_", " ") else categoryUid
        
        var dialog = Custom_Progress_Dialog(this)
        dialog.showDialog("Loading", RandomMessages().getRandomMessage())

        var adapter = GroupAdapter<ViewHolder>()

        FirebaseFirestore.getInstance()
            .collection("Menu_Items")
            .document(categoryUid)
            .collection("menu_items")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null){
                    PopUpDialogs(this).errorDialog("Error", "Something went wrong with the system")
                    return@addSnapshotListener
                }

                if (querySnapshot!=null && !querySnapshot.isEmpty){
                    adapter.clear()

                    for (doc in querySnapshot.documents){
                        var itemUid = doc.getString("menu_item_uid")!!
                        var imageUrl = doc.getString("menu_item_image_url")!!
                        var itemName = doc.getString("menu_item_name")!!
                        var price = doc.getDouble("menu_item_price")!!

                        adapter.add(MenuItemViewHolder(itemUid, imageUrl, itemName, price, this))
                    }

                    recyclerView_menuItemsActivityRecyler.adapter = adapter
                    recyclerView_menuItemsActivityRecyler.layoutManager = LinearLayoutManager(this)
                    dialog.dissmissDialog()
                }else{
                    dialog.dissmissDialog()
                    PopUpDialogs(this).infoDialog("Empty", "No items to show")
                }
            }

        adapter.setOnItemClickListener { item, view ->
            val menuItem = item as MenuItemViewHolder
            var intent = Intent(menuItem.context, MenuItemDetailsActivity::class.java)
            intent.putExtra("itemUid", menuItem.itemUid)
            startActivity(intent)
        }


        searchView_menuItemsActivitySearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                FirebaseFirestore.getInstance()
                    .collection("Menu_Items")
                    .document(categoryUid)
                    .collection("menu_items")
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        if (firebaseFirestoreException != null){
                            PopUpDialogs(searchView_menuItemsActivitySearchBar.context).errorDialog("Error", "Something went wrong with the system")
                            return@addSnapshotListener
                        }

                        if (querySnapshot!=null && !querySnapshot.isEmpty){
                            adapter.clear()

                            for (doc in querySnapshot.documents){
                                var itemUid = doc.getString("menu_item_uid")!!
                                var imageUrl = doc.getString("menu_item_image_url")!!
                                var itemName = doc.getString("menu_item_name")!!
                                var price = doc.getDouble("menu_item_price")!!

                                if(itemName.toLowerCase().contains(query.toLowerCase())){
                                    adapter.add(MenuItemViewHolder(itemUid, imageUrl, itemName, price, searchView_menuItemsActivitySearchBar.context))
                                }
                            }

                            if (adapter.itemCount == 0){
                                dialog.dissmissDialog()
                                PopUpDialogs(searchView_menuItemsActivitySearchBar.context).infoDialog("Empty", "No items to show")
                            }else{
                                recyclerView_menuItemsActivityRecyler.adapter = adapter
                                recyclerView_menuItemsActivityRecyler.layoutManager = LinearLayoutManager(searchView_menuItemsActivitySearchBar.context)
                                dialog.dissmissDialog()
                            }
                        }else{
                            dialog.dissmissDialog()
                            PopUpDialogs(searchView_menuItemsActivitySearchBar.context).infoDialog("Empty", "No items to show")
                        }
                    }


                return false
            }

        })
    }
}