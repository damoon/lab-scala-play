package users

object UserGateway {
  def getUser(email:String) : User = {
    new User(email)
  }
}
