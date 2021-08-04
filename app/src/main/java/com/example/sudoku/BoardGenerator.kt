package com.example.sudoku

import java.util.*


class BoardGenerator {
    private val board =
        Array(9) { IntArray(9) }
    private val x1 =
        arrayOf(intArrayOf(0, 0, 1), intArrayOf(1, 0, 0), intArrayOf(0, 1, 0))
    private val x2 =
        arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 0, 1), intArrayOf(1, 0, 0))
    private val s0 =
        Array(3) { IntArray(3) }
    private val s1 =
        Array(3) { IntArray(3) }
    private val s2 =
        Array(3) { IntArray(3) }
    private val s3 =
        Array(3) { IntArray(3) }
    private val s4 =
        Array(3) { IntArray(3) }
    private val s5 =
        Array(3) { IntArray(3) }
    private val s6 =
        Array(3) { IntArray(3) }
    private val s7 =
        Array(3) { IntArray(3) }
    private val s8 =
        Array(3) { IntArray(3) }

    fun get(): Array<IntArray> {
        return board
    }

    operator fun get(row: Int, col: Int): Int {
        return board[row][col]
    }

    private fun mul(
        x: Array<IntArray>,
        y: Array<IntArray>,
        r: Array<IntArray>
    ) {
        for (i in 0..2) {
            for (j in 0..2) {
                r[i][j] = 0
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                for (k in 0..2) {
                    r[i][j] += x[i][k] * y[k][j]
                }
            }
        }
    }

    init {
        val temp =
            Array(3) { IntArray(3) }

        // 랜덤 순서로 1~9의 숫자 생성
        val list = ArrayList<Int>()
        for (i in 1..9) {
            list.add(i)
        }
        Collections.shuffle(list) // 랜덤, 이 줄을 없애면 언제나 같은 보드가 나옴
        for (i in 0..8) {
            s0[i / 3][i % 3] = list[i]
        }
        mul(x2, s0, s1)
        mul(x1, s0, s2)
        mul(s0, x1, s3)
        mul(s1, x1, s4)
        mul(s2, x1, s5)
        mul(s0, x2, s6)
        mul(s1, x2, s7)
        mul(s2, x2, s8)
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = s0[i][j]
                board[i][j + 3] = s1[i][j]
                board[i][j + 6] = s2[i][j]
                board[i + 3][j] = s3[i][j]
                board[i + 3][j + 3] = s4[i][j]
                board[i + 3][j + 6] = s5[i][j]
                board[i + 6][j] = s6[i][j]
                board[i + 6][j + 3] = s7[i][j]
                board[i + 6][j + 6] = s8[i][j]
            }
        }
    }
}