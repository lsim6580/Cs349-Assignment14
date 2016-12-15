import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by luke on 12/1/2016.
 */
public class FunctionalProgramming {
    public static void main(String[] args) throws Exception{
        //set out put to a file
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        double first = 0.0;
        double second = 0.0;
        
        //find all movies that made more than 500 million
        System.out.println("The top movies that made more than 500 million:");
        try (Stream<String> stream = Files.lines(Paths.get("src/Movie.txt"))) {
            List<String> topMovies = stream
                    .filter((line) -> (Double.valueOf(line.split("[|]")[2]) > 500))
                    .collect(Collectors.toList());
            topMovies.stream().forEach((movie) -> System.out.println(movie));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //find the top grossing movie
        try (Stream<String> stream = Files.lines(Paths.get("src/Movie.txt"))) {
            System.out.println();
            System.out.println();
            System.out.println("The top grossing movie is");
            Optional<String> best = stream.sorted((line1, line2) -> (Double.valueOf(line2.split("[|]")[2]))
                    .compareTo(Double.valueOf(line1.split("[|]")[2])))
                    .findFirst();
            System.out.println(best  .get());

        } catch (IOException e) {
            e.printStackTrace();
        }

        //find which decade made the most money
        try (Stream<String> stream = Files.lines(Paths.get("src/Movie.txt"))) {
            first = stream
                    .filter((line) -> (line.split("[|]")[3].charAt(2) == '7'))
                    .mapToDouble((line)->Double.valueOf(line.split("[|]")[2]))
                    .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Stream<String> stream = Files.lines(Paths.get("src/Movie.txt"))) {
            second = stream
                    .filter((line) -> (line.split("[|]")[3].charAt(2) == '8'))
                    .mapToDouble((line)->Double.valueOf(line.split("[|]")[2]))
                    .sum();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println((first > second ? "the 70s made more money " + first: "the 80s made more money " + second));
    }
}
