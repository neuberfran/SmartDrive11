package com.example.neube.smartdrive.controlamotores

import android.content.ContentValues
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.arch.core.util.Function

class SmartViewModel : ViewModel() {

    // This is a LiveData<DataSnapshot> from part 1
    private val liveData = FirebaseQueryLiveData(HOT_STOCK_REF)

    @get:NonNull
    val hotStockLiveData =
        Transformations.map(liveData, Deserializer() as androidx.arch.core.util.Function<DataSnapshot, SmartModel>)

    private inner class Deserializer : Function<DataSnapshot, SmartModel> {
        override fun apply(dataSnapshot: DataSnapshot): SmartModel? {

            Log.i(ContentValues.TAG, "Volto K101 K101 K101")

            return dataSnapshot.getValue<SmartModel>(SmartModel::class.java)
        }
    }

    companion object {

        private val HOT_STOCK_REF = FirebaseDatabase.getInstance().getReference("/smartmodel")
    }

}