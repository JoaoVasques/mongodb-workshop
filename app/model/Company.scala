package model

import play.api.Play.current
import play.api.libs.json._
import play.Logger
import java.util.Date
import com.mongodb.MongoClient
import com.mongodb.MongoException
import com.mongodb.WriteConcern
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.DBCursor
import com.mongodb.ServerAddress;
import java.util.ArrayList
import scala.collection.JavaConversions._

case class Company(
    name: String,
    founded: Date,
    employees: List[Employees]
)

object Company {

    //TODO: define types
    val types = List()

    val UPDATE_NAME = 1
    val UPDATE_VALIDIT_DATE = 2
    val UPDATE_DESCRIPTION = 3
    val UPDATE_BUDGET = 4
    val UPDATE_COST_PER_CLICK = 5

    def createCompany(company: Company) : Unit = {
      val db = new MongoClient( "localhost" , 27017 ).getDB("workshop")
      val coll = db.getCollection("companies")
      
      val doc = new BasicDBObject("name", company.name).
                              append("founded", company.founded).
                              append("employees", new ArrayList())      
      coll.insert(doc)
      
    }
    
    def getAll() : List[Company] = {
      
      def getCompany(obj: DBObject) : Company = {
        
        val date = obj.get("founded") match {
          case w: Date => w
        }
        
        Company(
            obj.get("name").toString, 
            date,
            List[Employees]()
        )
      }
      
      val db = new MongoClient( "localhost" , 27017 ).getDB("workshop")
      db.getCollection("companies").find.toArray.toList.map(getCompany(_))
      
    }
}
