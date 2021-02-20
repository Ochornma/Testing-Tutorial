package com.ivorycloud.testingtutorial


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUtil.validateRegis(
            username = "",
            password = "123",
            confirmPassword = "123"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `valid username  and correct password`(){
        val result = RegistrationUtil.validateRegis(
            username = "philip",
            password = "123",
            confirmPassword = "123"
        )

        assertThat(result).isTrue()
    }

    @Test
    fun `username exist`(){
        val result = RegistrationUtil.validateRegis(
            username = "promise",
            password = "123",
            confirmPassword = "123"
        )

        assertThat(result).isFalse()
    }
    @Test
    fun `password is empty`(){
        val result = RegistrationUtil.validateRegis(
            username = "philip",
            password = "",
            confirmPassword = ""
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `password do not match`(){
        val result = RegistrationUtil.validateRegis(
            username = "philip",
            password = "123",
            confirmPassword = "124"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `password contains less than two digits`(){
        val result = RegistrationUtil.validateRegis(
            username = "philip",
            password = "1rrrr",
            confirmPassword = "12rrrrr"
        )

        assertThat(result).isFalse()
    }
}