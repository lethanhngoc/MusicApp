package com.android.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.adapter.BannerAdapter;
import com.android.mainapp.R;
import com.android.model.Advertisement;
import com.android.service.APIService;
import com.android.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBanner extends Fragment {

    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner, container, false);
        mapping();
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Advertisement>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Advertisement>>() {
            @Override
            public void onResponse(Call<List<Advertisement>> call, Response<List<Advertisement>> response) {
                ArrayList<Advertisement> banners = (ArrayList<Advertisement>) response.body();
//                Log.d("BBB",banners.get(0).getTenBaiHat());
                bannerAdapter = new BannerAdapter(getContext(), banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getAdapter() != null) {
                            currentItem = viewPager.getCurrentItem();
                            currentItem++;
                            if (currentItem >= viewPager.getAdapter().getCount()) {
                                currentItem = 0;
                            }
                            viewPager.setCurrentItem(currentItem, true);
                            handler.postDelayed(runnable, 4500);
                        }
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<Advertisement>> call, Throwable t) {

            }
        });
    }

    private void mapping() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }

}
