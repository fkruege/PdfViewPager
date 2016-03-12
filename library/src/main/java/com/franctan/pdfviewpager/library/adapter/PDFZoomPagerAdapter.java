package com.franctan.pdfviewpager.library.adapter;

import com.franctan.pdfviewpager.library.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by fkruege on 3/8/16.
 */
public class PDFZoomPagerAdapter extends PDFPagerAdapter {

    public PDFZoomPagerAdapter(Context context, String pdfPath, float renderQuality, int offScreenSize) {
        super(context, pdfPath, renderQuality, offScreenSize);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View v = inflater.inflate(R.layout.view_zoomable_pdf_page, container, false);
        ImageViewTouch ivt = (ImageViewTouch) v.findViewById(R.id.imageViewZoom);
        ivt.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);

        if(renderer == null || getCount() < position)
            return v;

        PdfRenderer.Page page = getPDFPage(position);

        Bitmap bitmap = mBitmapPool.getBitmap(position);

        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();

        ivt.setImageBitmap(bitmap);
        ((ViewPager) container).addView(v, 0);

        return v;
    }

}
