package com.example.quotesapp

import com.example.quotesapp.presentation.common.util.nextCircularIndex
import org.junit.Test

class TestingCircularFunction {
    private val numbers = listOf('a','b','c','d')

    @Test
    fun forward1(){
        val result = numbers.nextCircularIndex(1,2)
        assert(result==3)
    }
    @Test
    fun forward2(){
        val result = numbers.nextCircularIndex(2,2)
        assert(result==0)
    }

    @Test
    fun backward1(){
        val result = numbers.nextCircularIndex(2,-2)
        assert(result==0)
    }
    @Test
    fun backward2(){
        val result = numbers.nextCircularIndex(2,-1)
        assert(result==1)
    }
    @Test
    fun backward3(){
        val result = numbers.nextCircularIndex(1,-3)
        assert(result==2)
    }
}