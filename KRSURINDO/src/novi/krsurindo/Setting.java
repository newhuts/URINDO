package novi.krsurindo;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.adapter.SessionManager;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends Activity{

	EditText passlama, passbaru, passbarubaru;
	Button change;
	String npm, pl, pb, pbb;
	SessionManager session;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.ic_ab_back_holo_dark_am);
	    
	    session = new SessionManager(this);
		HashMap<String,String>user = session.getUserDetails();
        npm = user.get(SessionManager.KEY_NPM);
        
	    passlama = (EditText)findViewById(R.id.passlama);
	    passbaru = (EditText)findViewById(R.id.passbaru);
	    passbarubaru = (EditText)findViewById(R.id.passbarubaru);
	    change = (Button)findViewById(R.id.changepass);
	    change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pl = passlama.getText().toString();
				pb = passbaru.getText().toString();
				pbb = passbarubaru.getText().toString();
				if (pl.equals("") || pb.equals("") || pbb.equals("")) {
					Toast.makeText(getApplicationContext(), "Field masih ada yang kosong", Toast.LENGTH_LONG).show();
				}else{
					if (!pb.equals(""+pbb)) {
						Toast.makeText(getApplicationContext(), "password baru tidak cocok", Toast.LENGTH_LONG).show();
					}else{
						Load();
					}
				}
				
			}
		});
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
            finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void Load(){
		new MHSHelper(new FragmentCallback() {
			
			@Override
			public void onTaskDone(String result) {
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("mahasiswa");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							String npm = c.getString("password");
							if (pl.equals(npm)) {
								updatepass();
							}
						}
					}else{
						Toast.makeText(getApplicationContext(), "password lama tidak benar !", Toast.LENGTH_LONG).show();
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				
			}
		}).execute(MHSHelper.url_login, npm, pl);
	}
	
	private void updatepass(){
		new MHSHelper(new FragmentCallback() {
			
			@Override
			public void onTaskDone(String result) {
				System.out.println(""+result);
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						Toast.makeText(getApplicationContext(), "password berhasil di ubah :D ", Toast.LENGTH_LONG).show();
						passlama.setText("");
						passbaru.setText("");
						passbarubaru.setText("");
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}).execute(MHSHelper.url_updatepass, npm, pl, pb);
	}
}
