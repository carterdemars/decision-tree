
public class Util {

	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}

		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public static boolean isArrayNumeric(String[] array) {

		if (array == null || array.length == 0)
			return false;

		for (int i = 0; i < array.length; i++)
			if (!isNumeric(array[i])) {
				return false;
			}

		return true;
	}

	public static String nominalArrayToString(String[] array) {

		if (array == null)
			return null;

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		for (int i = 0; i < array.length; i++) {

			buffer.append('\'').append(array[i]).append('\'');

			if (i < array.length - 1) {
				buffer.append(", ");
			}
		}

		buffer.append(']');

		return buffer.toString();
	}

	public static String numericArrayToString(String[] array) {

		if (array == null)
			return null;

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		for (int i = 0; i < array.length; i++) {

			buffer.append(array[i]);

			if (i < array.length - 1) {
				buffer.append(", ");
			}
		}

		buffer.append(']');

		return buffer.toString();
	}


	public static String intArrayToString(int[] array) {

		if (array == null)
			return null;

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		for (int i = 0; i < array.length; i++) {

			buffer.append(array[i]);

			if (i < array.length - 1) {
				buffer.append(", ");
			}
		}

		buffer.append(']');

		return buffer.toString();
	}
}