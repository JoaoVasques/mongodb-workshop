package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.data._
import model.Employees
import model.Company

object CompanyController extends Controller {
	val companyForm: Form[Company] = Form(
	  mapping(
	    "name" -> text,
	    "founded" -> date("yyyy-MM-dd"),
	    "employees" -> ignored(List[Employees]())
	  )(Company.apply)(Company.unapply)
	)
	
	def newCompany = Action  {
	  Ok(views.html.new_company(companyForm))
	}
	
    def submit = Action { implicit request =>
      companyForm.bindFromRequest.fold(
        errors => BadRequest(views.html.new_company(errors)),
        company => {
          Company.createCompany(company)
          Redirect(routes.Application.index)
        }
      )
    }

}
