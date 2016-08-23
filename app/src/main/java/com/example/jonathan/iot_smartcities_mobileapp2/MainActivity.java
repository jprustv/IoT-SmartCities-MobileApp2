package com.example.jonathan.iot_smartcities_mobileapp2;


import android.content.Context;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String debugPrefix = "<Main> ";
    private PIPEList PIPEList = new PIPEList();
    private boolean statisticsRefreshed = false;

    public static Context context;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jonathan.iot_smartcities_mobileapp2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jonathan.iot_smartcities_mobileapp2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show RealTimeDataFragment
                    return RealTimeDataFragment.newInstance(0, "Níveis neste momento", "t0");
                case 1: // Fragment # 0 - This will show RealTimeDataFragment but different title
                    return StatisticsFragment.newInstance(1, "Estatísticas", "t1");
                case 2: // I can change to another fragment !
                    return tbdFragment.newInstance(2, "#Page #2", "t2");
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return context.getString(R.string.page0);
                case 1:
                    return context.getString(R.string.page1);
                case 2:
                    return context.getString(R.string.page2);
                default:
                    return null;
            }
        }

    }

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        context = getApplicationContext();
        updateThread();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
      //  StatisticsModel statisticsModel = new StatisticsModel(this);

    }


    private void updateThread() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {

                                PIPEList.getAllData();
                                PIPEInstance pipe1 = PIPEList.getPIPEList().get(PIPEList.getPIPEList().size()-1);

                                double temperatura = pipe1.getTemperatura();
                                double amonia = pipe1.getAmonia();
                                double co2 = pipe1.getCo2();
                                double nitrito = pipe1.getNitrito();
                                double oxigenio = pipe1.getOxigenioDissolvido();
                                double ph = pipe1.getPh();
                                double salinidade = pipe1.getSalinidade();
                                double solidos = pipe1.getSolidosSuspensos();

                                PIPEBoundaries boundaries = new PIPEBoundaries(pipe1);

                                TextView txtTemperatura = (TextView) findViewById(R.id.section_tempperatura_txt);
                                txtTemperatura.setText(Double.toString(temperatura));
                                txtTemperatura.setTextColor(boundaries.applyColor("temperatura", temperatura));

                                TextView txtAmonia = (TextView) findViewById(R.id.section_amonia_txt);
                                txtAmonia.setText(Double.toString(amonia));
                                txtAmonia.setTextColor(boundaries.applyColor("amonia", amonia));

                                TextView txtCO2 = (TextView) findViewById(R.id.section_co2_txt);
                                txtCO2.setText(Double.toString(co2));
                                txtCO2.setTextColor(boundaries.applyColor("co2", co2));

                                TextView txtNitrito = (TextView) findViewById(R.id.section_nitrito_txt);
                                txtNitrito.setText(Double.toString(nitrito));
                                txtNitrito.setTextColor(boundaries.applyColor("nitrito", nitrito));

                                TextView txtPH = (TextView) findViewById(R.id.section_ph_txt);
                                txtPH.setText(Double.toString(ph));
                                txtPH.setTextColor(boundaries.applyColor("ph", ph));

                                TextView txtSalinidade = (TextView) findViewById(R.id.section_salinidade_txt);
                                txtSalinidade.setText(Double.toString(salinidade));
                                txtSalinidade.setTextColor(boundaries.applyColor("salinidade", salinidade));

                                TextView txtSolidos = (TextView) findViewById(R.id.section_solidos_txt);
                                txtSolidos.setText(Double.toString(solidos));
                                txtSolidos.setTextColor(boundaries.applyColor("solidos", solidos));

                                TextView txtOxigenio = (TextView) findViewById(R.id.section_oxigenio_txt);
                                txtOxigenio.setText(Double.toString(oxigenio));
                                txtOxigenio.setTextColor(boundaries.applyColor("oxigenio", oxigenio));

                                TextView txtPorcentagem = (TextView) findViewById(R.id.section_porcentagem);
                                txtPorcentagem.setText(Integer.toString(boundaries.getPorcentagem()) + " %");
                                txtPorcentagem.setTextColor(boundaries.applyColor("porcentagem", boundaries.getPorcentagem()));

                                // Page 2 - Statistics

                                if (!statisticsRefreshed){
                                    LineChart chart = (LineChart) findViewById(R.id.chart);
                                    LineDataSet dataSet = createDataSet("Oxigenio", Color.BLUE, chart);
                                    LineData lineData = new LineData();
                                    lineData.addDataSet(dataSet);
                                    chart.setData(lineData);
                                    chart.invalidate();
                                    statisticsRefreshed = true;
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public LineDataSet createDataSet(String parametro, int color, LineChart chart){
        List<Entry> entries = new ArrayList<Entry>();
        for (PIPEInstance data : PIPEList.getPIPEList()){
            entries.add(new Entry(data.getPosition(), (float) data.getSpecificParameter(parametro)));
        }
        LineDataSet dataSet = new LineDataSet(entries, "");
        dataSet.setColor(color);
        dataSet.setValueTextColor(color);
        dataSet.setHighlightEnabled(true);
        dataSet.setDrawVerticalHighlightIndicator(false);
      //  dataSet.setValueFormatter(new MGLFormatter());
        dataSet.setValueTextSize(16f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setLineWidth(5f);
        dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSet.setDrawFilled(true);

        chart.setDescriptionTextSize(16f);
        chart.setDescription(parametro.toUpperCase());
        return dataSet;
    }

}
