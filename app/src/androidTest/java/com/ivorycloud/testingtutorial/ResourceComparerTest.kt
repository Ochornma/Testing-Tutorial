package com.ivorycloud.testingtutorial

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.Before

import org.junit.Test

class ResourceComparerTest{

    private lateinit var resourceComoarer: ResourceComparer

    @Before
    fun setUp(){
        resourceComoarer = ResourceComparer()
    }

    @Test
    fun stringResourceIsSameAsGivenString(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComoarer.isEqual(context, R.string.app_name, "Testing Tutorial")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceIsDifferentFromGivenString(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComoarer.isEqual(context, R.string.app_name, "Hello")
        assertThat(result).isFalse()
    }
}
