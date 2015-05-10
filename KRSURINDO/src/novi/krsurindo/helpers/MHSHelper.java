package novi.krsurindo.helpers;

import java.util.ArrayList;
import java.util.List;






import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

public class MHSHelper extends AsyncTask<String, Void, String> {

	List<NameValuePair>param;
	  private FragmentCallback mFragmentCallback;

	    public MHSHelper(FragmentCallback fragmentCallback) {
	        mFragmentCallback = fragmentCallback;
	    }

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}
	public static String url_login = "http://192.168.88.22/urindo/get_mahasiswa.php";
	public static String url_mhs = "http://192.168.88.22/urindo/getidmahasiswa.php";
	public static String url_matkul = "http://192.168.88.22/urindo/get_matkul.php";
	public static String url_buatkrs = "http://192.168.88.22/urindo/buatkrs.php";
	public static String url_listkrs = "http://192.168.88.22/urindo/get_listkrs.php";
	public static String url_idkrs = "http://192.168.88.22/urindo/get_idkrs.php";
	public static String url_update = "http://192.168.88.22/urindo/update_krs.php";
	public static String url_inputkhs = "http://192.168.88.22/urindo/nilai_khs.php";
	public static String url_transkip = "http://192.168.88.22/urindo/get_transkip.php";
	public static String url_listkhs = "http://192.168.88.22/urindo/get_listkhs.php";
	public static String url_listtranskipsmt = "http://192.168.88.22/urindo/get_transkipsmt.php";
	public static String url_updatepass = "http://192.168.88.22/urindo/update_pass.php";
	public static String url_ngulang = "http://192.168.88.22/urindo/get_ngulang.php";
	public static String url_krslist = "http://192.168.88.22/urindo/get_krslist.php";
	public static String url_krs = "http://192.168.88.22/urindo/get_krs.php";
	
	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		param = new ArrayList<NameValuePair>();
		System.out.println("banyak execute"+params.length);
		if (url.equals(""+url_login)) {
			param.add(new BasicNameValuePair("username", params[1]));
			param.add(new BasicNameValuePair("password", params[2]));
		}else if (url.equals(""+url_mhs)) {
			param.add(new BasicNameValuePair("username", params[1]));

		}else if (url.equals(""+url_matkul)) {
			param.add(new BasicNameValuePair("smt", params[1]));
		}else if (url.equals(""+url_buatkrs)) {
			param.add(new BasicNameValuePair("npm", params[1]));
			param.add(new BasicNameValuePair("kodemk", params[2]));
			param.add(new BasicNameValuePair("totalkrs", params[3]));
			param.add(new BasicNameValuePair("smt", params[4]));
		}else if (url.equals(""+url_listkrs)) {
			param.add(new BasicNameValuePair("status", params[1]));
			
		}else if (url.equals(""+url_idkrs)) {
			param.add(new BasicNameValuePair("npm", params[1]));
			param.add(new BasicNameValuePair("status", params[2]));
		}else if (url.equals(""+url_update)) {
			param.add(new BasicNameValuePair("id", params[1]));
			param.add(new BasicNameValuePair("npm", params[2]));
			
		}else if (url.equals(""+url_inputkhs)) {
			param.add(new BasicNameValuePair("npm", params[1]));
			param.add(new BasicNameValuePair("kodemk", params[2]));
			param.add(new BasicNameValuePair("nilai", params[3]));
			param.add(new BasicNameValuePair("naikkelas", params[4]));
			param.add(new BasicNameValuePair("smt", params[5]));
			System.out.println(param);
		}else if (url.equals(""+url_listkhs)) {
			param.add(new BasicNameValuePair("npm", params[1]));
		}else if (url.equals(""+url_transkip)) {
			param.add(new BasicNameValuePair("npm", params[1]));
		}else if (url.equals(""+url_listtranskipsmt)) {
			param.add(new BasicNameValuePair("npm", params[1]));
			param.add(new BasicNameValuePair("smt", params[2]));
		}else if (url.equals(""+url_updatepass)) {
			param.add(new BasicNameValuePair("npm", params[1]));
			param.add(new BasicNameValuePair("pass", params[2]));
			param.add(new BasicNameValuePair("newpass", params[3]));
		}else if (url.equals(""+url_ngulang)) {
			param.add(new BasicNameValuePair("npm", params[1]));
			param.add(new BasicNameValuePair("oddeven", params[2]));
		}else if (url.equals(""+url_krslist)) {
			param.add(new BasicNameValuePair("npm", params[1]));
		}else if (url.equals(""+url_krs)) {
			param.add(new BasicNameValuePair("npm", params[1]));
			param.add(new BasicNameValuePair("smt", params[2]));
		}
		
		
		return Utils.getJSONString(url, param);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
			mFragmentCallback.onTaskDone(result);
		
	}

}
