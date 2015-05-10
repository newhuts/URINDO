package novi.krsurindo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.adapter.SessionManager;
import novi.krsurindo.helpers.ConnectionDetector;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{

	Boolean isInternet = false;
	ConnectionDetector cd;
	SessionManager session;
	EditText txtUsername, txtPassword;
	Button btnLogin;
	JSONArray mahasiswa = null;
	JSONParser jParser = new JSONParser();
	ProgressDialog pDialog ;
	String cuser, cpass;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		cd = new ConnectionDetector(getApplicationContext());
		txtUsername = (EditText)findViewById(R.id.txtuser);
		txtPassword = (EditText)findViewById(R.id.txtpass);
		isInternet = cd.Jikaterkoneksi();
		if (isInternet) {
		}else{
			Toast.makeText(getApplicationContext(), "Tidak ada koneksi", Toast.LENGTH_LONG).show();
		}
		
		
		session = new SessionManager(getApplicationContext());
		btnLogin = (Button)findViewById(R.id.btnlogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				cuser = txtUsername.getText().toString();
				cpass = txtPassword.getText().toString();
				
				if (cuser.matches("") || cpass.matches("")) {
					Toast.makeText(getApplicationContext(), "Field belum terisi", Toast.LENGTH_LONG).show();
				}else {
					if (isInternet) {
						Load();	
					}else{
						Toast.makeText(getApplicationContext(), "Tidak ada koneksi", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}
	private void Load(){
		new MHSHelper(new FragmentCallback() {
			
			@Override
			public void onTaskDone(String result) {
				System.out.println(result);
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("mahasiswa");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							String npm = c.getString("npm");
							String nama = c.getString("nama");
							String akses = c.getString("akses");
							session.createLoginSession(npm, nama, akses);
							Intent is = new Intent(Login.this, DashBoard.class);
							startActivity(is);
							Login.this.finish();
						}
					}else{
						Toast.makeText(getApplicationContext(), "NPM / Password salah", Toast.LENGTH_LONG).show();
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
				
			}
		}).execute(MHSHelper.url_login, cuser, cpass);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	    	moveTaskToBack(true);
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
