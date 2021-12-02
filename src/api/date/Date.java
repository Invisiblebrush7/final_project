package api.date;

public class Date {
    private int day;
    private int month;
    private int year;

    private String monthName;

    public Date(int day, int month, int year){
        setDay(day);
        setMonth(month);
        setYear(year);

        setMonthName();
    }

    public String toString(){
        String msg = String.format("%d/%d/%d", this.day, this.month, this.year);
        return msg;
    }

    public boolean equals(Object o){
        if(! (o instanceof Date) ){
            return false;
        }
        Date obj = (Date) o;
        return this.day == obj.day && this.month == obj.month && this.year == obj.year;
    }

    public Date clone(){
        Date myNewDate = new Date(this.day, this.month, this.year);
        return myNewDate;
    }    

    public void setDay(int _day){
        if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
            //  [1..30]
            this.day = _day >= 1 && _day <= 30 ? _day : 1;
        } else if(this.month == 2) {
            // [1..28]
            this.day = _day >= 1 && _day <= 28 ? _day : 1;
        } else {
            // [1..31]
            this.day = _day >= 1 && _day <= 31 ? _day : 1;
        }
    }
    public void setMonth(int _month){
        this.month = _month >= 1 && _month <= 12 ? _month : 1;
        this.setMonthName();
    }
    public void setYear(int _year){
        this.year = _year >= 1900 && _year <= 3000 ? _year : 2021;
    }

    private void setMonthName(){
        String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        this.monthName = months[this.month - 1];
    }
    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }
    public String getMonthName(){
        return this.monthName;
    }

}
