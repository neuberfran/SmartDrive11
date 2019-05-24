//package com.example.neube.smartdrive.controlamotores;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.Transformations;
//import androidx.lifecycle.ViewModel;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.function.Function;
//
//public class HotStockViewModel extends ViewModel {
//
//    private static final DatabaseReference HOT_STOCK_REF =
//            FirebaseDatabase.getInstance().getReference("/home");
//
//    // This is a LiveData<DataSnapshot> from part 1
//    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(HOT_STOCK_REF);
//
//    private final LiveData<SmartModel> hotStockLiveData =
//            Transformations.map(liveData, new Deserializer());
//
//    private class Deserializer implements Function<DataSnapshot, SmartModel> {
//        @Override
//        public SmartModel apply(DataSnapshot dataSnapshot) {
//            return dataSnapshot.getValue(SmartModel.class);
//        }
//    }
//
//    @NonNull
//    public LiveData<SmartModel> getHotStockLiveData() {
//        return hotStockLiveData;
//    }
//
//}