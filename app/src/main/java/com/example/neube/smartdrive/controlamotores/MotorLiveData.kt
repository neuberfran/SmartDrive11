//package com.example.neube.smartdrive.controlamotores
//
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.ValueEventListener
//import com.google.firebase.database.DatabaseReference
//import android.arch.lifecycle.LiveData
//import android.util.Log
//
//
//class MotorLiveData : LiveData<DataSnapshot> {
//
//    private val query: Query
//    private val listener = MyValueEventListener()
//
//    constructor(query: Query) {
//        this.query = query
//    }
//
//    constructor(ref: DatabaseReference) {
//        this.query = ref
//    }
//
//    override fun onActive() {
//        Log.d(LOG_TAG, "onActive")
//        query.addValueEventListener(listener)
//    }
//
//    override fun onInactive() {
//        Log.d(LOG_TAG, "onInactive")
//        query.removeEventListener(listener)
//    }
//
//    private inner class MyValueEventListener : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            setValue(dataSnapshot)
//        }
//
//        override fun onCancelled(databaseError: DatabaseError) {
//            Log.e(LOG_TAG, "Can't listen to query $query", databaseError.toException())
//        }
//    }
//
//    companion object {
//        private val LOG_TAG = "FirebaseQueryLiveData"
//    }
//}