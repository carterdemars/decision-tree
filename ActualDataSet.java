
public class ActualDataSet extends DataSet {

	private String[][] matrix;
	private String dataSourceId;

	public ActualDataSet(DataReader reader) {

		this.dataSourceId = reader.getSourceId();
		this.numAttributes = reader.getNumberOfColumns();
		this.numRows = reader.getNumberOfDataRows();
		this.matrix = reader.getData();

		createAttributes(reader.getAttributeNames());

	}

	public String getValueAt(int row, int attributeIndex) {
		if (row < 0 || row >= numRows)
			return null;

		if (attributeIndex < 0 || attributeIndex >= numAttributes)
			return null;

		return matrix[row][attributeIndex];
	}

	public String getSourceId() {
		return dataSourceId;
	}

	private void createAttributes(String[] attributeNames) {
		this.attributes = new Attribute[numAttributes];

		for (int i = 0; i < numAttributes; i++) {
			String[] values = getUniqueAttributeValues(i);
			if (Util.isArrayNumeric(values))
				this.attributes[i] = new Attribute(attributeNames[i], i, AttributeType.NUMERIC, values);

			else
				this.attributes[i] = new Attribute(attributeNames[i], i, AttributeType.NOMINAL, values);
		}
	}

	public VirtualDataSet toVirtual() {
		int[] rows = new int[numRows];

		for (int i = 0; i < numRows; i++) {
			rows[i] = i;
		}

		return new VirtualDataSet(this, rows, attributes, "");
	}

	/**
	 * Override of toString() in DataSet
	 */
	public String toString() {
		return "Actual dataset (" + getSourceId() + ") with " + numAttributes + " attribute(s) and " + numRows + " row(s)"
				+ System.lineSeparator() + super.toString();
	}
}