package mongo

import com.mongodb.MongoClient
import com.mongodb.MongoException
import com.mongodb.WriteConcern
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.DBCursor
import com.mongodb.ServerAddress;

object MongoDBProxy {

  private val db = new MongoClient( "localhost" , 27017 ).getDB("workshop")
  
  def getCollection(name : String) : DBCollection = {
    db.getCollection(name)  
  }
}
