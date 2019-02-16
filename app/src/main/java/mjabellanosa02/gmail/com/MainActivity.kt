package mjabellanosa02.gmail.com

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView_mainActivityBackground.setColorFilter(Color.DKGRAY, PorterDuff.Mode.OVERLAY)

        button_mainActivityGetStarted.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}
