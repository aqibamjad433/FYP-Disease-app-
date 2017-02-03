
package me.Epidamic.newsfeed.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import me.Epidamic.rssreader.R;

/**
 * This fragment is the one which controls the app settings
 */
public class SettingsFragment extends PreferenceFragment{
    @Override
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.app_prefs);
    }
}
