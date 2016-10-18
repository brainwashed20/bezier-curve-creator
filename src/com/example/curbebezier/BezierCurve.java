package com.example.curbebezier;

public class BezierCurve {

	private double[] FactorialLookup;

	public BezierCurve() {
		CreateFactorialTable();
	}

	private double factorial(int n) throws Exception {
		if (n < 0) {
			throw new Exception("n is less than 0");
		}
		if (n > 32) {
			throw new Exception("n is greater than 32");
		}
		return FactorialLookup[n];
	}

	private double binom_coef(int n, int i) throws Exception {
		double rez, factn, fact_i, fact_n_i;

		factn = factorial(n);
		fact_i = factorial(i);
		fact_n_i = factorial(n - i);
		rez = factn / (fact_i * fact_n_i);

		return rez;
	}

	// Calculate Bernstein basis
	private double Bernstein(int n, int i, double t) throws Exception {

		double basis;

		double v; /* t^i */

		double k; /* (1 - t)^i */

		/* Prevent problems with pow */

		if (t == 0.0 && i == 0)
			v = 1.0;
		else
			v = Math.pow(t, i);

		if (n == i && t == 1.0)
			k = 1.0;
		else
			k = Math.pow((1 - t), (n - i));

		// Bernstein basis
		basis = binom_coef(n, i) * v * k;
		return basis;
	}

	public void Bezier2D(double[] initial_points, int cpts, double[] points_on_curve) throws Exception {
		
		int npts = (initial_points.length) / 2, icount, jcount, i, j;
		double basis, step, t;

		// Calculate points on curve

		icount = 0;
		t = 0;
		step = (double) 1.0 / (cpts - 1);

		for (j = 0; j != cpts; j++) {
			if ((1.0 - t) < 5e-6)
				t = 1.0;

			jcount = 0;
			points_on_curve[icount] = 0.0;
			points_on_curve[icount + 1] = 0.0;
			for (i = 0; i != npts; i++) {
				basis = Bernstein(npts - 1, i, t);
				points_on_curve[icount] += basis * initial_points[jcount];
				points_on_curve[icount + 1] += basis * initial_points[jcount + 1];
				jcount += 2;
			}

			icount += 2;
			t += step;
		}
	}

	private void CreateFactorialTable() {
		double[] a = new double[33];
		a[0] = 1.0;
		a[1] = 1.0;
		a[2] = 2.0;
		a[3] = 6.0;
		a[4] = 24.0;
		a[5] = 120.0;
		a[6] = 720.0;
		a[7] = 5040.0;
		a[8] = 40320.0;
		a[9] = 362880.0;
		a[10] = 3628800.0;
		a[11] = 39916800.0;
		a[12] = 479001600.0;
		a[13] = 6227020800.0;
		a[14] = 87178291200.0;
		a[15] = 1307674368000.0;
		a[16] = 20922789888000.0;
		a[17] = 355687428096000.0;
		a[18] = 6402373705728000.0;
		a[19] = 121645100408832000.0;
		a[20] = 2432902008176640000.0;
		a[21] = 51090942171709440000.0;
		a[22] = 1124000727777607680000.0;
		a[23] = 25852016738884976640000.0;
		a[24] = 620448401733239439360000.0;
		a[25] = 15511210043330985984000000.0;
		a[26] = 403291461126605635584000000.0;
		a[27] = 10888869450418352160768000000.0;
		a[28] = 304888344611713860501504000000.0;
		a[29] = 8841761993739701954543616000000.0;
		a[30] = 265252859812191058636308480000000.0;
		a[31] = 8222838654177922817725562880000000.0;
		a[32] = 263130836933693530167218012160000000.0;
		FactorialLookup = a;
	}

}
