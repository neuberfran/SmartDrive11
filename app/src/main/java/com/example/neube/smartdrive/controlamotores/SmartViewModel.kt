package com.example.neube.smartdrive.controlamotores

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.lifecycle.LiveData
import androidx.annotation.NonNull
import androidx.lifecycle.Transformations

class SmartViewModel : ViewModel() {

    // This is a LiveData<DataSnapshot> from part 1
    private val liveData = FirebaseQueryLiveData(HOT_STOCK_REF)

    val smartStockLiveData: LiveData<SmartModel> = Transformations.map(liveData, Deserializer())

    private inner class Deserializer : Function<DataSnapshot, SmartModel> {
        fun apply(dataSnapshot: DataSnapshot): SmartModel {
            return dataSnapshot.getValue(SmartModel::class.java)
        }
    }

    companion object {
        private val HOT_STOCK_REF = FirebaseDatabase.getInstance().getReference("/home")
    }

}
