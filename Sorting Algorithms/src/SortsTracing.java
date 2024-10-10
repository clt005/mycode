import java.util.ArrayList;

public class SortsTracing {

    //Note: Style is not required for this file

    //for reference
    public ArrayList<int[]> SelectionSortExampleList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{-1, 20, 18, 17, 9, 4, 2, 0, 40});
        answer.add(new int[]{-1, 0, 18, 17, 9, 4, 2, 20, 40});
        answer.add(new int[]{-1, 0, 2, 17, 9, 4, 18, 20, 40});
        answer.add(new int[]{-1, 0, 2, 4, 9, 17, 18, 20, 40});
        answer.add(new int[]{-1, 0, 2, 4, 9, 17, 18, 20, 40});
        answer.add(new int[]{-1, 0, 2, 4, 9, 17, 18, 20, 40});
        // etc... (the rest of the iterations)
        return answer;
    }

    //for reference
    public ArrayList<int[]> CountSortExampleList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        //After Counting Step
        answer.add(new int[]{0, 1, 1, 0, 1});
        //After "Running Total" Step
        answer.add(new int[]{0, 1, 2, 2, 3});
        //Rest of iterations to assemble output array
        answer.add(new int[]{0, 0, 2, 2, 3});
        answer.add(new int[]{0, 0, 2, 2, 2});
        answer.add(new int[]{0, 0, 1, 2, 2});
        return answer;
    }

    public ArrayList<int[]> InsertionSortRandomList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // random = [7,0,9,4,1,3,6]
        answer.add(new int[]{0,7,9,4,1,3,6});
        answer.add(new int[]{0,7,9,4,1,3,6});
        answer.add(new int[]{0,4,7,9,1,3,6});
        answer.add(new int[]{0,1,4,7,9,3,6});
        answer.add(new int[]{0,1,3,4,7,9,6});
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        return answer;
    }

    public ArrayList<int[]> InsertionSortSortedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // sorted = [0,1,3,4,6,7,9]
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        return answer;
    }

    public ArrayList<int[]> InsertionSortReversedList() {
        // reversed = [9,7,6,4,3,1,0]
        ArrayList<int[]> answer = new ArrayList<int[]>();
        answer.add(new int[]{7,9,6,4,3,1,0});
        answer.add(new int[]{6,7,9,4,3,1,0});
        answer.add(new int[]{4,6,7,9,3,1,0});
        answer.add(new int[]{3,4,6,7,9,1,0});
        answer.add(new int[]{1,3,4,6,7,9,0});
        answer.add(new int[]{0,1,3,4,6,7,9});
        answer.add(new int[]{0,1,3,4,6,7,9});
        return answer;
    }
    
    public ArrayList<int[]> CountSortRandomList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // random = [7,0,9,4,1,3,6]
        // After Counting Step
        answer.add(new int[]{1,1,0,1,1,0,1,1,0,1});
        // After "Running Total" Step
        answer.add(new int[]{1,2,2,3,4,4,5,6,6,7});
        // Rest of iterations to assemble output array
        answer.add(new int[]{1,2,2,3,4,4,5,5,6,7});
        // [0,0,0,0,0,7,0]
        answer.add(new int[]{0,2,2,3,4,4,5,5,6,7});
        // [0,0,0,0,0,7,0]
        answer.add(new int[]{0,2,2,3,4,4,5,5,6,6});
        // [0,0,0,0,0,7,9]
        answer.add(new int[]{0,2,2,3,3,4,5,5,6,6});
        // [0,0,0,4,0,7,9]
        answer.add(new int[]{0,1,2,3,3,4,5,5,6,6});
        // [0,1,0,4,0,7,9]
        answer.add(new int[]{0,1,2,2,3,4,5,5,6,6});
        // [0,1,3,4,0,7,9]
        answer.add(new int[]{0,1,2,2,3,4,4,5,6,6});
        // [0,1,3,4,6,7,9]
        return answer;
    }

    public ArrayList<int[]> CountSortSortedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // sorted = [0,1,3,4,6,7,9]
        // After Counting Step
        answer.add(new int[]{1,1,0,1,1,0,1,1,0,1});
        // After "Running Total" Step
        answer.add(new int[]{1,2,2,3,4,4,5,6,6,7});
        // Rest of iterations to assemble output array
        answer.add(new int[]{0,2,2,3,4,4,5,6,6,7});
        // [0,0,0,0,0,0,0]
        answer.add(new int[]{0,1,2,3,4,4,5,6,6,7});
        // [0,1,0,0,0,0,0]
        answer.add(new int[]{0,1,2,2,4,4,5,6,6,7});
        // [0,1,3,0,0,0,0]
        answer.add(new int[]{0,1,2,2,3,4,5,6,6,7});
        // [0,1,3,4,0,0,0]
        answer.add(new int[]{0,1,2,2,3,4,4,6,6,7});
        // [0,1,3,4,6,0,0]
        answer.add(new int[]{0,1,2,2,3,4,4,5,6,7});
        // [0,1,3,4,6,7,0]
        answer.add(new int[]{0,1,2,2,3,4,4,5,6,6});
        // [0,1,3,4,6,7,9]
        return answer;
    }

    public ArrayList<int[]> CountSortReversedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // reversed = [9,7,6,4,3,1,0]
        // After Counting Step
        answer.add(new int[]{1,1,0,1,1,0,1,1,0,1});
        // After "Running Total" Step
        answer.add(new int[]{1,2,2,3,4,4,5,6,6,7});
        // Rest of iterations to assemble output array
        answer.add(new int[]{1,2,2,3,4,4,5,6,6,6});
        // [0,0,0,0,0,0,9]
        answer.add(new int[]{1,2,2,3,4,4,5,5,6,6});
        // [0,0,0,0,0,7,9]
        answer.add(new int[]{1,2,2,3,4,4,4,5,6,6});
        // [0,0,0,0,6,7,9]
        answer.add(new int[]{1,2,2,3,3,4,4,5,6,6});
        // [0,0,0,4,6,7,9]
        answer.add(new int[]{1,2,2,2,3,4,4,5,6,6});
        // [0,0,3,4,6,7,9]
        answer.add(new int[]{1,1,2,2,3,4,4,5,6,6});
        // [0,1,3,4,6,7,9]
        answer.add(new int[]{0,1,2,2,3,4,4,5,6,6});
        // [0,1,3,4,6,7,9]
        return answer;
    }

    public ArrayList<int[]> QuickSortRandomList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // random = [6,1,9,3,2]
        /*
         QuickSort(list,0,4);
         pivotIndex = inPlacePartition(list,0,4,0)
         pivot = arr.get(0) = 6
         swap(arr,pivotix=0,stop=4)
         temp = arr.get(0) = 6
         arr.set(0, arr.get(4) = 2) & arr.set(4, temp = 6) -> [2,1,9,3,6]
         middleBarrier = start = 0
         for (int endBarrier = 0; endBarrier < stop = 4; endBarrier++)
            if(arr.get(endBarrier) < pivot = 6)
                swap(arr, middleBarrier, endBarrier)
                middleBarrier++;
         1. arr.get(0) = 2 < 6 -> swap(arr,0,0) & mid=1
         2. arr.get(1) = 1 < 6 -> swap(arr,1,1) & mid=2
         3. arr.get(2) = 9 > 6 -> mid=2
         4. arr.get(3) = 3 < 6 -> swap(arr,2,3) -> [2,1,3,9,6] & mid=3
         swap(arr,3,4) -> [2,1,3,6,9] -> answer
         pivotIndex = mid = 3
        */
        answer.add(new int[]{2,1,3,6,9});
        /*
         QuickSort(list,0,2); -> [2,1,3]
         pivotIndex = inPlacePartition(list,0,2,0)
         pivot = arr.get(0) = 2
         swap(arr,pivotix=0,stop=2) -> [3,1,2]
         mid= start =0
         for (end=0, end<stop=2, end++)
         1. arr.get(0) = 3 > 2 -> mid=0
         2. arr.get(1) = 1 < 2 -> swap(arr,0,1) -> [1,3,2] & mid=1
         swap(arr,1,2) -> [1,2,3] -> answer
         pivotIndex = mid = 1
        */
        answer.add(new int[]{1,2,3,6,9});
        /*
         QuickSort(list,0,0) -> end
         QuickSort(list,2,2) -> end
         QuickSort(list,4,4) -> end
        */
        return answer;
    }

    public ArrayList<int[]> QuickSortSortedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // sorted = [1,2,3,6,9]
        /*
         QuickSort(list,0,4)
         pivotIndex = inPlacePartition(list,0,4,0)
         pivot = arr.get(0) = 1
         swap(arr,pivotix=0,stop=4) -> [9,2,3,6,1]
         mid = start = 0
         for (end=0, end<stop=4, end++)
         1. arr.get(0) = 9 > 1 -> mid = 0
         2. arr.get(1) = 2 > 1 -> mid = 0
         3. arr.get(2) = 3 > 1 -> mid = 0
         4. arr.get(3) = 6 > 1 -> mid = 0
         swap(arr,mid=0,4) -> [1,2,3,6,9] -> answer
         pivotIndex = mid = 0
        */
        answer.add(new int[]{1,2,3,6,9});
        /*
         QuickSort(list,0,-1) -> end
         QuickSort(list,1,4) -> [1,2,3,6,9] & pivotIndex = mid = start = 1
         QuickSort(list,1,0) -> end
         QuickSort(list,2,4) -> [1,2,3,6,9] & pivotIndex = mid = start = 2
         QuickSort(list,2,1) -> end
         QuickSort(list,3,4) -> [1,2,3,6,9] & pivotIndex = mid = start = 3
         QuickSort(list,3,2) -> end
         QuickSort(list,4,4) -> end
        */
        answer.add(new int[]{1,2,3,6,9});
        answer.add(new int[]{1,2,3,6,9});
        answer.add(new int[]{1,2,3,6,9});
        return answer;
    }

    public ArrayList<int[]> QuickSortReversedList() {
        ArrayList<int[]> answer = new ArrayList<int[]>();
        // reversed = [9,6,3,2,1]
        /*
         QuickSort(list,0,4)
         pivotIndex = inPlacePartition(list,0,4,0)
         pivot = arr.get(0) = 9
         swap(arr,pivotix=0,stop=4) -> [1,6,3,2,9]
         .
         .
         .
         swap(arr,mid=4,4) -> [1,6,3,2,9] -> answer
         pivotIndex = mid = 4
        */
        answer.add(new int[]{1,6,3,2,9});
        /*
        QuickSort(list,0,3) -> [1,6,3,2]
        pivotIndex = inPlacePartition(list,0,3,0)
        pivot = arr.get(0) = 1
        swap(arr,pivotix=0,stop=3) -> [2,6,3,1]
        .
        .
        .
        swap(arr,mid=0,stop=3) -> [1,6,3,2] -> answer
        pivotIndex = mid = 0
        */
        answer.add(new int[]{1,6,3,2,9});
        /*
         QuickSort(list,0,-1) -> end
         QuickSort(list,1,3) -> [6,3,2]
         pivotIndex = inPlacePartition(list,1,3,1)
         pivot = arr.get(1) = 6
         swap(arr,pivotix=1,stop=3) -> [2,3,6]
         mid = start = 1
         for (end=start=1, end < stop=3, end++)
         1. arr.get(1) = 2 < 6 -> swap(arr,1,1) -> [2,3,6] & mid = 2
         2. arr.get(2) = 3 < 6 -> swap(arr,2,2) -> [2,3,6] & mid = 3
         swap(arr,mid=3,stop=3) -> [2,3,6]
         pivotIndex = mid = 3
        */
        answer.add(new int[]{1,2,3,6,9});
        /*
         QuickSort(list,1,2) -> [2,3]
         answer -> [1,2,3,6,9]
        */
        answer.add(new int[]{1,2,3,6,9});
        /*
         QuickSort(list,4,3) -> end
         QuickSort(list,5,4) -> end
        */
        return answer;
    }

}
