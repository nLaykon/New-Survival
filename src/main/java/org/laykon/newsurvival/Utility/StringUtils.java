package org.laykon.newsurvival.Utility;

import java.math.BigDecimal;

public class StringUtils {

    static int barLength = 15;

    public static String generateProgressBar(int current, int total) {
        if (current > total) {
            current = total;
        }

        int completedSegments = (int) (((double) current / total) * barLength);

        StringBuilder progressBar = new StringBuilder("§a[");
        for (int i = 0; i < barLength; i++) {
            if (i < completedSegments) {
                progressBar.append("§a█");
            } else {
                progressBar.append("§c-");
            }
        }
        progressBar.append("§a]");

        double percentCompleted = (((double) current / total) * 100);
        progressBar.append(" ").append(NumberUtils.round(percentCompleted, 2)).append("%");

        return progressBar.toString();
    }

    public static String generateProgressBar(BigDecimal current, BigDecimal total) {
        if (current.compareTo(total) > 0) {
            current = total;
        }

        int barLength = 15;
        BigDecimal completedSegmentsBD = current.divide(total, 15, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(barLength));
        int completedSegments = completedSegmentsBD.intValue();

        StringBuilder progressBar = new StringBuilder("§a[");
        for (int i = 0; i < barLength; i++) {
            if (i < completedSegments) {
                progressBar.append("§a█");
            } else {
                progressBar.append("§c-");
            }
        }
        progressBar.append("§a]");

        BigDecimal percentCompletedBD = current.divide(total, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        double percentCompleted = percentCompletedBD.doubleValue();
        progressBar.append(" ").append(NumberUtils.round(percentCompleted, 2)).append("%");

        return progressBar.toString();
    }
}
