package com.example.neube.smartdrive.controlamotores

import com.google.firebase.database.IgnoreExtraProperties

data class ModelMotor(

    val DirecaoDois  : Int = 0 ,
    val PararDois    : Int = 0 ,
    val DirecaoUm    : Int = 0 ,
    val PararUm      : Int = 0 ,
    val fcmotordoisa : Int = 0 ,
    val fcmotordoisb : Int = 0 ,
    val fcmotoruma   : Int = 0 ,
    val fcmotorumb   : Int = 0 ,
    val BaixaTemperatura : Boolean = false,
    val Chovendo     : Boolean = false

)

//data class HomeInformation(var button: Boolean = false,
//                          var light: Boolean = false,
//                          var temperature: Float = 0f)


//@IgnoreExtraProperties
//class ModelMotor{
//    var PararUm:     Long? = null
//    var DirecaoUm:   Int? = null
//    var PararDois:   Int? = null
//    var DirecaoDois: Int? = null
//
//    constructor() {}
//
//    constructor(PararUm: Long?, DirecaoUm: Int?, PararDois: Int?, DirecaoDois: Int?) {
//        this.PararUm     = PararUm
//        this.DirecaoUm   = DirecaoUm
//        this.PararDois   = PararDois
//        this.DirecaoDois = DirecaoDois
//    }
//
//}