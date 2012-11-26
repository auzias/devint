package util;

public final class Progress {
	private static long msBegin, msEnd, msTime;

	private Progress() {
	}

	/**
	 * Start the timer.
	 */
	public static void start() {
		msTime = 0;
		msBegin = System.currentTimeMillis();
	}

	/**
	 * Stop the timer.
	 */
	public static void stop() {
		msEnd = System.currentTimeMillis();
		msTime += msEnd - msBegin;
	}

	/**
	 * Resume the timer, that is stopped by <code>stop()</code>.
	 */
	public static void resume() {
		msBegin = System.currentTimeMillis();
	}

	/**
	 * Return the elapsed time measured in milliseconds.
	 * 
	 * @return long, the time.
	 */
	public static long time() {
		return msTime;
	}

	/**
	 * Return the elapsed time measured in seconds.
	 * 
	 * @return double, the time.
	 * @since Opale-Tools 0.13
	 */
	public static double stime() {
		return msTime / 1000.;
	}

	/**
	 * Return the elapsed time measured in minutes.
	 * 
	 * @return double, the time.
	 * @since Opale-Tools 0.13
	 */
	public static double mtime() {
		return msTime / 60000.;
	}

	/**
	 * Return the elapsed time measured in hours.
	 * 
	 * @return double, the time.
	 * @since Opale-Tools 0.13
	 */
	public static double htime() {
		return msTime / 3600000.;
	}

	/**
	 * Main function
	 * 
	 * @param arg
	 *            command-line arguments.
	 */
	public static void main(String[] arg) {
		Progress.start();
		for (int i = 1; i < 1000000; i++) {
		}
		Progress.stop();
		Progress.resume();
		for (int i = 1; i < 1000000; i++) {
		}
		Progress.stop();
		System.out.println("ms " + Progress.time());
		System.out.println("s " + Progress.stime());
		System.out.println("m " + Progress.mtime());
		System.out.println("h " + Progress.htime());
	}
}