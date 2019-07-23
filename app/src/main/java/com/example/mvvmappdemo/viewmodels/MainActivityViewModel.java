package com.example.mvvmappdemo.viewmodels;

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
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();


    public void init(){
        if(mNicePlaces != null){
            return;
        }

        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();

    }

    public void addNewValues(final NicePlace nicePlace){
        mIsUpdating.setValue(true);

        // bad practice , will leak memory
        new AsyncTask<Void, Void,Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
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
