package controllers

import play.api._
import play.api.mvc._
import model.Company
import java.util.ArrayList

object Application extends Controller {
  
  def index = Action {
    val companies = List[Company]()
    println(Company.getAll)
    Ok(views.html.index(Company.getAll))
  }
  
}