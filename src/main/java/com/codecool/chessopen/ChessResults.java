package com.codecool.chessopen;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChessResults {

    private static class Statistics implements Comparable {
        private String name;
        private int points;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        public void setPoints(int points) {
            this.points = points;
        }

        @Override
        public int compareTo(Object o) {
            Statistics otherStatistics = (Statistics) o;
            int difference = this.points - otherStatistics.points;
            if (difference > 0) {
                return -1;
            } else if (difference < 0) {
                return 1;
            } else return 0;
        }
    }

    public static void main(String[] args) {
        ChessResults chessResults = new ChessResults();
        List<String> names = chessResults.getCompetitorsNamesFromFile("src/main/resources/results.txt");
        System.out.println(names);
    }

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        List<String> lines;
        try {
            File file = new File(fileName);
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("File not found!");
            return null;
        }

        List<Statistics> statisticsList = new ArrayList<>();
        for (String line : lines) {
            String[] lineParts = line.split(",");
            Statistics statistics = new Statistics();
            statistics.setName(lineParts[0]);
            int sum = 0;
            for (int i = 1; i < lineParts.length; i++) {
                sum += Integer.parseInt(lineParts[i]);
            }
            statistics.setPoints(sum);
            statisticsList.add(statistics);
        }
        Collections.sort(statisticsList);
        List<String> result = new ArrayList<>();
        for (Statistics element : statisticsList) {
            result.add(element.getName());
        }
        return result;
    }
}
