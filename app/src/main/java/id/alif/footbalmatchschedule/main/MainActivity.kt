package id.alif.footbalmatchschedule.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.alif.footbalmatchschedule.Adapter.FragmentAdapter
import id.alif.footbalmatchschedule.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }

}
