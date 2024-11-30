package com.example.localizationapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void attachBaseContext(Context newBase) {
        preferences = newBase.getSharedPreferences("settings", MODE_PRIVATE);
        String localeTag = preferences.getString("locale", Locale.ENGLISH.toLanguageTag());
        Locale locale = Locale.forLanguageTag(localeTag);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        super.attachBaseContext(newBase.createConfigurationContext(config));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String localeTag = null;

        if (item.getItemId() == R.id.locale_english) {
            localeTag = Locale.ENGLISH.toLanguageTag();
        } else if (item.getItemId() == R.id.locale_ukrainian) {
            localeTag = Locale.forLanguageTag("uk").toLanguageTag();
        } else if (item.getItemId() == R.id.locale_spanish) {
            localeTag = Locale.forLanguageTag("es").toLanguageTag();
        }

        if (localeTag != null) {
            preferences.edit().putString("locale", localeTag).apply();
            recreate();
        }

        return super.onOptionsItemSelected(item);
    }
}
