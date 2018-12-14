package com.a2stars.juroscompostos;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    private double capital = 0.0;
    private int periodo = 0;
    private double juros = 0.0;
    private double acrescimo = 0.0;
    private double montx = 0;
    private TextView montante;
    private String Lingua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lingua = getLingua();

        ImageView iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.a2stars.juroscompostos");
                share.setType("text/plain");
                startActivity(share);
            }
        });

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLeftApplication ()
//            {
//            }
//        });

        montante = (TextView) findViewById(R.id.montante);

        montante.setText(numberFormat.format(montx));
        update();

        EditText capinicial = (EditText) findViewById(R.id.capinicial);
        capinicial.addTextChangedListener(capinicialWatcher);

        EditText txjuros = (EditText) findViewById(R.id.txjuros);
        txjuros.addTextChangedListener(txjurosWatcher);

        EditText periodo = (EditText) findViewById(R.id.periodo);
        periodo.addTextChangedListener(periodoWatcher);

        EditText mensal = (EditText) findViewById(R.id.mensal);
        mensal.addTextChangedListener(mensalWatcher);


    }


    private void update() {
        double cap = capital;
        int per = periodo;
        double jur = juros/100;
        double acres = acrescimo;
        double mont;

        mont = cap*(1+jur)+acres;
        if(per == 0){
            mont = 0.0;
        }else {
            for(int i =0;i<per-1;i++){
                mont = mont*(1+jur)+acres;
            }
        }

        if(Lingua.contains("português")){
            montante.setText(numberFormat.format(mont)+"\nRendimento: " + numberFormat.format(mont-(cap+(acres*per))));
        } else {
            montante.setText(numberFormat.format(mont)+"\n"+ getResources().getString(R.string.rendimento) + " " + numberFormat.format(mont-(cap+(acres*per))));
        }
    }

    private TextWatcher capinicialWatcher = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try
            {
                capital = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e)
            {
                capital = 0.0;
            }
            update();
        }

        @Override
        public void afterTextChanged(Editable s)
        {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }
    };

    private TextWatcher txjurosWatcher = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try
            {
                juros = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e)
            {
                juros = 0.0;
            }
            update();
        }

        @Override
        public void afterTextChanged(Editable s)
        {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }
    };

    private TextWatcher periodoWatcher = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try
            {
                periodo = Integer.parseInt(s.toString());
            }
            catch (NumberFormatException e)
            {
                periodo = 0;
            }
            update();
        }

        @Override
        public void afterTextChanged(Editable s)
        {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }
    };
    private TextWatcher mensalWatcher = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            try
            {
                acrescimo = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e)
            {
                acrescimo = 0.0;
            }
            update();
        }

        @Override
        public void afterTextChanged(Editable s)
        {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }
    };

    public String getLingua(){
        Context context = getApplicationContext();
        PackageManager packageManager = context.getPackageManager();
        Resources resources = null;
        try {
            resources = packageManager.getResourcesForApplication("android");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final String language = resources.getConfiguration().locale.getDisplayLanguage();
        return language;
    }
}
