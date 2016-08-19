package com.example.jonathan.iot_smartcities_mobileapp2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String debugPrefix = "<Main> ";
    PIPEList PIPEList = new PIPEList();

    public static Context context;

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public int getCount(){
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0: // Fragment # 0 - This will show RealTimeDataFragment
                    return RealTimeDataFragment.newInstance(0, "Page # 0", "t0");
                case 1: // Fragment # 0 - This will show RealTimeDataFragment but different title
                    return StatisticsFragment.newInstance(1, "Page # 1", "t1");
                case 2: // I can change to another fragment !
                    return tbdFragment.newInstance(2, "#Page #2", "t2");
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle (int position){
            switch (position){
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
    }

    private void updateThread() {
        final android.os.Handler handler = new android.os.Handler();
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String ts1 = "110820161131"; // Test1 : Valores dentro do limite
                            String ts2 = "110820161145"; // Test2 : Alguns valores fora do limite
                            PIPEList.getAllData();
                            PIPEInstance pipe1 = PIPEList.getPipeAtTime(ts1);

                            double temperatura = pipe1.getTemperatura();
                            double amonia = pipe1.getAmonia();
                            double co2 = pipe1.getCo2();
                            double nitrito = pipe1.getNitrito();
                            double oxigenio = pipe1.getOxigenioDissolvido();
                            double ph = pipe1.getPh();
                            double salinidade = pipe1.getSalinidade();
                            double solidos = pipe1.getSolidosSuspensos();

                            try {
                                PIPEBoundaries boundaries = new PIPEBoundaries(pipe1);

                                TextView txtTemperatura = (TextView) findViewById(R.id.section_tempperatura_txt);
                                txtTemperatura.setText (Double.toString(temperatura));
                                txtTemperatura.setTextColor(boundaries.applyColor("temperatura", temperatura));

                                TextView txtAmonia = (TextView) findViewById(R.id.section_amonia_txt);
                                txtAmonia.setText (Double.toString(amonia));
                                txtAmonia.setTextColor(boundaries.applyColor("amonia", amonia));

                                TextView txtCO2 = (TextView) findViewById(R.id.section_co2_txt);
                                txtCO2.setText (Double.toString(co2));
                                txtCO2.setTextColor(boundaries.applyColor("co2", co2));

                                TextView txtNitrito = (TextView) findViewById(R.id.section_nitrito_txt);
                                txtNitrito.setText (Double.toString(nitrito));
                                txtNitrito.setTextColor(boundaries.applyColor("nitrito", nitrito));

                                TextView txtPH = (TextView) findViewById(R.id.section_ph_txt);
                                txtPH.setText (Double.toString(ph));
                                txtPH.setTextColor(boundaries.applyColor("ph", ph));

                                TextView txtSalinidade = (TextView) findViewById(R.id.section_salinidade_txt);
                                txtSalinidade.setText (Double.toString(salinidade));
                                txtSalinidade.setTextColor(boundaries.applyColor("salinidade", salinidade));

                                TextView txtSolidos = (TextView) findViewById(R.id.section_solidos_txt);
                                txtSolidos.setText (Double.toString(solidos));
                                txtSolidos.setTextColor(boundaries.applyColor("solidos", solidos));

                                TextView txtOxigenio = (TextView) findViewById(R.id.section_oxigenio_txt);
                                txtOxigenio.setText (Double.toString(oxigenio));
                                txtOxigenio.setTextColor(boundaries.applyColor("oxigenio", oxigenio));

                                TextView txtPorcentagem = (TextView) findViewById(R.id.section_porcentagem);
                                txtPorcentagem.setText(Integer.toString(boundaries.getPorcentagem()) + " %");
                                txtPorcentagem.setTextColor(boundaries.applyColor("porcentagem", boundaries.getPorcentagem()));

                            } catch( Exception e){
                                e.printStackTrace();
                            }
                            //    TextView txtTemperatura = (TextView) findViewById(R.id.section_tempperatura_txt);
                            //   txtTemperatura.setText(temperatura);
                        }
                    });
                    try {
                        Thread.sleep(4000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

}
