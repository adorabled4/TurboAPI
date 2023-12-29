package com.dhx.apiinterface.util.TextReview;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * 词节点
 *
 * @author adorabled4
 * @date 2023/12/29
 */
@Data
@NoArgsConstructor
public class WordNode {

	private int value; // 节点名称

	private List<WordNode> subNodes; // 子节点

	private boolean isLast;// 默认false

	public WordNode(int value) {
		this.value = value;
	}

	public WordNode(int value, boolean isLast) {
		this.value = value;
		this.isLast = isLast;
	}

	/**
	 * 
	 * @param subNode
	 * @return 就是传入的subNode
	 */
	private WordNode addSubNode(final WordNode subNode) {
		if (subNodes == null)
			subNodes = new LinkedList<WordNode>();
		subNodes.add(subNode);
		return subNode;
	}

	/**
	 * 有就直接返回该子节点， 没有就创建添加并返回该子节点
	 * 
	 * @param value
	 * @return
	 */
	public WordNode addIfNoExist(final int value, final boolean isLast) {
		if (subNodes == null) {
			return addSubNode(new WordNode(value, isLast));
		}
		for (WordNode subNode : subNodes) {
			if (subNode.value == value) {
				if (!subNode.isLast && isLast)
					subNode.isLast = true;
				return subNode;
			}
		}
		return addSubNode(new WordNode(value, isLast));
	}

	public WordNode querySub(final int value) {
		if (subNodes == null) {
			return null;
		}
		for (WordNode subNode : subNodes) {
			if (subNode.value == value)
				return subNode;
		}
		return null;
	}

}