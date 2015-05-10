package novi.krsurindo.mahasiswa;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import novi.krsurindo.DashBoard;
import novi.krsurindo.R;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.adapter.SessionManager;
import novi.krsurindo.admin.InputKHS;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import android.R.integer;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class IsiKrs extends Fragment{

	SessionManager session;
	String npmpref, totkrss;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	TextView npmkrs, namakrs, smtkrs,skskrs, totmk, totkrs, das;
	ArrayList<HashMap<String, String>>terimalist;
	ArrayList<HashMap<String, String>>terimalists;
	ListView list, lists;
	Button btnisikrs;
	ListAdapter listadapter, listadapters;
	int totall;
	LinearLayout ll;
	TextView count;
	int pos;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.mhsisikrs, container, false);
			npmkrs = (TextView)rootView.findViewById(R.id.npmkrs);
			namakrs = (TextView)rootView.findViewById(R.id.namakrs);
			smtkrs = (TextView)rootView.findViewById(R.id.krssmt);
			totmk = (TextView)rootView.findViewById(R.id.totmk);
			totkrs = (TextView)rootView.findViewById(R.id.totkrs);
			skskrs = (TextView)rootView.findViewById(R.id.sksmk);
			list = (ListView)rootView.findViewById(R.id.mhslistkrs);
			lists = (ListView)rootView.findViewById(R.id.ngulanglist);
			btnisikrs = (Button)rootView.findViewById(R.id.isikrs);
			ll = (LinearLayout)rootView.findViewById(R.id.hill);
			session = new SessionManager(getActivity());
			HashMap<String,String>user = session.getUserDetails();
	        npmpref = user.get(SessionManager.KEY_NPM);
			loadmhs();
			lists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent,
						View view, int position, long id) {
					das = (TextView)view.findViewById(R.id.mkmk);
					pos = position;
						AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
				         alert.setMessage("tambah ke KRS");
				         alert.setPositiveButton("OK", new OnClickListener() {
				                 @Override
				                 public void onClick(DialogInterface arg0, int arg1) {
				                	 HashMap<String, String> maps = new HashMap<String, String>();
				                	 HashMap<String, Object> obj =(HashMap<String, Object>) listadapters.getItem(pos);
//				                	 Toast.makeText(getActivity(), ""+totkrs.getText().toString(), Toast.LENGTH_LONG).show();
				                	 int nambah = Integer.parseInt((String) obj.get("sks")) + Integer.parseInt(totkrs.getText().toString());
				                	 if (nambah <= 24) {
				                		 maps.put("mk", ""+obj.get("mk"));
					                	 maps.put("sks", ""+obj.get("sks"));
					                	 maps.put("kode_mk", ""+obj.get("kodemk"));
					                	 maps.put("idm", ""+obj.get("idm"));
					                	 terimalist.add(maps);
					                	 list.setAdapter(listadapter);
					                	 terimalists.get(pos).clear();
					                	 ((BaseAdapter) listadapters).notifyDataSetChanged();
					                	 ((BaseAdapter) listadapter).notifyDataSetChanged();
					                	 for(int i=0;i<list.getCount();i++)
											{
											   ViewGroup.LayoutParams params = list.getLayoutParams();
											    params.height = 76 * list.getCount();
											    list.setLayoutParams(params);
											    list.requestLayout();
											}
											int childCount = list.getCount();
											int totali = 0;
											
										    for (int i = 0; i < childCount; i++)
										    {
										    	HashMap<String, Object> objs =(HashMap<String, Object>) listadapter.getItem(i);
									            String  sks = (String) objs.get("sks");
									            int oi = Integer.parseInt(sks);
									            totali += oi;
									            totall = totali;
										    }
										   totmk.setText(""+list.getCount());
										   totkrs.setText(""+totall);
									}else {
										 Toast.makeText(getActivity(), "sudah lebih", Toast.LENGTH_LONG).show();
									}
				                	 
				                	 
				                	 
				                	 
				      
				                 }});
				         alert.setNegativeButton("cancel", null);
			         alert.show();
					
					return false;
				}
			});
			
			
			btnisikrs.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					inputkrs();
					Intent in = new Intent(getActivity(), DashBoard.class);
					startActivity(in);
				}
			});
			
			
		 return rootView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void loadmhs() {
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
							String krs = c.getString("krs");
							if (krs.equals("1") || krs.equals("2")) {
								AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
						         alert.setMessage("Sudah di Isi");
						         alert.setPositiveButton("Kembali", new OnClickListener() {
						                 @Override
						                 public void onClick(DialogInterface arg0, int arg1) {
						                	Intent in = new Intent(getActivity(), DashBoard.class);
						 					startActivity(in);
						                 }});
						         alert.show();
							}else{
								npmkrs.setText(npms);
								namakrs.setText(namas);
								smtkrs.setText(smts);
								loadMatkul(smts);
								int smtt = Integer.parseInt(smts);
								int gg = smtt%2;
								loadkhsngulang(""+gg);
//								if (smts.equals("1") || smts.equals("2")) {
//									ll.setVisibility(View.GONE);
//								}else {
//									int smtt = Integer.parseInt(smts);
//									int gg = smtt%2;
//									loadkhsngulang(""+gg);
//								}
							}
						}
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}).execute(MHSHelper.url_mhs, npmpref);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void loadMatkul(String smt){
		new MHSHelper(new FragmentCallback() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onTaskDone(String result) {
				terimalist = new ArrayList<HashMap<String, String>>();
				try {
					JSONObject json = new JSONObject(result);
					Log.e("", ""+result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("matkul");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("idm", c.getString("idm"));
							map.put("kode_mk", c.getString("kode_mk"));
							map.put("mk", c.getString("mk"));
							map.put("sks", c.getString("sks"));
							map.put("smt", c.getString("smt"));
							terimalist.add(map);
						}
						
						listadapter = new SimpleAdapter(getActivity(), terimalist, R.layout.listmatkul, 
								new String[] {"idm", "kode_mk", "mk", "sks", "smt"}, 
								new int[] {R.id.idmk, R.id.kdmk,R.id.mkmk,R.id.sksmk});
						list.setAdapter(listadapter);
//						Toast.makeText(getActivity(), ""+listadapter.getItem(1), Toast.LENGTH_LONG).show();
						for(int i=0;i<list.getCount();i++)
						{
						   ViewGroup.LayoutParams params = list.getLayoutParams();
						    params.height = 76 * list.getCount();
						    list.setLayoutParams(params);
						    list.requestLayout();
						}
						int childCount = list.getCount();
						int totali = 0;
						
					    for (int i = 0; i < childCount; i++)
					    {
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
		}).execute(MHSHelper.url_matkul, smt);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void loadkhsngulang(String gg){
		new MHSHelper(new FragmentCallback() {
			
			@Override
			public void onTaskDone(String result) {
				Log.e("", ""+result);
				terimalists = new ArrayList<HashMap<String, String>>();
				try {
					JSONObject json = new JSONObject(result);
					int a = json.getInt("success");
					if (a == 1) {
						mahasiswa = json.getJSONArray("transkip");
						for (int i = 0; i < mahasiswa.length(); i++) {
							JSONObject c = mahasiswa.getJSONObject(i);
							HashMap<String, String> map = new HashMap<String, String>();
							map.put("idm", c.getString("idm"));
							map.put("kodemk", c.getString("kodemk"));
							map.put("mk", c.getString("mk"));
							String sks = c.getString("sks");
							String nilai = c.getString("nilai");
							map.put("sks", sks);
							if (nilai.equals("4")) {
								map.put("nilai", "A");
							}else if (nilai.equals("3")) {
								map.put("nilai", "B");
							}else if (nilai.equals("2")) {
								map.put("nilai", "C");
							}else if (nilai.equals("1")) {
								map.put("nilai", "D");
							}else if (nilai.equals("0")) {
								map.put("nilai", "E");
							}
							double as = Double.parseDouble(sks);
							double bs = Double.parseDouble(nilai);
							double total = as * bs;
							map.put("hasil", ""+total);
							terimalists.add(map);
							
							
						}
						
						listadapters = new SimpleAdapter(getActivity(), terimalists, R.layout.listtranskip, 
								new String[] {"idm", "kodemk", "mk", "sks", "nilai", "hasil"}, 
								new int[] {R.id.idmkhs, R.id.kdkhs, R.id.mkkhs,R.id.skskhs,R.id.khsnilai, R.id.khsakhir});
						lists.setAdapter(listadapters);
						//ukuran list
						for(int i=0;i<lists.getCount();i++)
						{
						   ViewGroup.LayoutParams params = lists.getLayoutParams();
						    params.height = 76 * lists.getCount();
						    lists.setLayoutParams(params);
						    lists.requestLayout();
						}
						
					}else if (a == 0) {
						ll.setVisibility(View.GONE);
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			}
		}).execute(MHSHelper.url_ngulang, npmpref, gg);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	private void inputkrs(){
//		Toast.makeText(getActivity(), "OK !!!!"+list.getCount(), Toast.LENGTH_LONG).show();
		int childCount = list.getCount();
	    for (int i = 0; i < childCount; i++)
	    {
	    	HashMap<String, Object> obj =(HashMap<String, Object>) listadapter.getItem(i);
            String kodemk = (String) obj.get("kode_mk");
            System.out.println("npm:"+npmpref+":kode:"+kodemk);
            new MHSHelper(new FragmentCallback() {
				
				@Override
				public void onTaskDone(String result) {}
			}).execute(MHSHelper.url_buatkrs, npmpref, kodemk, totkrs.getText().toString(), smtkrs.getText().toString());
	    }
	}
}
