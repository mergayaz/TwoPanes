package kz.kuz.twopanes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class SecondActivity : AppCompatActivity() {
    private var fm = supportFragmentManager
    private var fragment: Fragment? = null

    @get:LayoutRes
    protected val layoutResId: Int
        get() = R.layout.activity_masterdetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        val bundle = intent.extras
        fragment = fm.findFragmentById(R.id.fragment_container1)
        if (fragment == null) {
            fragment = if (bundle != null) {
                Log.e("left_part", bundle.getString("left_part")!!)
                if (bundle.getString("left_part") == "second1") {
                    SecondFragment1()
                } else {
                    SecondFragment2()
                }
            } else {
                SecondFragment1()
            }
            fm.beginTransaction()
                    .add(R.id.fragment_container1, fragment!!)
                    .commitNow()
        }
        if (findViewById<View?>(R.id.fragment_container2) != null) {
            fragment = fm.findFragmentById(R.id.fragment_container2)
            if (fragment == null) {
                fragment = MainFragment1()
                fm.beginTransaction()
                        .add(R.id.fragment_container2, fragment as MainFragment1)
                        .commitNow()
            }
        }
    }
}