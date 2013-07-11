package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Question
import org.bson.types.ObjectId
//import org.bson.types.ObjectId

object Application extends Controller with Secured {
  
//  def index = Action {
//    Ok(views.html.index("Your new application is ready."))
//  }
  
  val adminForm = Form (
      "newQuestion" -> nonEmptyText
      )
  
    def index = withAuth { username => implicit request =>
    Ok(views.html.index(username))
  }
  
  def hello = IsAdmin() {	_ => implicit request =>
    {
        //val q = new Question(new ObjectId, "tralala")
    	//Question.insert(q)
    	val list = Question.findAllSorted().map(q => q.title)
    	var text = ""
    	//for(elem <- list)text = text + elem
    	text = list mkString "\n"
	    Ok(views.html.admin(text, adminForm))
    }
  }
  
  def addquestion = IsAdmin() {	_ => implicit request =>
    {
      
	    adminForm.bindFromRequest.fold(
	      formWithErrors => BadRequest(views.html.admin("", formWithErrors)),
	      question => Question.insert(new Question(new ObjectId, question))
	    )
        //val q = new Question(new ObjectId, "tralala")
    	//Question.insert(q)
    	val list = Question.findAllSorted().map(q => q.title)
    	var text = ""
    	//for(elem <- list)text = text + elem
    	text = list mkString "\n"
	    Ok(views.html.admin(text.format(HTML), adminForm))
    }
  }
  
}