package in.co.one.angry.acrservice.model;

public class FilePojo {
    private String file_name,file_date,file_number;

    public FilePojo(){
        this("","","");
    }

    public FilePojo(String file_name, String file_date, String file_number) {
        this.file_name = file_name;
        this.file_date = file_date;
        this.file_number = file_number;
    }


    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_date() {
        return file_date;
    }

    public void setFile_date(String file_date) {
        this.file_date = file_date;
    }

    public String getFile_number() {
        return file_number;
    }

    public void setFile_number(String file_number) {
        this.file_number = file_number;
    }

}
