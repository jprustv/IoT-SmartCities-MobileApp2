package com.example.jonathan.iot_smartcities_mobileapp2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 23/08/2016.
 */
public class StatisticsModel {

        PIPEList PIPEList = new PIPEList();

    StatisticsModel(Activity mainActivity){
        try {

            LineChart chart = (LineChart) mainActivity.findViewById(R.id.chart);

            LineDataSet ds_temperatura = createDataSet("Temperatura", Color.RED, chart);

            LineData lineData = new LineData();
            lineData.addDataSet(ds_temperatura);

            chart.setData(lineData);
            chart.invalidate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public LineDataSet createDataSet(String parametro, int color, LineChart chart){
        List<Entry> entries = new ArrayList<Entry>();
        for (PIPEInstance data : PIPEList.getPIPEList()){
            entries.add(new Entry(data.getPosition(), (float) data.getSpecificParameter(parametro)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(color);
        dataSet.setValueTextColor(color);
        chart.setDescriptionTextSize(30);
        chart.setDescription(parametro);
        return dataSet;
    }
}
