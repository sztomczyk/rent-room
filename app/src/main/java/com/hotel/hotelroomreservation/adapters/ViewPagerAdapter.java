package com.hotel.hotelroomreservation.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hotel.hotelroomreservation.App;
import com.hotel.hotelroomreservation.R;
import com.hotel.hotelroomreservation.imageloader.DoubleCache;
import com.hotel.hotelroomreservation.imageloader.ImageLoader;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private final List<String> imageUrls;
    private final LayoutInflater inflater;
    private final ImageLoader imageLoader;

    public ViewPagerAdapter(final Context context, final List<String> imageUrlsList) {
        this.imageUrls = imageUrlsList;
        inflater = LayoutInflater.from(context);

        imageLoader = new ImageLoader();
        imageLoader.setMemoryCache(new DoubleCache(App.getContext()));
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(final ViewGroup view, final int position) {
        final View imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false);
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
        imageLoader.displayImage(imageUrls.get(position), imageView);

        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }
}
