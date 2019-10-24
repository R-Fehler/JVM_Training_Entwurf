package model

import java.io.Serializable
import java.util.*

data class KnownExercise(var uuid: String = UUID.randomUUID().toString(), var name: String = "unknown", var id: Int = 0):Serializable {
    override fun toString(): String {
        return "name='$name', id=$id\n"
    }
}