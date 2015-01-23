package info.jameskerr.textdrive;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditReplyMessageActivity extends ActionBarActivity {

	private AutoReply auto_reply;
	private EditText edit_reply_message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_reply_message);
		
		auto_reply         = AutoReply.getInstance();
		edit_reply_message = (EditText) findViewById(R.id.edit_reply_message);
		edit_reply_message.setText(auto_reply.currentMessage());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_reply_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.reply_message_shadow) {
			String new_message = edit_reply_message.getText().toString();
			auto_reply.setCurrentMessage(new_message);
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("pref_message", new_message);
			editor.commit();
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
