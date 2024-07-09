/*
 * Average Waiting Time
 * There is a restaurant with a single chef. You are given an array customers, where customers[i] = [arrivali, timei]:
   ** arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
   ** timei is the time needed to prepare the order of the ith customer.
 * When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. 
 * The customer waits till the chef finishes preparing his order. 
 * The chef does not prepare food for more than one customer at a time. 
 * The chef prepares food for customers in the order they were given in the input.
 * Return the average waiting time of all customers. Solutions within 10-5 from the actual answer are considered accepted.
 */
class Leetcode1701 {
    public double averageWaitingTime(int[][] customers) {
      //TODO : This solution has to be checked and optimized.
        long currentTime = 0; 
        long preparationTime = 0;         
        long waitingTime = 0;         
        long totalWaitingTime = 0; 
        long totalCustomers = customers.length; 

        for(int i=0; i<totalCustomers; i++) {
            long arrivalTime = customers[i][0]; 
            long currentPreparationTime = customers[i][1]; 
            currentTime = Math.max(currentTime, arrivalTime); 
            preparationTime = currentTime + currentPreparationTime; 
            currentTime = preparationTime; 

            waitingTime = preparationTime - arrivalTime; 
            totalWaitingTime += waitingTime; 
        }    

        double averageWaitingTime = (double) totalWaitingTime/totalCustomers; 
        return averageWaitingTime; 
    }
}
