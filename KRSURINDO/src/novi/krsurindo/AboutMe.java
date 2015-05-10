package novi.krsurindo;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutMe extends Activity{

	TextView npm, nama, kampus, ket;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutme);
		ActionBar actionBar = getActionBar();
	    actionBar.setHomeButtonEnabled(true);
	    actionBar.setIcon(R.drawable.ic_ab_back_holo_dark_am);
		npm = (TextView)findViewById(R.id.aboutnpm);
		nama = (TextView)findViewById(R.id.aboutnama);
		kampus = (TextView)findViewById(R.id.aboutkampus);
		ket = (TextView)findViewById(R.id.aboutket);
		npm.setText("201043500070");
		nama.setText("Novi Yanti");
		kampus.setText("Universitas Indraprasta");
		ket.setText("Aplikasi KRS dan KHS ini adalah apliaksi sederhana yang dibuat untuk mempermudah mahasiswa dalam mengisi KRS dan melihat KRS dan KHS.\nAplikasi ini dibuat sebagai salah satu syarat untuk memperoleh gelar kesarjanaan S1 Teknik Informaika di Universitas Indraprasta PGRI.");
		
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
}
