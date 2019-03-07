package mjabellanosa02.gmail.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages

class MenuItemsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_items)

        var categoryUid = intent.getStringExtra("menuCategoryUid")

        var dialog = Custom_Progress_Dialog(this)
        dialog.showDialog("Loading", RandomMessages().getRandomMessage())


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
                    for (doc in querySnapshot.documents){
                        var itemUid = doc.getString("")
                        var imageUrl = doc.getString("")
                        var itemName = doc.getString("")
                        var price = doc.getDouble("")

                    }
                }else{
                    dialog.dissmissDialog()
                    PopUpDialogs(this).infoDialog("Empty", "No items to show")
                }
            }
    }
}
