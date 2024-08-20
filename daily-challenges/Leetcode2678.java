/*
 * Number of Senior Citizens
 * You are given a 0-indexed array of strings details. Each element of details provides information about a given passenger compressed into a string of length 15. 
 * The system is such that:
   ** The first ten characters consist of the phone number of passengers.
   ** The next character denotes the gender of the person.
   ** The following two characters are used to indicate the age of the person.
   ** The last two characters determine the seat allotted to that person.
   ** Return the number of passengers who are strictly more than 60 years old.
 */
class Leetcode2678 {
    public int countSeniors(String[] details) {
        int seniorCount = 0; 
        for(int i=0; i<details.length; i++) {
            //array length is 15
            String age_str = details[i].substring(11, 13); 
            Integer age_int = Integer.parseInt(age_str);
            if(age_int > 60) {
                seniorCount++; 
            }
        }
        return seniorCount; 
    }
}
