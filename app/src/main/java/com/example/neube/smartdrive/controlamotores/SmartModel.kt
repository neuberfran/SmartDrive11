package com.example.neube.smartdrive.controlamotores

import com.google.firebase.database.IgnoreExtraProperties

class SmartModel {

    companion object Factory {
        fun create(): SmartModel = SmartModel()
    }

    val DirecaoDois: Int = 1
    val PararDois: Int = 0
    val DirecaoUm: Int = 1
    val PararUm: Int = 0
    val fcmotordoisa: Int = 0
    val fcmotordoisb: Int = 0
    val fcmotoruma: Int = 0
    val fcmotorumb: Int = 0
    val BaixaTemperatura: Boolean = false
    val Chovendo: Boolean = false

//            override fun(): String {
//        return "{SmartModel DirecaoDois=$DirecaoUm ticker PararUm=$PararUm}"
//    }

}