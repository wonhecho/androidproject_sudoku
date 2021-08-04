package com.example.sudoku

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    var table: TableLayout? = null
    var table2: TableLayout? = null
    var table3: TableLayout? = null
    var buttons = Array(9) { arrayOfNulls<CustomButton>(9) }
    var clickedCustomButton: CustomButton? = null
    var tableRow: TableRow? = null
    var linearLayout: LinearLayout? = null
    var toggleButton1: ToggleButton? = null
    var toggleButton2: ToggleButton? = null
    var toggleButton3: ToggleButton? = null
    var toggleButton4: ToggleButton? = null
    var toggleButton5: ToggleButton? = null
    var toggleButton6: ToggleButton? = null
    var toggleButton7: ToggleButton? = null
    var toggleButton8: ToggleButton? = null
    var toggleButton9: ToggleButton? = null
    var starttime : Long = System.currentTimeMillis()
    var Wrongnum = 0

    var sum = 0
    var a = 0
    var b = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getlevel = intent.getStringExtra("level")
        var setlevel = getlevel.toInt()
        Log.d("level",getlevel)


        table = findViewById<View>(R.id.tableLayout) as TableLayout
        table2 = findViewById<View>(R.id.tableLayout2) as TableLayout
        table3 = findViewById<View>(R.id.tableLayout3) as TableLayout
        linearLayout = findViewById<View>(R.id.linearLayout) as LinearLayout
        table2!!.visibility = View.INVISIBLE
        table3!!.visibility = View.INVISIBLE
        toggleButton1 = findViewById<View>(R.id.toggleButton1) as ToggleButton
        toggleButton2 = findViewById<View>(R.id.toggleButton2) as ToggleButton
        toggleButton3 = findViewById<View>(R.id.toggleButton3) as ToggleButton
        toggleButton4 = findViewById<View>(R.id.toggleButton4) as ToggleButton
        toggleButton5 = findViewById<View>(R.id.toggleButton5) as ToggleButton
        toggleButton6 = findViewById<View>(R.id.toggleButton6) as ToggleButton
        toggleButton7 = findViewById<View>(R.id.toggleButton7) as ToggleButton
        toggleButton8 = findViewById<View>(R.id.toggleButton8) as ToggleButton
        toggleButton9 = findViewById<View>(R.id.toggleButton9) as ToggleButton


        val layoutParams =
            TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT,
                1.0f
            )
        //TableLayout.LayoutParams layoutParams1 = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT,1.0f);
        layoutParams.height = 168
        layoutParams.setMargins(1, 1, 1, 1)
        val board = BoardGenerator()
        val r = Random()
        for (i in 0..8) {
            tableRow = TableRow(this)
            tableRow!!.setBackgroundColor(-0x1000000)
            table!!.addView(tableRow)
            for (j in 0..8) {
                buttons[i][j] = CustomButton(this, i, j)
                buttons[i][j]!!.tableLayout?.setVisibility(View.INVISIBLE)
                buttons[i][j]!!.isClickable = true
                buttons[i][j]!!.layoutParams = layoutParams
                buttons[i][j]!!.setBackgroundResource(R.drawable.button_selector)
                tableRow!!.addView(buttons[i][j])
            }
        }



        for (i in 0..setlevel) {
            for (j in 0..setlevel) {
                val num1 = r.nextInt(9)
                val num2 = r.nextInt(9)
                buttons[num1][num2]!!.set(board[num1, num2])
            }
        }                                                                                           //힌트 줄이는거
        for (i in 0..8) {
            for (j in 0..8) {
                if (buttons[i][j]!!.value == 1 || buttons[i][j]!!.value == 2 || buttons[i][j]!!.value == 3 || buttons[i][j]!!.value == 4 || buttons[i][j]!!.value == 5 || buttons[i][j]!!.value == 6 || buttons[i][j]!!.value == 7 || buttons[i][j]!!.value == 8 || buttons[i][j]!!.value == 9
                ) buttons[i][j]!!.isClickable = false else {
                    buttons[i][j]?.setOnClickListener(View.OnClickListener { view ->
                        clickedCustomButton = view as CustomButton
                        table2!!.visibility = View.VISIBLE
                        view.textView?.setTextColor(-0xffff01)
                        linearLayout!!.setBackgroundColor(-0x78000000)

                    })
                    buttons[i][j]!!.setOnLongClickListener { v ->
                        clickedCustomButton = v as CustomButton
                        table3!!.visibility = View.VISIBLE
                        ToggleReset()
                        linearLayout!!.setBackgroundColor(-0x78000000)
                        true
                    }
                }
            }
        }
    }

    fun ToggleReset() {
        if (toggleButton1!!.isChecked) toggleButton1!!.toggle()
        if (toggleButton2!!.isChecked) toggleButton2!!.toggle()
        if (toggleButton3!!.isChecked) toggleButton3!!.toggle()
        if (toggleButton4!!.isChecked) toggleButton4!!.toggle()
        if (toggleButton5!!.isChecked) toggleButton5!!.toggle()
        if (toggleButton6!!.isChecked) toggleButton6!!.toggle()
        if (toggleButton7!!.isChecked) toggleButton7!!.toggle()
        if (toggleButton8!!.isChecked) toggleButton8!!.toggle()
        if (toggleButton9!!.isChecked) toggleButton9!!.toggle()
    }

    fun Check_Finish() {
        sum = 0
        a = 0
        while (a < 9) {
            b = 0
            while (b < 9) {
                sum += buttons[a][b]!!.value
                b++
            }
            a++
        }
        if (sum == 405) {
            finish()
            var endtime : Long = System.currentTimeMillis()
            var totaltime = (endtime-starttime)
            var fin = Intent(this,Finish::class.java)
            fin.putExtra("time",totaltime.toString())
            startActivity(fin)
//            Toast.makeText(applicationContext, "스도쿠 완성!", Toast.LENGTH_SHORT).show()

        }
    }

    fun Check_Conflict() {
        val x = clickedCustomButton!!.row
        val y = clickedCustomButton!!.col
        val x_idx: Int
        val y_idx: Int
        var cnt = 0
        for (i in 0..8) {
            if (buttons[x][i]!!.value == clickedCustomButton!!.value) {
                buttons[x][i]!!.setConflict()
                cnt++
            }
        }
        for (i in 0..8) {
            if (buttons[i][y]!!.value == clickedCustomButton!!.value) {
                buttons[i][y]!!.setConflict()
                cnt++
            }
        }
        x_idx =
            if (x == 1 || x == 4 || x == 7) x - 1 else if (x == 2 || x == 5 || x == 8) x - 2 else x
        y_idx =
            if (y == 1 || y == 4 || y == 7) y - 1 else if (y == 2 || y == 5 || y == 8) y - 2 else y
        for (i in x_idx until x_idx + 3) {
            for (j in y_idx until y_idx + 3) {
                if (buttons[i][j]!!.value == clickedCustomButton!!.value) {
                    buttons[i][j]!!.setConflict()
                    cnt++
                }
            }
        }
        if (cnt == 3) buttons[x][y]!!.unsetConflict()
    }

    fun onClickCancel(v: View?) {
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
    }

    fun onClickRemove(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(0)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
    }

    fun onClickButton1(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(1)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton2(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(2)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton3(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(3)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton4(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(4)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton5(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(5)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton6(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(6)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton7(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(7)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton8(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(8)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickButton9(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.INVISIBLE)
        clickedCustomButton!!.set(9)
        for (i in 0..8) {
            for (j in 0..8) {
                buttons[i][j]!!.unsetConflict()
            }
        }
        Check_Conflict()
        table2!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
        Check_Finish()
    }

    fun onClickToggleMenuInput(v: View?) {
        clickedCustomButton!!.tableLayout?.setVisibility(View.VISIBLE)
        if (toggleButton1!!.isChecked) clickedCustomButton!!.hints[0]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[0]?.visibility =
            View.INVISIBLE
        if (toggleButton2!!.isChecked) clickedCustomButton!!.hints[1]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[1]?.visibility =
            View.INVISIBLE
        if (toggleButton3!!.isChecked) clickedCustomButton!!.hints[2]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[2]?.visibility =
            View.INVISIBLE
        if (toggleButton4!!.isChecked) clickedCustomButton!!.hints[3]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[3]?.visibility =
            View.INVISIBLE
        if (toggleButton5!!.isChecked) clickedCustomButton!!.hints[4]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[4]?.visibility =
            View.INVISIBLE
        if (toggleButton6!!.isChecked) clickedCustomButton!!.hints[5]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[5]?.visibility =
            View.INVISIBLE
        if (toggleButton7!!.isChecked) clickedCustomButton!!.hints[6]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[6]?.visibility =
            View.INVISIBLE
        if (toggleButton8!!.isChecked) clickedCustomButton!!.hints[7]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[7]?.visibility =
            View.INVISIBLE
        if (toggleButton9!!.isChecked) clickedCustomButton!!.hints[8]?.visibility =
            View.VISIBLE else clickedCustomButton!!.hints[8]?.visibility =
            View.INVISIBLE
        table3!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
    }

    fun onClickToggleMenuRemove(v: View?) {
        ToggleReset()
    }

    fun onClickToggleMenuCancel(v: View?) {
        table3!!.visibility = View.INVISIBLE
        linearLayout!!.setBackgroundColor(0x00000000)
    }
}