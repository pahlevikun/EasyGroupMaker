package id.pahlevikun.easygroupmaker.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import id.pahlevikun.easygroupmaker.R
import id.voela.actrans.AcTrans

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
            AcTrans.Builder(this).performFade()
        }, 2500L)
    }

    override fun onBackPressed() {

    }
}
