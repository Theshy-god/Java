package comp1721.cwk1;
import java.io.IOException;
public class ActiveCases {
   public static void main(String[] args) throws IOException {

      CovidDataset a= new CovidDataset();
      a.readDailyCases(args[0]);
      a.writeActiveCases(args[1]);

   }
}

