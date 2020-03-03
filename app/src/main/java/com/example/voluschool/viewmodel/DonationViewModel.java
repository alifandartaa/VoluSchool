package com.example.voluschool.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.voluschool.model.PostDonation;
import com.example.voluschool.retrofit.ApiClient;
import com.example.voluschool.retrofit.MyApi;

import java.util.ArrayList;

public class DonationViewModel extends ViewModel {
    MyApi myApi;
    private MutableLiveData<ArrayList<PostDonation>> listPostDonation = new MutableLiveData<>();

    public DonationViewModel(){

    }

    public void setListPostDonation(){
        myApi = ApiClient.getClient().create(MyApi.class);

    }
}
