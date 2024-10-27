/*
 * Remove Sub-Folders from the Filesystem
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of folder[j] must start with folder[j], followed by a "/". 
 * For example, "/a/b" is a sub-folder of "/a", but "/b" is not a sub-folder of "/a/b/c".
 * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.

For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 */
class Solution {
    static class TrieNode {
        private HashMap<String, TrieNode> children;
        private Boolean isEndOfFolder;

        TrieNode() {
            children = new HashMap<>();
            isEndOfFolder = false;
        }

        public HashMap<String, TrieNode> getChildren() {
            return children;
        }

        public void addChild(String child) {
            this.children.put(child, new TrieNode());
        }

        public Boolean getEndOfFolder() {
            return isEndOfFolder;
        }

        public void setEndOfFolder(Boolean endOfFolder) {
            isEndOfFolder = endOfFolder;
        }

    }

    TrieNode root;

    Solution() {
        root = new TrieNode();
    }

    public List<String> removeSubfolders(String[] folder) {
        List<String> result = new ArrayList<>();

        // Build trie from folder paths
        for (String path : folder) {
            TrieNode currentNode = root;
            String[] folderNames = path.split("/");

            for (String folderName : folderNames) {
                if (folderName.isBlank()) { // Skip blank folder names
                    continue;
                }
                if (!currentNode.getChildren().containsKey(folderName)) {
                    currentNode.addChild(folderName);
                }
                currentNode = currentNode.getChildren().get(folderName); // mark child as current node for the next
                                                                         // iteration
                // If we find an end of folder at the time of insertion itself, should we skip
                // it ?
            }
            // mark boolean once the end is reached
            currentNode.setEndOfFolder(true);
        }

        // Check each path for sub folders
        for (String path : folder) {
            TrieNode currentNode = root;
            String[] folderNames = path.split("/");
            boolean isSubFolder = false;

            for (int i = 0; i < folderNames.length; i++) {
                String folderName = folderNames[i];
                if (folderName.isBlank()) { // Skip blank folder names
                    continue;
                }
                TrieNode nextNode = currentNode.getChildren().get(folderName);
                // check if current path is subfolder of an existing folder
                if (nextNode.getEndOfFolder() && i < folderNames.length - 1) {
                    isSubFolder = true;
                    break; // This can be skipped
                }
                currentNode = nextNode;
            }
            if (!isSubFolder) {
                result.add(path);
            }
        }

        return result;
    }
}
