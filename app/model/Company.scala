package model

import play.api.Play.current
import play.api.libs.json._
import play.Logger
import java.util.Date
import com.mongodb.MongoClient
import com.mongodb.DBCollection
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.DBCursor
import com.mongodb.BasicDBList
import java.util.ArrayList
import scala.collection.JavaConversions._
import mongo.MongoDBProxy

case class Company(
    name: String,
    founded: Date,
    employees: List[Employees]
)

object Company {

    def createCompany(company: Company) : Unit = {
      val coll =  MongoDBProxy.getCollection("companies")    
      val doc = new BasicDBObject("name", company.name).
                              append("founded", company.founded).
                              append("employees", new ArrayList())      
      coll.insert(doc)  
    }
    
    def deleteCompany(name: String) : Unit = {
      val companyCollection = MongoDBProxy.getCollection("companies")
      val company = companyCollection.findOne(new BasicDBObject("name", name.replace("+", " ")))
      companyCollection.remove(company)
    }
    
    def getAll() : List[Company] = {
      
      def getCompany(obj: DBObject) : Company = {
        
        def getEmployee(employee: Any) : Employees = {
        	employee match {
        	  case employee: BasicDBObject => {
        	    new Employees(employee.get("name").toString, employee.getLong("age"), null)
        	  }        	  
        	  case _ => null
        	}
        }
        
        val date = obj.get("founded") match {
          case w: Date => w
        }
        
        val listOfEmployees = obj.get("employees") match {
          case lst: BasicDBList => lst
        }       
        
        val employees = for{
          employee <- listOfEmployees
        } yield getEmployee(employee)
                
        Company(
            obj.get("name").toString, 
            date,
            employees.toList
        )
      }
      
      val collection = MongoDBProxy.getCollection("companies")
      collection.find.toArray.toList.map(getCompany(_))      
    }
}
