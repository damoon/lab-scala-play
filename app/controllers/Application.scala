package controllers

import play.api._
import play.api.mvc._
import users.Secured

object Application extends Controller with Secured {

  def index = withAuth { username => implicit request =>
    Ok(views.html.index("Your new application is ready."))
  }

  def user() = withUser { user => implicit request =>
    Ok(views.html.index("Your new application is ready. USER"))
  }
}