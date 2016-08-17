package com.valevich.lingvoapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.valevich.lingvoapp.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class ImageLoader {

    @RootContext
    Context mContext;

    public void loadImageByUrl(String imageUrl, ImageView imageView) {
        Glide.with(mContext)
                .load(imageUrl)
                .thumbnail(0.1f)
                .centerCrop()
                .placeholder(R.drawable.shark)
                .crossFade()
                .into(imageView);
    }
}
