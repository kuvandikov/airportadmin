package uz.aeroport.models;

import javafx.scene.image.ImageView;

import java.util.Locale;

/**
 * Created by Jack on 21.12.2018.
 */
public class TableData
{
    private Long dataId;
    private Long id;
    private String time;
    private ImageView imageView;
    private String flight;
    private String destinationUzb;
    private String destinationEng;
    private String destinationRus;
    private String status;
    private String statusTime;
    private String terminal;
    private String departDate;
    private Long airlineId;
    public TableData() {
    }

    public TableData(Long dataId, Long id, String time, ImageView imageView, String flight, String destinationUzb, String destinationEng, String destinationRus,
                     String status, String statusTime, String terminal,String departDate) {
        this.dataId = dataId;
        this.id = id;
        this.time = time;
        this.imageView = imageView;
        this.flight = flight;
        this.destinationUzb = destinationUzb;
        this.destinationEng = destinationEng;
        this.destinationRus = destinationRus;
        this.status = status;
        this.statusTime = statusTime;
        this.terminal = terminal;
        this.departDate = departDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getDestinationUzb() {
        return destinationUzb;
    }

    public void setDestinationUzb(String destinationUzb) {
        this.destinationUzb = destinationUzb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinationEng() {
        return destinationEng;
    }

    public void setDestinationEng(String destinationEng) {
        this.destinationEng = destinationEng;
    }

    public String getDestinationRus() {
        return destinationRus;
    }

    public void setDestinationRus(String destinationRus) {
        this.destinationRus = destinationRus;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }
}
