package com.example.sudoku

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calendar.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var count = 0
var totaltime : MutableList<Int> = mutableListOf(0,0,0,0,0)
var indx : Int = 0

/**
 * A simple [Fragment] subclass.
 * Use the [calendar.newInstance] factory method to
 * create an instance of this fragment.
 */
class calendar : Fragment() {
    // TODO: Rename and change types of parameters
    private var mparam1: String? = null
    private var mparam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mparam1 = it.getString(ARG_PARAM1)
            mparam2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_calendar, container, false)
        count++
        if(count%2==0)
        {
            totaltime[indx]= mparam1!!.toInt()
            indx++

        }

        totaltime.sortedDescending()
        view.result.setText("소요시간      :        ${totaltime[0]}초")
        view.result2.setText("소요시간      :        ${totaltime[1]}초")
        view.result3.setText("소요시간      :        ${totaltime[2]}초")
        view.result4.setText("소요시간      :        ${totaltime[3]}초")

        Log.d("Text","Param1 : ${mparam1} param2 : ${mparam2} ${count} ${totaltime}")         // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment calendar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            calendar().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)

                }
            }
    }
}
