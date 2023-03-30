package kz.kuz.twopanes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment1 : Fragment() {
    // методы фрагмента должны быть открытыми
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment1_second, container, false)
        val fm = activity!!.supportFragmentManager
        val btn1 = view.findViewById<Button>(R.id.second1_btn1)
        val btn2 = view.findViewById<Button>(R.id.second1_btn2)
        val btn3 = view.findViewById<Button>(R.id.second1_btn3)
        val btn4 = view.findViewById<Button>(R.id.second1_btn4)
        val editText = view.findViewById<EditText>(R.id.second1_edit)
        val numberView = view.findViewById<TextView>(R.id.second1_number)
        val args = arguments
        if (args != null) {
            numberView.text = arguments!!.getInt("number").toString()
        } else {
            numberView.text = "0"
        }
        if (container!!.id == R.id.fragment_container1) {
            activity!!.setTitle(R.string.second_fragment1_title)
            numberView.visibility = View.GONE
            btn1.visibility = View.GONE
            btn2.setOnClickListener {
                var fragment = fm.findFragmentById(R.id.fragment_container2)
                fm.beginTransaction().remove(fragment!!).commit()
                fragment = MainFragment1()
                val args = Bundle()
                val numberText = editText.text.toString()
                args.putInt("number", numberText.toInt())
                fragment.setArguments(args)
                fm.beginTransaction().add(R.id.fragment_container2, fragment).commit()
            }
            btn3.setOnClickListener {
                var fragment = fm.findFragmentById(R.id.fragment_container2)
                fm.beginTransaction().remove(fragment!!).commit()
                fragment = MainFragment2()
                val args = Bundle()
                val numberText = editText.text.toString()
                args.putInt("number", numberText.toInt())
                (fragment as MainFragment2).setArguments(args)
                fm.beginTransaction().add(R.id.fragment_container2, fragment as MainFragment2)
                        .commit()
            }
            btn4.setOnClickListener {
                var fragment = fm.findFragmentById(R.id.fragment_container1)
                fm.beginTransaction().remove(fragment!!).commit()
                fm.beginTransaction().add(R.id.fragment_container1, SecondFragment2()).commit()
                fragment = fm.findFragmentById(R.id.fragment_container2)
                fm.beginTransaction().remove(fragment!!).commit()
                fm.beginTransaction().add(R.id.fragment_container2, MainFragment1()).commit()
            }
        } else {
            editText.visibility = View.GONE
            btn1.setOnClickListener {
                val intent = Intent(activity, SecondActivity::class.java)
                val bundle = Bundle()
                bundle.putString("left_part", "second1")
                intent.putExtras(bundle)
                (activity as MainActivity?)!!.finish()
                startActivity(intent)
            }
            btn2.visibility = View.GONE
            btn3.visibility = View.GONE
            btn4.visibility = View.GONE
        }
        return view
    }
}