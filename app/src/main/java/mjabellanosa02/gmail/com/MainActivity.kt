package mjabellanosa02.gmail.com

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import mjabellanosa02.gmail.com.Model.FirestoreUser
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView_mainActivityBackground.setColorFilter(Color.DKGRAY, PorterDuff.Mode.OVERLAY)

        val dialog = Custom_Progress_Dialog(this)
        dialog.showDialog("Loading", RandomMessages().getRandomMessage())

        if (FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this, RestaurantHomePage::class.java))
            dialog.dissmissDialog()
            finish()
        }else{
            dialog.dissmissDialog()
        }

        button_mainActivityGetStarted.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}
