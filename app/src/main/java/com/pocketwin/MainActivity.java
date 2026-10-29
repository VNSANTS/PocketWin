package com.pocketwin;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;

public final class MainActivity extends Activity {
    private final int background = Color.rgb(9, 13, 24);
    private final int panel = Color.rgb(20, 28, 44);
    private final int accent = Color.rgb(64, 190, 255);
    private final int primaryText = Color.rgb(242, 246, 255);
    private final int secondaryText = Color.rgb(163, 176, 198);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(background);
        getWindow().setNavigationBarColor(background);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setBackgroundColor(background);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(22), dp(28), dp(22), dp(28));
        scroll.addView(root);

        TextView eyebrow = text("POCKETWIN  /  ANDROID", 12, accent, true);
        root.addView(eyebrow);

        TextView title = text("Seu ambiente Windows portátil.", 30, primaryText, true);
        LinearLayout.LayoutParams titleParams = params(-1, -2);
        titleParams.topMargin = dp(12);
        root.addView(title, titleParams);

        TextView intro = text(
                "Uma base multiplataforma para pesquisar e integrar runtimes compatíveis com programas Windows.",
                15, secondaryText, false);
        LinearLayout.LayoutParams introParams = params(-1, -2);
        introParams.topMargin = dp(10);
        root.addView(intro, introParams);

        LinearLayout statusCard = card();
        LinearLayout.LayoutParams cardParams = params(-1, -2);
        cardParams.topMargin = dp(26);
        root.addView(statusCard, cardParams);

        statusCard.addView(text("ESTADO DO PROJETO", 12, accent, true));
        statusCard.addView(spacer(10));
        statusCard.addView(text("Interface inicial instalada", 18, primaryText, true));
        statusCard.addView(text(
                "O mecanismo de execução Windows ainda não foi integrado. Esta versão não executa arquivos .exe.",
                14, secondaryText, false), params(-1, -2));

        LinearLayout deviceCard = card();
        LinearLayout.LayoutParams deviceParams = params(-1, -2);
        deviceParams.topMargin = dp(14);
        root.addView(deviceCard, deviceParams);
        deviceCard.addView(text("DIAGNÓSTICO DO DISPOSITIVO", 12, accent, true));
        deviceCard.addView(spacer(10));
        deviceCard.addView(text("Arquitetura: " + supportedAbis(), 15, primaryText, true));
        deviceCard.addView(text("Android: " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")",
                14, secondaryText, false), params(-1, -2));
        deviceCard.addView(text("Modelo: " + Build.MANUFACTURER + " " + Build.MODEL,
                14, secondaryText, false), params(-1, -2));

        Button runtimeButton = new Button(this);
        runtimeButton.setText("Runtime Windows — em desenvolvimento");
        runtimeButton.setAllCaps(false);
        runtimeButton.setEnabled(false);
        LinearLayout.LayoutParams buttonParams = params(-1, -2);
        buttonParams.topMargin = dp(20);
        root.addView(runtimeButton, buttonParams);

        TextView footer = text(
                "Próxima etapa: validar o build automatizado e selecionar um runtime compatível.",
                12, secondaryText, false);
        LinearLayout.LayoutParams footerParams = params(-1, -2);
        footerParams.topMargin = dp(20);
        root.addView(footer, footerParams);

        setContentView(scroll);
    }

    private LinearLayout card() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dp(18), dp(18), dp(18), dp(18));
        layout.setBackgroundColor(panel);
        return layout;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setGravity(Gravity.START);
        view.setIncludeFontPadding(true);
        if (bold) view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return view;
    }

    private View spacer(int heightDp) {
        View view = new View(this);
        view.setLayoutParams(params(1, dp(heightDp)));
        return view;
    }

    private LinearLayout.LayoutParams params(int width, int height) {
        return new LinearLayout.LayoutParams(width, height);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private String supportedAbis() {
        String[] abis = Build.SUPPORTED_ABIS;
        if (abis == null || abis.length == 0) return "não identificada";
        return String.join(", ", abis).toLowerCase(Locale.ROOT);
    }
}
