package org.laykon.newsurvival.Utility;

public class StringUtils {
    public static String generateProgressBar(int current, int total) {
        if (current > total) {
            current = total;
        }

        int barLength = 15;
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
}
