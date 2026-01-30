import java.util.Scanner;

public class Project4Rivelli {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        double gpuClockSpeed = 0;
        double cpuClockSpeed = 0;
        double multiplier = 0;
        double performanceScore = 0;
        double average = 0;
        double totalPerformanceScores = 0;

        int cpuCoreNumber = 0;
        int monitorResolution = 0;
        int computerNum = 0;

        String processor;
        String input;
        String recommendedQuality;
        String monitorResolutionAsString;

        char overclock;
        char repeat;

        final int CORES_LOW_THRESHOLD = 1, CORES_HI_THRESHOLD = 20;

        final double CLOCK_SPEED_GPU_LOW_THRESHOLD = 800,
                CLOCK_SPEED_GPU_HI_THRESHOLD = 2000,
                CLOCK_SPEED_CPU_LOW_THRESHOLD = 1000,
                CLOCK_SPEED_CPU_HI_THRESHOLD = 5500;

        System.out.println("Please enter the type of processor");
        processor = keyboard.nextLine();

        do {
            displayTitle();
            System.out.println();

            System.out.println("Please enter the clock speed (in Megahertz) of your graphics card: ");
            gpuClockSpeed = keyboard.nextDouble();

            while (gpuClockSpeed < CLOCK_SPEED_GPU_LOW_THRESHOLD || gpuClockSpeed > CLOCK_SPEED_GPU_HI_THRESHOLD) {
                System.out.println("The clock speed should be between " + CLOCK_SPEED_GPU_LOW_THRESHOLD + " and " + CLOCK_SPEED_GPU_HI_THRESHOLD + " Megahertz.");
                System.out.println("Please enter the clock speed (in Megahertz of your graphics card: ");
                gpuClockSpeed = keyboard.nextDouble();
            }

            System.out.println("Please enter the clock speed (in Megahertz) of your processor");
            cpuClockSpeed = keyboard.nextDouble();

            while (cpuClockSpeed < CLOCK_SPEED_CPU_LOW_THRESHOLD || cpuClockSpeed > CLOCK_SPEED_CPU_HI_THRESHOLD) {
                System.out.println("The clock speed should be between " + CLOCK_SPEED_CPU_LOW_THRESHOLD + " and " + CLOCK_SPEED_CPU_HI_THRESHOLD);
                System.out.println("Please enter the clock speed (in Megahertz of your CPU.");
                cpuClockSpeed = keyboard.nextDouble();
            }

            System.out.println("Please enter the number of cores of your processor:");
            cpuCoreNumber = keyboard.nextInt();

            while (cpuCoreNumber < CORES_LOW_THRESHOLD || cpuCoreNumber > CORES_HI_THRESHOLD) {
                System.out.println("The number of cores should be between " + CORES_LOW_THRESHOLD + " and " + CORES_HI_THRESHOLD);
                System.out.println("Please enter the number of cores of your processor:");
                cpuCoreNumber = keyboard.nextInt();
            }

            System.out.println("Is the hardware overclock-friendly? (Enter y for yes or n for no)");
            input = keyboard.next();
            overclock = input.charAt(0);

            while (overclock != 'y' && overclock != 'n') {
                System.out.println("Error: the response should be y for yes or n for no. Please enter a valid letter:");
                input = keyboard.next();
                overclock = input.charAt(0);
            }

            monitorResolution = getMenuChoice();
            monitorResolutionAsString = getResolutionString(monitorResolution);
            multiplier = getMultiplierValue(monitorResolution);
            performanceScore = calculatePerformanceScore(gpuClockSpeed, cpuClockSpeed, cpuCoreNumber, multiplier);
            recommendedQuality = getRecommendedQuality(performanceScore);

            System.out.println();
            displayInformation(processor, monitorResolutionAsString, gpuClockSpeed, cpuClockSpeed, cpuCoreNumber, performanceScore, recommendedQuality, overclock);
            System.out.println();

            System.out.print("\nWould you like to enter another computer? y/n: ");
            repeat = keyboard.next().charAt(0);

            computerNum++;

            totalPerformanceScores += performanceScore;

            System.out.println();

        } while (repeat == 'y');

        average = totalPerformanceScores / computerNum;

