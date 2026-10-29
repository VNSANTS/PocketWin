package com.pocketwin;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
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

    private long totalRamBytes;
    private long availableRamBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(background);
        getWindow().setNavigationBarColor(background);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }

        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            totalRamBytes = memoryInfo.totalMem;
            availableRamBytes = memoryInfo.availMem;
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
        deviceCard.addView(text("RAM total: " + formatBytes(totalRamBytes),
                14, secondaryText, false), params(-1, -2));
        deviceCard.addView(text("RAM disponível no sistema: " + formatBytes(availableRamBytes),
                14, secondaryText, false), params(-1, -2));

        LinearLayout processCard = card();
        LinearLayout.LayoutParams processParams = params(-1, -2);
        processParams.topMargin = dp(14);
        root.addView(processCard, processParams);
        processCard.addView(text("GERENCIAMENTO DE PROCESSOS", 12, accent, true));
        processCard.addView(spacer(10));
        processCard.addView(text("Liberar RAM", 18, primaryText, true));
        processCard.addView(text(
                "Quando o runtime Windows estiver integrado, o PocketWin poderá listar os processos do ambiente, selecionar em lote os processos de aplicativos e preservar os processos essenciais do Windows. Nenhum processo será encerrado nesta versão.",
                14, secondaryText, false), params(-1, -2));
        Button processButton = new Button(this);
        processButton.setText("Gerenciador de processos — em desenvolvimento");
        processButton.setAllCaps(false);
        processButton.setEnabled(false);
        LinearLayout.LayoutParams processButtonParams = params(-1, -2);
        processButtonParams.topMargin = dp(12);
        processCard.addView(processButton, processButtonParams);

        Button exportButton = new Button(this);
        exportButton.setText("Exportar diagnóstico");
        exportButton.setAllCaps(false);
        LinearLayout.LayoutParams exportParams = params(-1, -2);
        exportParams.topMargin = dp(16);
        root.addView(exportButton, exportParams);
        exportButton.setOnClickListener(view -> shareDiagnosticReport());

        Button runtimeButton = new Button(this);
        runtimeButton.setText("Runtime Windows — em desenvolvimento");
        runtimeButton.setAllCaps(false);
        runtimeButton.setEnabled(false);
        LinearLayout.LayoutParams buttonParams = params(-1, -2);
        buttonParams.topMargin = dp(12);
        root.addView(runtimeButton, buttonParams);

        TextView footer = text(
                "Próxima etapa: validar este diagnóstico no dispositivo e selecionar um runtime compatível.",
                12, secondaryText, false);
        LinearLayout.LayoutParams footerParams = params(-1, -2);
        footerParams.topMargin = dp(20);
        root.addView(footer, footerParams);

        setContentView(scroll);
    }

    private void shareDiagnosticReport() {
        String report = "Relatório de diagnóstico — PocketWin\n"
                + "Fabricante/modelo: " + Build.MANUFACTURER + " " + Build.MODEL + "\n"
                + "Android: " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")\n"
                + "ABIs suportadas: " + supportedAbis() + "\n"
                + "RAM total reportada: " + formatBytes(totalRamBytes) + "\n"
                + "RAM disponível no sistema: " + formatBytes(availableRamBytes) + "\n"
                + "Runtime Windows: não integrado; execução de EXE não disponível nesta versão.\n";
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Diagnóstico PocketWin");
        sendIntent.putExtra(Intent.EXTRA_TEXT, report);
        startActivity(Intent.createChooser(sendIntent, "Compartilhar diagnóstico"));
    }

    private String formatBytes(long bytes) {
        if (bytes <= 0) return "indisponível";
        double gib = bytes / (1024.0 * 1024.0 * 1024.0);
        return String.format(Locale.ROOT, "%.2f GiB", gib);
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
