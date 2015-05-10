package novi.krsurindo.mahasiswa;

import java.util.HashMap;

import novi.krsurindo.R;
import novi.krsurindo.adapter.JSONParser;
import novi.krsurindo.adapter.SessionManager;
import novi.krsurindo.helpers.FragmentCallback;
import novi.krsurindo.helpers.MHSHelper;
import novi.krsurindo.model.Mahasiswa;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuMahasiswa extends Fragment{

	TextView npm, nama, smt, krs, totkrs;
	ProgressDialog pDialog ;
	SessionManager session;
	String npmpref;
	JSONParser jParser = new JSONParser();
	JSONArray mahasiswa = null;
	Mahasiswa msh;
	FrameLayout krs1, krs3;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.mahasiswa, container, false);
			npm = (TextView)rootView.findViewById(R.id.npmmhs);
			nama = (TextView)rootView.findViewById(R.id.namamhs);
			smt = (TextView)rootView.findViewById(R.id.smtmhs);
			krs = (TextView)rootView.findViewById(R.id.statkrsmhs);
			totkrs = (TextView)rootView.findViewById(R.id.totkrsmhs);
			krs1 = (FrameLayout)rootView.findViewById(R.id.FrameLayout04);
			krs1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "asada", Toast.LENGTH_LONG).show();
					
				}
			});
			session = new SessionManager(getActivity());
			HashMap<String,String>user = session.getUserDetails();
	        npmpref = user.get(SessionManager.KEY_NPM);
			infomhs();
		 return rootView;			
	}
	
	
	
	private void infomhs() {
        MHSHelper testAsyncTask = new MHSHelper(new FragmentCallback() {
        	
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
							String krss = c.getString("krs");
							String totkrss = c.getString("totalkrs");
							if (krss.equals("0")) {
								krs.setText("Belum di Isi");
								krs.setTextColor(getResources().getColorStateList(R.color.Gainsboro));
							}else if (krss.equals("1")) {
								krs.setText("Sedang di Proses");
								krs.setTextColor(getResources().getColorStateList(R.color.Gold));
							}else {
								krs.setText("Diterima");
								krs.setTextColor(getResources().getColorStateList(R.color.urindobiru));
							}
							npm.setText(npms);
							nama.setText(namas);
							smt.setText(smts);
							totkrs.setText(totkrss);
							
						}
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
        });
        testAsyncTask.execute(MHSHelper.url_mhs, ""+npmpref);
    }


}
