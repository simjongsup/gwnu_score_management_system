import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class RankingPro_1 implements Comparable<RankingPro_1> {
    //field variable
    private int studentNumber, rank;    //학생 번호 및 학생 등수
    private double koreanScore, englishScore, mathScore, totalScore, average;   //국어 점수, 영어 점수, 수학 점수, 총 점수, 평균 점수
    private String name;    //학생 이름

    /**
     * 학생 정보 설정 하는 메소드
     */
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

    /**
     * 학생 정보 가져 오는 메소드
     */
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

    /**
     * 학생 정보 출력 format
     */
    @Override
    public String toString() {
        return "No. " + studentNumber + " Name: " + name + " Korean: " + koreanScore + " English: " + englishScore + " Math: " + mathScore + " TotalScore: " + totalScore + " Average: " + average + " Rank: " + rank;
    }

    /**
     * 평균 점수로 내림 차순 정렬
     */
    @Override
    public int compareTo(RankingPro_1 rankingPro_1) {
        return this.getAverage() <= rankingPro_1.getAverage() ? 1 : -1;
    }

    public static void main(String[] args) throws IOException {
        String line;    //csv 파일에서 한 줄 씩 읽어온 정보를 담는 변수
        ArrayList<RankingPro_1> dataList = new ArrayList<>();   //읽어온 데이터 저장하는 리스트
        int i = 0, assignRank = 1;  //배열 크기를 위한 변수, 등수를 위한 변수
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("/Users/simjongseop/Downloads/gwnu_score_management_system-master/score.csv"));   //데이터 파일 읽어오기

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

        //등수 중복 처리
        data[0].setRank(assignRank);
        for (int j = 0; j < data.length; j++) {
            for (int k = j + 1; k < data.length; k++) {
                if (data[j].getTotalScore() == data[k].getTotalScore())
                    data[k].setRank(data[j].getRank());
                else {
                    assignRank++;
                    data[k].setRank(assignRank);
                    break;
                }
            }
        }

        //csv 파일로 출력
        String createCSV = "/Users/simjongseop/Downloads/gwnu_score_management_system-master/result_score.csv";
        FileWriter fileWriter = new FileWriter(createCSV);
        for (RankingPro_1 datum : data) {
            fileWriter.append("Number: ").append(String.valueOf(datum.getNumber())).append(", Name: ").append(datum.getNames()).append(", Korean: ").append(String.valueOf(datum.getKoreanScore())).append(", English: ").append(String.valueOf(datum.getEnglishScore())).append(", Math: ").append(String.valueOf(datum.getMathScore())).append(", Total: ").append(String.valueOf(datum.getTotalScore())).append(", Average: ").append(String.valueOf(datum.getAverage())).append(", Rank: ").append(String.valueOf(datum.getRank())).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
