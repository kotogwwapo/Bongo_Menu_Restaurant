package mjabellanosa02.gmail.com.SignUpActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in2.*
import mjabellanosa02.gmail.com.R
import mjabellanosa02.gmail.com.RestaurantHomePage

class SignIn2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in2)

        title = "Login Page"
        button_signUpActivity2SignIn.setOnClickListener{
            startActivity(Intent(this, RestaurantHomePage::class.java))
            finish()
        }
    }


}
