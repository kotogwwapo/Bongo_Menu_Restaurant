package mjabellanosa02.gmail.com.RecallableClasses

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog

class PasswordChecker(val context: Context) {

    fun isValidPassword(password1: String, password2: String): Boolean {
        var password = password1
        var password2 = password2

        if(isStringContainNumber(password)){
            if(isStringContainUpperCase(password)){
                if(isStringContainLowerCase(password)){
                    if(isStringContainSpecialCharacter(password)){
                    if(password.length >=8){
                        if (password == password2){
                            return true
                        }else{
                            Log.e("ERROR", "Incomplete data")
                            var alertDialog = AlertDialog.Builder(context)
                            alertDialog.setMessage("Please make sure you have matching passwords")
                            alertDialog.setTitle("PASSWORD MISMATCH")
                            alertDialog.show()
                            }
                        }
                    }
                }
            }
        }
        return false
    }


    private fun isStringContainSpecialCharacter(string: String): Boolean {
        for(c in string.toCharArray()){
            if (!c.isLetterOrDigit())
                return true
        }

        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setMessage("Password must contain at least one (1) special character")
        alertDialog.setTitle("INCORRECT PASSWORD")
        alertDialog.show()

        return false
    }

    private fun isStringContainLowerCase(string: String): Boolean {
        for(c in string.toCharArray()){
            if (c.isLowerCase())
                return true
        }

        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setMessage("Password must contain at least one (1) lower case character")
        alertDialog.setTitle("INCORRECT PASSWORD")
        alertDialog.show()
        return false
    }

    private fun isStringContainUpperCase(string: String): Boolean {
        for(c in string.toCharArray()){
            if (c.isUpperCase())
                return true
        }
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setMessage("Password must contain at least one (1) upper case character")
        alertDialog.setTitle("INCORRECT PASSWORD")
        alertDialog.show()

        return false
    }

    private fun isStringContainNumber(string: String): Boolean {

        for(c in string.toCharArray()){
            if (c.isDigit())
                return true
        }

        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setMessage("Password must contain at least one (1) digit")
        alertDialog.setTitle("INCORRECT PASSWORD")
        alertDialog.show()

        return false
    }
}