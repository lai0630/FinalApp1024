package imd.ntub.myfrags0509

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var firstFragment: FirstFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstFragment = FirstFragment.newInstance()

        class ViewPagerAdater(activity: MainActivity): FragmentStateAdapter(activity){
            override fun getItemCount() = 3

            override fun createFragment(position: Int) =
                when(position){
                    0 -> firstFragment
                    1 -> SecondFragment.newInstance()
                    2 -> ThirdFragment.newInstance("11056051 梁詔恩" +
                            "11056036 徐湘婷")
                    else -> firstFragment
                }
        }

        viewPager = findViewById(R.id.viewpager)
        viewPager.adapter = ViewPagerAdater(this)

        findViewById<Button>(R.id.btn1).setOnClickListener {
            viewPager.currentItem = 0
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            viewPager.setCurrentItem(1, true)
        }
        findViewById<Button>(R.id.btn3).setOnClickListener {
            viewPager.setCurrentItem(2, true)
        }
    }

    override fun onBackPressed() {
        if(viewPager.currentItem > 0){
            viewPager.currentItem = viewPager.currentItem-1
        }else{
            super.onBackPressed()
        }
    }
}
