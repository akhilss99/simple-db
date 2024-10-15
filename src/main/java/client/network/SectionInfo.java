package client.network;

import engine.jdbc.network.NetworkDriver;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

/**
 * Assignment - 1
 *
 * @author Akhil S S
 * */
public class SectionInfo {
    public static void main(String[] args) {
        Driver d = new NetworkDriver();
        String url = "jdbc:simpledb://localhost";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a section number: ");
        int sectionNumber = scanner.nextInt();
        String q1 = "select Prof from SECTION where SectId = " + sectionNumber;
        String q2 = "select StudentId, Grade from ENROLL where SectionId = " + sectionNumber;

        try (Connection conn = d.connect(url, null);
             Statement stmt = conn.createStatement()) {

            ResultSet sectionRs = stmt.executeQuery(q1);

            String professor = null;

            while (sectionRs.next()) {
                professor = sectionRs.getString("Prof");
            }

            sectionRs.close();

            ResultSet gradesRs = stmt.executeQuery(q2);

            if (null != professor) {
                int AGradeCount = 0, studentCount = 0;
                while(gradesRs.next()) {
                    if (gradesRs.getString("Grade").trim().equalsIgnoreCase("A")) {
                        AGradeCount ++;
                    }
                    studentCount ++;
                }
                System.out.printf("Professor %s had %s students and gave %s A's.%n", professor, studentCount, AGradeCount);
            }
            else{
                System.out.println("No such section.");
            }

            gradesRs.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
