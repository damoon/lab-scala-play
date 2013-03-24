package users

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views.html

object Auth extends Controller {

  val loginForm = Form(
    tuple(
      "email" -> email,
      "password" -> text
    ) verifying("Invalid user name or password", fields => fields match {
      case (e, p) => check(e,p)
    })
  )

  def check(username: String, password: String) = {
    (username == "admin@test.com" && password == "1234")
  }

  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.login(formWithErrors)),
      user => Redirect(controllers.routes.Application.index).withSession(Security.username -> user._1)
    )
  }

  def logout = Action {
    Redirect(routes.Auth.login).withNewSession.flashing(
      "success" -> "You are now logged out."
    )
  }
}