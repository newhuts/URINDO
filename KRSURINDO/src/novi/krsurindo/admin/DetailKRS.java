package novi.krsurindo.admin;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import novi.krsurindo.DashBoard;
import novi.krsurindo.R;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DetailKRS extends Activity {

	ListView list;
	ListAdapter listadapter;
	ArrayList<HashMap<String, String>>terimalist;
	TextView npmkrs, namakrs, smtkrs, totmk, totkrs;
	String npm;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	int totall;
	LinearLayout ll;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mhsisikrs);
		ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.ic_ab_back_holo_dark_am);
		Intent in = getIntent();
		ll = (LinearLayout)findViewById(R.id.hill);
		ll.setVisibility(View.GONE);
		npm = in.getStringExtra("npm");
		npmkrs = (TextView)findViewById(R.id.npmkrs);
		namakrs = (TextView)findViewById(R.id.namakrs);
		smtkrs = (TextView)findViewById(R.id.krssmt);
		totmk = (TextView)findViewById(R.id.totmk);
		totkrs = (TextView)findViewById(R.id.totkrs);
		list = (ListView)findViewById(R.id.mhslistkrs);
		Button btnasep = (Button)findViewById(R.id.isikrs);
		btnasep.setText("Accept");
		loadmhs();
		btnasep.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				asepkrs();
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
	
	private void loadmhs(){
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
							String npms = c.getString("npm");
							String namas = c.getString("nama");
							String smts = c.getString("smt");
							npmkrs.setText(npms);
							namakrs.setText(namas);
							smtkrs.setText(smts);
							loadmatkul();
						}
					}
				}catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).execute(MHSHelper.url_mhs, npm);
	}
	
	private void loadmatkul(){
		new MHSHelper(new FragmentCallback() {
			@SuppressWarnings("unchecked")
			@Override
			public void onTaskDone(String result) {
				terimalist = new ArrayList<HashMap<String, String>>();
				try {
//					System.out.println(""+result);
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("matkul");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("id", c.getString("id"));
							map.put("idm", c.getString("idm"));
							map.put("kode_mk", c.getString("kodemk"));
							map.put("mk", c.getString("mk"));
							map.put("sks", c.getString("sks"));
							map.put("smt", c.getString("smt"));
							terimalist.add(map);
						}
						listadapter = new SimpleAdapter(DetailKRS.this, terimalist, R.layout.listmatkul, 
								new String[] {"idm", "kode_mk", "mk", "sks", "smt"}, 
								new int[] {R.id.idmk, R.id.kdmk,R.id.mkmk,R.id.sksmk});
						list.setAdapter(listadapter);
						
						//list ukuran
						for(int i=0;i<list.getCount();i++) {
						   ViewGroup.LayoutParams params = list.getLayoutParams();
						    params.height = 76 * list.getCount();
						    list.setLayoutParams(params);
						    list.requestLayout();
						}
						
						//list total
						int childCount = list.getCount();
						int totali = 0;
					    for (int i = 0; i < childCount; i++) {
					    	HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(i);
				            String  sks = (String) obj.get("sks");
				            int oi = Integer.parseInt(sks);
				            totali += oi;
				            totall = totali;
					    }
					    
					    totmk.setText(""+list.getCount());
						totkrs.setText(""+totall);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).execute(MHSHelper.url_idkrs, npm, "0");
	}
	
	@SuppressWarnings("unchecked")
	private void asepkrs(){
//		Toast.makeText(this, "OK !!!!"+list.getCount(), Toast.LENGTH_LONG).show();
		int childCount = list.getCount();
	    for (int i = 0; i < childCount; i++){
	    	HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(i);
            String idkrs = (String) obj.get("id");
//            System.out.println("npm:"+npm+":kode:"+idkrs);
            new MHSHelper(new FragmentCallback() {
				@Override
				public void onTaskDone(String result) {
					Intent in = new Intent(DetailKRS.this, DashBoard.class);
					in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(in);
					finish();
				}
			}).execute(MHSHelper.url_update, idkrs, npm);
	    }
	}
}
