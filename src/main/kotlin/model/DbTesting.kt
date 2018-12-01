package model

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object Species : Table() {
    val speciesID = integer("species_id").autoIncrement().primaryKey()
    val taxon = varchar("taxon", 100)
    val commonName = varchar("common_name", 250).nullable()
    val scientificName = varchar("scientific_name", 250)
}

object Observer : Table() {
    val observerID = integer("observer_id").autoIncrement().primaryKey()
    val name = varchar("name", 150)
    val email = varchar("email", 150)
    val phone = varchar("phone", 20).nullable()
    val isScientist = bool("scientist").default(false)
}

object Watch : Table() {
    val watchID = integer("watch_id").autoIncrement().primaryKey()
    val latitude = double("latitude")
    val longitude = double("longitude")
    val radius = double("radius").default(1.0)
    val startDate = datetime("start_date").default(DateTime.now())
    val endDate = datetime("end").nullable()
    val speciesID = integer("species_id").references(Species.speciesID)
    val scientistID = integer("scientist_id").references(Observer.observerID)
}


fun main(args: Array<String>) {
    Database.connect(
        url = "jdbc:mysql://35.231.160.160:3306",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "&3&!%M4SBD\$w9tqMSNT3"
    )


}
