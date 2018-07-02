package id.pahlevikun.easygroupmaker.view.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.presenter.implementation.QuickPresenter
import kotlinx.android.synthetic.main.activity_quick_group.*

class QuickGroupActivity : AppCompatActivity() {

    private val presenter = QuickPresenter()
    private var readableArray = ""
    private var randomArray: Array<IntArray>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_group)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = getString(R.string.activity_main_menu_quick)

        val sumOfGroup = intent.getStringExtra(getString(R.string.intentExtraSumOfGroup))
        val sumOfPerson = intent.getStringExtra(getString(R.string.intentExtraSumOfPerson))
        val isSizeFixed = intent.getBooleanExtra(getString(R.string.intentExtraIsSizeMethod), false)

        randomize(sumOfGroup, sumOfPerson, isSizeFixed)

        buttonRandom.setOnClickListener {
            presenter.countRandom(this)
            randomize(sumOfGroup, sumOfPerson, isSizeFixed)
        }

        buttonSave.setOnClickListener {
            val alert = android.support.v7.app.AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.adapter_save, null)

            val editTextName = dialogView.findViewById(R.id.editTextGroupName) as EditText
            val editTextDesc = dialogView.findViewById(R.id.editTextGroupDesc) as EditText

            alert.setView(dialogView)
            alert.setTitle(getString(R.string.alerDialogInformationTitleQuickSave))
            alert.setMessage(getString(R.string.alerDialogInformationSubTitleQuickSave))
            alert.setCancelable(false)
            alert.setPositiveButton(getString(R.string.alertDialogButtonPositiveQuickSave)) { _, _ ->
                val groupName = editTextName.text.toString()
                val groupDesc = editTextDesc.text.toString()
                if (presenter.isFieldEmpty(groupName, groupDesc)) {
                    Snackbar.make(coordinatorLayout, getString(R.string.snackbar_fill_correctly), Snackbar.LENGTH_SHORT).show()
                } else {
                    presenter.saveToDatabase(this, groupName, groupDesc, randomArray!!)
                    Snackbar.make(coordinatorLayout, getString(R.string.snackbar_success_save), Snackbar.LENGTH_SHORT).show()
                    buttonSave.isEnabled = false
                }

            }
            alert.setNegativeButton(getString(R.string.alertDialogButtonNegativeQuickSave)) { _, _ ->
            }
            alert.show()
        }
    }

    private fun randomize(sumOfGroup: String, sumOfPerson: String, isSizeFixed: Boolean) {
        randomArray = presenter.beginRandomize(sumOfPerson, sumOfGroup, isSizeFixed)
        readableArray = presenter.parseArrayToHumanReadable(randomArray!!)
        textViewQuick.text = readableArray
    }

    override fun onBackPressed() {
        backPress()
    }

    private fun backPress() {
        val alert = android.support.v7.app.AlertDialog.Builder(this)
        alert.setTitle(getString(R.string.alerDialogInformationTitleQuickSave))
        alert.setMessage(getString(R.string.alerDialogInformationSubTitleQuickSaveBack))
        alert.setCancelable(false)
        alert.setPositiveButton(getString(R.string.alertDialogButtonPositiveQuickSave)) { _, _ ->
            val intent = Intent(this@QuickGroupActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        alert.setNegativeButton(getString(R.string.alertDialogButtonNegativeQuickSave)) { _, _ ->
        }
        alert.show()
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                backPress()
                return true
            }
            R.id.action_share -> {
                val alert = android.support.v7.app.AlertDialog.Builder(this)
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.adapter_save, null)

                val editTextName = dialogView.findViewById(R.id.editTextGroupName) as EditText
                val editTextDesc = dialogView.findViewById(R.id.editTextGroupDesc) as EditText

                alert.setView(dialogView)
                alert.setTitle(getString(R.string.alerDialogInformationTitleQuickSave))
                alert.setMessage(getString(R.string.alerDialogInformationSubTitleQuickSave))
                alert.setCancelable(false)
                alert.setPositiveButton(getString(R.string.alertDialogButtonPositiveQuickSave)) { _, _ ->
                    val groupName = editTextName.text.toString()
                    val groupDesc = editTextDesc.text.toString()
                    if (presenter.isFieldEmpty(groupName, groupDesc)) {
                        Snackbar.make(coordinatorLayout, getString(R.string.snackbar_fill_correctly), Snackbar.LENGTH_SHORT).show()
                    } else {
                        val shareBody = "Group : $groupName\nFor : $groupDesc\n\n$readableArray"
                        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
                        sharingIntent.type = "text/plain"
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, groupName)
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
                        startActivity(Intent.createChooser(sharingIntent, getString(R.string.intentExtraBodyShareTo)))
                    }

                }
                alert.setNegativeButton(getString(R.string.alertDialogButtonNegativeQuickSave)) { _, _ ->
                }
                alert.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
