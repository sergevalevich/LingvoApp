package com.valevich.lingvoapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public void loadImageByResId(int resId, final ImageView imageView) {
        Glide.with(mContext)
                .load(resId)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public void loadRoundedImageByResId(int resId, int placeholderResId, final ImageView imageView) {

        BitmapRequestBuilder builder = Glide.with(mContext)
                .load(resId)
                .asBitmap()
                .thumbnail(0.1f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        if (placeholderResId != -1)
            builder.placeholder(placeholderResId);


        builder.into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}
