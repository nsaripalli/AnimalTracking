package controller

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import java.sql.Blob
import javax.imageio.ImageIO
import javax.sql.rowset.serial.SerialBlob

object SpeciesTable : Table() {
    val speciesID = integer("species_id").autoIncrement().primaryKey()
    val taxon = varchar("taxon", 100)
    val commonName = varchar("common_name", 250).nullable()
    val scientificName = varchar("scientific_name", 250)
}


object ObserverTable : Table() {
    val observerID = integer("observer_id").autoIncrement().primaryKey()
    val name = varchar("name", 150)
    val email = varchar("email", 150)
    val phone = varchar("phone", 20).nullable()
    val isScientist = bool("scientist").default(false)
}

object WatchTable : Table() {
    val watchID = integer("watch_id").autoIncrement().primaryKey()
    val latitude = double("latitude")
    val longitude = double("longitude")
    val radius = double("radius").default(1.0)
    val startDate = datetime("start_date").default(DateTime.now())
    val endDate = datetime("end").nullable()
    val speciesID = integer("species_id").references(SpeciesTable.speciesID)
    val scientistID = integer("scientist_id").references(ObserverTable.observerID)
}

object SightingTable : Table() {
    val sightingID = integer("sighting_id").autoIncrement().primaryKey()
    val quantity = integer("quantity").default(1)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val notes = varchar("notes", 500).nullable()
    val speciesID = integer("species_id").references(SpeciesTable.speciesID)
    val observerID = integer("observer_id").references(ObserverTable.observerID)
    val photo = blob("photo").nullable()
    val watchID = integer("watch_id").references(WatchTable.watchID).nullable()
    val date = datetime("date").default(DateTime.now())
}

data class Species(
    val speciesID: Int,
    val taxon: String,
    val commonName: String?,
    val scientificName: String
)

data class Observer(
    val observerID: Int,
    val name: String,
    val email: String,
    val phone: String?,
    val isScientist: Boolean = false
)

data class Watch(
    val watchID: Int,
    val latitude: Double,
    val longitude: Double,
    val radius: Double = 1.0,
    val startDate: DateTime = DateTime.now(),
    val endDate: DateTime?,
    val species: Species,
    val scientist: Observer
)


val bird1 = Species(1, "big big", "blue bird", "sciencey bird??? lol")
val observer1 = Observer(1, "Dr. Mario", "smashbros@friday.com", "111-111-1111", true)
val observer2 = Observer(1, "indiana jones", "ihatebeing@teacher.com", "222-222-2222", true)


val watch1 = Watch(1, 10.0, 10.0, 1.0, DateTime.now(), DateTime.now(), bird1, observer1)

data class Sighting(
    val sightingID: Int,
    val quantity: Int = 1,
    val latitude: Double,
    val longitude: Double,
    val notes: String?,
    val species: Species,
    val observer: Observer,
    val watch: Watch?,
    val photo: Blob? = null//I dont know if blob is the right type, but it will work for now
)

val sighting1 = Sighting(1, 1, 10.0, 10.0, "im a bird mf cacaw, cacaw", bird1, observer2, watch1)

val database = Database.connect(
    url = "jdbc:mysql://35.231.160.160:3306",
    driver = "com.mysql.jdbc.Driver",
    user = "root",
    password = "&3&!%M4SBD\$w9tqMSNT3"
)

private fun sightingQuery(): List<ResultRow> {
    print("Success")

    var results: List<ResultRow> = mutableListOf()

    transaction(database) {

        results = SightingTable.leftJoin(
            otherTable = SpeciesTable,
            onColumn = { SightingTable.speciesID },
            otherColumn = { SpeciesTable.speciesID }
        ).leftJoin(
            otherTable = ObserverTable,
            onColumn = { SightingTable.observerID },
            otherColumn = { ObserverTable.observerID }
        ).leftJoin(
            otherTable = WatchTable,
            onColumn = { SightingTable.watchID },
            otherColumn = { WatchTable.watchID }
        ).selectAll().toList()
    }

    return results
}


private fun speciesFromID(speciesID: Int) = SpeciesTable.select { SpeciesTable.speciesID eq speciesID }

private fun observerFromID(observerID: Int) = ObserverTable.select { ObserverTable.observerID eq observerID }

private fun resultToSpecies(result: ResultRow) = Species(
    speciesID = result[SpeciesTable.speciesID],
    taxon = result[SpeciesTable.taxon],
    commonName = result[SpeciesTable.commonName],
    scientificName = result[SpeciesTable.scientificName]
)

private fun resultToObserver(result: ResultRow) = Observer(
    observerID = result[ObserverTable.observerID],
    name = result[ObserverTable.name],
    email = result[ObserverTable.email],
    phone = result[ObserverTable.phone],
    isScientist = result[ObserverTable.isScientist]
)

private fun resultToWatch(result: ResultRow) = Watch(
    watchID = result[WatchTable.watchID],
    latitude = result[WatchTable.latitude],
    longitude = result[WatchTable.longitude],
    radius = result[WatchTable.radius],
    startDate = result[WatchTable.startDate],
    endDate = result[WatchTable.endDate],
    species = resultToSpecies(speciesFromID(result[WatchTable.speciesID]).single()),
    scientist = resultToObserver(observerFromID(result[WatchTable.scientistID]).single())
)


private fun resultToSighting(result: ResultRow): Sighting {
    val species = resultToSpecies(result)
    val observer = resultToObserver(result)
    val watch = if (result.hasValue(WatchTable.watchID)) resultToWatch(result) else null

    return Sighting(
        sightingID = result[SightingTable.sightingID],
        quantity = result[SightingTable.quantity],
        latitude = result[SightingTable.latitude],
        longitude = result[SightingTable.longitude],
        notes = result[SightingTable.notes],
        species = species,
        observer = observer,
        watch = watch
    )
}

fun main(args: Array<String>) {
    sightingList().forEach(::println)
}

fun sightingList(): List<Sighting> = sightingQuery().map { resultToSighting(it) }.toList()

fun blobToImage(data: Blob): Image = ImageIO.read(data.binaryStream)

fun imageToBlob(image: BufferedImage): Blob {

    val byteOutputStream = ByteArrayOutputStream()

    ImageIO.write(image, "jpg", byteOutputStream)

    val bytes = byteOutputStream.toByteArray()

    return SerialBlob(bytes)
}

fun loadImage(file: File): BufferedImage = ImageIO.read(file)

