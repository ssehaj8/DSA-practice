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

class Result {

    /*
     * Complete the 'largestRectangle' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY h as parameter.
     */

    public static long largestRectangle(List<Integer> h) {
        // Write your code here
        Stack<int[]> stack = new Stack<>();
        long maxArea = 0;

        for (int i = 0; i < h.size(); i++) {
            int start = i;
            while (!stack.isEmpty() && stack.peek()[1] > h.get(i)) {
                int[] top = stack.pop();
                int index = top[0];
                int height = top[1];
                long area = (long) height * (i - index);
                maxArea = Math.max(maxArea, area);
                start = index;
            }
            stack.push(new int[]{start, h.get(i)});
        }

        int size = h.size();
        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            int index = top[0];
            int height = top[1];
            long area = (long) height * (size - index);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;


    }

}

public class LargestRectangle {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> h = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
