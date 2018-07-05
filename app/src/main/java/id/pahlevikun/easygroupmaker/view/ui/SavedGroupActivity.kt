package id.pahlevikun.easygroupmaker.view.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import id.pahlevikun.easygroupmaker.R
import id.pahlevikun.easygroupmaker.view.ui.savedgroup.RandomedGroupFragment
import id.pahlevikun.easygroupmaker.view.ui.savedgroup.UserGroupFragment
import id.voela.actrans.AcTrans
import kotlinx.android.synthetic.main.activity_saved_group.*
import java.util.*

class SavedGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_group)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        title = getString(R.string.activity_main_menu_saved)
        setupViewPager(viewPager)
    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UserGroupFragment(), getString(R.string.activity_saved_fragment_list_group_user))
        adapter.addFragment(RandomedGroupFragment(), getString(R.string.activity_saved_fragment_list_randomed_group))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_FIXED
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        AcTrans.Builder(this).performSlideToRight()
    }
}
