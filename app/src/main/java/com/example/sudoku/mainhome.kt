package com.example.sudoku

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_mainhome.*
import kotlinx.android.synthetic.main.fragment_mainhome.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mainhome.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainhome : Fragment() {
    // TODO: Rename and change types of parameters

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_mainhome, container, false)
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val name = user.displayName

            val Date: LocalDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
            val formatted = Date.format(formatter)
            view.Timeview.setText(formatted)
            view.userview.setText("안녕하세요 ${name}님")
        }
        view.button1.setOnClickListener {
            val intent = Intent(getActivity(), MainActivity::class.java)
            intent.putExtra("level", "12")
            startActivity(intent)
        }
        view.button2.setOnClickListener {
            val intent = Intent(getActivity(), MainActivity::class.java)
            intent.putExtra("level", "8")
            startActivity(intent)
        }
        view.button3.setOnClickListener {
            val intent = Intent(getActivity(), MainActivity::class.java)
            intent.putExtra("level", "6")
            startActivity(intent)
        }
        view.button4.setOnClickListener {
            val intent = Intent(getActivity(), MainActivity::class.java)
            intent.putExtra("level", "4")
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return view

        }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mainhome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mainhome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
