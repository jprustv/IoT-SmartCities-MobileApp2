package com.example.jonathan.iot_smartcities_mobileapp2;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by Jonathan on 23/08/2016.
 */
public class MGLFormatter implements ValueFormatter {

    private DecimalFormat mFormat;

    public MGLFormatter() {
        mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
        return mFormat.format(value) + " mg/L"; // e.g. append a dollar-sign
    }
}