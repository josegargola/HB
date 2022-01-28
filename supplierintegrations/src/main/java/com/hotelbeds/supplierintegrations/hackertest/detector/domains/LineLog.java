package com.hotelbeds.supplierintegrations.hackertest.detector.domains;

public class LineLog {

    private String ip;
    private long fechaMiliSegundos;
    private Action action;
    private String username;


    
    public LineLog(String ip, long fechaMiliSegundos, Action action, String username) {
        this.ip = ip;
        this.fechaMiliSegundos = fechaMiliSegundos;
        this.action = action;
        this.username = username;
    }


    // Getters y Setters

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public long getFechaMiliSegundos() {
        return fechaMiliSegundos;
    }
    public void setFechaMiliSegundos(long fechaMiliSegundos) {
        this.fechaMiliSegundos = fechaMiliSegundos;
    }
    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineLog)) return false;

        LineLog that = (LineLog) o;

        if (action != that.action) return false;
        if (fechaMiliSegundos != that.fechaMiliSegundos) return false;
        if (!ip.equals(that.ip)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (int) (fechaMiliSegundos ^ (fechaMiliSegundos >>> 32));
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
