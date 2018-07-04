package id.pahlevikun.easygroupmaker.view.ui

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Spinner
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.presenter.implementation.PreferencesPresenter
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_preference.*


class PreferenceActivity : AppCompatActivity() {

    val presenter = PreferencesPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = getString(R.string.activity_main_menu_preferences)

        textViewMenu1.setOnClickListener {
            showDialog(getString(R.string.alertDialogPreferencesTitleMenu1), getString(R.string.alertDialogPreferencesSubTitleMenu1), 1)
        }

        textViewMenu2.setOnClickListener {
            showDialog(getString(R.string.alertDialogPreferencesTitleMenu2), getString(R.string.alertDialogPreferencesSubTitleMenu2), 2)
        }

        textViewMenu3.setOnClickListener {
            showDialog(getString(R.string.alertDialogPreferencesTitleMenu3), getString(R.string.alertDialogPreferencesSubTitleMenu3), 3)
        }

        textViewMenu4.setOnClickListener {
            showChangeLanguageDialog()
        }
    }

    private fun showDialog(title: String, subTitle: String, menu: Int) {
        val alert = android.support.v7.app.AlertDialog.Builder(this)
        alert.setTitle(title)
        alert.setMessage(subTitle)
        alert.setCancelable(false)
        alert.setPositiveButton(getString(R.string.alertDialogButtonPositiveQuickSave)) { _, _ ->
            when (menu) {
                1 -> {
                    presenter.deleteUserListGroup(this)
                }
                2 -> {
                    presenter.deleteRandomedGroup(this)
                }
                3 -> {
                    presenter.deleteAllData(this)
                }
            }
        }
        alert.setNegativeButton(getString(R.string.alertDialogButtonNegativeQuickSave)) { _, _ ->
        }
        alert.show()
    }

    private fun showChangeLanguageDialog() {
        val dialogBuilder = android.support.v7.app.AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.language_dialog, null)

        val spinner1 = dialogView.findViewById(R.id.spinner1) as Spinner

        dialogBuilder.setTitle(resources.getString(R.string.alertDialogLanguageTitle))
        dialogBuilder.setMessage(resources.getString(R.string.alertDialogLanguageSubTitle))
        dialogBuilder.setPositiveButton(getString(R.string.alertDialogLanguageButtonPositive)) { _, _ ->
            val langpos = spinner1.selectedItemPosition
            when (langpos) {
                0 -> {
                    PreferenceManager.getDefaultSharedPreferences(applicationContext)
                            .edit().putString("LANG", "en").apply()
                    presenter.changeLanguage(this, "en")
                }
                else -> {
                    PreferenceManager.getDefaultSharedPreferences(applicationContext)
                            .edit().putString("LANG", "id").apply()
                    presenter.changeLanguage(this, "id")
                }
            }
        }
        dialogBuilder.setNegativeButton(getString(R.string.alertDialogLanguageButtonNegative)) { dialog, _ ->
            dialog.dismiss()
        }
        val b = dialogBuilder.create()
        b.show()

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
