package mjabellanosa02.gmail.com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_checkout.*
import mjabellanosa02.gmail.com.GroupieViewHolders.CartSummaryViewHolder
import mjabellanosa02.gmail.com.GroupieViewHolders.OrderItemViewHolder
import mjabellanosa02.gmail.com.GroupieViewHolders.TotalViewHolder
import mjabellanosa02.gmail.com.GroupieViewHolders.VATViewHolder
import mjabellanosa02.gmail.com.Model.Cart
import mjabellanosa02.gmail.com.Model.CartDetail
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        var total= 0.0
        var vat = 0.0

        title = "Checkout"

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

                        adapter.add(OrderItemViewHolder(cart, this))
                    }

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
                                    adapter.add(VATViewHolder(vat))

                                    total = cartDetail.cart_detail_cost!! + cartDetail.cart_detail_cost!!*vat

                                    adapter.add(TotalViewHolder(total))

                                    recyclerView_checkoutActivityRecyler.adapter = adapter
                                    recyclerView_checkoutActivityRecyler.layoutManager = LinearLayoutManager(this)
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

        button_checkoutPlaceAnOrder.setOnClickListener {

        }
    }
}
