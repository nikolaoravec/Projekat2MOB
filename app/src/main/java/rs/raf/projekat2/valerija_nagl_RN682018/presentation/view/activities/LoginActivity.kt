package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.UserContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private val userViewModel : UserContract.ViewModel by viewModel<UserViewModel>()


    companion object {
        const val LOGIN_KEY = "loginKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBtn.setOnClickListener {

            if (check()) {
                val intentmain = Intent(this, MainActivity::class.java)
                startActivity(intentmain)
                finish()
            }
        }
    }

    private fun check(): Boolean {

        if (editTextName.text.isEmpty()) {
            Toast.makeText(this, "Sva polja moraju da budu popunjena!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (editTextPin.text.isEmpty()) {
            Toast.makeText(this, "Unesite PIN", Toast.LENGTH_SHORT).show()
            return false
        }
        if (editTextPin.text.toString().length != 4) {
            Toast.makeText(this, "PIN treba da sadzi 4 cifre!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (editTextPin.text.toString() != "0000") {
            Toast.makeText(this, "PIN je pogresan!", Toast.LENGTH_SHORT).show()
            return false
        }

        val username: String? = editTextName.text.toString()
        if (username != null) {
            userViewModel.setUser(username)
        }
        return true
    }
}