package model

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class Exercise(
    var uuid: String = UUID.randomUUID().toString(),
    var sets: MutableList<ExerciseSet> = ArrayList<ExerciseSet>(10),
    var knownExercise: KnownExercise = KnownExercise()
) :Serializable{
    override fun toString(): String {
        return "$knownExercise amount:${sets.size}:${sets}\n "
    }
}