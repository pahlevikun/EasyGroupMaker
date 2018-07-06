package id.pahlevikun.easygroupmaker.view.ui.newgroup

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.tooltip.Tooltip
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.model.database.usergroup.UserGroupTable
import id.pahlevikun.easygroupmaker.presenter.interfaces.callback.ItemTouchCallback
import id.pahlevikun.easygroupmaker.presenter.implementation.newgroup.NewGroupPresenter
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAdapter
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_new_group.*

class NewGroupActivity : AppCompatActivity() {

    private val presenter = NewGroupPresenter()
    private var data: List<UserGroupTable>? = null
    private var adapter: NewGroupAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_group)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = getString(R.string.activity_main_menu_quick)

        data = presenter.gettingData(this)
        if (data!!.isEmpty()) {
            viewNoData.visibility = View.VISIBLE
        } else {
            viewNoData.visibility = View.GONE
        }

        adapter = presenter.setupAdapter(this, recyclerView, data!!, object : ItemTouchCallback {
            override fun onItemTouch(data: Array<String>) {
                val alert = android.support.v7.app.AlertDialog.Builder(this@NewGroupActivity)
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.adapter_quick, null)

                val editTextGroup = dialogView.findViewById(R.id.editTextQuickGroup) as EditText
                val editTextPerson = dialogView.findViewById(R.id.editTextQuickPerson) as EditText
                val radioGroup = dialogView.findViewById(R.id.radioGroup) as RadioGroup
                val radioSize = dialogView.findViewById(R.id.radioSize) as RadioButton
                val radioFixed = dialogView.findViewById(R.id.radioFixed) as RadioButton

                editTextPerson.visibility = View.GONE

                radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.radioFixed -> {
                            editTextGroup.hint = getString(R.string.view_main_adapter_edittext_fixed)
                            editTextGroup.text.clear()
                            showTooltip(radioFixed, getString(R.string.tooltipFixedGroup))
                        }
                        R.id.radioSize -> {
                            editTextGroup.hint = getString(R.string.view_main_adapter_edittext_max)
                            editTextGroup.text.clear()
                            showTooltip(radioSize, getString(R.string.tooltipMaxGroup))
                        }
                    }

                }

                alert.setView(dialogView)
                alert.setTitle(getString(R.string.alerDialogInformationTitleQuick))
                alert.setMessage(getString(R.string.alerDialogInformationSubTitleQuick))
                alert.setCancelable(false)
                alert.setPositiveButton(getString(R.string.alertDialogButtonPositiveQuick)) { _, _ ->
                    val sumOfGroup = editTextGroup.text.toString()
                    val sumOfPerson = data.size.toString()
                    if (presenter.isFieldEmpty(sumOfGroup, sumOfPerson)) {
                        val isSizeMethod = radioSize.isChecked
                        val intent = Intent(this@NewGroupActivity, ResultGroupActivity::class.java)
                        intent.putExtra(getString(R.string.intentExtraSumOfGroup), sumOfGroup)
                        intent.putExtra(getString(R.string.intentExtraSumOfPerson), data)
                        intent.putExtra(getString(R.string.intentExtraIsSizeMethod), isSizeMethod)
                        startActivity(intent)
                        AcTrans.Builder(this@NewGroupActivity).performSlideToLeft()
                    } else {
                        Snackbar.make(coordinatorLayout, getString(R.string.snackbar_fill_correctly), Snackbar.LENGTH_SHORT).show()
                    }

                }
                alert.setNegativeButton(getString(R.string.alertDialogButtonNegativeQuick)) { _, _ ->
                }
                alert.show()
            }
        })
    }

    override fun onBackPressed() {
        finish()
        AcTrans.Builder(this).performSlideToRight()
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                AcTrans.Builder(this).performSlideToRight()
                return true
            }
            R.id.action_addgroup -> {
                startActivity(Intent(this@NewGroupActivity, NewGroupAddActivity::class.java))
                AcTrans.Builder(this).performSlideToLeft()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showTooltip(view: View, message: String) {
        Tooltip.Builder(this, view)
                .setText(message)
                .setGravity(Gravity.END)
                .setCancelable(true)
                .setTextSize(resources.getDimension(R.dimen._4ssp))
                .setDismissOnClick(true)
                .show()
    }
}
