package by.zemich.textprocessormicroservice.interfaces.rest.request;

public class Prices {
    Double costPerPiece;
    Double costPerSet;

    public Prices(Double costPerPiece, Double costPerSet) {
        this.costPerPiece = costPerPiece;
        this.costPerSet = costPerSet;
    }

    public Prices() {
    }

    public Double getCostPerPiece() {
        return costPerPiece;
    }

    public void setCostPerPiece(Double costPerPiece) {
        this.costPerPiece = costPerPiece;
    }

    public Double getCostPerSet() {
        return costPerSet;
    }

    public void setCostPerSet(Double costPerSet) {
        this.costPerSet = costPerSet;
    }

    @Override
    public String toString() {
        return "Prices{" +
                "costPerPiece=" + costPerPiece +
                ", costPerSet=" + costPerSet +
                '}';
    }
}
