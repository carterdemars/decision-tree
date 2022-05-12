
public abstract class DataSet {

	protected int numAttributes;
	protected int numRows;
	protected Attribute[] attributes;

	public int getNumberOfAttributes() {
		return numAttributes;
	}

	public int getNumberOfDatapoints() {
		return numRows;
	}

	public Attribute getAttribute(String attributeName) {
		if (attributeName == null) {
			return null;
		}

		for (int i = 0; i < numAttributes; i++)
			if (attributes[i].getName().equals(attributeName)) {
				return attributes[i].clone();
			}

		return null;
	}

	public Attribute getAttribute(int attributeIndex) {
		if (attributeIndex < 0 || attributeIndex >= numAttributes) {
			return null;
		}
		return attributes[attributeIndex].clone();
	}

	public int getAttributeIndex(String attributeName) {
		if (attributeName == null || attributeName.isEmpty()) {
			return -1;
		}

		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].getName().equals(attributeName)) {
				return i;
			}
		}

		return -1;

	}

	public abstract String getValueAt(int row, int attributeIndex);


	public String[] getUniqueAttributeValues(String attributeName) {

		if (attributeName == null) {
			return null;
		}

		for (int i = 0; i < attributes.length; i++) {
			if (attributeName.equals(attributes[i].getName())) {
				return getUniqueAttributeValues(i);
			}
		}
		return null;
	}

	public String[] getUniqueAttributeValues(int attributeIndex) {

		String[] tempValues = new String[numRows];

		int count = 0;

		for (int i = 0; i < numRows; i++) {
			boolean found = false;

			for (int j = 0; j < count; j++) {
				if (getValueAt(i, attributeIndex).equals(tempValues[j])) {
					found = true;
					break;
				}
			}

			if (!found) {
				tempValues[count++] = getValueAt(i, attributeIndex);
			}
		}

		String[] values = new String[count];

		for (int i = 0; i < count; i++) {
			values[i] = tempValues[i];
		}

		return values;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" - Metadata for attributes:").append(System.lineSeparator());

		for (int i = 0; i < getNumberOfAttributes(); i++)
			buffer.append(getAttribute(i)).append(System.lineSeparator());
		return buffer.toString();
	}
}