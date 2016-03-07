package com.franctan.pdfviewpager.library.util;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fkruege on 3/6/16.
 */
public class SimpleBitmapPool {

    Bitmap[] mBitmapArray;

    private int mPoolSize;

    private int mWidth;

    private int mHeight;

    private Bitmap.Config mConfig;

    public SimpleBitmapPool(int offScreenSize, int width, int height, Bitmap.Config config) {
        mPoolSize = getPoolSize(offScreenSize);
        mWidth = width;
        mHeight = height;
        mConfig = config;
        mBitmapArray = new Bitmap[mPoolSize];
    }

    private int getPoolSize(int offScreenSize) {
        return (offScreenSize) * 2 + 1;

    }

    public Bitmap getBitmap(int position) {
        int index = getIndexFromPosition(position);
        if (mBitmapArray[index] == null) {
            createBitmapAtIndex(index);
        }

        mBitmapArray[index].eraseColor(Color.TRANSPARENT);

        return mBitmapArray[index];
    }

    public void returnBitmap(int position) {
    }

    public void recycleAll() {
        for (int i = 0; i < mPoolSize; i++) {
            if (mBitmapArray[i] != null) {
                mBitmapArray[i].recycle();
                mBitmapArray[i] = null;
            }
        }
    }

    private void createBitmapAtIndex(int index) {
        Bitmap bitmap = Bitmap.createBitmap(mWidth, mHeight, mConfig);
        mBitmapArray[index] = bitmap;
    }

    private int getIndexFromPosition(int position) {
        return  position % mPoolSize;
    }

}
