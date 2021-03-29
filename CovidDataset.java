package comp1721.cwk1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.*;
import java.io.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class CovidDataset {
      ArrayList<CaseRecord> Caselist =new ArrayList<>();

    public CovidDataset(){

    }
    public void readDailyCases(String filename) throws IOException
    {
        Caselist.clear();
        File file = new File(filename);
        if(!file.exists())
        {
            throw new FileNotFoundException("Do not find the file");
        }
        Scanner scan = new Scanner(file);
        scan.nextLine();

        while(scan.hasNextLine())
        {
            String line =scan.nextLine();
            String []item = line.split(",");
            if(item.length < 4)
            {
                throw new DatasetException("Miss one column");
            }

            int staff,student,other;
            LocalDate date =LocalDate.parse(item[0]);
            staff = Integer.parseInt(item[1]);
            student = Integer.parseInt(item[2]);
            other = Integer.parseInt(item[3]);

            CaseRecord record = new CaseRecord(date,staff,student,other);
            this.Caselist.add(record);


        }

    }

    public int size()
    {
        return Caselist.size();
    }

    public void addRecord(CaseRecord rec){
        Caselist.add(rec);
    }

    public CaseRecord getRecord(int index){
       if(index<0||index >= this.Caselist.size())
        {
            throw new DatasetException("The index is invalid");
        }
        else
        return Caselist.get(index);
    }

    public CaseRecord dailyCasesOn(LocalDate day)
    {

        for (CaseRecord caseRecord : Caselist) {
            if (day.equals(caseRecord.getDate())) {
                return caseRecord;
            }
        }
        throw new DatasetException("No record found!");
    }

    public void writeActiveCases(String filename) throws IOException{
        if(Caselist.size() < 10)
        {
            throw new DatasetException("The records less than ten");
        }
        File file = new File(filename);
        PrintWriter a = new PrintWriter(file);
        a.println("Date,Staff,Students,Other");
        int i = 0;
        int the_staff = 0;
        int the_student = 0;
        int the_other = 0;
        LocalDate the_date = null;
        for(i = 0; i < 9;i++)
        {
            the_staff += Caselist.get(i).getStaffCases();
            the_student += Caselist.get(i).getStudentCases();
            the_other += Caselist.get(i).getOtherCases();
            the_date = Caselist.get(i).getDate();
        }
        while(i < Caselist.size())
        {

            the_staff += Caselist.get(i).getStaffCases();
            the_student += Caselist.get(i).getStudentCases();
            the_other += Caselist.get(i).getOtherCases();
            the_date = Caselist.get(i).getDate();
            if(i > 9)
            {
                the_staff -= Caselist.get(i-10).getStaffCases();
                the_student -= Caselist.get(i-10).getStudentCases();
                the_other -= Caselist.get(i-10).getOtherCases();
            }
            String line =(the_date)+","+(the_staff)+","+(the_student)+","+(the_other);
            a.println(line);
            i++;
        }
        a.close();
    }
}
