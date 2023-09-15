package education.multithreading.csp_jcsp;

public class Matrix {
    public static void seqMultiply(final double[][] X, final double[][] Y, final double[][] Z) {
        for (int i = 0; i < X.length; i++) {
            final double[] Xi = X[i];
            final double[] Zi = Z[i];
            for (int j = 0; j < Y[0].length; j++) {
                double sum = 0.0d;
                for (int k = 0; k < Y.length; k++) {
                    sum += Xi[k] * Y[k][j];
                }
                Zi[j] = sum;
            }
        }
    }
}
