package mjabellanosa02.gmail.com.SignUpActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in2.*
import mjabellanosa02.gmail.com.R
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages
import mjabellanosa02.gmail.com.RestaurantHomePage

class SignIn2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in2)
        val popUpDialogs = PopUpDialogs(this)

        title = "Login Page"
        button_signUpActivity2SignIn.setOnClickListener{
            var email = if(editText_signUpActivity2Email.text.toString().trim().isNullOrEmpty()) "" else editText_signUpActivity2Email.text.toString().trim()
            var password = if(editText_signUpActivity2Password.text.toString().trim().isNullOrEmpty()) "" else editText_signUpActivity2Password.text.toString().trim()

            if (email.isNotBlank() && password.isNotBlank()){
                val dialog = Custom_Progress_Dialog(this)
                dialog.showDialog("Loading", RandomMessages().getRandomMessage())

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        task: Task<AuthResult> ->
                        if (task.isSuccessful){
                            dialog.dissmissDialog()
                            startActivity(Intent(this, RestaurantHomePage::class.java))
                            finish()
                        }else{
                            Log.e("SI2", task.exception.toString())
                            dialog.dissmissDialog()
                            popUpDialogs.errorDialog("Invalid Credentials", "Please check your email and password")
                        }
                    }
            }else{
                popUpDialogs.errorDialog("Missing Info", "Please fill up all the needed fields")
            }

        }

        button_signUpActivity2SignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }


}
