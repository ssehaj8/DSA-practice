import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class countTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long,Long> map = new HashMap<>();

        for(int i = arr.size()-1;i>=0;i--){
            if(map.containsKey(arr.get(i))){
                map.put(arr.get(i), map.get(arr.get(i))+1);
            }
            else{
                map.put(arr.get(i), 1L);
            }
        }
        long count = 0;

        HashMap<Long,Long> leftMap = new HashMap<>();
        for(int i = 0;i<arr.size();i++){

            map.put(arr.get(i),map.get(arr.get(i))-1);

            if(arr.get(i)%r == 0){
                long lower = arr.get(i)/r;
                long upper = arr.get(i)*r;


                count += (leftMap.getOrDefault(lower,0L) * map.getOrDefault(upper,0L));
            }

            leftMap.put(arr.get(i), leftMap.getOrDefault(arr.get(i),0L)+1);
        }

        return count;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
