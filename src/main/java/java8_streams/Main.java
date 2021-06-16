package java8_streams;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        workWithUUID();
        defineEndOfWorldDay();
        createObjectsFromReadFile();
    }

    public static void workWithUUID() {
        List<UUID> uuidList = Stream.generate(UUID::randomUUID)
                .limit(10000)
                .collect(Collectors.toList());

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("result.txt")))){
            pw.write(String.valueOf(uuidList));
        } catch (Exception e){
            e.printStackTrace();
        }

        try (Stream<String> lineStream = Files.lines(Paths.get("result.txt"))) {
            long totalNumberUUID = lineStream.map(line -> line.split(", "))
                    .flatMap(Arrays::stream)
                    .filter(line -> sumOfNumbers(line) > 100)
                    .count();
            System.out.println("Количество элементов UUID, в которых сумма цифр > 100: " + totalNumberUUID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    4.	Найти дату конца света по формуле: сегодня + N месяцев + M дней,
    где N – первые два числа от полученного значения, а М – вторые.
    Значение с ведущими нулями, если цифр меньше 4.
    По тихоокеанской временной зоне. Дату вывести в формате
    ISO с датой и временем (см DateTimeFormatter)
     */
    public static void defineEndOfWorldDay() {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.of( "Pacific/Auckland" );
        ZonedDateTime zdt = instant.atZone( zoneId );

        int N = Integer.parseInt(zdt.toString().substring(0,2));
        int M = Integer.parseInt(zdt.toString().substring(2,4));

        ZonedDateTime endOfWorldDay = zdt.plusMonths(N).plusDays(M);
        String output = endOfWorldDay.format( DateTimeFormatter.ISO_LOCAL_DATE_TIME );
        System.out.println("End of World Day is " + output);
    }

    /*
    Одним стримом считать строки из файла и создать
    из полученных данных экземпляры объектов с данными.
     */
    public static void createObjectsFromReadFile() {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        Pattern pattern = Pattern.compile(",");

        try (Stream<String> lines = Files.lines(Paths.get("File.txt"))) {
            List<Sausage> sausages = lines.map(line -> {
                String[] arr;
                arr = pattern.split(new String(decoder.decode(line), StandardCharsets.UTF_8));
                return new Sausage(
                                arr[0].substring(6, arr[0].length() - 1),
                                Integer.parseInt(arr[1].substring(8)),
                                Long.parseLong(arr[2].substring(6)));
                    }).collect(Collectors.toList());
            sausages.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int sumOfNumbers(String s){
        int sum = 0;
        for (int i = 0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i))) {
                sum = sum + Character.getNumericValue(s.charAt(i));
            }
        }
        return sum;
    }
}
