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

//IDs are nullable for internal usage, used during creation prior to DB insertion
data class Species(
        val speciesID: Int? = null,
        val taxon: String,
        val commonName: String?,
        val scientificName: String
)


data class Observer(
        val observerID: Int? = null,
        val name: String,
        val email: String,
        val phone: String?,
        val isScientist: Boolean = false
)

data class Watch(
        val watchID: Int? = null,
        val latitude: Double,
        val longitude: Double,
        val radius: Double = 1.0,
        val startDate: DateTime = DateTime.now(),
        val endDate: DateTime?,
        val species: Species,
        val scientist: Observer
)

data class Sighting(
        val sightingID: Int? = null,
        val quantity: Int = 1,
        val latitude: Double,
        val longitude: Double,
        val notes: String?,
        val species: Species,
        val observer: Observer,
        val watch: Watch?,
        val photo: Blob? = null,
        val date: DateTime = DateTime.now()
)


val database by lazy {
    Database.connect(
            url = "jdbc:mysql://35.231.160.160:3306",
            driver = "com.mysql.jdbc.Driver",
            user = "root",
            password = "&3&!%M4SBD\$w9tqMSNT3",
            setupConnection = { connection ->
                connection.schema = "tracker"
            }
    )
}

private fun speciesFromID(speciesID: Int) = transaction(database) { SpeciesTable.select { SpeciesTable.speciesID eq speciesID } }

private fun speciesFromScientificName(scientificName: String) = transaction(database) { SpeciesTable.select { SpeciesTable.scientificName eq scientificName } }

private fun observerFromID(observerID: Int) = transaction(database) { ObserverTable.select { ObserverTable.observerID eq observerID } }

private fun observerFromEmail(email: String) = transaction(database) { ObserverTable.select { ObserverTable.email eq email } }


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
            watch = watch,
            date = result[SightingTable.date]
    )
}

private fun sightingQuery(): List<ResultRow> {
    var results: List<ResultRow> = mutableListOf()

    transaction(database) {
        addLogger(StdOutSqlLogger)
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


private fun watchQuery(): List<ResultRow> {
    var result: List<ResultRow> = mutableListOf()

    transaction(database) {
        result = WatchTable.selectAll().toList()
    }

    return result
}


fun sightingList(): List<Sighting> = sightingQuery().map { resultToSighting(it) }.toList()

fun watchList(): List<Watch> = watchQuery().map { resultToWatch(it) }.toList()

fun userFromEmail(email: String): Observer? = observerFromEmail(email).singleOrNull()?.let { resultToObserver(it) }

fun insertUser(user: Observer) {
    transaction(database) {
        ObserverTable.insert {
            it[name] = user.name
            it[email] = user.email
            it[phone] = user.phone
            it[isScientist] = user.isScientist
        }
    }
}

fun insertWatch(watch: Watch) {
    transaction(database) {
        WatchTable.insert {
            it[latitude] = watch.latitude
            it[longitude] = watch.longitude
            it[radius] = watch.radius
            it[startDate] = watch.startDate
            it[endDate] = watch.endDate
            it[speciesID] = watch.species.speciesID ?: throw IllegalStateException("Must be of a valid species")
            it[scientistID] = watch.scientist.observerID ?: throw IllegalStateException("Watch must be created by a scientist")
        }
    }
}

fun insertSighting(sighting: Sighting) {
    transaction(database) {
        SightingTable.insert {
            it[quantity] = sighting.quantity
            it[latitude] = sighting.latitude
            it[longitude] = sighting.longitude
            it[notes] = sighting.notes
            it[speciesID] = sighting.species.speciesID ?: throw IllegalStateException("Must be of a valid species")
            it[observerID] = sighting.observer.observerID ?: throw IllegalStateException("Must be observed by somebody")
            it[photo] = sighting.photo
            it[watchID] = sighting.watch?.watchID
            it[date] = sighting.date
        }
    }
}

fun speciesFromSciName(name: String) = resultToSpecies(speciesFromScientificName(name).singleOrNull()
        ?: throw IllegalStateException())


//Photo management is not fully implemented at this time
fun blobToImage(data: Blob): Image = ImageIO.read(data.binaryStream)

fun imageToBlob(image: BufferedImage): Blob {

    val byteOutputStream = ByteArrayOutputStream()

    ImageIO.write(image, "jpg", byteOutputStream)

    val bytes = byteOutputStream.toByteArray()

    return SerialBlob(bytes)
}

fun loadImage(file: File): BufferedImage = ImageIO.read(file)

