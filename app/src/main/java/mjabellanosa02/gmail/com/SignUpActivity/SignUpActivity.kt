package mjabellanosa02.gmail.com.SignUpActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mjabellanosa02.gmail.com.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        title = "Create new account"
    }
}
