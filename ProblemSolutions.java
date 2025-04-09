/******************************************************************
 *
 *   Alexa Solorzano
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values        - int[] array to be sorted.
     * @param ascending     - if true,method performs an ascending sort, else descending.
     *                        There are two method signatures allowing this parameter
     *                        to not be passed and defaulting to 'true (or ascending sort).
     */
//Helper Method
    public  void selectionSort(int[] values) {
        selectionSort(values, true); //When the method is called, it assumes ascending sort 
    }
    /**
     * Selection sort works by repeatedly selecting the smallest (or largest) element from the unsorted portion of the list & moving it to the sorted portion of the list
     * Pseudocode from notes: 1) looks through the unsorted part of the array 2) finds the smallest value 3) swap it with the value of the current position
     * for(int = 0 to a.length-1) //Goes through every index in the array 
     *    min = i; //Assumes that the first index is the smallest
     *    for(j = i+1 to a.length-1) //Look through the rest of the array ---> this is where we find the real min index
     *       if(a[j] < a[min]) //If another index is smaller than the current min 
     *          min = j; //Update the min variable to the actual min 
     *       if(min != i) //If there was another min found 
     *          swap a[min] and a[j] //Swap with the correct value 
     *
     * This pseudocode assumes ascending order 
     * The difference between this pseudocode and selectionSort is that we have to account for being controlled by boolean ascending (which can either be TRUE--which will make it ascending sort OR FALSE--which would result in descending sort
     * The new code has to be able to dynamically change between both ascending and descending 
     * 
     * New pseudocode:
     * Get array length 
     * Go through array length 
     * Instead of initializing a variable that reflects the min index, we need a variable that can either reflect the min (ascending sort) OR the max (descending sort)
     * Iterate over the rest of the unsorted array to find the actual largest/smallest value placed at index i
     * if(we are sorting in ascending order)
     *    compare the values of the current value at the current index (in the loop) with the smallest value found so far, 
     *        if the current value is smaller than the variable that reflects the min, update the new min 
     * else, that means that we are going to sort in descending order 
     *    compare the values of the current value at the current index (in the loop) with the largest value found so far, 
     *        if the current value is larger than the variable that reflects the max, update the new max 
     *
     * Now the psuedocode from our class notes and selectionSort become similar, we have to swap with the correct value
     * We have to move the correct smallest/largest value into the proper place in the array
     * If the min/max value found is not equal to i
     * then, we store the current value at position i in a temporary variable
     * Replace the value at position i with the value of the min/max variable
     * Put the original value from the temp variable into the min/max position 
     * //now it is swapped into the correct position 
     */
    public static void selectionSort(int[] values, boolean ascending ) {

        int n = values.length;

        for (int i = 0; i < n - 1; i++) {
            int targetIndex = i;
            
            for(int j = i + 1; j < n; j++){
                if(ascending){
                    if(values[j] < values[targetIndex]){
                        targetIndex = j;
                    }
                } else {
                    if(values[j] > values[targetIndex]){
                        targetIndex = j;
                    }
                }
            }

            if(targetIndex != i){
                int temp = values[i];
                values[i] = values[targetIndex];
                values[targetIndex] = temp;
            }
        }
    } // End class selectionSort


    /**
     *  Method mergeSortDivisibleByKFirst
     *
     *  This method will perform a merge sort algorithm. However, all numbers
     *  that are divisible by the argument 'k', are returned first in the sorted
     *  list. Example:
     *        values = { 10, 3, 25, 8, 6 }, k = 5
     *        Sorted result should be --> { 10, 25, 3, 6, 8 }
     *
     *        values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     *        Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     *
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values    - input array to sort per definition above
     * @param k         - value k, such that all numbers divisible by this value are first
     *
     * The usual implementation of merge sort has 3 steps (referring to class notes):
     * Divide: divide the input data S in two disjoint subsets S1 and S2
     * Recur: solve the subproblems associated with S1 and S
     * Conquer: combine the solutions for S1 and S2 into a solution for S
     * 
     * 
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {

        // Protect against bad input values
        if (k == 0)  return; //One cannot divide by 0 because it will result in a "undefined"
        if (values.length <= 1)  return; //No sorting needed for empty or arrays with only one element in it

        mergeSortDivisibleByKFirst(values, k, 0, values.length-1); //Recursive merge sort
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {
        //If there is one or no elements in the current portion, then return because there is nothing to sort
        if (left >= right) 
            return;

        int mid = left + (right - left) / 2; //Split the array into two halves--1st step
        //2nd step: Recur--sort both halves 
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        //3rd step: Conquer--combine the solutions
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    /*
     * The merging portion of the merge sort, divisible by k first
     */

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right)
    {
        // YOUR CODE GOES HERE, THIS METHOD IS NO MORE THAN THE STANDARD MERGE PORTION
        // OF A MERGESORT, EXCEPT THE NUMBERS DIVISIBLE BY K MUST GO FIRST WITHIN THE
        // SEQUENCE PER THE DISCUSSION IN THE PROLOGUE ABOVE.
        //
        // NOTE: YOU CAN PROGRAM THIS WITH A SPACE COMPLEXITY OF O(1) OR O(N LOG N).
        // AGAIN, THIS IS REFERRING TO SPACE COMPLEXITY. O(1) IS IN-PLACE, O(N LOG N)
        // ALLOCATES AUXILIARY DATA STRUCTURES (TEMPORARY ARRAYS). IT WILL BE EASIER
        // TO CODE WITH A SPACE COMPLEXITY OF O(N LOG N), WHICH IS FINE FOR PURPOSES
        // OF THIS PROGRAMMING EXERCISES.

        return;

    }


    /**
     * Method asteroidsDestroyed
     *
     * You are given an integer 'mass', which represents the original mass of a planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the mass
     * of the ith asteroid.
     *
     * You can arrange for the planet to collide with the asteroids in any arbitrary order.
     * If the mass of the planet is greater than or equal to the mass of the asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
     * planet is destroyed.
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     *
     * Example 1:
     *   Input: mass = 10, asteroids = [3,9,19,5,21]
     *   Output: true
     *
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
     * All asteroids are destroyed.
     *
     * Example 2:
     *   Input: mass = 5, asteroids = [4,9,23,4]
     *   Output: false
     *
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     *
     * Constraints:
     *     1 <= mass <= 105
     *     1 <= asteroids.length <= 105
     *     1 <= asteroids[i] <= 105
     *
     * @param mass          - integer value representing the mass of the planet
     * @param asteroids     - integer array of the mass of asteroids
     * @return              - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {

        // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT()

        return false;

    }


    /**
     * Method numRescueSleds
     *
     * You are given an array people where people[i] is the weight of the ith person,
     * and an infinite number of rescue sleds where each sled can carry a maximum weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     *
     * Example 1:
     *    Input: people = [1,2], limit = 3
     *    Output: 1
     *    Explanation: 1 sled (1, 2)
     *
     * Example 2:
     *    Input: people = [3,2,2,1], limit = 3
     *    Output: 3
     *    Explanation: 3 sleds (1, 2), (2) and (3)
     *
     * Example 3:
     *    Input: people = [3,5,3,4], limit = 5
     *    Output: 4
     *    Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people    - an array of weights for people that need to go in a sled
     * @param limit     - the weight limit per sled
     * @return          - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {

        // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT

        return -1;

    }

} // End Class ProblemSolutions

