package in.co.one.angry.acrservice.model;

public class sPojoDataBase {
    private String fileName;
    //private String date;
    //private boolean is_sync;
    private String number;

    public sPojoDataBase(){
        this("","");
    }

    public sPojoDataBase(String fileName, String number) {
        this.fileName = fileName;
        //this.date = date;
        //this.is_sync = is_sync;
        this.number = number;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public boolean isIs_sync() {
//        return is_sync;
//    }
//
//    public void setIs_sync(boolean is_sync) {
//        this.is_sync = is_sync;
//    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
