package com.example.quotesapp.presentation.common.util

fun <T> List<T>.nextCircularIndex(currInd: Int, inc: Int): Int? {
    if (this.isEmpty()) {
        return null
    }

    val newInd = currInd + inc

    return if(newInd < 0){
        newInd + this.size
    }
    else{
        newInd % this.size
    }
}