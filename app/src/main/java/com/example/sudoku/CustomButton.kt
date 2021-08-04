package com.example.sudoku

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView


class CustomButton : FrameLayout {
    var row = 0
    var col = 0
    var value = 0
    var textView: TextView? = null
    var layoutInflater: LayoutInflater? = null
    var tableLayout: TableLayout? = null
    var hints = arrayOfNulls<TextView>(9)

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context, row: Int, col: Int) : super(context) {
        textView = TextView(context)
        addView(textView)
        this.row = row
        this.col = col
        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        tableLayout = layoutInflater!!.inflate(R.layout.hint_layout, null) as TableLayout
        this.addView(tableLayout)
        var i = 0
        var k = 0
        while (i < 3) {
            val r = tableLayout!!.getChildAt(i) as TableRow
            var j = 0
            while (j < 3) {
                hints[k] = r.getChildAt(j) as TextView
                j++
                k++
            }
            i++
        }
    }

    fun set(a: Int) {
        if (a == 0) {
            textView!!.text = ""
            textView!!.gravity = Gravity.CENTER
            textView!!.textSize = 20f
            value = a
        } else {
            textView!!.text = a.toString()
            textView!!.gravity = Gravity.CENTER
            textView!!.textSize = 20f
            value = a
        }
    }

    fun setConflict() {
        setBackgroundColor(-0x10000)
    }

    fun unsetConflict() {
        setBackgroundColor(Color.rgb(255, 255, 255))
    }
}