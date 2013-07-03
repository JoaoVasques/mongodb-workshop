package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.data._
import model.Employees

object EmployeeController extends Controller {
  
  val employeeForm : Form[Employees] = Form(
      mapping(
         "name" -> nonEmptyText,
         "age" -> longNumber,
         "position" -> optional(text)
      )(Employees.apply)(Employees.unapply)
  )
  
  val fireEmployeeForm = Form(
    tuple(
        "name" -> text,
        "age" -> longNumber
    )  
  )
  def newEmployee(companyName: String) = Action  {
    Ok(views.html.new_employee(employeeForm, companyName))
  }  
  
  def submit(name: String) = Action {implicit request =>
    employeeForm.bindFromRequest.fold(
        errors => BadRequest(views.html.new_employee(employeeForm, errors.toString)),
        employee => {
          Employees.hireEmployee(employee, name)
          Redirect(routes.Application.index)
        }
      )  	  
  }
  	
  def fireEmployee(companyName: String) = Action {
  	 Ok(views.html.fire_employee(fireEmployeeForm, companyName))
  }
  	
  def submitFireEmployee(companyName: String) = Action { implicit request =>
  	 fireEmployeeForm.bindFromRequest.fold(
       errors => BadRequest(views.html.new_employee(employeeForm, errors.toString)),
       employeeData => {
         println("TODO: " + employeeData)
         Redirect(routes.Application.index)
       }  	      
  	 )
  }
  	
  def searchEmployee = TODO  	
  def submitSearchEmployee = TODO
}
