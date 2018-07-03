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
import id.pahlevikun.easygroupmaker.presenter.implementation.NewGroupAddPresenter
import id.pahlevikun.easygroupmaker.view.adapter.NewGroupAddlAdapter
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_new_group_add.*

class NewGroupAddActivity : AppCompatActivity() {

    private var arrayList = ArrayList<String>()
    private val presenter = NewGroupAddPresenter()
    private var adapter: NewGroupAddlAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_group_add)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = getString(R.string.activity_main_menu_new)

        buttonAdd.setOnClickListener {
            getDataFromEditText()
        }
    }

    private fun getDataFromEditText() {
        val data = editTextInputType.text.toString()
        arrayList = presenter.addItemToArrayList(arrayList, data)
        if (!arrayList.isEmpty()) {
            viewNoData.visibility = View.GONE
            adapter = presenter.setupAdapter(this, recyclerView, arrayList)
        } else {
            arrayList = presenter.addItemAdapter(data, adapter!!, arrayList)
        }
        editTextInputType.setText("")
    }

    override fun onBackPressed() {
        finish()
        AcTrans.Builder(this).performSlideToRight()
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        menuInflater.inflate(R.menu.menu_addnew, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                AcTrans.Builder(this).performSlideToRight()
                return true
            }
            R.id.action_addnew -> {
                if (arrayList.isEmpty()) {
                    Snackbar.make(coordinatorLayout, getString(R.string.snackbar_fill_correctly), Snackbar.LENGTH_SHORT).show()
                } else {
                    val alert = android.support.v7.app.AlertDialog.Builder(this)
                    val inflater = this.layoutInflater
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
                        val sumOfPerson = arrayList.size.toString()
                        if (presenter.isQuickFieldEmpty(sumOfGroup, sumOfPerson)) {
                            val isSizeMethod = radioSize.isChecked
                            val intent = Intent(this@NewGroupAddActivity, ResultGroupActivity::class.java)
                            intent.putExtra(getString(R.string.intentExtraSumOfGroup), sumOfGroup)
                            intent.putExtra(getString(R.string.intentExtraSumOfPerson), presenter.parsingToArray(arrayList))
                            intent.putExtra(getString(R.string.intentExtraIsSizeMethod), isSizeMethod)
                            startActivity(intent)
                            AcTrans.Builder(this).performSlideToLeft()
                        } else {
                            Snackbar.make(coordinatorLayout, getString(R.string.snackbar_fill_correctly), Snackbar.LENGTH_SHORT).show()
                        }

                    }
                    alert.setNegativeButton(getString(R.string.alertDialogButtonNegativeQuick)) { _, _ ->
                    }
                    alert.show()
                }
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
