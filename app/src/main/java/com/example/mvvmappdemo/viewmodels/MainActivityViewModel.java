package com.example.mvvmappdemo.viewmodels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmappdemo.models.NicePlace;
import com.example.mvvmappdemo.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    /**
     *
     *     LiveData object is an observable data-holder class. It is responsible for holding the data that's displayed
     *     in the view and the data is observable. Meaning that the data is actively being watched for changes.
     *     If a change occurs, the view is updated automatically.
     *
     *     MutuableLiveData is a subclass of LiveData ( MutuableLiveData extends LiveData)
     *     MutuableLiveData is mutable whereas LiveData isn't.
     *
     *     LiveData cannot be changed directly but it can be changed indirectly via MutableLiveData
      */
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    // Instantiate our repository
    private NicePlaceRepository mRepo;
    // determine when a query is getting made in our database or api.
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    // init repo and get the mutable live data list from the repo
    public void init(){
        if(mNicePlaces != null){
            return;
        }

        mRepo = NicePlaceRepository.getInstance();
        // get mutablelive data list from the repo
        mNicePlaces = mRepo.getNicePlaces();

    }

    // it will add another new item to our mutablelivedata list
    @SuppressLint("StaticFieldLeak")
    public void addNewValues(final NicePlace nicePlace){
        mIsUpdating.setValue(true);

        // bad practice , will leak memory
        new AsyncTask<Void, Void,Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                // add a new item into our existing list
                List<NicePlace> currentPlaces = mNicePlaces.getValue();
                currentPlaces.add(nicePlace);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);

            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }.execute();

    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }

    public LiveData<List<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }


}
