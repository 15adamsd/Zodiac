/*
 * AD80Zodiac.java
 * This is the class to be used to keep
 * the client class clean and keep all the
 * calculations over here for the Zodiac object
 * 
 * Author: Danielle Adams
 * Last Updated: 11/22/2021
 */

package adams;

public class AD80Zodiac {
  private String inputName;          // user and other first name    
  private String inputLastName;      // user and other last name
  private String inputMonth;         // user and other month selection
  private String inputDay;           // user and other day selection
  private String inputCompat;        // compatibility between other and user
  private int inputDayNum;           // day selection converted to int
  private String sign;               // string version of user and other's zodiac sign
  private int signNum;               // user/other sign as int. Used to help calculate compatibility
  private int inputCompatNum;        // # for compatibility, user to sort ranking of compatibility
  //===============================================================================================================
  // empty constructor
  public AD80Zodiac() {}
  
  // constructor w appropriate parameters
  public AD80Zodiac (String firstName, String lastName, String month, String day, String sign, String compat) {
    this.inputName = firstName;
    this.inputLastName = lastName;
    this.inputMonth = month;
    this.inputDay = day;
    this.sign = sign;
    this.inputCompat = compat;
  }
  //===============================================================================================================
  // setters
  public void setFirstName (String firstName) {
    this.inputName = firstName;
  }
  
  public void setLastName (String lastName) {
    this.inputLastName = lastName;
  }
  
  public void setMonth (String month) {
    this.inputMonth = month;
  }
  
  public void setDay (String day) {
    this.inputDay = day;
  }
  
  public void setSign (String sign) {
    this.sign = sign;
  }
  
  public void setCompat (String compat) {
    this.inputCompat = compat;
  }
  //==================================================================================================================
  // getters
  public String getFirstName() {
    return this.inputName;
  }
  
  public String getLastName() {
    return this.inputLastName;
  }
  
  public String getMonth() {
    return this.inputMonth;
  }
  
  public String getDay() {
    return this.inputDay;
  }
  
  public String getSignStr() {
    return this.sign;
  }
  
  public int getSignNum() {
    return this.signNum;
  }
  
  public String getCompat() {
    return this.inputCompat;
  }
  
  public int getCompatNum() {
    return this.inputCompatNum;
  }
//=========================================================================================
// this figures out the zodiac sign of the object
  public void calcZodiac(String monthS, String dayS) {
    this.inputMonth = monthS;
    this.inputDayNum = Integer.parseInt(dayS);
    
    if (inputMonth.equals("March") && inputDayNum >= 21 
        || inputMonth.equals("April") && inputDayNum <= 19 ) {
      this.sign = "Aries";
      this.signNum = 1;
    }
    
    else if (inputMonth.equals("April") && inputDayNum >= 20 ||
      inputMonth.equals("May") && inputDayNum <= 20) {
      this.sign = "Taurus";
      this.signNum = 2;
    }
    
    else if (inputMonth.equals("May") && inputDayNum >= 21 ||
        inputMonth.equals("June") && inputDayNum <= 20) {
      this.sign = "Gemini";
      this.signNum = 3;
    }
    
    else if (inputMonth.equals("June") && inputDayNum >= 21 ||
        inputMonth.equals("July")&& inputDayNum <= 22) {
        this.sign = "Cancer";
        this.signNum = 4;
      }
    
    else if (inputMonth.equals("July") && inputDayNum >= 23 ||
        inputMonth.equals("August") && inputDayNum <= 22) {
        this.sign = "Leo";
        this.signNum = 5;
      }
    
    else if (inputMonth.equals("August") && inputDayNum >= 23 ||
        inputMonth.equals("September") && inputDayNum <= 22) {
        this.sign = "Virgo";
        this.signNum = 6;
      }
    
    else if (inputMonth.equals("September") && inputDayNum >= 23 ||
        inputMonth.equals("October") && inputDayNum <= 22) {
        this.sign = "Libra";
        this.signNum = 7;
      }
    
    else if (inputMonth.equals("October") && inputDayNum >= 23 ||
        inputMonth.equals("November") && inputDayNum <= 21) {
        this.sign = "Scorpio";
        this.signNum = 8;
      }
    
    else if (inputMonth.equals("November") && inputDayNum >= 22 ||
        inputMonth.equals("December") && inputDayNum <= 21) {
        this.sign = "Sagittarius";
        this.signNum = 9;
      }
    
    else if (inputMonth.equals("December") && inputDayNum >= 22 ||
        inputMonth.equals("January") && inputDayNum <= 20) {
        this.sign = "Capricorn";
        this.signNum = 10;
      }
    
    else if (inputMonth.equals("January") && inputDayNum >= 21 ||
        inputMonth.equals("February") && inputDayNum <= 18) {
        this.sign = "Aquarius";
        this.signNum = 11;
      }
    
    else {
        this.sign = "Pisces";
        this.signNum = 12;
      }
  } // end calcZodiac
  //=================================================================================================
  // calculates compatibility based on astrological aspects
  public String calcCompatibility(AD80Zodiac other) {
    
    // conjunct ======== 8 ^-^
    if (this.getSignNum() - other.getSignNum() == 0) {
      this.inputCompat = "======== 80% c:";
      this.inputCompatNum = 8;
    }
    
    // square ===== 5 :c
    else if (this.getSignNum() - other.getSignNum() == 3 ||
        this.getSignNum() - other.getSignNum() == -3 ||
        this.getSignNum() - other.getSignNum() == 9 ||
        this.getSignNum() - other.getSignNum() == -9) {
      this.inputCompat = "===== 50% :c";
      this.inputCompatNum = 5;
    }
    
    // trine ========= 9 :D
    else if (this.getSignNum() - other.getSignNum() == -4 ||
        this.getSignNum() - other.getSignNum() == 4 ||
        this.getSignNum() - other.getSignNum() == 8 ||
        this.getSignNum() - other.getSignNum() == -8) {
      this.inputCompat = "========= 90% <3";
      this.inputCompatNum = 9;
    }
    
    // opposition ======= 7 :)
    else if (this.getSignNum() - other.getSignNum() == 6 ||
        this.getSignNum() - other.getSignNum() == -6) {
      this.inputCompat = "======= 70% :)";
      this.inputCompatNum = 7;
    }
    
    // sextile ========== 10? 95% <3
    else if (this.getSignNum() - other.getSignNum() == 2 || 
        this.getSignNum() - other.getSignNum() == -2 ||
        this.getSignNum() - other.getSignNum() == 10 ||
        this.getSignNum() - other.getSignNum() == -10)
    {
      this.inputCompat = "========== 95% <3<3<3";
      this.inputCompatNum = 10;
    }
    
    //unimportant aspect
    else {
      this.inputCompat = "==== 40% :\\";
      this.inputCompatNum = 4;
    }
    return this.inputCompat;
  } // end calcCompatibility
 //========================================================================================================= 
//toString for one zodiac object
  public String toString() {
    return this.inputName + " " + this.inputLastName + " | " + this.inputMonth + " " + 
  this.inputDay + " | " + this.sign + "\n\n";
  } // end toString 
} // end class
