package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.valerija_nagl_RN682018.R
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.contract.UserContract
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.viewmodel.UserViewModel
import timber.log.Timber

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    private val userViewModel : UserContract.ViewModel by viewModel<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()

    }

    private fun initObservers() {
        userViewModel.user.observe(this, Observer {

            val intent = Intent(this, LoginActivity::class.java)
            val intentMain = Intent(this, MainActivity::class.java)

            if (it.name == "") {
                startActivity(intent)
                finish()
            } else {
                Timber.e(it.name)
                startActivity(intentMain)
                finish()
            }
        })
        userViewModel.getUser()
    }
}