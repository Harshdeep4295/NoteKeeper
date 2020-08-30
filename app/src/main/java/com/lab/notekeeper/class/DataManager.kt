package com.lab.notekeeper.`class`

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programing with Intents");
        courses.set(course.courseId, course)

        course = CourseInfo("android_async", "Android Async Programming And Services");
        courses.set(course.courseId, course)

        course = CourseInfo("java_lang", "Java Language");
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java : The Core Language");
        courses.set(course.courseId, course)
    }

    private fun initializeNotes() {
        var note = NoteInfo(
            courses.getValue("android_intents"),
            "Android Programing with Intents",
            "Working with Intents"
        );
        notes.add(note)

        note = NoteInfo(
            courses.getValue("java_core"),
            "Java Core",
            "Working With java Core code"
        );
        notes.add(note)
    }
}