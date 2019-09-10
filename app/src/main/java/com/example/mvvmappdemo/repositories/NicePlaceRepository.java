package com.example.mvvmappdemo.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmappdemo.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

/**
 * This repository class helps to make db query, api calls to servers. The data that this class receives will be sent to ViewModel class
 *  Singleton pattern ; because you only want to create one instance so that you won't a bunch of instances of database objects and caches.
 */
public class NicePlaceRepository {

    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();


    public static NicePlaceRepository getInstance(){
        if(instance ==  null){
            instance = new NicePlaceRepository();
        }
        return instance;
    }

    // this is the method where you would want to make db query or make api calls to get the data
    public MutableLiveData<List<NicePlace>> getNicePlaces(){
        setNicePlaces();

        MutableLiveData<List<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces(){
        dataSet.add(
                new NicePlace("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/tpsnoz5bzo501.jpg",
                        "Trondheim")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/qn7f9oqu7o501.jpg",
                        "Portugal")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/j6myfqglup501.jpg",
                        "Rocky Mountain National Park")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/0h2gm1ix6p501.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/k98uzl68eh501.jpg",
                        "Mahahual")
        );
        dataSet.add(
                new NicePlace("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                        "Frozen Lake")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/obx4zydshg601.jpg",
                        "Austrailia")
        );
    }

}