        System.out.printf("The average performance score is: %,.3f", average);
    }

    public static void displayTitle() {
        String title = "Graphics Quality Recommendation Tool";
        System.out.println(title);
    }

    public static int getMenuChoice() {
        Scanner keyboard = new Scanner(System.in);
        int monitorResolution;
        System.out.println("What is the resolution of your monitor?");
        System.out.println("\t1. 1280 x 1024");
        System.out.println("\t2. 1366 x 768");
        System.out.println("\t3. 1600 x 900");
        System.out.println("\t4. 1920 x 1080");
        System.out.println("Please select from the options above: ");
        monitorResolution = keyboard.nextInt();

        while (monitorResolution < 1 || monitorResolution > 4) {
            System.out.println("Invalid menu selection. Please select from the options above:");
            monitorResolution = keyboard.nextInt();
        }

        return monitorResolution;
    }

    public static double getMultiplierValue(int monitorResolution) {
        final int RESOLUTION_1280_1024 = 1,
                RESOLUTION_1366_768 = 2,
                RESOLUTION_1600_900 = 3,
                RESOLUTION_1920_1080 = 4;

        final double MULTIPLIER_1280_1024 = 1.0,
                MULTIPLIER_1366_768 = .75,
                MULTIPLIER_1600_900 = .55,
                MULTIPLIER_1920_1080 = .35;

        double multiplier = 0;

        if (monitorResolution == RESOLUTION_1280_1024) {
            multiplier = MULTIPLIER_1280_1024;
        } else if (monitorResolution == RESOLUTION_1366_768) {
            multiplier = MULTIPLIER_1366_768;
        } else if (monitorResolution == RESOLUTION_1600_900) {
            multiplier = MULTIPLIER_1600_900;
        } else if (monitorResolution == RESOLUTION_1920_1080) {
            multiplier = MULTIPLIER_1920_1080;
        }

        return multiplier;
    }

    public static String getResolutionString(int monitorResolution) {
        final int RESOLUTION_1280_1024 = 1;
        final int RESOLUTION_1366_768 = 2;
        final int RESOLUTION_1600_900 = 3;
        final int RESOLUTION_1920_1080 = 4;

        String monitorResolutionAsString = "";

        if (monitorResolution == RESOLUTION_1280_1024) {
            monitorResolutionAsString = "1280 X 1024";
        } else if (monitorResolution == RESOLUTION_1366_768) {
            monitorResolutionAsString = "1366 X 768";
        } else if (monitorResolution == RESOLUTION_1600_900) {
            monitorResolutionAsString = "1600 X 900";
        } else if (monitorResolution == RESOLUTION_1920_1080) {
            monitorResolutionAsString = "1920 X 1080";
        }

        return monitorResolutionAsString;
    }

    public static double calculatePerformanceScore(double gpuClockSpeed, double cpuClockSpeed, int cpuCoreNumber, double multiplier) {
        int attribute = 6;
        return (attribute * gpuClockSpeed + (cpuCoreNumber * cpuClockSpeed)) * multiplier;
    }

    public static String getRecommendedQuality(double performanceScore) {
        double PERFORMANCE_THRESHOLD_HD_PLUS = 17000;
        double PERFORMANCE_THRESHOLD_HD = 15000;
        double PERFORMANCE_THRESHOLD_FHD = 13000;
        double PERFORMANCE_THRESHOLD_WUXGA = 11000;

        String recommendedQuality = "";

        if (performanceScore > PERFORMANCE_THRESHOLD_HD_PLUS) {
            recommendedQuality = "Ultra Extended";
        } else if (performanceScore > PERFORMANCE_THRESHOLD_HD) {
            recommendedQuality = "Ultra";
        } else if (performanceScore > PERFORMANCE_THRESHOLD_FHD) {
            recommendedQuality = "Medium";
        } else if (performanceScore > PERFORMANCE_THRESHOLD_WUXGA) {
            recommendedQuality = "Low";
        } else {
            recommendedQuality = "Unable to Play";
        }

        return recommendedQuality;
    }

    public static void displayInformation(String processor, String monitorResolutionAsString, double gpuClockSpeed, double cpuClockSpeed, int cpuCoreNumber, double performanceScore, String recommendedQuality, char overclock) {
        System.out.println("Monitor Resolution: " + monitorResolutionAsString);
        System.out.println("GPU Clock Speed: " + gpuClockSpeed + " MHz");
        System.out.println("CPU Clock Speed: " + cpuClockSpeed + " MHz");
        System.out.println("Number of Cores: " + cpuCoreNumber);
        System.out.printf("Performance Score: %,.3f\n", performanceScore);
        System.out.println("Recommended Graphics Quality: " + recommendedQuality);

        if (overclock == 'n')
            System.out.println("Warning, your cooling system may work harder. Consider upgrading to overclock-friendly components.");
    }
}
