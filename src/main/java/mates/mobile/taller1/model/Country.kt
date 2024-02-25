package mates.mobile.taller1.model

import java.io.Serializable

data class Country(val id : Int, val name : String, val capital : String, val abbreviation : String) : Serializable {
    override fun toString(): String {
        return super.toString()
    }
}