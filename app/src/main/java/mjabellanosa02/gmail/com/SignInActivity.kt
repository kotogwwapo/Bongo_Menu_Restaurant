package mjabellanosa02.gmail.com

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        imageView_signInActivityBackground.setColorFilter(Color.DKGRAY, PorterDuff.Mode.OVERLAY)

        button_signInActivitySignIn.setOnClickListener {
            startActivity(Intent(this, SignIn2Activity::class.java))
        }

        button_signInActivitySignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
