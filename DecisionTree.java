
public class DecisionTree {

	private static class Node<E> {
		E data;
		Node<E>[] children;

		Node(E data) {
			this.data = data;
		}
	}

	Node<VirtualDataSet> root;


	public DecisionTree(ActualDataSet data) {
		root = new Node<VirtualDataSet>(data.toVirtual());
		build(root);
	}


	private void build(Node<VirtualDataSet> node) {

		// EDGE CASES

			// are note amd node.data non-null?
			if (node == null)
				throw new IllegalArgumentException("node cannot be null");

			if (node.data == null)
				throw new NullPointerException("node.data cannot be null");

			// does node.data have at least one attribute?
			if (node.data.getNumberOfAttributes() < 1)
				throw new IllegalArgumentException("The dataset must have at least one attribute");
			

			// does node.data have at least one datapoint
			if (node.data.getNumberOfDatapoints() < 1)
				throw new IllegalArgumentException("The dataset must have at least one datapoint");

		// BASE CASES

			// node.data has only one attribute (the data cannot be split any further)
			if (node.data.getNumberOfAttributes() <= 1) {
				//System.out.println("node.data has only one attribute");
				return;
			}

			// the unique value set of node.data's last attribute has only one value (no need to split further)
			if (node.data.getUniqueAttributeValues(node.data.getNumberOfAttributes()-1).length <= 1) {
				//System.out.println("the unique value set has only one value");
				return;
			}

			// No attribute of node.data has more than one unique value (there is nothing left to split on)
			Boolean flag = false;
			for (Attribute a : node.data.attributes) {
				if ((node.data).getUniqueAttributeValues(a.getName()).length > 1) {
					flag = true;
				}
			}

			if (flag == false) {
				//System.out.println("no attribute has more than one unique value");
				return;
			}

		
		// RECURSIVE CASE

			// let a_max be the attribute of node.data that yields the best information gain
			GainInfoItem a_max_split = InformationGainCalculator.calculateAndSortInformationGains(node.data)[0];
			Attribute a_max = node.data.getAttribute(node.data.getAttributeIndex(a_max_split.getAttributeName()));
			int a_max_index = node.data.getAttributeIndex(a_max.getName());

			// let [partition_0, ... , partition_(n-1)] be the result of splitting node.data over a_max (check numeric/nominal)

			VirtualDataSet[] p = new VirtualDataSet[0];

			if (a_max.getType() == AttributeType.NOMINAL) {
				p = (node.data).partitionByNominallAttribute(a_max_index);
			}
			else if (a_max.getType() == AttributeType.NUMERIC) {

				String splitAt = a_max_split.getSplitAt();

				int datapoint_index = -1;
				int k = 0;

				while (datapoint_index == -1 && k < a_max.getValues().length) {
					if (a_max.getValues()[k].equals(splitAt)) {
						datapoint_index = k;
					}
					k++;
				}

				p = (node.data).partitionByNumericAttribute(a_max_index, datapoint_index);

			}

			// Instantiate the children array of nodes
			node.children = (Node<VirtualDataSet>[]) new Node[p.length];

			// Populate node.children with the partitions resulting from 3.2
			for (int j = 0; j < node.children.length; j++) {
				node.children[j] = new Node(p[j]);
			}

			// For every node in node.children, build(node.children[i])
			for (Node child : node.children) {
				build(child);
			}
	}

	@Override
	public String toString() {
		return toString(root, 0);
	}


	@SuppressWarnings("unchecked")
	private String toString(Node<VirtualDataSet> node, int indentDepth) {

		if (node == null) {
			throw new IllegalArgumentException("node cannot be null");
		}

		String s = "";

		if (node.children != null) {

			int counter = 0;

			for (Node child : node.children) {

				VirtualDataSet tmp = (VirtualDataSet) child.data;
				if (counter == 0) {
					s = s + createIndent(indentDepth) + "if (" + tmp.getCondition() + ") {\n" + toString(child, indentDepth + 1) + createIndent(indentDepth) + "}\n";
				}
				else {
					s = s + createIndent(indentDepth) + "else if (" + tmp.getCondition() + ") {\n" + toString(child, indentDepth + 1) + createIndent(indentDepth) + "}\n";
				}
				
				counter ++;
			}
		}

		else if (node.children == null) {
			String name = node.data.getAttribute(node.data.getNumberOfAttributes()-1).getName();
			s = s + createIndent(indentDepth) + name + " = " + node.data.getUniqueAttributeValues(node.data.getNumberOfAttributes()-1)[0] + "\n";
		}

		return s;

	}


	private static String createIndent(int indentDepth) {
		if (indentDepth <= 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < indentDepth; i++) {
			buffer.append("   ");
		}
		return buffer.toString();
	}



	public static void main(String[] args) throws Exception {
	
		// StudentInfo.display();

		if (args == null || args.length == 0) {
			System.out.println("Expected a file name as argument!");
			System.out.println("Usage: java DecisionTree <file name>");
			return;
		}

		String strFilename = args[0];

		ActualDataSet data = new ActualDataSet(new CSVReader(strFilename));

		DecisionTree dtree = new DecisionTree(data);

		System.out.println(dtree);
	}

	
}