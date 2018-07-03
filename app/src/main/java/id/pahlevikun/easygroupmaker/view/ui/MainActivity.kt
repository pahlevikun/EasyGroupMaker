@file:Suppress("INACCESSIBLE_TYPE", "DEPRECATION")

package id.pahlevikun.easygroupmaker.view.ui

import android.arch.lifecycle.LifecycleOwner
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.tooltip.Tooltip
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.composer.application.AppController.context
import id.pahlevikun.easygroupmaker.presenter.implementation.MainPresenter
import id.pahlevikun.easygroupmaker.view.ui.newgroup.NewGroupActivity
import id.pahlevikun.easygroupmaker.view.ui.newgroup.NewGroupAddActivity
import id.pahlevikun.easygroupmaker.view.ui.quick.QuickGroupActivity
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), LifecycleOwner {

    private var doubleBackToExitPressedOnce = false
    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arcProgressSaved.progress = presenter.getSumOfGroupSize(this)
        arcProgressRandomed.progress = presenter.getSumOfRandomNumber(this)

        val powerMenu = PowerMenu.Builder(context)
                .addItem(PowerMenuItem("Type Group Member", false))
                .addItem(PowerMenuItem("Select Saved Group Member", false))
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                .setWidth(600)
                .setBackgroundAlpha(0f)
                .setMenuShadow(10f)
                .setHeight(500)
                .setTextColor(resources.getColor(R.color.colorText))
                .setSelectedTextColor(Color.WHITE)
                .setMenuColor(Color.WHITE)
                .setSelectedMenuColor(resources.getColor(R.color.colorPrimary))
                .setOnMenuItemClickListener { position, _ ->
                    when (position) {
                        0 -> {
                            startActivity(Intent(this@MainActivity, NewGroupAddActivity::class.java))
                            AcTrans.Builder(this).performSlideToLeft()
                        }
                        1 -> {
                            startActivity(Intent(this@MainActivity, NewGroupActivity::class.java))
                            AcTrans.Builder(this).performSlideToLeft()
                        }
                    }
                }
                .build()

        linearlayoutMenuQuick.setOnClickListener {
            val alert = android.support.v7.app.AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.adapter_quick, null)

            val editTextGroup = dialogView.findViewById(R.id.editTextQuickGroup) as EditText
            val editTextPerson = dialogView.findViewById(R.id.editTextQuickPerson) as EditText
            val radioGroup = dialogView.findViewById(R.id.radioGroup) as RadioGroup
            val radioSize = dialogView.findViewById(R.id.radioSize) as RadioButton
            val radioFixed = dialogView.findViewById(R.id.radioFixed) as RadioButton

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
                val sumOfPerson = editTextPerson.text.toString()
                if (presenter.isQuickFieldEmpty(sumOfGroup, sumOfPerson)) {
                    presenter.countRandom(this)
                    val isSizeMethod = radioSize.isChecked
                    val intent = Intent(this@MainActivity, QuickGroupActivity::class.java)
                    intent.putExtra(getString(R.string.intentExtraSumOfGroup), sumOfGroup)
                    intent.putExtra(getString(R.string.intentExtraSumOfPerson), sumOfPerson)
                    intent.putExtra(getString(R.string.intentExtraIsSizeMethod), isSizeMethod)
                    startActivity(intent)
                    AcTrans.Builder(this).performSlideToLeft()
                } else {
                    Snackbar.make(coordinatorMain, getString(R.string.snackbar_fill_correctly), Snackbar.LENGTH_SHORT).show()
                }

            }
            alert.setNegativeButton(getString(R.string.alertDialogButtonNegativeQuick)) { _, _ ->
            }
            alert.show()
        }

        linearlayoutMenuNew.setOnClickListener {
            powerMenu.showAsAnchorRightTop(linearlayoutMenuNew)
        }

        linearlayoutMenuPreferences.setOnClickListener {
            startActivity(Intent(this@MainActivity, PreferenceActivity::class.java))
            AcTrans.Builder(this).performSlideToLeft()
        }

        linearlayoutMenuSaved.setOnClickListener {
            startActivity(Intent(this@MainActivity, SavedGroupActivity::class.java))
            AcTrans.Builder(this).performSlideToLeft()
        }

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

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Snackbar.make(coordinatorMain, getString(R.string.snackbar_double_click), Snackbar.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)

    }

}
