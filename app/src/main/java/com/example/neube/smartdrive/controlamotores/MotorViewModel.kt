//package com.example.neube.smartdrive.controlamotores
//
//import android.content.ContentValues
//import android.content.ContentValues.TAG
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.*
//import timber.log.Timber
//
//class MotorViewModel : ViewModel() {
//    var authData: MutableLiveData<ModelMotor> = MutableLiveData()
//
//    private var dbReference: DatabaseReference? = null
//
//    companion object {
//        var direcaodois: Int? = null
//        var parardois: Int? = null
//
//        var direcaoum: Int? = null
//        var pararum: Int? = null
//
//        var fcmotordoisb: Int? = null
//        var fcmotordoisa: Int? = null
//
//        var fcmotorumb: Int? = null
//        var fcmotoruma: Int? = null
//
//        var baixatemperatura: Boolean? = null
//        var chovendo: Boolean? = null
//
//    }
//
//
//   // fun getAuthData(): LiveData<ModelMotor> {
//
//    fun getAuthData(){
//
//        Log.i(ContentValues.TAG, "passei 1 no viewmodel")
//
//        val firebase = FirebaseAuth.getInstance()
//        firebase.signInAnonymously()
//            .addOnSuccessListener {
//                Timber.d("Firebase logged in successfully 1001 1001 1001")
//                dbReference = FirebaseDatabase.getInstance().reference.child("home")
//                Log.i(TAG, "passei 410 passei 410 passei 410" + dbReference)
//
//                dbReference?.addValueEventListener(object : ValueEventListener {
//                    override fun onCancelled(error: DatabaseError) {
//                        Timber.e("Something has gone wrong: ${error.message}")
//                    }
//
//                    override fun onDataChange(motorValue: DataSnapshot) {
//                        Log.i(ContentValues.TAG, "passei 2 no viewmodel")
//                        val motorInformation = motorValue.getValue(ModelMotor::class.java)
//                        Timber.d("New value received 1002 1002 1002: $motorInformation")
//                        motorInformation?.let {
//                            Log.i(ContentValues.TAG, "passei 3 no viewmodel")
//                            // If homeInformation not null
//                            pararum = it.PararUm
//                            direcaoum = it.DirecaoUm
//                            authData.postValue(motorInformation)
//
//                            Log.i(TAG, "passei 711 711 711 authData"+authData)
//
//                            Log.i(TAG, "passei 713 713 713 713" + pararum)
//                        }
//                    }
//                }
//                )
//
//            }
//
//            .addOnFailureListener { Timber.e("Failed to login $it") }
//      //  return authData
//    }
//
//}