package hl.gps.distance;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	private LocationManager locationManager;
	private TextView txtLatitude;
	private TextView txtLongitude;
	private TextView txtDistance;
	private double lLatitude, lLongitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity);
		txtLatitude = (TextView)findViewById(R.id.txtLatitude);
		txtLongitude = (TextView)findViewById(R.id.txtLongitude);
		txtDistance = (TextView) findViewById(R.id.txtDistance);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				3000, // 3 sec
				10, this);
		Location locationFirst = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(locationFirst!=null){
			lLatitude = locationFirst.getLatitude();
			lLongitude = locationFirst.getLongitude();
			
			Toast.makeText(getBaseContext(), lLatitude + "  "+lLongitude, Toast.LENGTH_LONG).show();
		
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		String str = "Latitude: " + location.getLatitude() + "Longitude: "
				+ location.getLongitude();
		lLatitude = location.getLatitude();
		lLongitude = location.getLongitude();
		txtLatitude.setText(location.getLatitude()+"");
		txtLongitude.setText(location.getLongitude()+"");
		
		Toast.makeText(getBaseContext(),"GPS CHANGE: "+ lLatitude + "  "+lLongitude, Toast.LENGTH_LONG).show();

	}

	@Override
	public void onProviderDisabled(String arg0) {
		Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onProviderEnabled(String arg0) {
		Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
// Location locationA = new Location("point A");
//
// locationA.setLatitude(latA);
// locationA.setLongitude(lngA);
//
// Location locationB = new Location("point B");
//
// locationB.setLatitude(latB);
// locationB.setLongitude(lngB);
//
// float distance = locationA.distanceTo(locationB);