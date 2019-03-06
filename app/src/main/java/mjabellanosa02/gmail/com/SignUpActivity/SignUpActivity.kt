package mjabellanosa02.gmail.com.SignUpActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*
import mjabellanosa02.gmail.com.Model.FirestoreUser
import mjabellanosa02.gmail.com.R
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog
import mjabellanosa02.gmail.com.RecallableClasses.PasswordChecker
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages
import mjabellanosa02.gmail.com.RestaurantHomePage

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        FirebaseApp.initializeApp(applicationContext)

        title = "Create new account"
        val popUpDialogs = PopUpDialogs(this)

        button_signUpActivitySignUp.setOnClickListener {
            val displyName = if(editText_signUpActivityName.text.toString().trim().isEmpty()) "" else editText_signUpActivityName.text.toString().trim()
            val userName = if(editText_signUpActivityUserName.text.toString().trim().isEmpty()) "" else editText_signUpActivityUserName.text.toString().trim()
            val email = if (editText_signUpActivityEmail.text.toString().trim().isEmpty()) "" else editText_signUpActivityEmail.text.toString().trim()
            val password = if(editText_signUpActivityPassword.text.toString().trim().isEmpty()) "" else editText_signUpActivityPassword.text.toString().trim()

            if (displyName.isNotBlank() && userName.isNotBlank() && email.isNotBlank() && password.isNotBlank()){
                if(email.contains("@")){
                    if(PasswordChecker(this).isValidPassword(password, password)){
                        val dialog = Custom_Progress_Dialog(this)
                        dialog.showDialog("Loading", RandomMessages().getRandomMessage())
                        val auth = FirebaseAuth.getInstance()

                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener {
                                task: Task<AuthResult> ->
                                if (task.isSuccessful){
                                    val user = FirestoreUser()
                                    user.user_display_name = displyName
                                    user.user_email = email
                                    user.user_user_name = userName
                                    user.user_uid = auth.uid

                                    FirebaseFirestore.getInstance()
                                       .collection("Users")
                                        .document(user.user_uid!!)
                                       .set(user)
                                        .addOnCompleteListener {
                                            task1: Task<Void> ->
                                            if (task1.isSuccessful){
                                                startActivity(Intent(this, RestaurantHomePage::class.java))
                                                dialog.dissmissDialog()
                                                finishAndRemoveTask()
                                            }else{
                                                dialog.dissmissDialog()
                                                popUpDialogs.errorDialog("Error", "Database Error")
                                            }
                                        }
                                }else{
                                    dialog.dissmissDialog()
                                    popUpDialogs.errorDialog("Error", "Email is already in use")
                                    Log.e("SUA", task.exception.toString())
                                }
                            }
                    }
                }else{
                    popUpDialogs.errorDialog("Missing Data", "Please provide a valid email")
                }
            }else{
                popUpDialogs.errorDialog("Missing Data", "Please fill up all required fields")
            }
        }
    }
}
