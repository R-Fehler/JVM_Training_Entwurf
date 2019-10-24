package model

import java.util.*
import kotlin.collections.ArrayList
import java.io.Serializable
 data class Training (
    var uuid: String = UUID.randomUUID().toString(),
    var notes: String = "keine",
    var date: Date = Date(),
    //duration, location, time, blabla
    var exercises: MutableList<Exercise> = ArrayList<Exercise>(10),
    var duration: Long = 0
):Serializable {
    override fun toString(): String {
        return "Training(notes='$notes', date=$date, exercises:\n $exercises)"
    }
}