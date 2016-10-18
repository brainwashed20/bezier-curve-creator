package com.example.curbebezier;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SingleTouch extends View {

	private Paint paint = new Paint();
	private Path path = new Path();
	public double[] points = new double[90];

	private int rez;
	private double lastEventX;
	private double lastEventY;
	private boolean in_dialog = false;

	public SingleTouch(Context context, AttributeSet attrs) {
		super(context, attrs);

		paint.setAntiAlias(true);
		paint.setStrokeWidth(6f);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}

	public void addPoint(int index) {
		// adding the point at the index position

		points[index * 2] = lastEventX;
		points[index * 2 + 1] = lastEventY;

		Toast.makeText(
				getContext(),
				String.valueOf(index) + " " + String.valueOf(lastEventX) + " "
						+ String.valueOf(lastEventY), Toast.LENGTH_SHORT)
				.show();

		drawRectangle((float) lastEventX, (float) lastEventY);
	}

	public void drawRectangle(float x, float y) {
		path.moveTo((float) x - 3f, (float) y - 3f);
		path.lineTo((float) x, (float) y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (in_dialog == false) {

			lastEventX = event.getX();
			lastEventY = event.getY();

			// get user input for postition
			alertdialog();
		}

		// switch (event.getAction()) {
		// case MotionEvent.ACTION_DOWN:
		// path.moveTo(eventX, eventY);
		// return true;
		// case MotionEvent.ACTION_MOVE:
		// path.lineTo(eventX, eventY);
		// break;
		// case MotionEvent.ACTION_UP:
		// // nothing to do
		// break;
		// default:
		// return false;
		// }
		//
		// // Schedules a repaint.
		// invalidate();
		return true;
	}

	public int isUnsignedInteger(String input) {
		try {
			int rez = Integer.parseInt(input);
			if (rez < 0)
				return -1;
			return rez;
		} catch (Exception e) {
			return -1;
		}
	}

	void alertdialog() {
		in_dialog = true;
		AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

		alert.setTitle("");
		alert.setMessage("Insert the index position of the point");

		// Set an EditText view to get user input
		final EditText input = new EditText(getContext());
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String str = input.getText().toString();
				int aux = isUnsignedInteger(str);
				if (aux != -1)
					addPoint(aux);
				else
					Toast.makeText(getContext(),
							"Please insert a valid index!", Toast.LENGTH_SHORT)
							.show();
				dialog.dismiss();
				in_dialog = false;
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						in_dialog = false;
					}
				});

		alert.show();
	}
}
