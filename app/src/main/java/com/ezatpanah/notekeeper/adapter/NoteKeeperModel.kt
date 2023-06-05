package com.ezatpanah.notekeeper.adapter

import android.graphics.Color

data class CourseInfo (val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo(var course: CourseInfo? = null, var title: String? = null, var text: String? = null,var color:Int = Color.TRANSPARENT)

