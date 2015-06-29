package models

import play.api.libs.json._

case class Address(street: String, no: Int, zip: Int)

object Address {
  implicit val addressFormat: Format[Address] = Json.format[Address]
}

case class Vehicle(id: Int, make: String, model: String, registAdd: Address)

object Vehicle {
  val testVehicles = Seq(
    Vehicle(0, "Alfa", "Mito", Address("Fraunhofer Str", 9, 81543)),
    Vehicle(1, "Fiat", "Panda", Address("Gradestr", 20, 12347)),
    Vehicle(2, "Audi", "A4", Address("Dingolfinger Str", 9, 81673))
  )

  implicit val vehicleWrites = new Writes[Vehicle] {
    import Address._
    def writes(v: Vehicle) = JsObject(Seq(
      ("id", JsNumber(v.id)),
      ("make", JsString(v.make)),
      ("model", JsString(v.model)),
      ("registAdd", Json.toJson(v.registAdd))
    ))
  }
}