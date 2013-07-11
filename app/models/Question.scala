package models

import org.bson.types.ObjectId
import org.joda.time.DateTime
import com.novus.salat.dao.{SalatDAO, ModelCompanion}
import se.radley.plugin.salat._
import com.mongodb.casbah.commons.MongoDBObject
import play.api.Play.current
import models.salatctx._

case class Question  (
  id: ObjectId = new ObjectId,
  title: String
)

object Question extends ModelCompanion[Question, ObjectId]{

  val dao = new SalatDAO[Question, ObjectId](collection= mongoCollection("questions")) {}

  def findAllSorted() = findAll()
}