package com.lab.notekeeper.`class`

data class CourseInfo(val courseId: String, val title: String) {

    override fun toString(): String {
        return title
    }
}

data class NoteInfo(
    var courseInfo: CourseInfo? = null,
    var title: String? = null,
    var text: String? = null
)