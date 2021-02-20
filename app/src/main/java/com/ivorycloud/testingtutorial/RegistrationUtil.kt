package com.ivorycloud.testingtutorial

object RegistrationUtil {

    private val existingUsers = listOf<String>("peter", "promise")
    /*
    * we will check if usernmae is taken
    * check if password matches
    *
    * */
    fun validateRegis(username: String, password: String, confirmPassword: String): Boolean {
      if (username.isEmpty() || password.isEmpty()){
          return false
      }

        if (username in existingUsers){
            return false
        }

        if (password != confirmPassword){
            return false
        }

        if (password.count { it.isDigit() } < 2){
            return false
        }

        return true
    }
}
