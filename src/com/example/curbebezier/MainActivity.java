package com.example.curbebezier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	public SingleTouch st;
	private BezierCurve bc = new BezierCurve();
	private double[] points_on_curve, initial_points;
	private int Points_On_Curve = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		st = new SingleTouch(this, null);
		setContentView(st);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.my_options_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			return true;
		case R.id.create_bezier_curve:
			points_on_curve = new double[Points_On_Curve];
			initial_points = new double[st.points.length];
			for (int i = 0; i < initial_points.length; ++i)
				initial_points[i] = st.points[i];
			st.drawRectangle(300.37f, 214.12f);
			try {
				/*bc.Bezier2D(initial_points, Points_On_Curve / 2,
						points_on_curve);
				for (int i = 1; i < Points_On_Curve; ++i) {
					st.drawRectangle((float) points_on_curve[i + 1],
							(float) points_on_curve[i]);
				}*/
				
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "Exceptie",
						Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
			return true;
		case R.id.reset:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
