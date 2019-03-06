package mjabellanosa02.gmail.com.RecallableClasses

import android.app.AlertDialog
import android.content.Context
import mjabellanosa02.gmail.com.R

class PopUpDialogs(val context: Context) {

    fun errorDialog(title: String, message: String) {
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setIcon(R.drawable.ic_error_green_24dp)
        alertDialog.setMessage(message)
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    fun successDialog(title: String, message: String) {
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setIcon(R.drawable.ic_check_circle_green_24dp)
        alertDialog.setMessage(message)
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)

        alertDialog.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    fun infoDialog(title: String, message: String) {
        var alertDialog = AlertDialog.Builder(context)
        alertDialog.setIcon(R.drawable.ic_info_green_24dp)
        alertDialog.setMessage(message)
        alertDialog.setTitle(title)
        alertDialog.setCancelable(false)

        alertDialog.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}