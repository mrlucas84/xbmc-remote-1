package ch.countableset.android.xbmcremote;

import android.app.Activity;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsActivity extends Activity {
    public static final String IP_ADDRESS = "ip_address";
    public static final String PORT_NUMBER = "port";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefsFragment()).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {

        private static final String PATTERN =
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            EditTextPreference ip_address = (EditTextPreference) getPreferenceScreen().findPreference(IP_ADDRESS);
            ip_address.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(Preference preference, Object value) {
                    boolean validate = false;
                    if(ipAddressValid((String) value)) {
                        validate = true;
                    }
                    return validate;
                }

            });
        }

        public boolean ipAddressValid(String value) {
            Pattern pattern = Pattern.compile(PATTERN);
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
    }
}
