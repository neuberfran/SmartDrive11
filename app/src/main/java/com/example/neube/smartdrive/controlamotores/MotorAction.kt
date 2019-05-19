package com.example.neube.smartdrive.controlamotores

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.google.android.things.pio.Gpio
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import timber.log.Timber

class MotorAction : Application(){

    private var dbReference: DatabaseReference? = null

    companion object {
        var direcaodois: Int? = null
        var parardois: Int? = null

        var direcaoum: Int? = null
        var pararum: Int? = null

        var fcmotordoisb: Int? = null
        var fcmotordoisa: Int? = null

        var fcmotorumb: Int? = null
        var fcmotoruma: Int? = null

        var baixatemperatura: Boolean? = null
        var chovendo: Boolean? = null

    }

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        Log.i(TAG, "Starting MotorAction")

        Log.i(TAG, "passei 408 passei 408 passei 408" + pararum)

        loginFirebase()

        Log.i(TAG, "passei 415 passei 415 passei 415" + pararum)

    }

    fun loginFirebase() {

        val firebase = FirebaseAuth.getInstance()
        firebase.signInAnonymously()
            .addOnSuccessListener {
                Timber.d("Firebase logged in successfully 1001 1001 1001")
//                dbReference = FirebaseDatabase.getInstance().reference
//                FirebaseDatabase.getInstance()
//                    .getReference("/smartdrive-f819f")

                dbReference = FirebaseDatabase.getInstance().reference.child("home")

                Log.i(TAG, "passei 410 passei 410 passei 410" + dbReference)
//                dbReference = FirebaseDatabase.getInstance().reference.child("PararUm")
                observeChanges()
            }
            .addOnFailureListener { Timber.e("Failed to login $it") }

    }

    fun observeChanges() {

        dbReference?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Timber.e("Something has gone wrong: ${error.message}")
            }

            override fun onDataChange(motorValue: DataSnapshot) {
                val motorInformation = motorValue.getValue(ModelMotor::class.java) // Convert the value to the HomeInformation data class
                Timber.d("New value received 1002 1002 1002: $motorInformation")
                motorInformation?.let { // If homeInformation not null
                    pararum = it.PararUm // Set the LED to the light boolean value
                    direcaoum = it.DirecaoUm

                    Log.i(TAG, "passei 409 passei 409 passei 409" + pararum)
                }
            }

        })
    }


//    fun onDestroy() {
//        mSmartDrive?.close()
//        super.onStop()
//    }


}



