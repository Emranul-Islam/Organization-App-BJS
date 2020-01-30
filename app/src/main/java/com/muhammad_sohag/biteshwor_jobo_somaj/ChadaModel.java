package com.muhammad_sohag.biteshwor_jobo_somaj;

public class ChadaModel  {
    private String chadaName;
    private String chadaTime;

    public ChadaModel(String chadaName, String chadaTime) {
        this.chadaName = chadaName;
        this.chadaTime = chadaTime;
    }
    public ChadaModel(){
    }

    public String getChadaName() {
        return chadaName;
    }

    public void setChadaName(String chadaName) {
        this.chadaName = chadaName;
    }

    public String getChadaTime() {
        return chadaTime;
    }

    public void setChadaTime(String chadaTime) {
        this.chadaTime = chadaTime;
    }
}
