package model.database.worldonlinedb;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "ExchangeRates", schema = "", catalog = "worldonline")
@Entity
public class ExchangeRatesEntity {
    @javax.persistence.Column(name = "ExchangeRatesID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exchangeRatesID;

    @javax.persistence.Column(name = "USD")
    @Basic
    private Double usd;

    @javax.persistence.Column(name = "EUR")
    @Basic
    private Double eur;

    @javax.persistence.Column(name = "DownloadTimestamp")
    @Basic
    private Timestamp downloadTimestamp;

    @javax.persistence.Column(name = "ActualDayTimestamp")
    @Basic
    private Timestamp actualDayTimestamp;

    public Long getExchangeRatesID() {
        return exchangeRatesID;
    }

    public void setExchangeRatesID(Long exchangeRatesID) {
        this.exchangeRatesID = exchangeRatesID;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }

    public Timestamp getDownloadTimestamp() {
        return downloadTimestamp;
    }

    public void setDownloadTimestamp(Timestamp downloadTimestamp) {
        this.downloadTimestamp = downloadTimestamp;
    }

    public Timestamp getActualDayTimestamp() {
        return actualDayTimestamp;
    }

    public void setActualDayTimestamp(Timestamp actualDayTimestamp) {
        this.actualDayTimestamp = actualDayTimestamp;
    }
}
