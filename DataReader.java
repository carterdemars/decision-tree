/**
 * An interface that provides methods for reading a raw (unprocessed) dataset.
 */
public interface DataReader {

	int getNumberOfColumns();

	/**
	 * @return the number of rows (datapoints) in the dataset
	 */
	int getNumberOfDataRows();

	/**
	 * @return the names of the dataset's attributes
	 */
	String[] getAttributeNames();

	String[][] getData();

	String getSourceId();
}