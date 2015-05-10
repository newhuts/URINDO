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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class InputKHS extends Activity{

	String npm, kodemk, nilai, smtplus;
	TextView npmkhs, namakhs, smtkhs, totinpmk;
	ListView list;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	ArrayList<HashMap<String, String>>terimalist;
	ListAdapter listadapter;
	TextView nilaiii;
	int pos, totall;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inputkhs);
		ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.ic_ab_back_holo_dark_am);
	    Intent in = getIntent();
		npm = in.getStringExtra("npm");
		npmkhs = (TextView)findViewById(R.id.npmkhs);
		namakhs = (TextView)findViewById(R.id.namakhs);
		smtkhs = (TextView)findViewById(R.id.khssmt);
		totinpmk = (TextView)findViewById(R.id.totinpmk);
		list = (ListView)findViewById(R.id.detaillistkhs);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				pos = position;
				nilaiii = (TextView)view.findViewById(R.id.nilaikhs);
				AlertDialog.Builder builder = new AlertDialog.Builder(InputKHS.this);
			    builder.setTitle("Nilai");
			    builder.setItems(R.array.nilaiarray, new DialogInterface.OnClickListener() {
			               @SuppressWarnings("unchecked")
						public void onClick(DialogInterface dialog, int itemClicked) {
			                   String[] option_array = getResources().getStringArray(R.array.nilaiarray);
			                   String optionSelected = option_array[itemClicked];
			                   if (optionSelected.equals("4")) {
			                	   nilaiii.setText("A");
			                   }else if (optionSelected.equals("3")) {
			                	   nilaiii.setText("B");
			                   }else if (optionSelected.equals("2")) {
			                	   nilaiii.setText("C");
			                   }else if (optionSelected.equals("1")) {
			                	   nilaiii.setText("D");
			                   }else if (optionSelected.equals("0")) {
			                	   nilaiii.setText("E");
			                   }
			                   HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(pos);
			                   obj.put("nilai", ""+optionSelected);
			           }
			    });
			    builder.show();
			}
		});
		
		Button btnasep = (Button)findViewById(R.id.isikrs);
		btnasep.setText("Input");
		loadmhs();
		btnasep.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				inputkhs();
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
							npmkhs.setText(npms);
							namakhs.setText(namas);
							smtkhs.setText(smts);
							int smtpls = Integer.parseInt(smts) + 1;
							smtplus = ""+smtpls;
//							Toast.makeText(getApplicationContext(), ""+smtplus, Toast.LENGTH_LONG).show();
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
//				Log.e("khs", ""+result);
				terimalist = new ArrayList<HashMap<String, String>>();
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("matkul");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("kode_mk", c.getString("kodemk"));
							map.put("mk", c.getString("mk"));
							map.put("sks", c.getString("sks"));
							map.put("smt", c.getString("smt"));
							terimalist.add(map);
						}
						listadapter = new SimpleAdapter(InputKHS.this, terimalist, R.layout.listkhs, 
								new String[] {"kode_mk", "mk", "sks"}, 
								new int[] {R.id.khskodemk, R.id.khsmk,R.id.khssks});
						list.setAdapter(listadapter);
						
						for(int i=0;i<list.getCount();i++) {
						   ViewGroup.LayoutParams params = list.getLayoutParams();
						    params.height = 76 * list.getCount();
						    list.setLayoutParams(params);
						    list.requestLayout();
						}
						
						int childCount = list.getCount();
						int totali = 0;
					    for (int i = 0; i < childCount; i++) {
					    	HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(i);
				            String  sks = (String) obj.get("sks");
				            int oi = Integer.parseInt(sks);
				            totali += oi;
				            totall = totali;
					    }
					    totinpmk.setText(""+totall);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).execute(MHSHelper.url_idkrs, npm, "1");
	}
	
	@SuppressWarnings("unchecked")
	private void inputkhs(){
		int childCount = list.getCount();
		
		for (int i = 0; i < childCount; i++){
	    	HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(i);
            kodemk = (String) obj.get("kode_mk");
            nilai = (String) obj.get("nilai");
            new MHSHelper(new FragmentCallback() {
				@Override
				public void onTaskDone(String result) {
					System.out.println(result);
					Intent in = new Intent(InputKHS.this, DashBoard.class);
					in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(in);
					finish();
				}
			}).execute(MHSHelper.url_inputkhs, npm, kodemk, nilai, smtplus, smtkhs.getText().toString());
            
	    }
	}
}
