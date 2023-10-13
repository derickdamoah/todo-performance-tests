package computerdatabase.utils

import org.mongodb.scala.bson.BsonValue
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class mongoConnector {
  lazy val mongoClient: MongoClient = MongoClient("mongodb://localhost:27017")

  val database: MongoDatabase = mongoClient.getDatabase("todo-database")

  val collection: MongoCollection[Document] = database.getCollection("todo-collection")

  val getAllTaskIds: Future[Seq[Option[BsonValue]]] = collection.find().toFuture().map{ documents =>
    documents.map{document => document.get("_id")}
  }

  val insert: Future[Seq[Option[BsonValue]]] = collection.find().toFuture().map{ documents =>
    documents.map{document => document.get("_id")}
  }

}
