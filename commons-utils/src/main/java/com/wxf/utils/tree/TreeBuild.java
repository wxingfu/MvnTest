package com.wxf.utils.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeBuild {

    private final static Integer rootValue = 0;

    public List<TreeNode> nodeList = new ArrayList<>();

    public TreeBuild(List<TreeNode> nodeList) {
        this.nodeList = nodeList;
    }

    public List<TreeNode> buildTree() {
        List<TreeNode> treeList = new ArrayList<>();
        for (TreeNode rootTreeNode : getRootNode()) {
            treeList.add(buildTree(rootTreeNode));
        }
        return treeList;
    }

    private List<TreeNode> getRootNode() {
        List<TreeNode> rootNodeList = new ArrayList<>();
        for (TreeNode treeNode : nodeList) {
            if (rootValue.equals(treeNode.getParentId())) {
                rootNodeList.add(treeNode);
            }
        }
        return rootNodeList;
    }

    private TreeNode buildTree(TreeNode rootTreeNode) {
        List<TreeNode> childTree = new ArrayList<>();
        for (TreeNode treeNode : nodeList) {
            if (treeNode.getParentId().equals(rootTreeNode.getId())) {
                childTree.add(buildTree(treeNode));
            }
        }
        rootTreeNode.setChildren(childTree);
        return rootTreeNode;
    }

}
