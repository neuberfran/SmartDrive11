//package com.example.neube.smartdrive.controlamotores
//
//import android.arch.lifecycle.LiveData
//import android.arch.lifecycle.MutableLiveData
//import android.arch.lifecycle.ViewModel
//import com.google.firebase.database.*
//
//class MotorViewModel : ViewModel() {
//    var authData: MutableLiveData<ModelMotor> = MutableLiveData()
//    private var mDatabase: DatabaseReference? = null
//
//    fun getAuthData(): LiveData<ModelMotor> {
//        mDatabase = FirebaseDatabase.getInstance().reference
//        FirebaseDatabase.getInstance()
//            .getReference("/smartdrive-f819f")
//            .addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onCancelled(p0: DatabaseError) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        var spotifyAuthData: ModelMotor? =
//                            dataSnapshot.getValue<ModelMotor>(ModelMotor::class.java)
//                        authData.postValue(spotifyAuthData)
//                    }
//                }
//            }
//        )
//
//        return authData
//
//    }
//}