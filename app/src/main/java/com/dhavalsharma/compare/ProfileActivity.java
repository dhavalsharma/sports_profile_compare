package com.dhavalsharma.compare;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private PieChart mChart;
    private Typeface mTfLight;
    protected String[] mWinLossLabels = new String[] {
            "Win", "Loss"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        TextView playerName = (TextView) findViewById(R.id.playerName);
        playerName.setText(name);
        //set the drawable from intent
        int drawableId = intent.getExtras().getInt("drawableId");
        ImageView imageView = (ImageView) findViewById(R.id.app_bar_image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        imageView.setImageBitmap(bitmap);

        //get chart
        setupChart();
    }

    private void setupChart() {
        mChart = (PieChart) findViewById(R.id.chartWinLoss);
        mChart.setUsePercentValues(true);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);
        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(48f);
        mChart.setTransparentCircleRadius(51f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);
        // add a selection listener
        //mChart.setOnChartValueSelectedListener(this);

        setData(2, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
    }

    /*
    helper to set pie chart center text
     */
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("20\nTotal");

        int len = 0;
        int len_to = len + 2;
        s.setSpan(new RelativeSizeSpan(3.7f), len, len_to, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), len, len_to , 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), len, len_to, 0);

        len = 3;
        s.setSpan(new RelativeSizeSpan(1.8f), len, s.length() , 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), len, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), len, s.length() , 0);

        return s;
    }
    /*
    set data for chart
     */
    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array
        // determines their position around the center of
        // the chart.
        PieEntry win = new PieEntry(60, mWinLossLabels[0]);
        entries.add(win);
        PieEntry loss = new PieEntry(40, mWinLossLabels[1]);
        entries.add(loss);

        PieDataSet dataSet = new PieDataSet(entries, "Win/Loss Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);


        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(21f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }
}
