package mjabellanosa02.gmail.com.Fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_favorite_menu.view.*
import mjabellanosa02.gmail.com.GroupieViewHolders.FavoriteItemViewHolder
import mjabellanosa02.gmail.com.MenuItemDetailsActivity

import mjabellanosa02.gmail.com.R
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages
import mjabellanosa02.gmail.com.RestaurantHomePage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FavoriteMenuFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FavoriteMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FavoriteMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_favorite_menu, container, false)

        val currentUser = FirebaseAuth.getInstance().currentUser!!
        var adapter = GroupAdapter<ViewHolder>()
        var dialog = Custom_Progress_Dialog(this.context!!)

        dialog.showDialog("Loading", RandomMessages().getRandomMessage())

        FirebaseFirestore.getInstance()
            .collection("Favorite_Food")
            .document(currentUser.uid)
            .collection("favorite_food")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null){
                    dialog.dissmissDialog()
                    PopUpDialogs(this.context!!).errorDialog("Error", "Something went wrong")
                    return@addSnapshotListener
                }

                if (querySnapshot != null && querySnapshot.size() != 0){
                    for (doc in querySnapshot.documents){
                        var itemUid = doc.getString("favorite_item_uid")!!
                        var imageUrl = doc.getString("favorite_item_image_url")!!
                        var itemName = doc.getString("favorite_item_name")!!
                        var price = doc.getDouble("favorite_item_price")!!

                        adapter.add(FavoriteItemViewHolder(itemUid, imageUrl, itemName, price, this.context!!))
                    }

                    view.recyclerView_favoriteMenuFragementRecycler.adapter = adapter
                    view.recyclerView_favoriteMenuFragementRecycler.layoutManager = GridLayoutManager(this.context, 2)
                }else{
                    dialog.dissmissDialog()
                    PopUpDialogs(this.context!!).infoDialog("No Items", "You have no favorite food")
                }
            }

        adapter.setOnItemClickListener { item, view ->
            val favoriteItem = item as FavoriteItemViewHolder
            var intent = Intent(this.context!!, MenuItemDetailsActivity::class.java)
            intent.putExtra("itemUid", favoriteItem.itemUid)

            if (view.id != R.id.imageButton_favoriteItemRowDelete) {
                startActivity(intent)
            }
        }


        return view
    }
}
