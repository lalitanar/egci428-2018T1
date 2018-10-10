package com.egci428.ex_listactivitymodel

/**
 * Created by lalita on 10/1/2017 AD.
 */
class Course(val courseNumber: Int, val title: String, val description: String, val credits: Double) {

    override fun toString(): String {
        return title
    }
}