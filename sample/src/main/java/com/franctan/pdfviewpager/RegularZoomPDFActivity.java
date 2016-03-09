package com.franctan.pdfviewpager;

import com.franctan.pdfviewpager.library.adapter.PDFZoomPagerAdapter;
import com.franctan.pdfviewpager.library.view.PDFViewPager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import es.voghdev.pdfviewpager.R;

/**
 * Created by fkruege on 3/6/16.
 */
public class RegularZoomPDFActivity extends AppCompatActivity{

    @Bind(R.id.pdfViewPager)
    PDFViewPager mPDFViewPager;

    private PDFZoomPagerAdapter mPdfPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_pdf);
        ButterKnife.bind(this);

        String pdfPath = getExternalCacheDir() + "/" + Constants.MOBY_PDF;

        mPdfPagerAdapter = new PDFZoomPagerAdapter(this, pdfPath, 2.0f, mPDFViewPager.getOffscreenPageLimit() );
        mPDFViewPager.setAdapter(mPdfPagerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPdfPagerAdapter.cleanup();
    }
}
