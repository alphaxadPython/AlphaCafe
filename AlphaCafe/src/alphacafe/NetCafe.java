
package alphacafe;

import java.sql.Date;

public class NetCafe {
    public String name;
    public String amount;
    public java.sql.Date date;
    public String number;

//    method to assign coputer for 15 min
    public String AssignComputer(String number){
        String assigned="";
        
        if(Integer.parseInt(amount) < 1000 && Integer.parseInt(number) == 1){
            assigned = "Not more than 15 minute!! Computer number"+ number +"allowed!!";
        }
        
        return assigned;
    }
    
//    asign computer to person with greater amout for whole day
      public String AssignComputer(){
        String assigned="";
        
        if(Integer.parseInt(amount) > 50000){
            assigned = "Whole day allowed!!";
        }
        
        return assigned;
    }

}
