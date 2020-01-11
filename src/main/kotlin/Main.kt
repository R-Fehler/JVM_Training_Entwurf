import model.Exercise
import model.ExerciseSet
import model.KnownExercise
import model.Training
import java.io.*
import java.text.SimpleDateFormat


fun main() {
//    var f:File= File("./log.txt")
//    f.appendText("hallo das ist text\n")
//    var test=f.readLines()
//    println(test)

//    val st = ReadObjectFromFile("./ser.txt") as Student
//    println(st)

//
//    val student = Student("John", "Frost", 22)
//    WriteObjectToFile(student)
    var log: MutableList<Training> = ArrayList()
    if (File("./ser.txt").exists()){
    log= ReadObjectFromFile("./ser.txt") as MutableList<Training>
}

    var cat: MutableSet<KnownExercise> = LinkedHashSet()
    // es existiert ein Katalog an uebungen unabhängig von var training

    readCatalog(cat, "./catalog.txt")
    println("Starte Eingabe: t->k-->n end to stop")

//  TODO: liest nur alte Logs. checke IO und readLogFile Fkt.
    var sw: String = ""
    var stop = false
    while (!stop) {
        when (readLine().toString()) {
            "r" -> {
                newTraining(log)
                readLogFile(cat, log.last(), "./log.txt")
            }
            "t" -> {
                newTraining(log);println("t ended")
            }
            "n" -> {
                newExercise(log.last(), cat);println("n ended")
            }
            "k" -> {
                newKnown(cat);println("k ended")
            }
            "end" -> stop = true
        }
    }


    WriteObjectToFile(log)
    println("warte")
    var t_list= ReadObjectFromFile("./ser.txt") as MutableList<Training>
    println(t_list)
}

fun newExercise(training: Training, cat: MutableSet<KnownExercise>) {
    println("new Exercise:")
    var args = readLine().toString()
    var result = cat.filter { it.name == args }
    if (result.isNotEmpty()) {
        training.exercises.add(Exercise(knownExercise = result.first()))
        println("Sets eingeben: 100/10/10 110/8 etc.")
        var setInput = readLine().toString()
        var inputlist = setInput.split(" ")
        for (input in inputlist) {
            var setvals = input.split("/")
            var nn=setvals.size
            var ii=1
            var weight= setvals.first().toInt()
            var reps=0
            while(ii < nn){
                reps=setvals[ii].toInt()
                training.exercises.last().sets.add(
                    ExerciseSet(
                        weight = weight,
                        reps = reps
                    )
                )
                ii++

            }


        }
        println("added: ${training.exercises.last().sets} ")
    } else
        println("unknown Exercisetype")

}


fun newKnown(cat: MutableSet<KnownExercise>) {
    println("newKnown:")
    var args = readLine().toString()
    var arglist = args.split(' ')
    for (name in arglist) {
        cat.add(KnownExercise(name = name))
    }
    println("$arglist had been added")
}


fun newTraining(log: MutableList<Training>) {
    println("new Training")
    log.add(Training())
}

fun saveToFile(training: Training) {

}

fun readfromFile(): Training {
    return Training()
}

fun WriteObjectToFile(serObj: Any) {

    try {

        val fileOut = FileOutputStream("./ser.txt")
        val objectOut = ObjectOutputStream(fileOut)
        objectOut.writeObject(serObj)
        objectOut.close()
        println("The Object  was succesfully written to a file")

    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}

fun ReadObjectFromFile(filepath: String): Any? {

    try {

        val fileIn = FileInputStream(filepath)
        val objectIn = ObjectInputStream(fileIn)

        val obj = objectIn.readObject()

        println("The Object has been read from the file")
        objectIn.close()
        return obj

    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }

}

fun readCatalog(cat: MutableSet<KnownExercise>, filepath: String) {
    val f: File = File(filepath)
    var inputLines = f.readLines()
    for (str in inputLines) {
        var item = str.split("\t")
        cat.add(KnownExercise(id = item.first().toInt(), name = item.last()))
    }
    println("added: \n $cat")
}
// TODO: hier 100/3/3/4 ermöglichen
fun readLogFile(cat: MutableSet<KnownExercise>, training: Training, filepath: String) {
    val f: File = File(filepath)
    var inputLines = f.readLines()
    var format = SimpleDateFormat("dd.MM.yyyy")
    // first line: 20.01.1995
    var date = format.parse(inputLines.first())
    println("test " + date)
    training.date=date
    for (str in inputLines.drop(1)) {
        var id = str.split(":").first().trim()
        val sets = str.split(":").last().trim()
        val exercises = training.exercises
        try {
            var idInt = id.toInt()
            exercises.add(Exercise(knownExercise = cat.filter { it.id == idInt }.first()))
        } catch(e: java.lang.Exception) {
            exercises.add(Exercise(knownExercise = cat.filter { it.name == id }.first()))

        }
        val setsCollection = sets.split(" ")

        for (setsWithConstW in setsCollection) {
            var setvals = setsWithConstW.split("/")
            var nn=setvals.size
            var ii=1
            var weight= setvals.first().toInt()
            var reps=0

            while(ii < nn){
                reps=setvals[ii].toInt()
                training.exercises.last().sets.add(
                    ExerciseSet(
                        weight = weight,
                        reps = reps
                    )
                )
                ii++

            }


        }

    }
    println(training)
}

