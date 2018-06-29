package id.pahlevikun.easygroupmaker.view.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.presenter.implementation.QuickPresenter
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_quick.*

class QuickActivity : AppCompatActivity() {

    private val presenter = QuickPresenter()
    private var readableArray = ""
    private var randomArray: Array<IntArray>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = getString(R.string.activity_main_menu_quick)

        val sumOfGroup = intent.getStringExtra(getString(R.string.intentExtraSumOfGroup))
        val sumOfPerson = intent.getStringExtra(getString(R.string.intentExtraSumOfPerson))
        val isSizeFixed = intent.getBooleanExtra(getString(R.string.intentExtraIsSizeMethod), false)

        randomize(sumOfGroup, sumOfPerson, isSizeFixed)

        buttonRandom.setOnClickListener {
            randomize(sumOfGroup, sumOfPerson, isSizeFixed)
        }
    }

    private fun randomize(sumOfGroup: String, sumOfPerson: String, isSizeFixed: Boolean) {
        randomArray = presenter.beginRandomize(sumOfPerson, sumOfGroup, isSizeFixed)
        readableArray = presenter.parseArrayToHumanReadable(randomArray!!)
        textViewQuick.text = readableArray
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
