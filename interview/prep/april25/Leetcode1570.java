/*
 * 1570. Dot Product of Two Sparse Vectors
 * Given two sparse vectors, compute their dot product.
 * Implement class SparseVector:
   ** SparseVector(nums) Initializes the object with the vector nums
   ** dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, 
 * you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 * Follow up: What if only one of the vectors is sparse?
 * Constraints:
   ** n == nums1.length == nums2.length
   ** 1 <= n <= 10^5
   ** 0 <= nums1[i], nums2[i] <= 100
 */
public class Leetcode1570 {
    
    class SparseAndDenseVector {
        //Follow-up, one of the vectors is dense and not sparse
        //Hash Collisions Increase Computation Time so not using hashmap approach. Instead, using pairs and bin search
        List<int[]> pairs = new ArrayList<>();

        SparseAndDenseVector(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    pairs.add(new int[]{i, nums[i]});
                }
            }
        }

        public int dotProduct(SparseAndDenseVector vec) {
            List<int[]> smallerArr = pairs.size() > vec.pairs.size() ? vec.pairs : pairs;
            List<int[]> largerArr = pairs.size() > vec.pairs.size() ? pairs : vec.pairs;

            int dotProduct = 0;
            for (int[] arr : smallerArr) {
                int idx = arr[0], val1 = arr[1], targetIdx = arr[0];
                int val2 = findTargetValueUsingBinarySearch(largerArr, targetIdx);
                val2 = val2 == -1 ? 0 : val2;
                dotProduct += val1 * val2;
            }

            return dotProduct;
        }

        int findTargetValueUsingBinarySearch(List<int[]> arrays, int targetIdx) {
            int leftPtr = 0, rightPtr = arrays.size() - 1, mid = 0;
            while (leftPtr <= rightPtr) {
                //r-l computed first to avoid overflow of int
                //adding l to r-l because we don't want offset from left but the actual mid idx
                mid = (leftPtr + (rightPtr - leftPtr)) / 2;
                if (arrays.get(mid)[0] == targetIdx) {
                    return arrays.get(mid)[1];
                }
                if (arrays.get(mid)[0] > targetIdx) {
                    rightPtr = mid - 1;
                } else {
                    leftPtr = mid + 1;
                }
            }
            return -1;
        }
    }

    class SparseVector {

        //TODO : Alternatively, use a hashmap or List<int[]> to store nums for indices which only have non zero elts
        int[] vector;

        SparseVector(int[] nums) {
            vector = nums;
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int sum = 0;
            for (int i = 0; i < vector.length; i++) {
                sum += vector[i] * vec.vector[i];
            }
            return sum;
        }
    }
  
}
