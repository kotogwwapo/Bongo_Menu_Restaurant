package mjabellanosa02.gmail.com

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_your_cart.*
import mjabellanosa02.gmail.com.GroupieViewHolders.CartItemViewHolder
import mjabellanosa02.gmail.com.GroupieViewHolders.CartSummaryViewHolder
import mjabellanosa02.gmail.com.Model.Cart
import mjabellanosa02.gmail.com.Model.CartDetail
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages

class YourCartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_cart)

        title = "Your Cart"

        val currentUser = FirebaseAuth.getInstance().currentUser!!
        var dialog = Custom_Progress_Dialog(this)
        dialog.showDialog("Loading", RandomMessages().getRandomMessage())

        var adapter = GroupAdapter<ViewHolder>()

        FirebaseFirestore.getInstance()
            .collection("Cart")
            .document(currentUser.uid)
            .collection("cart")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null){
                    PopUpDialogs(this).errorDialog("Error", "Something went wrong with the system")
                    return@addSnapshotListener
                }

                if (querySnapshot!=null && !querySnapshot.isEmpty){
                    adapter.clear()

                    for (doc in querySnapshot.documents){
                        var cart = doc.toObject(Cart::class.java)!!

                        adapter.add(CartItemViewHolder(cart, this))
                    }

//                    FirebaseFirestore.getInstance()
//                        .collection("Cart_Details")
//                        .document(currentUser.uid)
//                        .addSnapshotListener { documentSnapshot, firebaseFirestoreException1 ->
//
//                            if (documentSnapshot != null) {
//                                var cartDetail = documentSnapshot.toObject(CartDetail::class.java)!!
//
//                                adapter.remove(CartSummaryViewHolder(cartDetail, this))
//                                adapter.add(CartSummaryViewHolder(cartDetail, this))
//
//                                recyclerView_yourCartRecycler.adapter = adapter
//                                recyclerView_yourCartRecycler.layoutManager = LinearLayoutManager(this)
//                                dialog.dissmissDialog()
//                            } else {
//                                dialog.dissmissDialog()
//                                PopUpDialogs(this).infoDialog("Empty", "No items to show")
//                            }
//                        }

                    FirebaseFirestore.getInstance()
                        .collection("Cart_Details")
                        .document(currentUser.uid)
                        .get()
                        .addOnCompleteListener {
                            task: Task<DocumentSnapshot> ->
                            if (task.isSuccessful) {
                                var doc = task.result
                                if (doc != null) {
                                    var cartDetail = doc.toObject(CartDetail::class.java)!!

                                    adapter.add(CartSummaryViewHolder(cartDetail, this))

                                    recyclerView_yourCartRecycler.adapter = adapter
                                    recyclerView_yourCartRecycler.layoutManager = LinearLayoutManager(this)
                                    dialog.dissmissDialog()
                                } else {
                                    dialog.dissmissDialog()
                                    PopUpDialogs(this).infoDialog("Empty", "No items to show")
                                }
                            }else {
                                dialog.dissmissDialog()
                                PopUpDialogs(this).infoDialog("Empty", "No items to show")
                            }
                        }
                }else{
                    dialog.dissmissDialog()
                    PopUpDialogs(this).infoDialog("Empty", "No items to show")
                }
            }

        button_yourCartBacktoMenu.setOnClickListener {
            startActivity(Intent(this, RestaurantHomePage::class.java))
            finish()
        }
    }
}
