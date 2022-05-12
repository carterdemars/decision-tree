
public class Attribute {

	private String name;
	private int absoluteIndex;
	private AttributeType type;
	private String[] values;


	public Attribute(String name, int absoluteIndex, AttributeType type, String[] values) {
		this.name = name;
		this.absoluteIndex = absoluteIndex;
		this.type = type;

		this.values = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			this.values[i] = values[i];
		}
	}


	public String getName() {
		return name;
	}
	public int getAbsoluteIndex() {
		return absoluteIndex;
	}
	public AttributeType getType() {
		return type;
	}

	public String[] getValues() {
		String[] clonedValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			clonedValues[i] = values[i];
		}
		return clonedValues;

	}

	public void replaceValues(String[] newValues) {
		this.values = new String[newValues.length];
		for (int i = 0; i < newValues.length; i++) {
			this.values[i] = newValues[i];
		}
	}

	public Attribute clone() {
		String[] clonedValues = new String[values.length];

		for (int i = 0; i < values.length; i++) {
			clonedValues[i] = values[i];
		}

		return new Attribute(this.name, this.absoluteIndex, this.type, clonedValues);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("   [absolute index: " + absoluteIndex + "] ");
		buffer.append(name);
		if (type == AttributeType.NUMERIC)
			buffer.append(" (numeric): ");
		else
			buffer.append(" (nominal): ");

		buffer.append("{");

		for (int i = 0; i < values.length; i++) {
			if (type == AttributeType.NUMERIC)
				buffer.append(values[i]);
			else
				buffer.append('\'').append(values[i]).append('\'');

			if (i < values.length - 1)
				buffer.append(", ");
		}

		buffer.append('}');

		return buffer.toString();
	}
}
