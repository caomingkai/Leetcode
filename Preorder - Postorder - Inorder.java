
// 1 --- Post-order

public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if(root == null) return list;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while(!stack.empty()){
        root = stack.pop();
        list.add(0, root.val);
        if(root.left != null) stack.push(root.left);
        if(root.right != null) stack.push(root.right);
    }
    return list;
}



// 2 --- Pre-order

public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if(root == null) return list;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while(!stack.empty()){
        root = stack.pop();
        list.add(root.val);
        if(root.right != null) stack.push(root.right);
        if(root.left != null) stack.push(root.left);
    }
    return list;
}



// 3 --- Inorder traversal

public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    if(root == null) return list;
    Stack<TreeNode> stack = new Stack<>();
    while(root != null || !stack.empty()){
        while(root != null){
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        list.add(root.val);
        root = root.right;
    }
    return list;
}