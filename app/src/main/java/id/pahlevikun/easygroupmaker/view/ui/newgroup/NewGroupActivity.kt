package id.pahlevikun.easygroupmaker.view.ui.newgroup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import id.pahlevikun.easygroupmaker.R
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_new_group.*

class NewGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_group)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = getString(R.string.activity_main_menu_quick)
    }

    override fun onBackPressed() {
        finish()
        AcTrans.Builder(this).performSlideToRight()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                AcTrans.Builder(this).performSlideToRight()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
