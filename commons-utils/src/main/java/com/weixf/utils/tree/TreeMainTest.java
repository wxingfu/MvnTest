package com.weixf.utils.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeMainTest {

    public static void main(String[] args) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(new TreeNode(1, 0, "A, 根节点"));
        treeNodeList.add(new TreeNode(2, 0, "B, 根节点"));
        treeNodeList.add(new TreeNode(3, 1, "C, 父节点是A"));
        treeNodeList.add(new TreeNode(4, 2, "D, 父节点是B"));
        treeNodeList.add(new TreeNode(5, 2, "E, 父节点是B"));
        treeNodeList.add(new TreeNode(6, 3, "F, 父节点是C"));

        // 创建树形结构（数据集合作为参数）
        TreeBuild treeBuild = new TreeBuild(treeNodeList);
        // 原查询结果转换树形结构
        treeNodeList = treeBuild.buildTree();
        System.out.println(treeNodeList);
    }
}
