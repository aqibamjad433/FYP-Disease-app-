
package me.Epidamic.newsfeed.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import me.Epidamic.newsfeed.fragments.SettingsFragment;
import me.Epidamic.rssreader.R;

/**
 * This activity holds a PreferenceFragment
 */
public class SettingsActivity extends ActionBarActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_layout);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction().replace(R.id.content, new SettingsFragment()).commit();
    }
}
