package id.pahlevikun.easygroupmaker.view.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.presenter.implementation.QuickPresenter

class QuickActivity : AppCompatActivity() {

    val presenter = QuickPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick)

        val sumOfGroup = intent.getStringExtra(getString(R.string.intentExtraSumOfGroup))
        val sumOfPerson = intent.getStringExtra(getString(R.string.intentExtraSumOfPerson))
        val isSizeFixed = intent.getBooleanExtra(getString(R.string.intentExtraIsSizeMethod), false)

        val randomedArray = presenter.beginRandomize(sumOfPerson, sumOfGroup, isSizeFixed)
        val readableArray = presenter.parseArrayToHumanReadable(randomedArray)
    }
}
