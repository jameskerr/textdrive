package info.jameskerr.textdrive;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;


public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	private AutoReply auto_reply = AutoReply.getInstance();
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_general);
	}
	
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
        .registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	protected void onPause() {
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);
	}
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("pref_eta")) {
        	auto_reply.setIncludeEta(sharedPreferences.getBoolean(key, false));
        	Log.d("settings eta", "Changing eta");
        } else if (key.equals("pref_signature")) {
        	auto_reply.setIncludeSignature(sharedPreferences.getBoolean(key, true));
        	Log.d("settings eta", "Changing signature");
        }
	}
}
