package com.javacodegeeks.java.core

import java.io.Serializable

class Student(var firstName: String,var lastName: String, var age: Int) : Serializable {



    override fun toString(): String {
        return StringBuffer(" First Name: ").append(this.firstName)
            .append(" Last Name : ").append(this.lastName).append(" Age : ").append(this.age).toString()
    }

    companion object {

        //default serialVersion id
        private const val serialVersionUID = 1L
    }

}