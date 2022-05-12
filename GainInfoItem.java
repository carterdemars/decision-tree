
public class GainInfoItem {

	private String attributeName;
	private AttributeType attributeType;
	private double gainValue;
	private String splitAt;


	public GainInfoItem(String attributeName, AttributeType attributeType, double gainValue, String splitAt) {
		this.attributeName = attributeName;
		this.attributeType = attributeType;
		this.gainValue = gainValue;
		this.splitAt = splitAt;
	}

	public String getAttributeName() {
		return attributeName;
	}
	public AttributeType getAttributeType() {
		return attributeType;
	}
	public double getGainValue() {
		return gainValue;
	}
	public String getSplitAt() {
		return splitAt;
	}
	public String toString() {
		if (this.attributeType == AttributeType.NOMINAL) {
			return "(" + this.attributeName + ", " + String.format("%.6f", this.gainValue) + ")";
		}

		return "(" + this.attributeName + " [split at <= " + this.splitAt + "], "
				+ String.format("%.6f", this.gainValue) + ")";
	}

	public static void reverseSort(GainInfoItem[] items) {

		if (items == null) {
			throw new IllegalArgumentException("items cannot be a null reference");
		}

		for (int i = 0; i < items.length - 1; i++) {
			int indexMin = i;
			for (int j = i + 1; j < items.length; j++) {
				if (items[j].gainValue > items[indexMin].gainValue)
					indexMin = j;
			}

			GainInfoItem temp = items[indexMin];
			items[indexMin] = items[i];
			items[i] = temp;
		}
	}
}