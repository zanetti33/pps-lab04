package tasks.typeclasses

enum Json:
  case JNull
  case JNumber(number: Double)
  case JString(string: String)
  case JSeq(sequence: Seq[Json])
  case JObject(objects: Map[String, Json])

trait Jsonable[A]:
  def toJson(a: A): Json

object Jsonable:
  given Jsonable[Int] with
    def toJson(a: Int): Json = Json.JNumber(a)
