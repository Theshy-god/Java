package comp1721.cwk1;
import java.util.*;
import java.time.*;

public class CaseRecord {
    private final LocalDate date;
    private final int staffCases;
    private final int studentCases;
    private final int otherCases;

    public CaseRecord(LocalDate date,int staffCases,int studentCases,int otherCases){
        this.date = date;
        this.staffCases = staffCases;
        this.studentCases = studentCases;
        this.otherCases = otherCases;
        if(staffCases<0||studentCases<0||otherCases<0)
        {
            throw new DatasetException("The cases should not be nevegate.");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public int getStaffCases() {
        return staffCases;
    }

    public int getStudentCases(){
        return studentCases;
    }

    public int getOtherCases() {

        return otherCases;
    }

    public int totalCases(){
        return staffCases + studentCases + otherCases;
    }
    @Override
    public String toString(){
        return date + ": "+ staffCases + " staff, "+ studentCases + " students, "+otherCases+" other";
    }

}
