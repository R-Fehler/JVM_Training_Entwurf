package model

import java.io.Serializable
import java.util.*

data class ExerciseSet(
    var uuid: String = UUID.randomUUID().toString(),
    var weight: Int = 0,
    var reps: Int = 0,
    var duration: Long = 0
):Serializable {
    override fun toString(): String {
        return "(w:$weight / r:$reps)"
    }
//reps, time, duration, ...
}