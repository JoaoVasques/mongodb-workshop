package model

import play.api.Play.current

case class Employees(
    name: String,
    age: Int,
    position: String
)

object Employees {
  
  def hireEmployee(employee: Employees, companyName: String) : Unit = {
    //TODO
  }
  
  def fireEmployee(name: String, companyName: String) : Unit = {
    //TODO
  }
  
}
