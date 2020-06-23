import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class RankingPro_1 implements Comparable<RankingPro_1> {
    private int studentNumber, rank;
    private double koreanScore, englishScore, mathScore, totalScore, average;
    private String name;

    public RankingPro_1() {

    }

    //setter
    private void setNumber(int number) {
        this.studentNumber = number;
    }

    private void setNames(String name) {
        this.name = name;
    }

    private void setKoreanScore(double score) {
        this.koreanScore = score;
    }

    private void setEnglishScore(double score) {
        this.englishScore = score;
    }

    private void setMathScore(double score) {
        this.mathScore = score;
    }

    private void setRank(int rank) {
        this.rank = rank;
    }

    private void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    private void setAverage(double average) {
        this.average = average;
    }

    //getter
    private int getNumber() {
        return this.studentNumber;
    }

    private String getNames() {
        return this.name;
    }

    private double getKoreanScore() {
        return this.koreanScore;
    }

    private double getEnglishScore() {
        return this.englishScore;
    }

    private double getMathScore() {
        return this.mathScore;
    }

    private int getRank() {
        return this.rank;
    }

    private double getTotalScore() {
        return this.totalScore;
    }

    private double getAverage() {
        return this.average;
    }

    @Override
    public String toString() {
        return "No. " + studentNumber + " Name: " + name + " Korean: " + koreanScore + " English: " + englishScore + " Math: " + mathScore + " TotalScore: " + totalScore + " Average: " + average + " Rank: " + rank;
    }

    @Override
    public int compareTo(RankingPro_1 rankingPro_1) {
        return this.getAverage() < rankingPro_1.getAverage() ? 1 : -1;
    }

    public static void main(String[] args) throws IOException {
        String line = "";
        ArrayList<RankingPro_1> dataList = new ArrayList<RankingPro_1>();
        int i = 0, assignRank = 0;
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("/Users/simjongseop/Downloads/gwnu_score_management_system-master/score.csv"));

        while ((line = bufferedReader.readLine()) != null) {
            String[] parser = line.split(",");
            dataList.add(new RankingPro_1());
            dataList.get(i).setNumber(Integer.parseInt(parser[0]));
            dataList.get(i).setNames(parser[1]);
            dataList.get(i).setKoreanScore(Integer.parseInt(parser[2]));
            dataList.get(i).setEnglishScore(Integer.parseInt(parser[3]));
            dataList.get(i).setMathScore(Integer.parseInt(parser[4]));
            i++;
        }

        for (RankingPro_1 rankingPro_1 : dataList) {
            rankingPro_1.setTotalScore(rankingPro_1.getKoreanScore() + rankingPro_1.getEnglishScore() + rankingPro_1.getMathScore());
            rankingPro_1.setAverage(rankingPro_1.getTotalScore() / 3);
        }

        RankingPro_1[] data = dataList.toArray(new RankingPro_1[i]);
        Arrays.sort(data);

        for (RankingPro_1 datum : data) {
            datum.setRank(++assignRank);
        }

        for (RankingPro_1 datum : data) {
            System.out.println(datum);
        }
    }
}