import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CalendarApp {

    private static int year;
    private static MonthAndOrdinal monthAndOrdinal;
    public static void main(String[] args) {

        if (!validateAndParseArguments(args)) {
            System.out.println("Invalid arguments!");
            System.out.println("Usage: java CalendarApp <year> <month>");
            return;
        }

        showCalendar();
        
    }

    public static boolean validateAndParseArguments(String[] args) {

        if(args.length != 2 || args[0].length() != 4 || args[1].length() != 3) {
            return false;
        }
        try {
            year = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            return false;
        }

        monthAndOrdinal = convert(args[1]);
        return null != monthAndOrdinal;
    }

    public static MonthAndOrdinal convert(String month) {

        String monthToLowercase = month.toLowerCase();
        switch(monthToLowercase) {
            case "jan"  : return new MonthAndOrdinal("January",1);
            case "feb"  : return new MonthAndOrdinal("February", 2);
            case "mar"  : return new MonthAndOrdinal("March", 3);
            case "apr"  : return new MonthAndOrdinal("April", 4);
            case "may"  : return new MonthAndOrdinal("May", 5);
            case "jun"  : return new MonthAndOrdinal("June", 6);
            case "jul"  : return new MonthAndOrdinal("July", 7);
            case "aug"  : return new MonthAndOrdinal("August", 8);
            case "sep"  : return new MonthAndOrdinal("September", 9);
            case "oct"  : return new MonthAndOrdinal("October", 10);
            case "nov"  : return new MonthAndOrdinal("November", 11);
            case "dec"  : return new MonthAndOrdinal("December", 12);
            
            default : return null;
        }
    }

    public static void showCalendar() {

        YearMonth yearAndMonth = YearMonth.of(year,monthAndOrdinal.ordinal);
        int daysOfMonth = yearAndMonth.lengthOfMonth();

        LocalDate firstDayofMonth = yearAndMonth.atDay(1);
        int firstDayofWeek = firstDayofMonth.getDayOfWeek().getValue();

        System.out.println(monthAndOrdinal.month + " " + year);
        System.out.println("Su\tMo\tTu\tWe\tTh\tFr\tSa");

        List<String> calendar = new ArrayList<>();

        int startingPlaceOfWeek = firstDayofWeek % 7;
        for(int i = 1; i <= startingPlaceOfWeek; i ++) {
            calendar.add("\t");
        }

        int currentWeekday = startingPlaceOfWeek;
        for(int day = 1; day <= daysOfMonth; day ++) {
            calendar.add(String.valueOf(day) + "\t");
            currentWeekday++;
            if(currentWeekday % 7 == 0) {
                calendar.add("\n");
                currentWeekday = 0;
            }
        }

        for(String c : calendar) {
            System.out.print(c);
        }
    }
}

class MonthAndOrdinal {

    String month;
    int ordinal;

    public MonthAndOrdinal(String month,int ordinal) {
        this.month = month;
        this.ordinal = ordinal;
    }
}