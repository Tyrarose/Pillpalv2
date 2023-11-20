package com.codingwithme.meowbottomnavigationbar

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*
import com.jakewharton.threetenabp.AndroidThreeTen


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /////////////// SET BUTTONS /////////////
        addFragment(DrugdrugFragment.newInstance())
        bottomNavigation.show(0)
        bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_calendar))
        bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_user))


        /////////////// COLOR THE PHONE /////////////
//        getWindow().setNavigationBarColor(Color.parseColor("#D9F1F4"))
        getWindow().setStatusBarColor(Color.parseColor("#D9F1F4"))
        bottomNavigation.setBackgroundColor(Color.parseColor("#D9F1F4"))


        /////////////// NAVIGATION BAR LOGIC /////////////
        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(DrugdrugFragment.newInstance())
                    getWindow().setStatusBarColor(Color.parseColor("#D9F1F4"))
                    bottomNavigation.setBackgroundColor(Color.parseColor("#D9F1F4"))
                }
                1 -> {
                    replaceFragment(CalendarFragment.newInstance())
                    getWindow().setStatusBarColor(Color.parseColor("#E2EFE3"))
                    bottomNavigation.setBackgroundColor(Color.parseColor("#E2EFE3"))
                }
                2 -> {
                    replaceFragment(ProfileFragment.newInstance())
                    getWindow().setStatusBarColor(Color.parseColor("#F1E9F3"))
                    bottomNavigation.setBackgroundColor(Color.parseColor("#F1E9F3"))
                }
                else -> {
                    replaceFragment(DrugdrugFragment.newInstance())
                }
            }
        }
    } // ============================== END OF ONCREATE ============================================


    private fun replaceFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}