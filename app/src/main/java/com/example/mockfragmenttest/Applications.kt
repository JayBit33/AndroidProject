package com.example.mockfragmenttest

data class Applications (var app: String = "", var userName: String = "", var passWord: String = "", var notes: String = ""){
    override fun toString(): String {
        return "$userName $passWord $notes"
    }
}