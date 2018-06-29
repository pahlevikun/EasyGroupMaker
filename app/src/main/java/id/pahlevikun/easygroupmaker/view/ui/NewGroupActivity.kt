package id.pahlevikun.easygroupmaker.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import id.pahlevikun.easygroupmaker.R
import id.voela.actrans.AcTrans

class NewGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_group)
    }

    override fun onBackPressed() {
        finish()
        AcTrans.Builder(this).performSlideToRight()
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                AcTrans.Builder(this).performSlideToRight()
                return true
            }
            R.id.action_share -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
