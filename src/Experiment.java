public class Experiment {

    private Sorter sorter = new Sorter();
    private Searcher searcher = new Searcher();

    public long measureSortTime(int[] arr, String type) {
        int[] copy = arr.clone();

        long start = System.nanoTime();

        if (type.equals("basic")) {
            sorter.basicSort(copy);
        } else {
            sorter.advancedSort(copy);
        }

        long end = System.nanoTime();
        return end - start;
    }

    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();

        searcher.search(arr, target);

        long end = System.nanoTime();
        return end - start;
    }

    public void runAllExperiments() {

        int[] sizes = {10, 100, 1000};

        for (int size : sizes) {

            System.out.println("\n===============================");
            System.out.println("Array size: " + size);

            // 🔹 RANDOM ARRAY
            System.out.println("\n--- RANDOM ARRAY ---");
            int[] randomArr = sorter.generateRandomArray(size);

            long bubbleRandom = measureSortTime(randomArr, "basic");
            long mergeRandom = measureSortTime(randomArr, "advanced");

            sorter.advancedSort(randomArr); // для бинарного поиска
            long searchRandom = measureSearchTime(randomArr, randomArr[size / 2]);

            System.out.println("Bubble Sort: " + bubbleRandom);
            System.out.println("Merge Sort: " + mergeRandom);
            System.out.println("Binary Search: " + searchRandom);

            // 🔹 SORTED ARRAY
            System.out.println("\n--- SORTED ARRAY ---");
            int[] sortedArr = sorter.generateSortedArray(size);

            long bubbleSorted = measureSortTime(sortedArr, "basic");
            long mergeSorted = measureSortTime(sortedArr, "advanced");

            long searchSorted = measureSearchTime(sortedArr, sortedArr[size / 2]);

            System.out.println("Bubble Sort: " + bubbleSorted);
            System.out.println("Merge Sort: " + mergeSorted);
            System.out.println("Binary Search: " + searchSorted);
        }
    }
}