package model

import play.api.Play.current
import com.mongodb.MongoClient
import com.mongodb.MongoException
import com.mongodb.WriteConcern
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.DBCursor
import com.mongodb.ServerAddress;
import mongo.MongoDBProxy

case class Employees(
    name: String,
    age: Long,
    position: Option[String]
)

object Employees {
  
  def hireEmployee(employee: Employees, companyName: String) : Unit = {
    val coll = MongoDBProxy.getCollection("companies")
    val query = new BasicDBObject("name", companyName)
    val e = new BasicDBObject("name", employee.name).
    			append("age", employee.age).
    			append("position", employee.position.get)
    
    val updateQuery = new BasicDBObject("$push", new BasicDBObject( "employees", e))
    coll.update(query, updateQuery)
  }
  
  def fireEmployee(name: String, companyName: String) : Unit = {
    //TODO :)
  }
  
  def getPositions() : List[String] = {
     return List(
        "CEO",
        "CTO",
        "COO",
        "CFO",
        "Engineer",
        "Marketing"
      )
  }  
}
