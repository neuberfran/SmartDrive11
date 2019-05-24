package com.example.neube.smartdrive.controlamotores

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.function.Function

class SmartViewModel : ViewModel() {

    // This is a LiveData<DataSnapshot> from part 1
    private val liveData = FirebaseQueryLiveData(HOT_STOCK_REF)

    var hotStockLiveData = Transformations.map(liveData) { Deserializer()!!}

    inner class Deserializer : Function<DataSnapshot, SmartModel?> {

        override fun apply(dataSnapshot: DataSnapshot): SmartModel? {
            return dataSnapshot.getValue(SmartModel::class.java)
        }
    }

    companion object {

        private val HOT_STOCK_REF = FirebaseDatabase.getInstance().getReference("/home")
    }

}