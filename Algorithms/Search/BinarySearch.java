package com.ria.gradle.algorithms.search;

/**
 * 二分查找
 */
public class BinarySearch {
    /**
     * 二分查找非递归实现
     */
    public int binarySearch(int[] array, int target) {
        int len = array.length;
        if (len < 1) {
            return -1;
        }

        int low = 0, high = len-1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找递归实现
     */
    public int recursiveBinarySearch(int[] array, int target) {
        int len = array.length;
        if (len < 1) {
            return -1;
        }
        return recursiveBinarySearch(array, 0, len - 1, target);
    }

    private int recursiveBinarySearch(int[] array, int low, int high, int target) {
        if (low > high || high >= array.length) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] < target) {
            return recursiveBinarySearch(array, mid + 1, high, target);
        } else {
            return recursiveBinarySearch(array, low, mid - 1, target);
        }
    }

    /**
     * 二分查找延伸：寻找第一个等于给定值的元素位置
     */
    public int findFirstPos(int[] array, int target) {
        int len = array.length;
        if (len < 1) {
            return -1;
        }

        int low = 0, high = len - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > target) {
                high = mid - 1;
            } else if (array[mid] < target){
                low = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 二分查找延伸：寻找最后一个等于给定元素的位置
     */
    public int findLastPos(int[] array, int target) {
        int len = array.length;
        if (len < 1) {
            return -1;
        }

        int low = 0, high = len - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < target) {
                low = mid + 1;
            } else if (array[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == len - 1 || array[mid + 1] > target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        return -1;
    }

    /**
     * 二分查找延伸：寻找第一个大于等于指定元素的位置
     */
    public int findFirstMaxPos(int[] array, int target) {
        int len = array.length;
        if (len < 1) {
            return -1;
        }

        int low = 0, high = len - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] >= target) {
                if (mid == 0 || array[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 二分查找延伸：寻找最后一个小于等于指定元素的位置
     */
    public int findLastMinPos(int[] array, int target) {
        int len = array.length;
        if (len < 1) {
            return -1;
        }

        int low = 0, high = len - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == len - 1 || array[mid + 1] > target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 4, 5, 6, 7, 8, 9, 10};

        int pos = new BinarySearch().binarySearch(array, 7);
        System.out.println("pos = " + pos);

        int pos2 = new BinarySearch().recursiveBinarySearch(array,7);
        System.out.println("pos2 = " + pos2);

        int[] array3 = {1, 3, 4, 4, 5, 6, 6, 6, 7, 8, 9, 10};
        int pos3 = new BinarySearch().findFirstPos(array3, 6);
        System.out.println("pos3 = " + pos3);

        int[] array4 = {1, 3, 4, 4, 5, 6, 6, 6, 7, 8, 9, 10};
        int pos4 = new BinarySearch().findLastPos(array4, 6);
        System.out.println("pos4 = " + pos4);

        int[] array5 = {1, 3, 4, 4, 5, 6, 6, 6, 8, 9, 10};
        int pos5 = new BinarySearch().findFirstMaxPos(array5, 7);
        System.out.println("pos5 = " + pos5);


        int[] array6 = {1, 3, 4, 4, 5, 6, 6, 6, 8, 9, 10};
        int pos6 = new BinarySearch().findLastMinPos(array6, 7);
        System.out.println("pos6 = " + pos6);
    }
}
